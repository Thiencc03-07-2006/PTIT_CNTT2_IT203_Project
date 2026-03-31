package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService {
    private static ProductService instance;
    private final ProductDAO productDAO = ProductDAO.getInstance();

    public static ProductService getInstance() {
        if (instance == null) instance = new ProductService();
        return instance;
    }

    public boolean add(Product p) {
        return productDAO.save(p);
    }

    public List<Product> getAll() {
        return productDAO.findAll();
    }

    public List<Product> getAllActive() {
        return productDAO.findAllActive();
    }

    public boolean update(Product p) {
        return productDAO.update(p);
    }

    public boolean delete(int id) {
        return productDAO.softDelete(id);
    }

    public List<Product> search(String keyword) {
        return productDAO.search(keyword);
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}