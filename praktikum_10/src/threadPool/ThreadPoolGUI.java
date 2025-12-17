package threadPool;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class ThreadPoolGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField threadCountField = new JTextField("3", 5);
    private final JTextField taskCountField = new JTextField("20", 5);
    private final JButton startButton = new JButton("Mulai Proses");
    private final JButton clearButton = new JButton("Bersihkan Log");
    private final JTextArea logArea = new JTextArea(15, 30);
    private final JLabel statusLabel = new JLabel("Log dibersihkan. Siap untuk proses baru.");
    private final JList<String> taskList = new JList<>();
    private final DefaultListModel<String> taskListModel = new DefaultListModel<>();

    private ExecutorService threadPool;

    public ThreadPoolGUI() {
        setTitle("Aplikasi ThreadPool dengan GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        taskList.setModel(taskListModel);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Pengaturan"));
        controlPanel.add(new JLabel("Jumlah Thread:"));
        controlPanel.add(threadCountField);
        controlPanel.add(new JLabel("Jumlah Tugas:"));
        controlPanel.add(taskCountField);

        startButton.setBackground(new Color(46, 204, 113));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startProcessing());
        controlPanel.add(startButton);

        clearButton.addActionListener(e -> clearLog());
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.NORTH);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setDividerLocation(200);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status Tugas"));
        statusPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
 
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        mainSplitPane.setLeftComponent(statusPanel);
        mainSplitPane.setRightComponent(logPanel);
        add(mainSplitPane, BorderLayout.CENTER);

        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); 
    }

    private void startProcessing() {
        int threadCount;
        int taskCount;

        try {
            threadCount = Integer.parseInt(threadCountField.getText());
            taskCount = Integer.parseInt(taskCountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Input Tidak Valid", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (threadCount < 1 || taskCount < 1) {
            JOptionPane.showMessageDialog(this, "Jumlah thread dan tugas harus lebih dari 0!", "Input Tidak Valid", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clearLog();
        startButton.setEnabled(false);
        logArea.append("ThreadPool dibuat dengan " + threadCount + " worker threads\n\n");
        statusLabel.setText("Memproses " + taskCount + " tugas dengan " + threadCount + " threads...");

        threadPool = Executors.newFixedThreadPool(threadCount);

        for (int i = 1; i <= taskCount; i++) {
            taskListModel.addElement("Task #" + i + " - Waiting");
            Task task = new Task(i, logArea, taskListModel);
            threadPool.execute(task);
        }

        new Thread(() -> {
            threadPool.shutdown();
            try {
                if (threadPool.awaitTermination(5, TimeUnit.MINUTES)) {
                    SwingUtilities.invokeLater(() -> {
                        logArea.append("\n=== Semua tugas selesai ===\n");
                        statusLabel.setText("Semua tugas selesai!");
                        startButton.setEnabled(true);
                    });
                }
            } catch (InterruptedException e) {
                threadPool.shutdownNow();
                SwingUtilities.invokeLater(() -> {
                    logArea.append("\nProses terganggu!\n");
                    startButton.setEnabled(true);
                });
            }
        }).start();
    }

    private void clearLog() {
        logArea.setText("");
        taskListModel.clear();
        statusLabel.setText("Log dibersihkan. Siap untuk proses baru.");
        startButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadPoolGUI().setVisible(true);
        });
    }
}
