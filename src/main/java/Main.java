import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Product[] products = new Product[20];
    private static int lastProductIndex = 0;
    private static final Customer[] customers = new Customer[20];
    private static int lastCustomerIndex = 0;
    private static Customer verifiedCustomer;
    private static final Order[] orders = new Order[20];
    private static int lastOrderIndex = 0;

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

    private static void managePhysicalProducts(PhysicalProduct physicalProduct) {
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

        physicalProduct.setWeightInKg(weightInKg);
        physicalProduct.setWeightCostPerKg(weightCostPerKg);

        products[lastProductIndex] = physicalProduct;
        lastProductIndex += 1;

        System.out.println("\nProduct created!");
        System.out.println(physicalProduct);
    }

    private static void manageDigitalProducts(DigitalProduct digitalProduct) {
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

        digitalProduct.setDownloadUrl(downloadUrl);
        digitalProduct.setSizeInMb(sizeInMb);
        digitalProduct.setPlatformFee(platformFee);

        products[lastProductIndex] = digitalProduct;
        lastProductIndex += 1;

        System.out.println("\nProduct created!");
        System.out.println(digitalProduct);
    }

    private static void manageProducts() {
        System.out.println("\nInsert product information");
        System.out.print("Product name: ");
        String productName = scanner.nextLine();

        System.out.println("Category"); // ELECTRONIC, FASHION, SOFTWARE, EBOOK
        System.out.print("(1. ELECTRONIC, 2. FASHION. 3. SOFTWARE, 4. EBOOK): ");
        String categoryCode = scanner.nextLine().toUpperCase();

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

        String category = switch (categoryCode) {
            case "1" -> "ELECTRONIC";
            case "2" -> "FASHION";
            case "3" -> "SOFTWARE";
            case "4" -> "EBOOK";
            default -> "UNKNOWN";
        };

        boolean isPhysicalProduct = categoryCode.equalsIgnoreCase("1") || categoryCode.equalsIgnoreCase("2");
        Product product = isPhysicalProduct ?
                new PhysicalProduct(productName, category, basePrice, stock) :
                new DigitalProduct(productName, category, basePrice, stock);

        if (isPhysicalProduct) {
            managePhysicalProducts((PhysicalProduct) product);
            return;
        }

        manageDigitalProducts((DigitalProduct) product);
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

    private static Product findProduct(String productId) {
        if (lastProductIndex == 0) return null;
        for (Product product: products) {
            if (product == null) break;
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private static void createOrder() {
        OrderItem[] orderItems = new OrderItem[20];
        int lastItemIndex = 0;
        System.out.println("Create your order!");

        while (lastItemIndex < 20) {
            System.out.print("\nProduct ID: ");
            String productId = scanner.nextLine();

            Product product = findProduct(productId);
            if (product == null) {
                System.out.println("Product not found!");
                return;
            }

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Quantity must be equal or greater than 1!");
                return;
            }

            if (product.getStock() < quantity) {
                System.out.println("Stock isn't enough!");
                return;
            }

            OrderItem orderItem = new OrderItem(product, quantity);
            orderItems[lastItemIndex] = orderItem;
            lastItemIndex += 1;

            System.out.print("Submit another product? (Y/n): ");
            boolean cancel = scanner.nextLine().equalsIgnoreCase("n");
            if (cancel) break;
        }

        Order order = new Order(verifiedCustomer, orderItems);
        orders[lastOrderIndex] = order;
        lastOrderIndex += 1;

        System.out.println("Order created! Your order ID: " + order.getOrderId());
        for (OrderItem orderItem: orderItems) {
            if (orderItem == null) break;
            System.out.println(orderItem);
        }
    }
    // -------------- Option 2 Section --------------

    // -------------- Option 3 Section --------------
    private static void browseCatalog() {
        System.out.println("\nCatalog:");
        if (lastProductIndex == 0) {
            System.out.println("<product empty>");
            return;
        }
        for (Product product: products) {
            if (product == null) break;
            System.out.println(product);
        }
    }
    // -------------- Option 3 Section --------------

    // -------------- Option 4 Section --------------
    private static Order findOrder(String orderId) {
        if (lastOrderIndex == 0) return null;
        for (Order order: orders) {
            if (order == null) break;
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    private static void trackOrder() {
        System.out.println("Track your order!");
        System.out.print("Order ID: ");
        String orderId = scanner.nextLine();

        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Your order status:");
        System.out.println(order);
    }
    // -------------- Option 4 Section --------------

    // -------------- Option 5 Section --------------
    private static void payOrder() {
        System.out.println("Start pay your order!");
        System.out.print("Your order ID: ");
        String orderId = scanner.nextLine();

        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        double amountToPay = order.getGrandTotal();
        double customerBalance = verifiedCustomer.getBalance();
        if (customerBalance < amountToPay) {
            System.out.println("Insufficient balance! Try to top up first!");
            return;
        }

        double finalBalance = customerBalance - amountToPay;
        verifiedCustomer.setBalance(finalBalance);
        order.setOrderStatus("PROCESSING");

        System.out.println("Order successfully paid!");
        System.out.println(order);
    }
    // -------------- Option 5 Section --------------

    // -------------- Option 6 Section --------------
    private static void browseOrder() {
        System.out.println("\nOrder list:");
        if (lastOrderIndex == 0) {
            System.out.println("<order empty>");
            return;
        }
        for (Order order: orders) {
            if (order == null) break;
            System.out.println(order);
        }
    }

    private static void manageOrder() {
        System.out.println("Manager order");
        browseOrder();

        System.out.print("\nSelect Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Current status (1. ON_DELIVERY, 2. ARRIVED, 3. CANCELLED): ");
        String currentStatus = scanner.nextLine();

        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found or wrong ID!");
            return;
        }

        String orderStatus = switch (currentStatus) {
            case "1" -> "ON_DELIVERY";
            case "2" -> "ARRIVED";
            case "3" -> "CANCELLED";
            default -> "UNKNOWN";
        };

        order.setOrderStatus(orderStatus);
        System.out.println("Order updated!");
        System.out.println(order);
    }
    // -------------- Option 6 Section --------------

    // -------------- Main Menu Section --------------
    private static void forwarder(int choice) {
        switch (choice) {
            case 1 -> {
                if (!verifyAdmin()) return;
                manageProducts();
            }
            case 2 -> {
                if (!verifyCustomer()) return;
                createOrder();
            }
            case 3 -> browseCatalog();
            case 4 -> trackOrder();
            case 5 -> {
                if (!verifyCustomer()) return;
                payOrder();
            }
            case 6 -> {
                if (!verifyAdmin()) return;
                manageOrder();
            }
        }
    }

    private static int mainMenu() {
        System.out.println("\nSelect action");
        System.out.println("1. Manage products");
        System.out.println("2. Create order");
        System.out.println("3. Browse catalog");
        System.out.println("4. Track your order");
        System.out.println("5. Pay order");
        System.out.println("6. Manage order");
        System.out.println("0. Exit");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= 6;
    }
    // -------------- Main Menu Section --------------

    public static void main(String[] args) {
        // -------------- Mock Customer Data --------------
        Customer customer = new Customer("John Doe", 200000);
        customers[lastCustomerIndex] = customer;
        lastCustomerIndex += 1;
        System.out.println("Customer mock credential: " + customer.getCustomerId());
        // -------------- Mock Customer Data --------------

        // -------------- Mock Product Data --------------
        PhysicalProduct product1 = new PhysicalProduct("Laptop", "ELECTRONIC", 4800000.0, 10, 1.6, 9000.0);
        products[lastProductIndex] = product1;
        lastProductIndex += 1;

        DigitalProduct product2 = new DigitalProduct("Windows 11", "SOFTWARE", 4800000.0, 10, "http://example.com", 4096, 9000.0);
        products[lastProductIndex] = product2;
        lastProductIndex += 1;
        // -------------- Mock Product Data --------------

        int choice;
        do {
            choice = mainMenu();
            if (isValidChoice(choice)) {
                forwarder(choice);
            }
        } while (choice != 0);
    }
}
