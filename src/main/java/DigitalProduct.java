public class DigitalProduct extends Product {
    private String downloadUrl;
    private double sizeInMb;
    private double platformFee;

    public DigitalProduct(String productName, ProductCategory category, double basePrice, int stock) {
        super(productName, category, basePrice, stock);
    }

    public DigitalProduct(String productName, ProductCategory category, double basePrice, int stock, String downloadUrl, double sizeInMb, double platformFee) {
        super(productName, category, basePrice, stock);
        this.downloadUrl = downloadUrl;
        this.sizeInMb = sizeInMb;
        this.platformFee = platformFee;
    }

    @Override
    public double getActualPrice() {
        return super.getBasePrice() + this.platformFee;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public double getSizeInMb() {
        return sizeInMb;
    }

    public void setSizeInMb(double sizeInMb) {
        this.sizeInMb = sizeInMb;
    }

    public double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(double platformFee) {
        this.platformFee = platformFee;
    }

    @Override
    public String toString() {
        return String.format("%s\nURL: %s, Size in MB: %.2f, Platform Fee: IDR %.2f",
                super.toString(), this.downloadUrl, this.sizeInMb, this.platformFee);
    }
}
