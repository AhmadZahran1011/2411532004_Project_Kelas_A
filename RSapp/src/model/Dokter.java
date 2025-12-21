package model;

public class Dokter extends Person {

    private String spesialis;

    public Dokter() {}

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getSpesialis() {
        return spesialis;
    }
}

