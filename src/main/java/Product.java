import java.security.SecureRandom;

public class Product {
    private final SecureRandom RANDOM = new SecureRandom();
    private final String productId;
    private String productName;
    private String category;
    private double basePrice;
    private int stock;

    public Product(String productName, String category, double basePrice, int stock) {
        this.productId = generateId();
        this.productName = productName;
        this.category = category;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    private String generateId() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public double getActualPrice() {
        return basePrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s): IDR %.2f (Stock: %d)",
                productId, productName, category, basePrice, stock);
    }
}
