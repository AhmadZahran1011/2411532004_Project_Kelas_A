package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.PasienDAO;
import model.Pasien;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PasienList extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private JButton btnTambah, btnEdit, btnHapus, btnRefresh;
    private PasienDAO pasienDAO;

    public PasienList() {
        try {
            pasienDAO = new PasienDAO();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            return;
        }
        setTitle("Daftar Pasien");
        initComponents();
        loadTable();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        table = new JTable();
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnRefresh = new JButton("Refresh");

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(btnTambah);
        bottom.add(btnEdit);
        bottom.add(btnHapus);
        bottom.add(btnRefresh);
        p.add(bottom, BorderLayout.SOUTH);

        add(p);

        btnTambah.addActionListener(e -> {
            PasienForm form = new PasienForm(this);
            form.setVisible(true);
            loadTable();
        });

        btnEdit.addActionListener(e -> editSelected());
        btnHapus.addActionListener(e -> deleteSelected());
        btnRefresh.addActionListener(e -> loadTable());
    }

    private void loadTable() {
        try {
            List<Pasien> list = pasienDAO.getAll();
            String[] cols = {"ID", "Nama", "Alamat", "Jenis Kelamin", "Tanggal Masuk", "Keluhan"};
            Object[][] data = new Object[list.size()][cols.length];

            for (int i = 0; i < list.size(); i++) {
                Pasien p = list.get(i);
                data[i][0] = p.getId();
                data[i][1] = p.getNama();
                data[i][2] = p.getAlamat();
                data[i][3] = p.getJenisKelamin();
                data[i][4] = p.getTanggalMasuk();
                data[i][5] = p.getKeluhan();
            }

            DefaultTableModel model = new DefaultTableModel(data, cols) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error load: " + ex.getMessage());
        }
    }

    private Integer getSelectedId() {
        int r = table.getSelectedRow();
        if (r == -1) return null;
        return (Integer) table.getValueAt(r, 0);
    }

    private void editSelected() {
        Integer id = getSelectedId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Pilih data untuk diedit");
            return;
        }
        try {
            Pasien p = pasienDAO.getById(id);
            if (p != null) {
                PasienForm form = new PasienForm(this);
                form.setEditingPasien(p);
                form.setVisible(true);
                loadTable();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteSelected() {
        Integer id = getSelectedId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Pilih data untuk dihapus");
            return;
        }
        int conf = JOptionPane.showConfirmDialog(
                this,
                "Hapus data id=" + id + "?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );
        if (conf == JOptionPane.YES_OPTION) {
            try {
                pasienDAO.delete(id);
                loadTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error hapus: " + ex.getMessage());
            }
        }
    }
}
