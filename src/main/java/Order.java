import java.util.Arrays;

public class Order {
    private final String orderId;
    private Customer customer;
    private OrderItem[] orderItems;
    private double grandTotal;
    private String orderStatus; // WAIT_FOR_PAYMENT, PROCESSING, ON_DELIVERY, ARRIVED, CANCELLED

    public Order(Customer customer, OrderItem[] orderItems) {
        this.orderId = IdGenerator.generateId();
        this.customer = customer;
        this.orderItems = orderItems;
        this.grandTotal = initGrandTotal();
        this.orderStatus = "WAIT_FOR_PAYMENT";
    }

    public double initGrandTotal() {
        double sum = 0.0;
        for (OrderItem orderItem: orderItems) {
            sum += orderItem.getSubTotal();
        }
        return sum;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] Customer: %s, Grand Total: %.2f, Status: %s",
                orderId, customer.getName(), grandTotal, orderStatus);
    }
}
