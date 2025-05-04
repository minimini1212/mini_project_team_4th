package stockManagement.item.controller;

import stockManagement.item.model.entity.Item;
import stockManagement.item.model.service.ItemService;
import stockManagement.item.view.ItemView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemController {
    private final ItemService itemService;
    private final ItemView itemView = new ItemView();

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    public void run(Scanner scanner) {
        while (true) {
            itemView.showItemMenu();
            String input = scanner.nextLine();
            if (input.isBlank()) continue;
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> createItem(scanner);
                case 2 -> updateItem(scanner);
                case 3 -> deleteItem(scanner);
                case 4 -> listItems();
                case 5 -> searchItems(scanner);
                case 0 -> {
                    return;
                }
                default -> itemView.showInvalid();
            }
        }
    }

    private void createItem(Scanner scanner) {
        Item item = itemView.getItemInput(scanner);
        int quantity = itemView.getInitialStockQuantity(scanner);
        itemService.createItem(item, quantity);
        itemView.showSuccess("물품과 재고가 생성되었습니다.");
    }

    private void updateItem(Scanner scanner) {
        int itemId = itemView.getItemId(scanner);
        Item item = itemService.findById(itemId);
        if (item == null) {
            itemView.showError("해당 ID의 물품이 존재하지 않습니다.");
            return;
        }
        itemView.updateItemInput(scanner, item);
        itemService.updateItem(item);
        itemView.showSuccess("물품이 수정되었습니다.");
    }

    private void deleteItem(Scanner scanner) {
        int itemId = itemView.getItemId(scanner);
        itemService.deleteItem(itemId);
        itemView.showSuccess("물품이 삭제되었습니다.");
    }

    private void listItems() {
        List<Item> items = itemService.findAll();
        itemView.displayItemList(items);
    }

    private void searchItems(Scanner scanner) {
        String keyword = itemView.getSearchKeyword(scanner);
        List<Item> results = itemService.searchItems(keyword);
        itemView.displayItemList(results);
    }
}
