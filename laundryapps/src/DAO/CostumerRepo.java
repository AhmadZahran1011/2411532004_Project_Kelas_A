package DAO;

import config.Database;
import model.Costumer;
import model.CostumerBuilder;
import java.sql.*;
import java.util.*;

public class CostumerRepo implements CostumerDAO {
	private static CostumerRepo instance;

	public static CostumerRepo getInstance() {
	    if (instance == null) {
	        instance = new CostumerRepo();
	    }
	    return instance;
	}
	
		private Connection connection;
		private final String insert = "INSERT INTO costumer (nama, alamat, nomorhp) VALUES (?,?,?);";
		private final String select = "SELECT * FROM costumer;";
		private final String delete = "DELETE FROM costumer WHERE id=?;";
		private final String update = "UPDATE costumer SET nama=?, alamat=?, nomorhp=? WHERE id=?;";
		
		public CostumerRepo() {
			// TODO Auto-generated constructor stub
			connection = Database.koneksi();
		}
		@Override
		public List<Costumer> show() {
		    List<Costumer> list = new ArrayList<>();
		    try (Statement st = connection.createStatement()) {
		        ResultSet rs = st.executeQuery(select);
		        while (rs.next()) {

		            Costumer cs = new CostumerBuilder()
		                    .setId(rs.getString("id"))
		                    .setNama(rs.getString("nama"))
		                    .setAlamat(rs.getString("alamat"))
		                    .setNomorhp(rs.getString("nomorhp"))
		                    .build();

		            list.add(cs);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
		}

	    @Override
	    public void save(Costumer cs) {
	        PreparedStatement st = null;
	        try {
	            st = connection.prepareStatement(insert);
	            Costumer built = new CostumerBuilder()
	                    .setNama(cs.getNama())
	                    .setAlamat(cs.getAlamat())
	                    .setNomorhp(cs.getNomorhp())
	                    .build();

	            st.setString(1, built.getNama());
	            st.setString(2, built.getAlamat());
	            st.setString(3, built.getNomorhp());
	            st.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try { if (st != null) st.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	    @Override
	    public void update(Costumer cs) {
	        try (PreparedStatement st = connection.prepareStatement(update)) {
	            Costumer built = new CostumerBuilder()
	                    .setId(cs.getId())
	                    .setNama(cs.getNama())
	                    .setAlamat(cs.getAlamat())
	                    .setNomorhp(cs.getNomorhp())
	                    .build();

	            st.setString(1, built.getNama());
	            st.setString(2, built.getAlamat());
	            st.setString(3, built.getNomorhp());
	            st.setString(4, built.getId());
	            st.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(String id) {
	        try (PreparedStatement st = connection.prepareStatement(delete)) {
	            Costumer temp = new CostumerBuilder()
	                    .setId(id)
	                    .build();

	            st.setString(1, temp.getId());
	            st.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}
