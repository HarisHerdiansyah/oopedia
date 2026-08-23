public class PhysicalProduct extends Product {
    private double weightInKg;
    private double weightCostPerKg;

    public PhysicalProduct(String productName, String category, double basePrice, int stock) {
        super(productName, category, basePrice, stock);
    }

    public PhysicalProduct(String productName, String category, double basePrice, int stock, double weightInKg, double weightCostPerKg) {
        super(productName, category, basePrice, stock);
        this.weightInKg = weightInKg;
        this.weightCostPerKg = weightCostPerKg;
    }

    @Override
    public double getActualPrice() {
        return super.getBasePrice() + (this.weightInKg * this.weightCostPerKg);
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    public double getWeightCostPerKg() {
        return weightCostPerKg;
    }

    public void setWeightCostPerKg(double weightCostPerKg) {
        this.weightCostPerKg = weightCostPerKg;
    }

    @Override
    public String toString() {
        return String.format("%s\nWeight: %.2f Kg, Additional Cost per Kg: IDR %.2f",
                super.toString(), this.weightInKg, this.weightCostPerKg);
    }
}
