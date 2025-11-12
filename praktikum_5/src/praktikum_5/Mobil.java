package praktikum_5;

final class Mobil extends Kendaraan implements BahanBakar {
	public String jenisTransmisi;
	
	public Mobil(String merk, String model, int tahunProduksi, String jenisTransmisi) {	
	super(merk, model, tahunProduksi);
	this.jenisTransmisi = jenisTransmisi;
	}
	
	public void nyalakanMesin() {
		System.out.println("Nyalakan Mesin: Tekan tombol start");
	}
	
	public String jenisBahanBakar() {
		return "Bensin";
	}
	
	public void fiturMobil() {
		System.out.println("Fitur Mobil: Memiliki AC dan audio premium");
	}
    
}
