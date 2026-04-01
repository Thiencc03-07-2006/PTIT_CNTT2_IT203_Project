package presentation.menu;

import model.Product;
import presentation.ui.ProductUI;
import util.GetColor;
import util.InputMethod;

import java.util.List;

public class ProductManagement {
    public static void main(String[] args) {
        ProductUI productFeature = new ProductUI();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                  "+ GetColor.GREEN+"PRODUCT MANAGEMENT"+GetColor.RESET+"                                                  |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|   1. Display list products   |     2. Add new product     |    3. Update product      |   4. View product detail     |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|      5. Delete product       |     6. Search product      |     7. Sort product       |           8. Back            |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = InputMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    productFeature.displayList();
                    break;
                }
                case 2 : {
                    productFeature.add();
                    break;
                }
                case 3 : {
                    productFeature.update();
                    break;
                }
                case 4 : {
                    productFeature.viewDetail();
                    break;
                }
                case 5 : {
                    productFeature.delete();
                    break;
                }
                case 6 : {
                    productFeature.searchProduct();
                    break;
                }
                case 7 : {
                    productFeature.sortProduct();
                    break;
                }
                case 8 : {
                    return;
                }
                default:{
                    System.out.println("Enter choice from 1 to 7 !");
                }
            }
        }
    }
}