package threadPool;

import javax.swing.*;
import java.util.Random;

class Task implements Runnable {
    private final int taskId;
    private final JTextArea logArea;
    private final DefaultListModel<String> taskListModel;

    public Task(int taskId, JTextArea logArea, DefaultListModel<String> taskListModel) {
        this.taskId = taskId;
        this.logArea = logArea;
        this.taskListModel = taskListModel;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            logArea.append("Task #" + taskId + " dimulai oleh " + 
                           Thread.currentThread().getName() + "\n");
            updateTaskList("Task #" + taskId + " - Running");
        });

        try {
            int processingTime = new Random().nextInt(3000) + 1000;
            Thread.sleep(processingTime);

            SwingUtilities.invokeLater(() -> {
                logArea.append("Task #" + taskId + " selesai oleh " + 
                               Thread.currentThread().getName() + " (" + 
                               processingTime + "ms)\n");
                updateTaskList("Task #" + taskId + " - Completed");
            });

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void updateTaskList(String status) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < taskListModel.size(); i++) {
                if (taskListModel.get(i).startsWith("Task #" + taskId)) {
                    taskListModel.set(i, status);
                    break;
                }
            }
        });
    }
}