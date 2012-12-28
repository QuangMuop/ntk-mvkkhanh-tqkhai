package pojos;

import java.math.BigDecimal;

public class DoChoiQuery {
	private String tenDoChoi;
	private LoaiDoChoi loaiDoChoi;
	private NhaSanXuat nhaSanXuat;
	private long giaMin;
	private long giaMax;
	
	public String getTenDoChoi() {
		return tenDoChoi;
	}
	public void setTenDoChoi(String tenDoChoi) {
		this.tenDoChoi = tenDoChoi;
	}
	public LoaiDoChoi getLoaiDoChoi() {
		return loaiDoChoi;
	}
	public void setLoaiDoChoi(LoaiDoChoi loaiDoChoi) {
		this.loaiDoChoi = loaiDoChoi;
	}
	public NhaSanXuat getNhaSanXuat() {
		return nhaSanXuat;
	}
	public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}
	public long getGiaMin() {
		return giaMin;
	}
	public void setGiaMin(long giaMin) {
		this.giaMin = giaMin;
	}
	public long getGiaMax() {
		return giaMax;
	}
	public void setGiaMax(long giaMax) {
		this.giaMax = giaMax;
	}
	
}
