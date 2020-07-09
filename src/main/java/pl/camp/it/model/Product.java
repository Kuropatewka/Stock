package pl.camp.it.model;

public class Product {
    private int id;
    private String name;
    private int amount;
    private long bareCode;
    private String category;

    public Product(int id, String name, int amount, long bareCode, String category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.bareCode = bareCode;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id= " + id +
                ", name= " + name +
                ", amount= " + amount +
                ", bareCode= " + bareCode +
                ", category= " + category +
                '}';
    }
}
