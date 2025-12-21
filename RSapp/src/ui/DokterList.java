package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import DAO.DokterDAO;
import model.Dokter;

public class DokterList extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private JButton btnTambah, btnEdit, btnHapus, btnRefresh;
    private DokterDAO dokterDAO;

    public DokterList() {
        try {
            dokterDAO = new DokterDAO();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            return;
        }
        setTitle("Daftar Dokter");
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
            DokterForm f = new DokterForm(this);
            f.setVisible(true);
            loadTable();
        });

        btnEdit.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Pilih data");
                return;
            }
            int id = (Integer) table.getValueAt(r, 0);
            try {
                Dokter d = dokterDAO.getById(id);
                DokterForm f = new DokterForm(this);
                f.setEditingDokter(d);
                f.setVisible(true);
                loadTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnHapus.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Pilih data");
                return;
            }
            int id = (Integer) table.getValueAt(r, 0);
            int conf = JOptionPane.showConfirmDialog(
                    this,
                    "Hapus id=" + id + "?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );
            if (conf == JOptionPane.YES_OPTION) {
                try {
                    dokterDAO.delete(id);
                    loadTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        });

        btnRefresh.addActionListener(e -> loadTable());
    }

    private void loadTable() {
        try {
            List<Dokter> list = dokterDAO.getAll();
            String[] cols = {"ID", "Nama", "Alamat", "Jenis Kelamin", "Spesialis"};
            Object[][] data = new Object[list.size()][cols.length];

            for (int i = 0; i < list.size(); i++) {
                Dokter d = list.get(i);
                data[i][0] = d.getId();
                data[i][1] = d.getNama();
                data[i][2] = d.getAlamat();
                data[i][3] = d.getJenisKelamin();
                data[i][4] = d.getSpesialis();
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
}
