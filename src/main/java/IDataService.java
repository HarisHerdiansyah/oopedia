import java.util.List;

public interface IDataService<T, U> {
    void save(T data);
    T findById(U dataId);
    List<T> findAll();
}
