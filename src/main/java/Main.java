import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PhysicalProduct[] physicalProducts = new PhysicalProduct[20];
    private static int lastPhysicalProductIndex = 0;
    private static final DigitalProduct[] digitalProducts = new DigitalProduct[20];
    private static int lastDigitalProductIndex = 0;
    private static final Customer[] customers = new Customer[20];
    private static int lastCustomerIndex = 0;
    private static Customer verifiedCustomer;

    // -------------- Option 1 Section --------------
    private static boolean verifyAdmin() {
        System.out.println("Verify if you're and admin!");
        System.out.print("Credential: ");
        String credential = scanner.nextLine();

        if (!credential.equalsIgnoreCase("admin-oopedia")) {
            System.out.println("You're not allowed to manage product!");
            return false;
        }

        System.out.println("You're verified!");
        return true;
    }

    private static void managePhysicalProducts(Product baseProductData) {
        System.out.print("Weight in Kg: ");
        double weightInKg = Double.parseDouble(scanner.nextLine());
        if (weightInKg <= 0) {
            System.out.println("Product weight must be greater than or equal to zero!");
            return;
        }

        System.out.print("Weight Cost per Kg (Input 1.0 if there is no cost): ");
        double weightCostPerKg = Double.parseDouble(scanner.nextLine());
        if (weightCostPerKg < 1.0) {
            System.out.println("Weight cost of product must be greater than or equal to IDR 1!");
            return;
        }

        String productName = baseProductData.getProductName();
        String category = baseProductData.getCategory();
        double basePrice = baseProductData.getBasePrice();
        int stock = baseProductData.getStock();

        PhysicalProduct physicalProduct = new PhysicalProduct(productName, category, basePrice, stock);
        physicalProduct.setWeightInKg(weightInKg);
        physicalProduct.setWeightCostPerKg(weightCostPerKg);

        System.out.println("\nProduct created!");
        System.out.println(physicalProduct);

        physicalProducts[lastPhysicalProductIndex] = physicalProduct;
        lastPhysicalProductIndex += 1;
    }

    private static void manageDigitalProducts(Product baseProductData) {
        System.out.print("Download URL: ");
        String downloadUrl = scanner.nextLine();
        if (!downloadUrl.contains("http")) {
            System.out.println("Download URL must be valid URL!");
            return;
        }

        System.out.print("Size in MB: ");
        double sizeInMb = Double.parseDouble(scanner.nextLine());
        if (sizeInMb < 10.0) {
            System.out.println("Size in megabytes must be greater than or equal to 10 MB!");
            return;
        }

        System.out.print("Platform fee (Input 1.0 if there is no fee): ");
        double platformFee = Double.parseDouble(scanner.nextLine());
        if (platformFee < 1.0) {
            System.out.println("Platform fee must be greater than or equal to IDR 1!");
            return;
        }

        String productName = baseProductData.getProductName();
        String category = baseProductData.getCategory();
        double basePrice = baseProductData.getBasePrice();
        int stock = baseProductData.getStock();

        DigitalProduct digitalProduct = new DigitalProduct(productName, category, basePrice, stock);
        digitalProduct.setDownloadUrl(downloadUrl);
        digitalProduct.setSizeInMb(sizeInMb);
        digitalProduct.setPlatformFee(platformFee);

        System.out.println("\nProduct created!");
        System.out.println(digitalProduct);

        digitalProducts[lastDigitalProductIndex] = digitalProduct;
        lastDigitalProductIndex += 1;
    }

    private static void manageProducts() {
        System.out.println("\nInsert product information");
        System.out.print("Product name: ");
        String productName = scanner.nextLine();

        System.out.println("Category"); // ELECTRONIC, FASHION, SOFTWARE, EBOOK
        System.out.print("(1. ELECTRONIC, 2. FASHION. 3. SOFTWARE, 4. EBOOK): ");
        String category = scanner.nextLine().toUpperCase();

        System.out.print("Base price (IDR): ");
        double basePrice = Double.parseDouble(scanner.nextLine());
        if (basePrice < 10000.0) {
            System.out.println("Product price must be greater than or equal to IDR 10000.0!");
            return;
        }

        System.out.print("Initial stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        if (stock < 0) {
            System.out.println("Product initial stock can't be negative!");
            return;
        }

        Product product = new Product(productName, category, basePrice, stock);
        boolean isPhysicalProduct = category.equalsIgnoreCase("1") || category.equalsIgnoreCase("2");
        if (isPhysicalProduct) {
            managePhysicalProducts(product);
            return;
        }
        manageDigitalProducts(product);
    }
    // -------------- Option 1 Section --------------

    // -------------- Option 2 Section --------------
    private static Customer findCustomer(String customerId) {
        if (lastCustomerIndex == 0) return null;
        for (Customer customer: customers) {
            if (customer == null) break;
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private static boolean verifyCustomer() {
        System.out.println("Verify if you valid customer!");
        System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = findCustomer(customerId);
        if (customer == null) {
            System.out.println("You're not allowed to create an order!");
            return false;
        }

        System.out.println("You're verified");
        verifiedCustomer = customer;
        return true;
    }

    private static PhysicalProduct findPhysicalProduct(String productId) {
        if (lastPhysicalProductIndex == 0) return null;
        for (PhysicalProduct product: physicalProducts) {
            if (product == null) break;
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private static DigitalProduct findDigitalProduct(String productId) {
        if (lastDigitalProductIndex == 0) return null;
        for (DigitalProduct product: digitalProducts) {
            if (product == null) break;
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private static void createOrder() {
        System.out.println("Create your order!");
        System.out.print("\nProduct ID: ");
        String productId = scanner.nextLine();

        PhysicalProduct physicalProduct = findPhysicalProduct(productId);
        DigitalProduct digitalProduct = findDigitalProduct(productId);
        if (physicalProduct == null && digitalProduct == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (quantity <= 0) {
            System.out.println("Quantity must be equal or greater than 1");
            return;
        }

        System.out.println("Order created!");
        if (physicalProduct != null) {
            System.out.printf("%s (%dx), IDR %.2f\n",
                    physicalProduct.getProductName(), quantity, physicalProduct.getActualPrice());
            return;
        }
        System.out.printf("%s (%dx), IDR %.2f\n",
                digitalProduct.getProductName(), quantity, digitalProduct.getActualPrice());
    }
    // -------------- Option 2 Section --------------

    // -------------- Option 3 Section --------------
    private static void browseCatalog() {
        System.out.println("\nCatalog:");
        if (lastPhysicalProductIndex == 0 && lastDigitalProductIndex == 0) {
            System.out.println("<product empty>");
            return;
        }
        for (PhysicalProduct product: physicalProducts) {
            if (product == null) break;
            System.out.println(product);
        }
        for (DigitalProduct product: digitalProducts) {
            if (product == null) break;
            System.out.println(product);
        }
    }
    // -------------- Option 3 Section --------------

    // -------------- Option 4 Section --------------
    private static void trackOrder() {}
    // -------------- Option 4 Section --------------

    // -------------- Main Menu Section --------------
    private static void forwarder(int choice) {
        if (choice == 1) {
            if (!verifyAdmin()) return;
            manageProducts();
        } else if (choice == 2) {
            if (!verifyCustomer()) return;
            createOrder();
        } else if (choice == 3) {
            browseCatalog();
        } else {
            trackOrder();
        }
    }

    private static int mainMenu() {
        System.out.println("\nSelect action");
        System.out.println("1. Manage products");
        System.out.println("2. Create order");
        System.out.println("3. Browse catalog");
        System.out.println("4. Track your order");
        System.out.println("0. Exit");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= 4;
    }
    // -------------- Main Menu Section --------------

    public static void main(String[] args) {
        // -------------- Mock Customer Data --------------
        Customer customer = new Customer("John Doe", 200000);
        customers[lastCustomerIndex] = customer;
        lastCustomerIndex += 1;
        System.out.println("Customer mock credential: " + customer.getCustomerId());
        // -------------- Mock Customer Data --------------

        int choice;
        do {
            choice = mainMenu();
            if (isValidChoice(choice)) {
                forwarder(choice);
            }
        } while (choice != 0);
    }
}
