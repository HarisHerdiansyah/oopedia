public abstract class Product {
    private final String productId;
    private String productName;
    private ProductCategory category;
    private double basePrice;
    private int stock;

    public Product(String productName, ProductCategory category, double basePrice, int stock) {
        this.productId = IdGenerator.generateId();
        this.productName = productName;
        this.category = category;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    public abstract double getActualPrice();

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
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
                productId, productName, category.getDesc(), basePrice, stock);
    }
}
