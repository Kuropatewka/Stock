package pl.camp.it.database;

import pl.camp.it.model.ProductCategory;

import java.util.List;

public class ProductCategoryRepository {
    private static List<ProductCategory> categories;
    private static final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();

    public static List<ProductCategory> getCategories() {
        return categories;
    }

    public static ProductCategoryRepository getProductCategoryRepository() {
        categories = SQLdb.getAllCategories();
        return productCategoryRepository;
    }
}
