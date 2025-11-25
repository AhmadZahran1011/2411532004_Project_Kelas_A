package DAO;

import model.Costumer;
import java.util.List;

public interface CostumerDAO {
    public void save(Costumer cs);
    public void update(Costumer cs);
    public void delete(String id);
    public List<Costumer> show();
}