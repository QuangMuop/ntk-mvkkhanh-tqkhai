package pojos;

public class ThanhToan {
	private String nguoiNhan;
	private String congTy;
	private String diaChiNhanChinh;
	private String diaChiNhanPhu;
	private String thanhPho;
	private String maBuuChinh;
	private String email;
	private String soDienThoai;
	private String ghiChu;
	private Boolean status;
	
	public ThanhToan(){}
	
	public ThanhToan(String nguoiNhan, String congTy, String diaChiNhanChinh, String diaChiNhanPhu, 
			String thanhPho, String maBuuChinh, String email, String soDienThoai, String ghiChu, Boolean status){
		this.nguoiNhan = nguoiNhan;		
		this.congTy = congTy;
		this.diaChiNhanChinh = diaChiNhanChinh;
		this.diaChiNhanPhu = diaChiNhanPhu;
		this.thanhPho = thanhPho;
		this.maBuuChinh = maBuuChinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.ghiChu = ghiChu;
		this.status = status;
	}
	
	public String getNguoiNhan() {
		return nguoiNhan;
	}
	public void setNguoiNhan(String nguoiNhan) {
		this.nguoiNhan = nguoiNhan;
	}
	public String getCongTy() {
		return congTy;
	}
	public void setCongTy(String congTy) {
		this.congTy = congTy;
	}
	public String getDiaChiNhanChinh() {
		return diaChiNhanChinh;
	}
	public void setDiaChiNhanChinh(String diaChiNhanChinh) {
		this.diaChiNhanChinh = diaChiNhanChinh;
	}
	public String getDiaChiNhanPhu() {
		return diaChiNhanPhu;
	}
	public void setDiaChiNhanPhu(String diaChiNhanPhu) {
		this.diaChiNhanPhu = diaChiNhanPhu;
	}
	public String getThanhPho() {
		return thanhPho;
	}
	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}
	public String getMaBuuChinh() {
		return maBuuChinh;
	}
	public void setMaBuuChinh(String maBuuChinh) {
		this.maBuuChinh = maBuuChinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
