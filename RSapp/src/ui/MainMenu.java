package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnPasien, btnDokter, btnLogout;

    public MainMenu() {
        setTitle("Hospicare - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        btnPasien = new JButton("Pasien");
        btnDokter = new JButton("Dokter");
        btnLogout = new JButton("Logout");

        JPanel p = new JPanel(new GridLayout(5,1,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        p.add(btnPasien); p.add(btnDokter); p.add(btnLogout);
        add(p);

        btnPasien.addActionListener(e -> {
            PasienList pl = new PasienList();
            pl.setVisible(true);
        });

        btnDokter.addActionListener(e -> {
            DokterList dl = new DokterList();
            dl.setVisible(true);
        });


        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}