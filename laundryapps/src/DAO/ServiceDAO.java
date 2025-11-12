package DAO;

import model.Service;
import java.util.List;

public interface ServiceDAO {
    void save(Service l);
    void update(Service l);
    void delete(String id);
    List<Service> show();
}
