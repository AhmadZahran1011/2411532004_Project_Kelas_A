package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import DAO.*;
import model.Costumer;
import model.CostumerBuilder;
import java.util.*;
import table.TableCostumer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public String id;
    List<Costumer> ls;

    CostumerRepo costumerRepo = CostumerRepo.getInstance();

    private JTextField txtCostumerName;
    private JTextField txtCostumerAddress;
    private JTextField txtCostumerPhone;
    private JTable tableCostumers;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CostumerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 502, 441);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Pelanggan");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(178, 10, 123, 40);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nama");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(41, 54, 65, 22);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Alamat");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1.setBounds(41, 104, 65, 22);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("No Hp");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_2.setBounds(41, 161, 65, 22);
        contentPane.add(lblNewLabel_1_2);

        txtCostumerName = new JTextField();
        txtCostumerName.setBounds(131, 59, 263, 22);
        contentPane.add(txtCostumerName);
        txtCostumerName.setColumns(10);

        txtCostumerAddress = new JTextField();
        txtCostumerAddress.setColumns(10);
        txtCostumerAddress.setBounds(131, 109, 263, 22);
        contentPane.add(txtCostumerAddress);

        txtCostumerPhone = new JTextField();
        txtCostumerPhone.setColumns(10);
        txtCostumerPhone.setBounds(131, 161, 263, 22);
        contentPane.add(txtCostumerPhone);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 280, 468, 114);
        contentPane.add(scrollPane);

        tableCostumers = new JTable();
        scrollPane.setViewportView(tableCostumers);

        tableCostumers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableCostumers.getSelectedRow();
                if (row != -1) {
                    id = tableCostumers.getValueAt(row, 0).toString();
                    txtCostumerName.setText(tableCostumers.getValueAt(row, 1).toString());
                    txtCostumerAddress.setText(tableCostumers.getValueAt(row, 2).toString());
                    txtCostumerPhone.setText(tableCostumers.getValueAt(row, 3).toString());
                }
            }
        });

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Costumer costumer = new CostumerBuilder()
                        .setNama(txtCostumerName.getText())
                        .setAlamat(txtCostumerAddress.getText())
                        .setNomorhp(txtCostumerPhone.getText())
                        .build();
                costumerRepo.save(costumer);
                reset();
                loadTable();
            }
        });
        btnSimpan.setBounds(130, 250, 85, 21);
        contentPane.add(btnSimpan);

        JButton btnUpdate = new JButton("Ubah");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu!");
                    return;
                }

                Costumer costumer = new CostumerBuilder()
                        .setId(id)
                        .setNama(txtCostumerName.getText())
                        .setAlamat(txtCostumerAddress.getText())
                        .setNomorhp(txtCostumerPhone.getText())
                        .build();

                costumerRepo.update(costumer);
                reset();
                loadTable();
            }
        });
        btnUpdate.setBounds(230, 250, 85, 21);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data untuk dihapus!");
                    return;
                }

                costumerRepo.delete(id);
                reset();
                loadTable();
                id = null;
            }
        });
        btnDelete.setBounds(330, 250, 85, 21);
        contentPane.add(btnDelete);

        JButton btnReset = new JButton("Batal");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                id = null;
            }
        });
        btnReset.setBounds(20, 250, 85, 21);
        contentPane.add(btnReset);
    }

    public void loadTable() {
        ls = costumerRepo.show();
        TableCostumer tc = new TableCostumer(ls);
        tableCostumers.setModel((TableModel) tc);
        tableCostumers.getTableHeader().setVisible(true);
    }

    public void reset() {
        txtCostumerName.setText("");
        txtCostumerAddress.setText("");
        txtCostumerPhone.setText("");
    }
}
