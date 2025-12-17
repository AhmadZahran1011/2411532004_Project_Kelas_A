package multiThreading;

public class DownloadLambdaApp {

    // Definisikan logika download tanpa parameter
    private static Runnable createDownloadJob(String fileName) {
        return () -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(fileName + " progress: " + i + "%");
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(fileName + " selesai diunduh!");
        };
    }

    public static void main(String[] args) throws InterruptedException {
        
        // Buat Runnable secara langsung di dalam deklarasi Thread
        Thread f1 = new Thread(createDownloadJob("File-1"), "Download-Thread-1");
        Thread f2 = new Thread(createDownloadJob("File-2"), "Download-Thread-2");
        Thread f3 = new Thread(createDownloadJob("File-3"), "Download-Thread-3");

        f1.start();
        f2.start();
        f3.start();

        System.out.println("\nDownloading (using Lambda)...");
        
        f1.join();
        f2.join();
        f3.join();

        System.out.println("Semua file selesai diunduh (Lambda)!");
    }
}
