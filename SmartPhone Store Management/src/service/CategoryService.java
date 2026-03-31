package service;

import dao.CategoryDAO;
import model.Category;

import java.util.List;

public class CategoryService {
    private static CategoryService instance;
    private CategoryDAO categoryDAO = CategoryDAO.getInstance();

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    public List<Category> search(String keyword) {
        return categoryDAO.searchByName(keyword);
    }

    public Category findById(int id){
        return categoryDAO.findById(id);
    }

    public List<Category> getByPage(int page, int size) {
        return categoryDAO.findByPage(page, size);
    }

    public int getTotalPage(int size) {
        int total = categoryDAO.count();
        return (int) Math.ceil((double) total / size);
    }

    public boolean add(String name) {
        if (name.length() < 2 || name.length() > 100) {
            System.out.println("Length must be 2-100 characters");
            return false;
        }

        if (!name.matches("^[\\p{L}0-9 ]+$")) {
            System.out.println("Invalid characters!");
            return false;
        }

        if (categoryDAO.existsByName(name)) {
            System.out.println("Category already exists!");
            return false;
        }

        Category category = new Category(name);

        return categoryDAO.save(category);
    }

    public boolean update(int id, String newName, Boolean status) {

        Category old = categoryDAO.findById(id);

        if (old == null) {
            System.out.println("Category not found!");
            return false;
        }

        if (newName != null && !newName.isEmpty()) {

            newName = newName.trim();

            if (newName.length() < 2) {
                System.out.println("Name must >= 2 characters");
                return false;
            }

            newName = newName.substring(0, 1).toUpperCase() + newName.substring(1).toLowerCase();

        boolean exists = categoryDAO.existsByName(newName)
                && !old.getCateName().equalsIgnoreCase(newName);

        if (exists) {
            System.out.println("Category name already exists!");
            return false;
        }

            old.setCateName(newName);
        }
        if (status != null) {
            old.setStatus(status);
        }

        return categoryDAO.update(old);
    }

    public boolean delete(int id) {
        Category category = categoryDAO.findById(id);

        if (category == null) {
            System.out.println("Category not found!");
            return false;
        }

        return categoryDAO.delete(id);
    }

    public boolean softDelete(int id) {
        return categoryDAO.softDelete(id);
    }

    public List<Category> searchByName(String keyword, int page, int size) {
        return categoryDAO.searchByName(keyword, page, size);
    }

    public int getTotalPageSearch(String keyword, int size) {
        int total = categoryDAO.countSearch(keyword);
        return (int) Math.ceil((double) total / size);
    }
}