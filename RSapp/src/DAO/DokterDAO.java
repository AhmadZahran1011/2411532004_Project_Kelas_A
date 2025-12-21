package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DBConnection;
import model.Dokter;


public class DokterDAO {
    private Connection conn;

    public DokterDAO() throws SQLException {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public void insert(Dokter d) throws SQLException {
        String sql = "INSERT INTO doctors(nama, alamat, jenisKelamin, spesialis) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNama());
            ps.setString(2, d.getAlamat());
            ps.setString(3, d.getJenisKelamin());
            ps.setString(4, d.getSpesialis());
            ps.executeUpdate();
        }
    }

    public void update(Dokter d) throws SQLException {
        String sql = "UPDATE doctors SET nama=?, alamat=?, jenisKelamin=?, spesialis=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNama());
            ps.setString(2, d.getAlamat());
            ps.setString(3, d.getJenisKelamin());
            ps.setString(4, d.getSpesialis());
            ps.setInt(5, d.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Dokter> getAll() throws SQLException {
        List<Dokter> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Dokter d = new Dokter();
                d.setId(rs.getInt("id"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setJenisKelamin(rs.getString("jenisKelamin"));
                d.setSpesialis(rs.getString("spesialis"));
                list.add(d);
            }
        }
        return list;
    }

    public Dokter getById(int id) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Dokter d = new Dokter();
                    d.setId(rs.getInt("id"));
                    d.setNama(rs.getString("nama"));
                    d.setAlamat(rs.getString("alamat"));
                    d.setJenisKelamin(rs.getString("jenisKelamin"));
                    d.setSpesialis(rs.getString("spesialis"));
                    return d;
                }
            }
        }
        return null;
    }
}

