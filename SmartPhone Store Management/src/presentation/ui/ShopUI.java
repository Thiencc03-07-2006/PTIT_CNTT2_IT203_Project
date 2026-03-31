package presentation.ui;

import model.Category;
import model.Product;
import model.ProductCart;
import model.ProductStatus;
import service.CartService;
import service.ProductService;
import util.GetColor;
import util.InputMethod;
import util.Session;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopUI {

    private final ProductService productService = ProductService.getInstance();
    private final CartService cartService=CartService.getInstance();

    public void displayList() {
        List<Product> products = productService.getAllActive();
        showList(products);
    }

    private void showList(List<Product> products) {
        ProductUI productFeature = new ProductUI();
        int currentPage = 1;
        int itemPerPage = 5;
        int totalPage = (int) Math.ceil((double) products.size() / itemPerPage);
        int skip = (currentPage - 1) * itemPerPage;
        int size = products.size();
        if (products.isEmpty()) {
            System.err.println("List products is empty !");
        } else {
            while (true) {
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|  " + GetColor.GREEN + "SHOP : IPHONE STORE" + GetColor.RESET + "                                   LIST PRODUCT                                                       |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-3s | %-28s | %-3s | %-13s | %-3s | %-18s | %-8s | %-3s | %-18s |\n", "ID", "Product Name", "-%", "FinalPrice", "Inv", "Size", "Color", "CID", "Status");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
                if (!products.isEmpty()) {
                    for (int i = skip; i < (skip + itemPerPage); i++) {
                        if (i < size) {
                            products.get(i).displayData();
                        } else {
                            break;
                        }
                    }
                } else {
                    System.err.println("Not found product !");
                }
                StringBuilder pagination = new StringBuilder();
                int startPage;
                int endPage;
                if (currentPage <= 3) {
                    startPage = 1;
                    endPage = 5;
                } else if (currentPage + 2 <= totalPage) {
                    startPage = currentPage - 2;
                    endPage = currentPage + 2;
                } else {
                    startPage = totalPage - 4;
                    endPage = totalPage;
                }

                for (int i = startPage; i <= endPage; i++) {
                    if (currentPage == i) {
                        pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                    } else {
                        pagination.append("[").append(i).append("]");
                    }

                    pagination.append("     ");

                }
                String rs = "|";
                int spaceStart = (132 - (pagination.length())) / 2;
                int spaceEnd = (132 - pagination.length()) - spaceStart;
                for (int j = 1; j <= spaceStart; j++) {
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for (int j = 1; j <= spaceEnd; j++) {
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|            1. Previous page             |                   2. Back                 |           3. Next page              |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.println("|     4. Search by name or description    |            5. See product detail          |             6. Sort product         |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.println("|                       7. List new products                    |               8. Search product by catalog                |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

                int choice = InputMethod.getNumber("Enter choice : ");
                switch (choice) {
                    case 1: {
                        if (currentPage > 1) {
                            currentPage--;
                            skip = (currentPage - 1) * itemPerPage;
                        } else {
                            System.err.println("Cannot previous !");
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
                            System.err.println("Cannot next !");
                        }
                        break;
                    }
                    case 4: {
                        productFeature.searchProduct();
                        break;
                    }
                    case 5: {
                        seeProductDetail();
                        break;
                    }
                    case 6: {
                        productFeature.sortProduct();
                        break;
                    }
                    case 7: {
//                        List<Product> products1 = InputMethod.listProduct();
//                        products = products1.stream()
//                                .sorted((productA, productB) -> productB.getCreatedDate()
//                                        .compareTo(productA.getCreatedDate())).toList().stream()
//                                .limit(10).toList();
//                        productFeature.displayList(products);
                        break;
                    }
                    case 8: {
//                        searchProductByCatalog(products);
                        break;
                    }
                    default: {
                        System.err.println("Enter choice from 1 to 8 !");
                    }
                }
            }
        }
    }

    private void searchProductByCatalog(List<Product> products) {
//        List<Category> categories = InputMethod.listCategory();
//        CategoryUI categoryFeature = new CategoryUI();
//        categoryFeature.displayList(categories);
//        int cateId;
//        int indexCatalog;
//        while (true) {
//            cateId = InputMethod.getNumber("Enter id catalog want search : ");
//            indexCatalog = categories.stream().map(Category::getCateId).toList().indexOf(cateId);
//            if (indexCatalog != -1) {
//                break;
//            } else {
//                System.err.println("Not found id catalog !");
//            }
//        }
//        int finalCateId = cateId;
//        products = products.stream().filter(product -> product.getCateId() == finalCateId).toList();
//        ProductUI productFeature = new ProductUI();
//        productFeature.displayList(products);
    }

    private boolean seeProductDetail() {
        if (!Session.isLogin()){
            System.out.println("Please log in first !");
            return false;
        }
        int idProduct = InputMethod.getNumber("Enter id product : ");
        Product product = productService.findById(idProduct);
        if (product==null) {
            System.out.println("Not found id product !");
        } else {
            NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                              " + GetColor.GREEN + "PAGE : PRODUCT DETAIL" + GetColor.RESET + "                                           |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("|                                      Product Name :  %-56s|\n", product.getProductName());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Price : " + format.format(product.getFinalPrice()) + "VNĐ");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Discount : " + product.getDiscount() + " %");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Inventory : " + product.getInventory());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Storage : " + product.getStorage());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Color : " + product.getColor());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Description : " + product.getDescription());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.printf("| - %-107s|\n", "Category : " + product.getCateId());
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|   1. add to favorites    |       2. Add to cart      |         3. Buy now        |          4 . Back         |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
            int select = InputMethod.getNumber("Enter choice : ");
            switch (select) {
                case 1: {
                    if (Session.isLogin()) {
                        System.err.println("Please log in first !");
                    }
//                    List<FavoriteProduct> favoriteProducts = InputMethod.listFavoriteProduct();
//                    boolean isExist = favoriteProducts.stream().anyMatch(favoriteProduct -> Objects.equals(favoriteProduct.getFavoriteProducts().getProductId(), product.getProductId()));
//                    if (isExist) {
//                        System.err.println("The product is already in your favorites list !");
//                    } else {
//                        FavoriteProduct favoriteProduct = new FavoriteProduct(product, idCustomer);
//                        favoriteProducts.add(favoriteProduct);
//                        boolean result = InputMethod.saveDatabase("listFavoriteProduct.txt", favoriteProducts);
//                        if (result) {
//                            System.out.println("Add favorite product successfully !");
//                        } else {
//                            System.err.println("Add favorite product error !");
//                        }
//                    }
                    break;
                }
                case 2: {
                    if (!Session.isLogin()) {
                        System.err.println("Please log in first !");
                    }
                    if(product.getStatus()!= ProductStatus.ACTIVE){
                        System.out.println("Out of stock");
                    }
                    int idCustomer=Session.getCurrentUser().getUserId();
                    int quantity = 0;
                    while (true) {
                        quantity = InputMethod.getNumber("Enter quantity : ");
                        if (quantity <= 0) {
                            System.err.println("Quantity must > 0 !");
                        } else if (quantity > product.getInventory()) {
                            System.err.println("Quantity > inventory !");
                        } else {
                            break;
                        }
                    }
                    if (cartService.addToCart(idCustomer, product, quantity)) {
                        System.out.println("Add to cart successfully !");
                    } else {
                        System.err.println("Add to cart error !");
                    }
                    break;
                }
                case 3: {
//                    if (checkLogin.getFirst() == null) {
//                        System.err.println("Please log in first !");
//                        Login.main(args);
//                    }
//                    int quantity = InputMethod.getNumber("Enter quantity : ");
//                    ProductCart productCart = new ProductCart(idCustomer, product.getProductName(), product.getFinalPrice(), product.getSize(), product.getColor(), product.getCateId(), quantity, product.getFinalPrice() * quantity);
//                    List<ProductCart> listCarts = new ArrayList<>();
//                    listCarts.add(productCart);
//                    PaymentFeature paymentFeature = new PaymentFeature();
//                    paymentFeature.paymentPage(listCarts, false);
                    break;
                }
                case 4: {
                    return true;
                }
                default: {
                    System.err.println("Enter choice from 1 to 4 !");
                }
            }

        }
        return false;
    }
}