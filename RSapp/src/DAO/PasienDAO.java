package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import model.Pasien;

/*
 Contoh Tabel SQL untuk patients:
 CREATE TABLE patients (
   id INT AUTO_INCREMENT PRIMARY KEY,
   nama VARCHAR(100),
   alamat VARCHAR(255),
   jenisKelamin VARCHAR(10),
   tanggalMasuk VARCHAR(20),
   keluhan TEXT
 );
*/

public class PasienDAO {
    private Connection conn;

    public PasienDAO() throws SQLException {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public void insert(Pasien p) throws SQLException {
        String sql = "INSERT INTO patients(nama, alamat, jenisKelamin, tanggalMasuk, keluhan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getAlamat());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getTanggalMasuk());
            ps.setString(5, p.getKeluhan());
            ps.executeUpdate();
        }
    }

    public void update(Pasien p) throws SQLException {
        String sql = "UPDATE patients SET nama=?, alamat=?, jenisKelamin=?, tanggalMasuk=?, keluhan=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getAlamat());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getTanggalMasuk());
            ps.setString(5, p.getKeluhan());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Pasien> getAll() throws SQLException {
        List<Pasien> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
            	Pasien p = new Pasien();
                p.setId(rs.getInt("id"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setJenisKelamin(rs.getString("jenisKelamin"));
                p.setTanggalMasuk(rs.getString("tanggalMasuk"));
                p.setKeluhan(rs.getString("keluhan"));
                list.add(p);
            }
        }
        return list;
    }

    public Pasien getById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pasien p = new Pasien();
                    p.setId(rs.getInt("id"));
                    p.setNama(rs.getString("nama"));
                    p.setAlamat(rs.getString("alamat"));
                    p.setJenisKelamin(rs.getString("jenisKelamin"));
                    p.setTanggalMasuk(rs.getString("tanggalMasuk"));
                    p.setKeluhan(rs.getString("keluhan"));
                    return p;
                }
            }
        }
        return null;
    }
}

