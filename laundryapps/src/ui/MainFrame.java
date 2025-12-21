package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPesanan = new JButton("PESANAN");
		btnPesanan.setBounds(53, 104, 118, 47);
		contentPane.add(btnPesanan);
		
		JButton btnPengguna = new JButton("PENGGUNA");
		btnPengguna.setBounds(53, 192, 118, 40);
		contentPane.add(btnPengguna);
		
		JButton btnLayanan = new JButton("LAYANAN");
		btnLayanan.setBounds(235, 104, 105, 47);
		contentPane.add(btnLayanan);
		
		JButton btnLaporan = new JButton("LAPORAN");
		btnLaporan.setBounds(235, 192, 105, 40);
		contentPane.add(btnLaporan);
		
		JButton btnPelanggan = new JButton("PELANGGAN");
		btnPelanggan.setBounds(407, 104, 97, 47);
		contentPane.add(btnPelanggan);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setBounds(407, 192, 97, 40);
		contentPane.add(btnProfile);
		
		JButton btnKeluar = new JButton("KELUAR");
		btnKeluar.setBounds(168, 289, 238, 40);
		contentPane.add(btnKeluar);
		
		JLabel lblNewLabel = new JLabel("Laundry Disini");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(153, 20, 272, 47);
		contentPane.add(lblNewLabel);
	}
}
