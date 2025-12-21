package ui;

import javax.swing.*;

import DAO.PasienDAO;
import model.Pasien;
import model.PasienBuilder;

import java.awt.*;
import java.sql.SQLException;

public class PasienForm extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNama, txtAlamat, txtTanggalMasuk;
    private JComboBox<String> cmbJenisKelamin;
    private JTextArea txtKeluhan;
    private JButton btnSimpan, btnBatal;

    private PasienDAO pasienDAO;
    private Pasien editingPasien = null;

    public PasienForm(Frame owner) {
        super(owner, "Form Pasien", true);
        try {
            pasienDAO = new PasienDAO();
        } catch (SQLException ex) {
            showErrorAndClose(ex);
        }
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtNama = new JTextField(20);
        txtAlamat = new JTextField(20);
        cmbJenisKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        txtTanggalMasuk = new JTextField(12);
        txtKeluhan = new JTextArea(4, 20);
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);

        c.gridx = 0; c.gridy = 0; p.add(new JLabel("Nama:"), c);
        c.gridx = 1; p.add(txtNama, c);

        c.gridx = 0; c.gridy = 1; p.add(new JLabel("Alamat:"), c);
        c.gridx = 1; p.add(txtAlamat, c);

        c.gridx = 0; c.gridy = 2; p.add(new JLabel("Jenis Kelamin:"), c);
        c.gridx = 1; p.add(cmbJenisKelamin, c);

        c.gridx = 0; c.gridy = 3; p.add(new JLabel("Tanggal Masuk:"), c);
        c.gridx = 1; p.add(txtTanggalMasuk, c);

        c.gridx = 0; c.gridy = 4; p.add(new JLabel("Keluhan:"), c);
        c.gridx = 1; p.add(new JScrollPane(txtKeluhan), c);

        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSimpan);
        btnPanel.add(btnBatal);
        p.add(btnPanel, c);

        add(p);

        btnSimpan.addActionListener(e -> onSave());
        btnBatal.addActionListener(e -> dispose());
    }

    public void setEditingPasien(Pasien p) {
        this.editingPasien = p;
        txtNama.setText(p.getNama());
        txtAlamat.setText(p.getAlamat());
        cmbJenisKelamin.setSelectedItem(p.getJenisKelamin());
        txtTanggalMasuk.setText(p.getTanggalMasuk());
        txtKeluhan.setText(p.getKeluhan());
    }

    private void onSave() {
        try {
        	if (editingPasien == null) {
        	Pasien pasien = new PasienBuilder()
        	        .nama(txtNama.getText())
        	        .alamat(txtAlamat.getText())
        	        .jenisKelamin(cmbJenisKelamin.getSelectedItem().toString())
        	        .tanggalMasuk(txtTanggalMasuk.getText())
        	        .keluhan(txtKeluhan.getText())
        	        .build();

        	pasienDAO.insert(pasien);
        	}
        	else {
                editingPasien.setNama(txtNama.getText());
                editingPasien.setAlamat(txtAlamat.getText());
                editingPasien.setJenisKelamin((String) cmbJenisKelamin.getSelectedItem());
                editingPasien.setTanggalMasuk(txtTanggalMasuk.getText());
                editingPasien.setKeluhan(txtKeluhan.getText());
                pasienDAO.update(editingPasien);
            }
            JOptionPane.showMessageDialog(this, "Data tersimpan");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error menyimpan: " + ex.getMessage());
        }
    }

    private void showErrorAndClose(SQLException ex) {
        JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        dispose();
    }
}
