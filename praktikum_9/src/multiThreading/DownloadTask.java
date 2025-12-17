package multiThreading;

public class DownloadTask extends Thread {
    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " progress: " + i + "%");
            try {
                // simulasi waktu download
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName + " selesai diunduh!");
    }
}