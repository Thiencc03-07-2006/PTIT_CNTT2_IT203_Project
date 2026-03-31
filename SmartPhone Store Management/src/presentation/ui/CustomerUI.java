package presentation.ui;

public class CustomerUI {
//    String fileName = "listCustomer.txt";
//    Scanner scanner = new Scanner(System.in);
//    public void displayList(List<Customer> customers) {
//        int currentPage = 1 ;
//        int catePerPage = 5 ;
//        int totalPage = (int) Math.ceil((double) customers.size() /catePerPage);
//        int skip = (currentPage -1 ) * catePerPage ;
//        int size = customers.size();
//        if(customers.isEmpty()){
//            System.err.println("List customer is empty !");
//        }else {
//            while (true){
//                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//                System.out.println("|                                                             "+ GetColor.GREEN+"LIST CUSTOMER"+GetColor.RESET+"                                                               |");
//                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
//                System.out.printf("[ %-3s | %-28s | %-8s | %-13s | %-13s | %-28s | %-8s | %-13s ]\n" , "ID","Username","Gender","Birthday","Phone Number","Email","Status","Created Date");
//                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
//                for(int i = skip ; i < (skip + catePerPage) ; i++){
//                    if(i < size){
//                        customers.get(i).displayData();
//                    }else {
//                        break;
//                    }
//                }
//                StringBuilder pagination = new StringBuilder();
//                int startPage = Math.max(currentPage - 2, 1);
//                int endPage = Math.min(currentPage + 2, totalPage);
//                for(int i = startPage ; i <= endPage ; i++){
//                    if(currentPage == i){
//                        pagination.append(GetColor.RED +"["+ i +"]"+ GetColor.RESET) ;
//                    }else {
//                        pagination.append("[" + i + "]") ;
//                    }
//
//                    pagination.append("     ");
//
//                }
//                String rs ="|";
//                int spaceStart = (146 - (pagination.length())) / 2 ;
//                int spaceEnd = (146 - pagination.length()) - spaceStart ;
//                for(int j = 1 ; j <= spaceStart ; j++){
//                    rs += " ";
//                }
//                rs = rs.concat(pagination.toString());
//                for(int j = 1 ; j <= spaceEnd ; j++){
//                    rs += " ";
//                }
//                rs += "|";
//                System.out.println(rs);
//                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//                System.out.println("|              1. Previous page                 |                  2. Back                   |                 3. Next page               |");
//                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
//
//                int choice = InputMethod.getNumber("Enter choice : ");
//                switch (choice){
//                    case 1 : {
//                        if(currentPage > 1){
//                            currentPage--;
//                            skip = (currentPage -1 ) * catePerPage ;
//                        }else {
//                            System.err.println("Cannot previous !");
//                        }
//                        break;
//                    }
//                    case 2 : {
//                        return;
//                    }
//                    case 3 : {
//                        if(currentPage < totalPage){
//                            currentPage++;
//                            skip = (currentPage -1 ) * catePerPage ;
//                        }else {
//                            System.err.println("Cannot next !");
//                        }
//                        break;
//                    }
//                    default: {
//                        System.err.println("Choice invalid !");
//                    }
//                }
//            }
//        }
//    }
//
//    public boolean add() {
//        List<Customer>  customers = InputMethod.listCustomer();
//        int number = InputMethod.getNumber("Enter number customer want add : ");
//        for(int i = 1 ; i <= number ; i++){
//            System.out.println("Enter customer number " + i);
//            Customer customer = new Customer();
//            customer.inputData(scanner);
//            customers.add(customer);
//            InputMethod.saveDatabase(fileName,customers);
//        }
//        boolean rs = InputMethod.saveDatabase(fileName,customers);
//        if(rs){
//            System.out.println("Add successfully " + number + " customer !");
//        }else {
//            System.err.println("Add customer error !");
//        }
//        return  rs ;
//    }
//
//    public boolean update() {
//        List<Customer> customers = InputMethod.listCustomer();
//        int idCustomer = InputMethod.getNumber("Enter id customer to update : ");
//        int index = customers.stream().map(Customer::getCustomerId).toList().indexOf(idCustomer);
//        if(index == -1){
//            System.err.println("Not found id customer !");
//            return  false ;
//        }else {
//            customers.get(index).updateData(scanner);
//            InputMethod.saveDatabase(fileName,customers);
//            System.out.println("Update customer successfully !");
//            return  true ;
//        }
//    }
//
//    public void updateStatus() {
//        List<Customer> customers = InputMethod.listCustomer();
//        int idCustomer = InputMethod.getNumber("Enter id customer to update status: ");
//        int index = customers.stream().map(Customer::getCustomerId).toList().indexOf(idCustomer);
//        if(index == -1){
//            System.err.println("Not found id customer !");
//        }else {
//            customers.get(index).updateCustomerStatus();
//            InputMethod.saveDatabase(fileName,customers);
//            System.out.println("Update customer status successfully !");
//        }
//    }
//
//    public boolean delete() {
//        List<Customer> customers = InputMethod.getListObject(fileName);
//        int idCustomer = InputMethod.getNumber("Enter id customer to delete : ");
//        int index = customers.stream().map(Customer::getCustomerId).toList().indexOf(idCustomer);
//        if(index == -1){
//            System.err.println("Not found id customer !");
//            return  false ;
//        }else {
//            customers.remove(index);
//            InputMethod.saveDatabase(fileName,customers);
//            System.out.println("Delete customer successfully !");
//            return  true ;
//        }
//    }
//
//    public void searchCustomerByName(){
//        List<Customer> customers = InputMethod.getListObject(fileName);
//        String search ;
//        while (true){
//            System.out.println("Enter id or name customer : ");
//            search = scanner.nextLine().trim();
//            if (search.isEmpty()){
//                System.err.println("Cannot be left blank !");
//            }else {
//                break;
//            }
//        }
//        String finalSearch = search;
//        List<Customer> rs = customers.stream()
//                .filter(customer -> customer.getUserName().contains(finalSearch) || customer.getCustomerId().toString().contains(finalSearch))
//                .toList();
//        if(rs.isEmpty()){
//            System.err.println("Not found customer with key word : " + finalSearch);
//        }else {
//            System.out.println("Result search : ");
//            displayList(rs);
//        }
//    }
//
//    public void sortCustomer(){
//        List<Customer> customers = InputMethod.listCustomer();
//        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//        System.out.println("|           1. Sort names in descending order             |              2. Sort names in ascending order              |");
//        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//        int number = 0 ;
//        while (true){
//            try {
//                System.out.println("Enter choice :");
//                number = Integer.parseInt(scanner.nextLine().trim());
//                if(number == 1 || number == 2){
//                    break;
//                }else {
//                    System.err.println("Enter choice 1 or 2 !");
//                }
//            }catch (NumberFormatException n){
//                System.err.println("Number invalid !");
//            }
//        }
//        List<Customer> rs ;
//        if(number == 1){
//            rs = customers.stream().sorted(Comparator.comparing(Customer::getUserName)).toList().reversed();
//        }else {
//            rs = customers.stream().sorted(Comparator.comparing(Customer::getUserName)).toList();
//        }
//        System.out.println("Result sort :");
//        displayList(rs);
//    }
}