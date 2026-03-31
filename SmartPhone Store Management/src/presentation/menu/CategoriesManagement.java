package presentation.menu;

import presentation.ui.CategoryUI;
import util.GetColor;
import util.InputMethod;

public class CategoriesManagement {
    public static void main(String[] args) {
        CategoryUI categoryUI = new CategoryUI();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                 "+ GetColor.GREEN+"CATEGORIES MANAGEMENT"+GetColor.RESET+"                                                |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      1. Display list categories        |          2. Add categories         |          3. Update categories          |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┻━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|     4. Delete categories     |    5. Search categories    |    6. Sort categories     |           7. Back            |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            int choice = InputMethod.getNumber("Enter choice: ");
            switch (choice){
                case 1 : {
                    categoryUI.displayList();
                    break;
                }
                case 2 : {
                    categoryUI.add();
                    break;
                }
                case 3 : {
                    categoryUI.update();
                    break;
                }
                case 4 : {
                    categoryUI.softDelete();
                    break;
                }
                case 5 : {
                    categoryUI.searchCateByName();
                    break;
                }
                case 6 : {
//                    categoryUI.sortCategories();
                    break;
                }
                case 7 : {
                    return;
                }
                default: {
                    System.out.println("Enter choice from 1 to 7 !");
                }
            }
        }

    }
}