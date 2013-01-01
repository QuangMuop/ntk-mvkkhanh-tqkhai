package pojos;

public class Product {
	private DoChoi doChoi;
	private int soLuongMua;
	private long tongTien;
	
	public Product(){
		
	}
	
	public Product(DoChoi doChoi, int soLuongMua, long tongTien){
		this.doChoi = doChoi;
		this.setSoLuongMua(soLuongMua);
		this.tongTien = tongTien;
	}

	public DoChoi getDoChoi() {
		return doChoi;
	}

	public void setDoChoi(DoChoi doChoi) {
		this.doChoi = doChoi;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}
}
