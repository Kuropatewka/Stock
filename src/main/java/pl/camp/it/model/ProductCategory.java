package pl.camp.it.model;

public class ProductCategory {
    private int id;
    private String name;
    private boolean deleted;

    public ProductCategory(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id= " + id +
                ", name= " + name +
                ", deleted= " + deleted +
                '}';
    }
}
