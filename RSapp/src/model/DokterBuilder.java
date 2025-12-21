package model;

public class DokterBuilder {

    private int id;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private String spesialis;

    public DokterBuilder id(int id) {
        this.id = id;
        return this;
    }

    public DokterBuilder nama(String nama) {
        this.nama = nama;
        return this;
    }

    public DokterBuilder alamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public DokterBuilder jenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
        return this;
    }

    public DokterBuilder spesialis(String spesialis) {
        this.spesialis = spesialis;
        return this;
    }

    public Dokter build() {
        Dokter d = new Dokter();
        d.setId(id);
        d.setNama(nama);
        d.setAlamat(alamat);
        d.setJenisKelamin(jenisKelamin);
        d.setSpesialis(spesialis);
        return d;
    }
}

