import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Product[] products = new Product[20];
    private static int lastProductIndex = 0;
    private static final Customer[] customers = new Customer[20];
    private static int lastCustomerIndex = 0;

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

    private static void manageProducts() {
        System.out.println("\nInsert product information");
        System.out.print("Product name: ");
        String productName = scanner.nextLine();
        System.out.print("Category: "); // ELECTRONIC, FASHION, SOFTWARE, EBOOK
        String category = scanner.nextLine().toUpperCase();
        System.out.print("Base price (IDR): ");
        double basePrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Initial stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Product product = new Product(productName, category, basePrice, stock);
        System.out.println("\nProduct created!");
        System.out.println(product);

        products[lastProductIndex] = product;
        lastProductIndex += 1;
    }
    // -------------- Option 1 Section --------------

    // -------------- Option 2 Section --------------
    private static Customer findCustomer(String customerId) {
        if (lastCustomerIndex == 0) return null;
        for (Customer customer: customers) {
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
        return true;
    }

    private static void createOrder() {}
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
