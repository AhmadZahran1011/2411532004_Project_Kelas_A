package model;

public class PasienBuilder {

    private int id;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private String tanggalMasuk;
    private String keluhan;

    public PasienBuilder id(int id) {
        this.id = id;
        return this;
    }

    public PasienBuilder nama(String nama) {
        this.nama = nama;
        return this;
    }

    public PasienBuilder alamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public PasienBuilder jenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
        return this;
    }

    public PasienBuilder tanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
        return this;
    }

    public PasienBuilder keluhan(String keluhan) {
        this.keluhan = keluhan;
        return this;
    }

    public Pasien build() {
    	Pasien p = new Pasien();
        p.setId(id);
        p.setNama(nama);
        p.setAlamat(alamat);
        p.setJenisKelamin(jenisKelamin);
        p.setTanggalMasuk(tanggalMasuk);
        p.setKeluhan(keluhan);
        return p;
    }
}
