package model;

public class Pasien extends Person {

    private String tanggalMasuk;
    private String keluhan;

    public Pasien() {}

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getKeluhan() {
        return keluhan;
    }
}
