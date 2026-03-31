package presentation.menu;

public class CustomerManagement {
//    public static void main(String[] args) {
//        CustomerUI customerFeature = new CustomerUI();
//        List<Customer> customers = InputMethod.listCustomer();
//        if(customers.getFirst() == null){
//            System.err.println("Please login first. !");
//            Login.main(args);
//        }
//        while (true){
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                                  "+ GetColor.GREEN+"CUSTOMER MANAGEMENT"+GetColor.RESET+"                                                 |");
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|      1. Display list customer          |      2. Update status customer     |           3. Delete customer           |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|      4. Search customer by name        |      5. Sort customer by name      |               6. Back                  |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//
//            int choice = InputMethod.getNumber("Enter choice : ");
//            switch (choice){
//                case 1 : {
//                    List<Customer> customers1 = InputMethod.listCustomer().stream().filter(customer -> customer.getRole() == Role.CUSTOMER).toList();
//                    customerFeature.displayList(customers1);
//                    break;
//                }
//                case 2 : {
//                    customerFeature.updateStatus();
//                    break;
//                }
//                case 3 : {
//                    customerFeature.delete();
//                    break;
//                }
//                case 4 : {
//                    customerFeature.searchCustomerByName();
//                    break;
//                }
//                case 5 : {
//                    customerFeature.sortCustomer();
//                    break;
//                }
//                case 6 : {
//                    return;
//                }
//                default: {
//                    System.err.println("Enter choice from 1 to 6 !");
//                }
//            }
//        }
//    }
}