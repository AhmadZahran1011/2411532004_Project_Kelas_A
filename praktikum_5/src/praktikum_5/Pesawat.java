package praktikum_5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {

    public Pesawat(String merk, String model, int tahunProduksi) {
        super(merk, model, tahunProduksi);
    }

    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Bersiap lepas landas");
    }

    public String jenisBahanBakar() {
        return "Avtur";
    }

    public void jenisPenerbangan() {
        System.out.println("Jenis Penerbangan: Komersial");
    }

    public String namaMaskapai() {
        return "Garuda Indonesia";
    }
}