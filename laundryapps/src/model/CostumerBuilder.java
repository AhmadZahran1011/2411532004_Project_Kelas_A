package model;

public class CostumerBuilder {
	private String id;
	private String nama;
	private String alamat;
	private String nomorhp;
	
	public CostumerBuilder() {
		
	}

	public CostumerBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public CostumerBuilder setNama(String nama) {	
		this.nama = nama;
		return this;
	}

	public CostumerBuilder setAlamat(String alamat) {
		this.alamat = alamat;
		return this;
	}

	public CostumerBuilder setNomorhp(String nomorhp) {
		this.nomorhp = nomorhp;
		return this;
	}
	public Costumer build () {
		return new Costumer (id, nama, alamat, nomorhp);
	}
	
}
