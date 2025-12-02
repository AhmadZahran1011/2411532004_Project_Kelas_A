package multiThreading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DownloadManagerGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar[] progressBars;
    private JLabel[] fileLabels;
    private JButton downloadButton;

    public DownloadManagerGUI() {
        // Konfigurasi Frame Utama
        setTitle("Download Manager App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel Utama untuk progress bar
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 baris, 1 kolom
        
        progressBars = new JProgressBar[3];
        fileLabels = new JLabel[3];
        String[] fileNames = {"File 1", "File 2", "File 3"};

        for (int i = 0; i < 3; i++) {
            JPanel fileRow = new JPanel(new BorderLayout(5, 0));
            
            fileLabels[i] = new JLabel(fileNames[i]);
            fileLabels[i].setPreferredSize(new Dimension(60, 20));
            fileRow.add(fileLabels[i], BorderLayout.WEST);

            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setStringPainted(true);
            progressBars[i].setValue(0);
            fileRow.add(progressBars[i], BorderLayout.CENTER);

            progressPanel.add(fileRow);
        }

        add(progressPanel, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        downloadButton = new JButton("Downloading");
        downloadButton.addActionListener(this::startDownloads);
        buttonPanel.add(downloadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Finalisasi
        pack();
        setSize(400, 250);
        setLocationRelativeTo(null); // Pusatkan di layar
    }

    private void startDownloads(ActionEvent e) {
        downloadButton.setEnabled(false); // Nonaktifkan tombol saat proses berjalan

        for (int i = 0; i < 3; i++) {
            progressBars[i].setValue(0);
            new FileDownloader(progressBars[i], fileLabels[i].getText()).execute();
        }
    }

    // Kelas SwingWorker untuk menjalankan download di latar belakang
    class FileDownloader extends SwingWorker<Void, Integer> {
        private JProgressBar progressBar;
        private String fileName;

        public FileDownloader(JProgressBar progressBar, String fileName) {
            this.progressBar = progressBar;
            this.fileName = fileName;
        }

        @Override
        protected Void doInBackground() throws Exception {
            // Simulasi proses download (di Worker Thread)
            for (int i = 1; i <= 10; i++) {
                int progress = i * 10;
                publish(progress); // Mengirim data ke process()
                Thread.sleep(500); // Simulasi jeda waktu
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            // Memperbarui UI (di Event Dispatch Thread / EDT)
            for (Integer progress : chunks) {
                progressBar.setValue(progress);
                System.out.println(fileName + " progress: " + progress + "%");
            }
        }

        @Override
        protected void done() {
            // Dipanggil setelah doInBackground selesai (di EDT)
            progressBar.setValue(100);
            System.out.println(fileName + " selesai diunduh!");
            
            // Cek apakah semua sudah selesai untuk mengaktifkan tombol kembali
            boolean allDone = true;
            for (JProgressBar pb : progressBars) {
                if (pb.getValue() < 100) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) {
                downloadButton.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        // Menjalankan UI di Event Dispatch Thread (disarankan oleh Swing)
        SwingUtilities.invokeLater(() -> {
            new DownloadManagerGUI().setVisible(true);
        });
    }
}
