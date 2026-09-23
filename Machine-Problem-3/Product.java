package inventorystockmonitor;

public class Product {

    private String code;
    private String name;
    private double price;
    private int stock;
    private static int productCount = 0;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        setPrice(price);
        setStock(stock);
        productCount++;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public static int getProductCount() {
        return productCount;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    public boolean restock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
            return true;
        }
        return false;
    }

    public boolean sell(int quantity) {
        if (quantity > 0 && quantity <= stock) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    public double getInventoryValue() {
        return price * stock;
    }

    public boolean isLowStock() {
        return stock <= 5;
    }
}