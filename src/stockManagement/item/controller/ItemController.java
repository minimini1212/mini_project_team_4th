package stockManagement.item.controller;

import stockManagement.item.model.entity.Item;
import stockManagement.item.model.service.ItemService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemController {
    private final ItemService itemService;
    private final Scanner scanner;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> createItem();
                    case 2 -> updateItem();
                    case 3 -> deleteItem();
                    case 4 -> listItems();
                    case 5 -> searchItems();
                    case 0 -> {
                        System.out.println("물품 관리 종료");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== 물품 관리 메뉴 =====");
        System.out.println("1. 물품 생성");
        System.out.println("2. 물품 수정");
        System.out.println("3. 물품 삭제");
        System.out.println("4. 전체 물품 조회");
        System.out.println("5. 물품 검색");
        System.out.println("0. 뒤로 가기");
        System.out.print("선택: ");
    }

    private void createItem() throws SQLException {
        System.out.print("물품 코드: ");
        String itemCode = scanner.nextLine();

        System.out.print("물품 이름: ");
        String itemName = scanner.nextLine();

        System.out.print("카테고리: ");
        String category = scanner.nextLine();

        System.out.print("초기 재고 수량: ");
        int stockQty = Integer.parseInt(scanner.nextLine());

        Item item = new Item();
        item.setItemCode(itemCode);
        item.setItemName(itemName);
        item.setCategory(category);

        itemService.createItem(item, stockQty);
//        System.out.println("물품이 생성되었습니다.");
    }

    private void updateItem() throws SQLException {
        System.out.print("수정할 물품 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());

        // 기존 아이템 조회
        Item existingItem = itemService.findItemById(itemId);
        if (existingItem == null) {
            System.out.println("해당 ID의 물품이 존재하지 않습니다.");
            return;
        }

        System.out.print("새 코드 (현재: " + existingItem.getItemCode() + "): ");
        String code = scanner.nextLine();

        System.out.print("새 이름 (현재: " + existingItem.getItemName() + "): ");
        String name = scanner.nextLine();

        System.out.print("새 카테고리 (현재: " + existingItem.getCategory() + "): ");
        String category = scanner.nextLine();

        // 입력 값이 비어있으면 기존 값 유지
        if (!code.isBlank()) existingItem.setItemCode(code);
        if (!name.isBlank()) existingItem.setItemName(name);
        if (!category.isBlank()) existingItem.setCategory(category);

        itemService.updateItem(existingItem);
        System.out.println("물품이 수정되었습니다.");
    }

    private void deleteItem() throws SQLException {
        System.out.print("삭제할 물품 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());

        itemService.deleteItem(itemId);
        System.out.println("물품이 삭제되었습니다.");
    }

    private void listItems() throws SQLException {
        List<Item> items = itemService.getAllItems();
        System.out.println("\n전체 물품 목록:");
        for (Item item : items) {
            printItem(item);
        }
    }

    private void searchItems() throws SQLException {
        System.out.print("검색어 (품명 or 카테고리 or 코드): ");
        String keyword = scanner.nextLine();

        List<Item> results = itemService.searchItems(keyword);
        if (results.isEmpty()) {
            System.out.println("검색 결과 없음.");
        } else {
            for (Item item : results) {
                printItem(item);
            }
        }
    }

    private void printItem(Item item) {
        System.out.println("---------------");
        System.out.println("ID: " + item.getItemId());
        System.out.println("코드: " + item.getItemCode());
        System.out.println("이름: " + item.getItemName());
        System.out.println("카테고리: " + item.getCategory());
    }
}
