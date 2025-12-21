package DAO;

import config.Database;
import model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo implements ServiceDAO {
    private Connection conn;

    public ServiceRepo() {
        conn = Database.koneksi();
    }

    @Override
    public void save(Service s) {
        String sql = "INSERT INTO service(id, nama_service, harga) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (s.getId() == null) s.setId(String.valueOf(System.currentTimeMillis()));
            ps.setString(1, s.getId());
            ps.setString(2, s.getJenis());
            ps.setString(3, s.getHarga());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Service s) {
        String sql = "UPDATE service SET nama_service = ?, harga = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getJenis());
            ps.setString(2, s.getHarga());
            ps.setString(3, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM service WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> show() {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM service";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getString("id"));
                s.setJenis(rs.getString("jenis"));
                s.setHarga(rs.getString("harga"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
