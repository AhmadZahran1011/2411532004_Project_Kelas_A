package ui;

import javax.swing.*;

import DAO.UserDAO;

import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private UserDAO userDAO;

    public LoginFrame() {
        try {
            userDAO = new UserDAO();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB connection error: " + ex.getMessage());
            System.exit(1);
        }

        setTitle("Hospicare - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Username:"), c);
        c.gridx = 1; panel.add(txtUsername, c);

        c.gridx = 0; c.gridy = 1; panel.add(new JLabel("Password:"), c);
        c.gridx = 1; panel.add(txtPassword, c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        panel.add(btnLogin, c);

        add(panel);

        btnLogin.addActionListener(e -> doLogin());
        getRootPane().setDefaultButton(btnLogin);
    }

    private void doLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        try {
            if (userDAO.login(username, password)) {
                JOptionPane.showMessageDialog(this, "Login berhasil");
                SwingUtilities.invokeLater(() -> {
                    MainMenu m = new MainMenu();
                    m.setVisible(true);
                });
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah", "Login gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saat login: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
        } catch (Exception ignored){}
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

