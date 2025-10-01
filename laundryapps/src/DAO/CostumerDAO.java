package DAO;

import model.Costumer;
import java.util.List;

public interface CostumerDAO {
    void save(Costumer p);
    void update(Costumer p);
    void delete(String id);
    List<Costumer> show();
}