package praktikum_5;

public class MainApp {
    public static void main(String[] args) {

        Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Automatic");
        mobil.tampilkanInfo();
        mobil.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + mobil.jenisBahanBakar());
        mobil.infoKonsumsi();
        mobil.fiturMobil();

        System.out.println();

        Bus bus = new Bus("Mercedes–Benz", "Bus Pariwisata", 2018, "Eksekutif");
        bus.tampilkanInfo();
        bus.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + bus.jenisBahanBakar());
        bus.infoKonsumsi();
        System.out.println("Kapasitas Penumpang: " + bus.kapasitasPenumpang() + " penumpang");
        bus.fiturBus();

        Bus.JadwalPerjalanan jadwal = bus.new JadwalPerjalanan("Jakarta – Bandung", "08:00");
        System.out.println("Jadwal Perjalanan: Rute " + jadwal.rute + ", Waktu Berangkat: " + jadwal.waktuBerangkat);
        
        System.out.println();
        
        Pesawat pesawat = new Pesawat("Garuda", "Boeing 737", 100);
        pesawat.tampilkanInfo();
        pesawat.nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + pesawat.jenisBahanBakar());
        
    }
  }