import java.util.ArrayList;
import java.util.List;

public class OrderService implements IDataService<Order, String> {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public void save(Order data) {
        orderList.add(data);
    }

    @Override
    public Order findById(String dataId) {
        for (Order order: orderList) {
            if (order.getOrderId().equals(dataId)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orderList;
    }
}
