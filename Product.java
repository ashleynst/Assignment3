public class Product {
    private final String id, name, category;
    private final double price;

    public Product(String id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String toString() {
        return String.format("ID: %s, Name: %s, Category: %s, Price: $%.2f", id, name, category, price);
    }
}


