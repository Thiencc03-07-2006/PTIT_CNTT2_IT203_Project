package presentation.ui;

import model.Category;
import service.CategoryService;
import util.GetColor;
import util.InputMethod;

import java.util.List;
import java.util.Scanner;

public class CategoryUI {
    Scanner scanner = new Scanner(System.in);
    CategoryService categoryService = CategoryService.getInstance();

    public void displayList() {
        int currentPage = 1;
        int catePerPage = 5;
        while (true) {
            List<Category> categories = categoryService.getByPage(currentPage, catePerPage);
            int totalPage = categoryService.getTotalPage(catePerPage);
            if (totalPage == 0) {
                System.out.println("List category is empty!");
                return;
            }
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                   " + GetColor.GREEN + "LIST CATEGORIES" + GetColor.RESET + "                                 |");
            System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("| %-8s | %-38s | %-8s | %-18s |\n", "ID", "Category Name", "Status", "createdDate");
            System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
            for (Category c : categories) {
                c.displayData();
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
            int spaceStart = (92 - (pagination.length())) / 2;
            int spaceEnd = (92 - pagination.length()) - spaceStart;
            for (int j = 1; j <= spaceStart; j++) {
                rs += " ";
            }
            rs = rs.concat(pagination.toString());
            for (int j = 1; j <= spaceEnd; j++) {
                rs += " ";
            }
            rs += "|";
            System.out.println(rs);
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|     1. Previous page       |         2. Back          |        3. Next page       |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

            int choice = InputMethod.getNumber("Enter choice : ");
            switch (choice) {
                case 1: {
                    if (currentPage > 1) {
                        currentPage--;
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
                    } else {
                        System.out.println("Cannot next !");
                    }
                    break;
                }
                default: {
                    System.out.println("Choice invalid !");
                }
            }
        }
    }


    public boolean add() {
        CategoryService categoryService = CategoryService.getInstance();
        int number = InputMethod.getNumber("Enter number category want add : ");
        boolean isSuccess = true;
        for (int i = 1; i <= number; i++) {
            System.out.println("Enter category " + i);
            String name = InputMethod.getString("Enter category name:");
            boolean result = categoryService.add(name);
            if (result) {
                System.out.println("Add success!");
            } else {
                System.out.println("Add failed!");
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean update() {
        CategoryService categoryService = CategoryService.getInstance();
        int idCate = InputMethod.getNumber("Enter category id to update :");
        System.out.print("Enter new name (Enter to skip):");
        String name = scanner.nextLine().trim();
        System.out.println("Choose status:");
        System.out.println("1. Active");
        System.out.println("2. Inactive");
        System.out.println("3. Skip");
        int choice = InputMethod.getNumber("Enter choice:");
        Boolean status = null;
        switch (choice) {
            case 1:
                status = true;
                break;
            case 2:
                status = false;
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice!");
                return false;
        }
        boolean result = categoryService.update(idCate, name, status);
        if (result) {
            System.out.println("Update success!");
        } else {
            System.out.println("Update failed!");
        }
        return result;
    }

    public boolean delete() {
        CategoryService categoryService = CategoryService.getInstance();
        int idCate = InputMethod.getNumber("Enter category id number to delete :");
        Category category = categoryService.findById(idCate);
        if (category == null) {
            System.out.println("Category not found!");
            return false;
        }
        System.out.println("Category will be deleted:");
        category.displayData();
        System.out.println("Are you sure to delete?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int confirm = InputMethod.getNumber("Enter choice: ");
        if (confirm != 1) {
            System.out.println("Delete cancelled!");
            return false;
        }
        boolean result = categoryService.delete(idCate);
        if (result) {
            System.out.println("Delete success");
        } else {
            System.out.println("Delete failed!");
        }
        return result;
    }

    public boolean softDelete() {
        int idCate = InputMethod.getNumber("Enter category id number to delete :");
        Category category = categoryService.findById(idCate);
        if (category == null) {
            System.out.println("Category not found!");
            return false;
        }
        System.out.println("Category will be deleted:");
        category.displayData();
        System.out.println("Are you sure to delete?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int confirm = InputMethod.getNumber("Enter choice: ");
        if (confirm != 1) {
            System.out.println("Delete cancelled!");
            return false;
        }
        boolean result = categoryService.softDelete(idCate);
        if (result) {
            System.out.println("Delete success");
        } else {
            System.out.println("Delete failed!");
        }
        return result;
    }


    public void searchCateByName() {
        CategoryService categoryService = CategoryService.getInstance();
        String keyword = InputMethod.getString("Enter name categories want search: ");
        int currentPage = 1;
        int catePerPage = 5;
        while (true) {
            List<Category> categories = categoryService.searchByName(keyword,currentPage, catePerPage);
            int totalPage = categoryService.getTotalPageSearch(keyword,catePerPage);
            if (totalPage == 0) {
                System.out.println("List category is empty!");
                return;
            }
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                   " + GetColor.GREEN + "LIST CATEGORIES" + GetColor.RESET + "                                 |");
            System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("| %-8s | %-38s | %-8s | %-18s |\n", "ID", "Category Name", "Status", "createdDate");
            System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
            for (Category c : categories) {
                c.displayData();
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
            int spaceStart = (92 - (pagination.length())) / 2;
            int spaceEnd = (92 - pagination.length()) - spaceStart;
            for (int j = 1; j <= spaceStart; j++) {
                rs += " ";
            }
            rs = rs.concat(pagination.toString());
            for (int j = 1; j <= spaceEnd; j++) {
                rs += " ";
            }
            rs += "|";
            System.out.println(rs);
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|     1. Previous page       |         2. Back          |        3. Next page       |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

            int choice = InputMethod.getNumber("Enter choice : ");
            switch (choice) {
                case 1: {
                    if (currentPage > 1) {
                        currentPage--;
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
                    } else {
                        System.out.println("Cannot next !");
                    }
                    break;
                }
                default: {
                    System.out.println("Choice invalid !");
                }
            }
        }
    }

    public void sortCategories() {
        CategoryService categoryService = CategoryService.getInstance();
    }

}