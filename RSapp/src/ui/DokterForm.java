package ui;

import javax.swing.*;

import DAO.DokterDAO;
import model.Dokter;
import model.DokterBuilder;

import java.awt.*;
import java.sql.SQLException;

public class DokterForm extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNama, txtAlamat, txtSpesialis;
    private JComboBox<String> cmbJenisKelamin;
    private JButton btnSimpan, btnBatal;

    private DokterDAO dokterDAO;
    private Dokter editing;

    public DokterForm(Frame owner) {
        super(owner, "Form Dokter", true);
        try {
            dokterDAO = new DokterDAO();
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
        txtSpesialis = new JTextField(20);
        cmbJenisKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
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

        c.gridx = 0; c.gridy = 3; p.add(new JLabel("Spesialis:"), c);
        c.gridx = 1; p.add(txtSpesialis, c);

        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        JPanel btns = new JPanel();
        btns.add(btnSimpan);
        btns.add(btnBatal);
        p.add(btns, c);

        add(p);

        btnSimpan.addActionListener(e -> onSave());
        btnBatal.addActionListener(e -> dispose());
    }

    public void setEditingDokter(Dokter d) {
        this.editing = d;
        txtNama.setText(d.getNama());
        txtAlamat.setText(d.getAlamat());
        txtSpesialis.setText(d.getSpesialis());
        cmbJenisKelamin.setSelectedItem(d.getJenisKelamin());
    }

    private void onSave() {
        try {
            if (editing == null) {
            	Dokter dokter = new DokterBuilder()
            	        .nama(txtNama.getText())
            	        .alamat(txtAlamat.getText())
            	        .jenisKelamin(cmbJenisKelamin.getSelectedItem().toString())
            	        .spesialis(txtSpesialis.getText())
            	        .build();

            	dokterDAO.insert(dokter);
            } else {
                editing.setNama(txtNama.getText());
                editing.setAlamat(txtAlamat.getText());
                editing.setJenisKelamin((String) cmbJenisKelamin.getSelectedItem());
                editing.setSpesialis(txtSpesialis.getText());
                dokterDAO.update(editing);
            }
            JOptionPane.showMessageDialog(this, "Tersimpan");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void showErrorAndClose(SQLException ex) {
        JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        dispose();
    }
}
