package presentation.ui;

import model.ProductCart;
import service.CartService;
import util.GetColor;
import util.InputMethod;
import util.Session;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartUI {
    private final CartService cartService = CartService.getInstance();

    public void viewCart() {
        if (!Session.isLogin()) {
            System.out.println("Please log in first !");
        } else {
            int customerId = Session.getCurrentUser().getUserId();
            int currentPage = 1;
            int itemPerPage = 5;
            while (true) {
                List<ProductCart> productCarts = cartService.getCartByCustomer(customerId);
                if (productCarts.isEmpty()) {
                    System.out.println("Cart empty !");
                    break;
                } else {
                    int skip = (currentPage - 1) * itemPerPage;
                    int totalPage = (int) Math.ceil((double) productCarts.size() / itemPerPage);
                    int size = productCarts.size();
                    double sumMoney = productCarts.stream()
                            .mapToDouble(ProductCart::getTotalMoney)
                            .sum();
                    displayCart(skip, itemPerPage, size, productCarts, currentPage, totalPage, sumMoney);
                    int choice = InputMethod.getNumber("Enter your choice : ");
                    switch (choice) {
                        case 1: {
                            if (currentPage > 1) {
                                currentPage--;
                                skip = (currentPage - 1) * itemPerPage;
                            } else {
                                System.out.println("Cannot previous !");
                            }
                            break;
                        }
                        case 2: {
                            return;
                        }
                        case 3: {
                            if (currentPage < totalPage) {
                                currentPage++;
                                skip = (currentPage - 1) * itemPerPage;
                            } else {
                                System.out.println("Cannot next !");
                            }
                            break;
                        }
                        case 4: {
                            updateQuantityProduct(productCarts);
                            break;
                        }
                        case 5: {
                            if (deleteProduct(productCarts)) currentPage = 1;
                            break;
                        }
                        case 6: {
                            if (clearCart()) return;
                            break;
                        }
                        case 7: {
                            if (cartService.checkout(customerId)) {
                                System.out.println("Order placed successfully!");
                                return;
                            } else {
                                System.out.println("Checkout failed!");
                            }
                            break;
                        }
                        default: {
                            System.out.println("Enter choice from 1 to 6 !");
                        }
                    }
                }
            }
        }

    }

    private boolean updateQuantityProduct(List<ProductCart> productCarts) {
        int choice = InputMethod.getNumber("Enter product number (No): ");

        if (choice < 0 || choice >= productCarts.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        ProductCart selected = productCarts.get(choice);

        int quantity = InputMethod.getNumber("New quantity: ");
        if (quantity <= 0) {
            System.out.println("Quantity must > 0 !");
            return false;
        }

        if (cartService.updateQuantity(Session.getCurrentUser().getUserId(), selected.getCartId(), quantity)) {
            System.out.println("Updated!");
            return true;
        } else {
            System.out.println("Update failed!");
            return false;
        }
    }

    private boolean deleteProduct(List<ProductCart> productCarts) {
        int choiceItem = InputMethod.getNumber("Enter product number (No): ");
        if (choiceItem < 0 || choiceItem >= productCarts.size()) {
            System.out.println("Invalid choice!");
            return false;
        }
        ProductCart selected = productCarts.get(choiceItem);
        selected.displayData(choiceItem);
        System.out.println("Are you sure to delete?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int confirm = InputMethod.getNumber("Enter choice: ");
        if (confirm != 1) {
            System.out.println("Delete cancelled!");
            return false;
        }
        if (cartService.deleteItem(Session.getCurrentUser().getUserId(), selected.getCartId())) {
            System.out.println("Deleted successfully!");
            return true;
        } else {
            System.out.println("Delete failed!");
            return false;
        }
    }

    private boolean clearCart() {
        System.out.println("Are you sure to delete?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int confirm = InputMethod.getNumber("Enter choice: ");
        if (confirm != 1) {
            System.out.println("Delete cancelled!");
            return false;
        }
        if (cartService.clearCart(Session.getCurrentUser().getUserId())) {
            System.out.println("Clear cart successfully!");
            return true;
        } else {
            System.out.println("Clear failed!");
            return false;
        }
    }

    private void displayCart(int skip, int itemPerPage, int size, List<ProductCart> productCarts, int currentPage, int totalPage, Double sumMoney) {
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|                                                       " + GetColor.GREEN + "CART PRODUCT" + GetColor.RESET + "                                                              |");
        System.out.println("|━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.printf("| %-3s | %-39s | %-13s | %-8s | %-13s | %-13s | %-20s |\n", "No", "Product Name", "finalPrice", "Quantity", "Size", "Color", "TotalMoney");
        System.out.println("|━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━|");
        for (int i = skip; i < (skip + itemPerPage); i++) {
            if (i < size) {
                productCarts.get(i).displayData(i);
            } else {
                break;
            }
        }
        StringBuilder pagination = new StringBuilder();
        int startPage = Math.max(currentPage - 2, 1);
        int endPage = Math.min(currentPage + 2, totalPage);
        for (int i = startPage; i <= endPage; i++) {
            if (currentPage == i) {
                pagination.append(GetColor.RED + "[" + i + "]" + GetColor.RESET);
            } else {
                pagination.append("[" + i + "]");
            }

            pagination.append("     ");

        }
        String rs = "|";
        int spaceStart = (138 - (pagination.length())) / 2;
        int spaceEnd = (138 - pagination.length()) - spaceStart;
        for (int j = 1; j <= spaceStart; j++) {
            rs += " ";
        }
        rs = rs.concat(pagination.toString());
        for (int j = 1; j <= spaceEnd; j++) {
            rs += " ";
        }
        rs += "|";
        System.out.println(rs);
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.printf("|                                                                                     Total money : %-29s |\n", format.format(sumMoney) + " VNĐ");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|           1. Previous page                |              2. Back                        |             3. Next page              |");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|                      4. Update quantity product                    |                        5. Delete one product               |");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|                      6. Delete all product                         |                        7. Proceed to order                 |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}