package ui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import error.ValidationException;
import model.User;
import service.LoginService;
import util.ValidationUtil;



public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setBounds(156, 11, 86, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(72, 56, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(71, 81, 233, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(72, 118, 63, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(72, 143, 233, 26);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    String userValue = txtUsername.getText();
				    String passValue = txtPassword.getText();

				    User user = new User(userValue, passValue); 

				    try {
				        ValidationUtil.validate(user);
				        LoginService loginService = new LoginService();
				        if (loginService.authenticate(user)) {
				            System.out.println("Login successful!");
				            new MainFrame().setVisible(true);
				            dispose();
				        } else {
				            System.out.println("Invalid username or password.");
				            JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
				        }
				    } catch (ValidationException | NullPointerException exception) {
				        System.out.println("Data tidak valid : " + exception.getMessage());
				        JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
				    } finally {
				        System.out.println("Selalu di eksekusi");
				    }
				}

			
		});
		btnNewButton.setBounds(117, 194, 143, 31);
		contentPane.add(btnNewButton);

	}
}