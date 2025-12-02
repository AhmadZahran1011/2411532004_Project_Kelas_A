package model;

public class Costumer {
	private String id;
	private String nama;
	private String alamat;
	private String nomorhp;
	
	public Costumer (String id, String nama, String alamat, String nomorhp) {
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.nomorhp = nomorhp;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public String getNomorhp() {
		return nomorhp;
	}
		
}


