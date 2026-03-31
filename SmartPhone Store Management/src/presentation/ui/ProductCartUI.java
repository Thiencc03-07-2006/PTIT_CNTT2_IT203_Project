package presentation.ui;

import model.ProductCart;
import util.GetColor;
import util.InputMethod;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductCartUI {
    public void displayList(List<ProductCart> productCarts) {
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        int totalPage = (int) Math.ceil((double) productCarts.size() /itemPerPage);
        int skip = (currentPage -1 ) * itemPerPage ;
        int size = productCarts.size();
        if(productCarts.isEmpty()){
            System.err.println("List products cart is empty !");
        }else {
            while (true){
                NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|                                                         "+ GetColor.GREEN+"LIST PRODUCT"+GetColor.RESET+"                                                            |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-3s | %-28s | %-13s | %-8s | %-8s | %-13s | %-13s | %-19s  |\n","ID","Product Name","Price","CateId","Quantity","Storage","Color","TotalMoney");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
                double sumMoney = productCarts.stream().map(ProductCart::getTotalMoney).toList().stream().reduce(0.0, Double::sum) ;
                for(int i = skip ; i < (skip + itemPerPage) ; i++){
                    if(i < size){
                        productCarts.get(i).displayData(i);
                    }else {
                        break;
                    }
                }
                StringBuilder pagination = new StringBuilder();
                int startPage = Math.max(currentPage - 2, 1);
                int endPage = Math.min(currentPage + 2, totalPage);
                for(int i = startPage ; i <= endPage ; i++){
                    if(currentPage == i){
                        pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                    }else {
                        pagination.append("[").append(i).append("]");
                    }

                    pagination.append("     ");

                }
                String rs ="|";
                int spaceStart = (138 - (pagination.length())) / 2 ;
                int spaceEnd = (138 - pagination.length()) - spaceStart ;
                for(int j = 1 ; j <= spaceStart ; j++){
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for(int j = 1 ; j <= spaceEnd ; j++){
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("|                                                                                     Total money : %-29s |\n",format.format(sumMoney)+" VNĐ");
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|          1. Previous page               |                   2. Back                |                 3. Next page               |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

                int choice = InputMethod.getNumber("Enter choice : ");
                switch (choice){
                    case 1 : {
                        if(currentPage > 1){
                            currentPage--;
                            skip = (currentPage -1 ) * itemPerPage ;
                        }else {
                            System.err.println("Cannot previous !");
                        }
                        break;
                    }
                    case 2 : {
                        return;
                    }
                    case 3 : {
                        if(currentPage < totalPage){
                            currentPage++;
                            skip = (currentPage -1 ) * itemPerPage ;
                        }else {
                            System.err.println("Cannot next !");
                        }
                        break;
                    }
                    default: {
                        System.out.println("Choice invalid !");
                    }
                }
            }
        }

    }


    public boolean add() {
        return false;
    }


    public boolean update() {
        return false;
    }


    public boolean delete() {
        return false;
    }
}