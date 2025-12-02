package DAO;

import model.User;
import java.util.List;

public interface UserDAO {
	void save(User user); 
	public List<User> show(); 
	public void delete(String id); 
	public void update(User user); 
	}

