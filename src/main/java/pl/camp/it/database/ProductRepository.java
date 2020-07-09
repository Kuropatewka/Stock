package pl.camp.it.database;

import pl.camp.it.model.Product;

import java.util.List;

public class ProductRepository {
    private static List<Product> products;
    private static final ProductRepository productRepository = new ProductRepository();

    public static List<Product> getProducts() {
        return products;
    }

    public static ProductRepository getProductRepository() {
        products = SQLdb.getAllProducts();
        return productRepository;
    }

    public static ProductRepository getProductRepositoryByCategory(String whatCategory) {
        products = SQLdb.getAllProductsByCategory(whatCategory);
        return productRepository;
    }
}
