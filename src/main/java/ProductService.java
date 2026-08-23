import java.util.ArrayList;
import java.util.List;

public class ProductService implements IDataService<Product, String> {
    private final List<Product> productList = new ArrayList<>();

    @Override
    public void save(Product data) {
        productList.add(data);
    }

    @Override
    public Product findById(String dataId) {
        for (Product product: productList) {
            if (product.getProductId().equals(dataId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }
}
