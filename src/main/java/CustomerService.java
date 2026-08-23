import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IDataService<Customer, String> {
    private final List<Customer> customerList = new ArrayList<>();

    @Override
    public void save(Customer data) {
        customerList.add(data);
    }

    @Override
    public Customer findById(String dataId) {
        for (Customer customer: customerList) {
            if (customer.getCustomerId().equals(dataId)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }
}
