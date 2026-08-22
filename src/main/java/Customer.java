import java.security.SecureRandom;

public class Customer {
    private final SecureRandom RANDOM = new SecureRandom();
    private final String customerId;
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.customerId = generateId();
        this.name = name;
        this.balance = balance;
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

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s; Balance: IDR %.2f",
                customerId, name, balance);
    }
}
