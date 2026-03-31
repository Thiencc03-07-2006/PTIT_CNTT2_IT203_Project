package presentation.ui;

import model.Product;
import model.ProductStatus;
import service.ProductService;
import util.GetColor;
import util.InputMethod;

import java.text.NumberFormat;

import java.util.*;

public class ProductUI {
    private final ProductService productService = ProductService.getInstance();

    public void displayList() {
        List<Product> products = productService.getAll();
        showList(products);
    }

    private static void showList(List<Product> products) {
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
                System.out.printf("| %-3s | %-28s | %-3s | %-13s | %-3s | %-18s | %-8s | %-3s | %-18s |\n", "ID", "Product Name", "-%", "FinalPrice", "Inv", "Storage", "Color", "CID", "Status");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
                for (int i = skip; i < (skip + itemPerPage); i++) {
                    if (i < size) {
                        products.get(i).displayData();
                    } else {
                        break;
                    }
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
                    if (i > totalPage) {
                        break;
                    }
                    if (i < 1) {
                        continue;
                    }
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
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

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
                    default: {
                        System.out.println("Choice invalid !");
                    }
                }
            }
        }
    }

    public boolean add() {
        boolean rs = true;
        int number = InputMethod.getNumber("Enter number product want add : ");
        for (int i = 1; i <= number; i++) {
            System.out.println("Enter product number " + i);
            Product p = new Product();
            p.setProductName(InputMethod.getString("Enter product name : "));

            while (true) {
                Double rootPrice = InputMethod.getDouble("Enter selling price : ");
                if (rootPrice > 0) {
                    p.setRootPrice(rootPrice);
                    break;
                } else {
                    System.err.println("Selling price > 0 !");
                }
            }
            while (true) {
                int discount = InputMethod.getNumber("Enter discount product (0% - 100%) : ");
                if (discount >= 0 && discount <= 100) {
                    p.setDiscount(discount);
                    break;
                } else {
                    System.err.println("Enter discount from 0 to 100 ! ");
                }
            }
            while (true) {
                int inventory = InputMethod.getNumber("Enter inventory : ");
                if (inventory > 0) {
                    p.setInventory(inventory);
                    break;
                } else {
                    System.err.println("Inventory > 0 !");
                }
            }

            while (true) {
                System.out.println("*************** List Color ****************");
                for (int j = 0; j < GetColor.colors.size(); j++) {
                    System.out.println((j + 1) + " : " + GetColor.colors.get(j));
                }
                int color = InputMethod.getNumber("Enter color number : ");
                if (color > 0 && color < 9) {
                    p.setColor(GetColor.colors.get(color - 1));
                    break;
                } else {
                    System.err.println("Enter number from 1 to 8 !");
                }
            }


            p.setBrand(InputMethod.getString("Brand: "));

            p.setStorage(InputMethod.getString("Storage: "));

            int cateId = InputMethod.getNumber("Enter category ID: ");
            p.setCateId(cateId);
            if (productService.add(p)) {
                System.out.println("Add success!");
            } else {
                System.out.println("Add failed!");
                rs = false;
            }
        }
        return rs;
    }


    public boolean update() {
        int id = InputMethod.getNumber("Enter product ID: ");
        Product p = productService.findById(id);

        if (p == null) {
            System.err.println("Not found!");
            return false;
        }

        System.out.println("=== Leave blank to skip ===");

        String name = InputMethod.getStringOptional("New name (" + p.getProductName() + "): ");
        if (!name.isEmpty()) p.setProductName(name);

        String brand = InputMethod.getStringOptional("New brand (" + p.getBrand() + "): ");
        if (!brand.isEmpty()) p.setBrand(brand);

        String storage = InputMethod.getStringOptional("New storage (" + p.getStorage() + "): ");
        if (!storage.isEmpty()) p.setStorage(storage);

        while (true) {
            System.out.println("*************** List Color ****************");
            for (int j = 0; j < GetColor.colors.size(); j++) {
                System.out.println((j + 1) + " : " + GetColor.colors.get(j));
            }
            int color = InputMethod.getNumber("New color (" + p.getColor() + "): ");
            if (color > 0 && color < 9) {
                p.setColor(GetColor.colors.get(color - 1));
                break;
            } else {
                System.err.println("Enter number from 1 to 8 !");
            }
        }

        String desc = InputMethod.getStringOptional("New description: ");
        if (!desc.isEmpty()) p.setDescription(desc);

        String cate = InputMethod.getStringOptional("New category ID (" + p.getCateId() + "): ");
        if (!cate.isEmpty()) p.setCateId(Integer.parseInt(cate));

        System.out.println("1. ACTIVE | 2. INACTIVE | 3. OUT_OF_STOCK");
        String st = InputMethod.getStringOptional("New status (" + p.getStatus() + "): ");
        if (!st.isEmpty()) {
            switch (st) {
                case "1" -> p.setStatus(ProductStatus.ACTIVE);
                case "2" -> p.setStatus(ProductStatus.INACTIVE);
                case "3" -> p.setStatus(ProductStatus.OUT_OF_STOCK);
            }
        }

        String priceInput = InputMethod.getStringOptional("New price (" + p.getRootPrice() + "): ");
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            if (price <= 0) {
                System.err.println("Price must > 0");
                return false;
            }
            p.setRootPrice(price);
        }

        String discountInput = InputMethod.getStringOptional("New discount (" + p.getDiscount() + "): ");
        if (!discountInput.isEmpty()) {
            int discount = Integer.parseInt(discountInput);
            if (discount < 0 || discount > 100) {
                System.err.println("Discount 0-100");
                return false;
            }
            p.setDiscount(discount);
        }

        String invInput = InputMethod.getStringOptional("New inventory (" + p.getInventory() + "): ");
        if (!invInput.isEmpty()) {
            int inv = Integer.parseInt(invInput);
            if (inv < 0) {
                System.err.println("Inventory >= 0");
                return false;
            }
            p.setInventory(inv);
        }

        if (productService.update(p)) {
            System.out.println("Updated!");
            return true;
        }

        System.err.println("Update failed!");
        return false;
    }


    public boolean delete() {
        int id = InputMethod.getNumber("Enter ID: ");
        Product p = productService.findById(id);

        if (p == null) {
            System.err.println("Not found!");
            return false;
        }

        System.out.println("Product will be deleted:");
        p.displayData();
        System.out.println("Are you sure to delete?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int confirm = InputMethod.getNumber("Enter choice: ");
        if (confirm != 1) {
            System.out.println("Delete cancelled!");
            return false;
        }
        boolean result = productService.delete(p.getProductId());
        if (result) {
            System.out.println("Delete success");
        } else {
            System.out.println("Delete failed!");
        }
        return result;
    }

    public void searchProduct() {
        String keyword = InputMethod.getString("Enter keyword: ");

        List<Product> list = productService.search(keyword);

        if (list.isEmpty()) {
            System.err.println("Not found!");
        } else {
            showList(list);
        }
    }

    public void sortProduct() {
        List<Product> list = productService.getAll();

        System.out.println("1. ASC");
        System.out.println("2. DESC");

        int choice = InputMethod.getNumber("Choice: ");
        if (choice == 1) {
            list.sort(Comparator.comparing(Product::getFinalPrice));
        } else {
            list.sort(Comparator.comparing(Product::getFinalPrice).reversed());
        }
        showList(list);
    }

    public void viewDetail() {
        int id = InputMethod.getNumber("Enter product ID: ");
        Product p = productService.findById(id);

        if (p == null) {
            System.err.println("Not found!");
            return;
        }

        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("====== PRODUCT DETAIL ======");
        System.out.println("ID: " + p.getProductId());
        System.out.println("Name: " + p.getProductName());
        System.out.println("Brand: " + p.getBrand());
        System.out.println("Storage: " + p.getStorage());
        System.out.println("Color: " + p.getColor());
        System.out.println("Price: " + format.format(p.getRootPrice()) + "VND");
        System.out.println("Discount: " + p.getDiscount() + "%");
        System.out.println("Final Price: " + format.format(p.getFinalPrice()) + "VND");
        System.out.println("Inventory: " + p.getInventory());
        System.out.println("Status: " + p.getStatus());
        System.out.println("Description: " + p.getDescription());
    }
}