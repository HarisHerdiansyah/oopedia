public class Customer {
    private final String customerId;
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.customerId = IdGenerator.generateId();
        this.name = name;
        this.balance = balance;
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
