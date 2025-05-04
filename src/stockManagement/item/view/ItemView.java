package stockManagement.item.view;

import stockManagement.item.model.entity.Item;

import java.util.List;
import java.util.Scanner;

public class ItemView {
    public void showItemMenu() {
        System.out.println("\n===== 물품 관리 =====");
        System.out.println("1. 물품 생성");
        System.out.println("2. 물품 수정");
        System.out.println("3. 물품 삭제");
        System.out.println("4. 전체 물품 조회");
        System.out.println("5. 물품 검색");
        System.out.println("0. 뒤로 가기");
        System.out.print("선택: ");
    }

    public Item getItemInput(Scanner scanner) {
        Item item = new Item();
        System.out.print("물품 코드: ");
        item.setItemCode(scanner.nextLine());

        System.out.print("물품명: ");
        item.setItemName(scanner.nextLine());

        System.out.print("카테고리: ");
        item.setCategory(scanner.nextLine());

        return item;
    }

    public int getInitialStockQuantity(Scanner scanner) {
        System.out.print("초기 재고 수량: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getItemId(Scanner scanner) {
        System.out.print("물품 ID 입력: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void updateItemInput(Scanner scanner, Item item) {
        System.out.print("수정할 코드 (현재: " + item.getItemCode() + "): ");
        String code = scanner.nextLine();
        if (!code.isBlank()) item.setItemCode(code);

        System.out.print("수정할 이름 (현재: " + item.getItemName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) item.setItemName(name);

        System.out.print("수정할 카테고리 (현재: " + item.getCategory() + "): ");
        String category = scanner.nextLine();
        if (!category.isBlank()) item.setCategory(category);
    }

    public String getSearchKeyword(Scanner scanner) {
        System.out.print("검색어 (코드/이름/카테고리): ");
        return scanner.nextLine();
    }

    public void displayItemList(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("❌ 결과가 없습니다.");
            return;
        }
        System.out.println("\n===== 물품 목록 =====");
        for (Item item : items) {
            System.out.println("ID: " + item.getItemId() + " / 코드: " + item.getItemCode() +
                    " / 이름: " + item.getItemName() + " / 카테고리: " + item.getCategory());
        }
    }

    public void showSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public void showNotFound() {
        System.out.println("❌ 해당 물품을 찾을 수 없습니다.");
    }

    public void showInvalid() {
        System.out.println("⚠ 잘못된 입력입니다.");
    }

    public void showError(String message) {
        System.out.println("❌ " + message);
    }
}
