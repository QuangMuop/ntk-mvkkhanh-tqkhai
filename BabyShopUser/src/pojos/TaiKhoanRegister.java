package pojos;

public class TaiKhoanRegister {
	private String hoTen;
	private String dd;
	private String mm;
	private String yy;
	private String gioiTinh;
	private String thanhPho;
	private String email;
	private String dienThoai;
	private String maTaiKhoan;
	private String matKhau;
	private String xacNhanMatKhau;
	private String verifycode;
	private String avatar;

	public TaiKhoanRegister() {
	}

	public TaiKhoanRegister(String hoTen, String ngaySinh, String thangSinh,
			String namSinh, String gioiTinh, String thanhPho, String email,
			String dienThoai, String maTaiKhoan, String matKhau,
			String xacNhanMatKhau, String verifycode, String avatar) {
		this.hoTen = hoTen;
		this.dd = ngaySinh;
		this.mm = thangSinh;
		this.yy = namSinh;
		this.gioiTinh = gioiTinh;
		this.thanhPho = thanhPho;
		this.email = email;
		this.dienThoai = dienThoai;
		this.maTaiKhoan = maTaiKhoan;
		this.matKhau = matKhau;
		this.xacNhanMatKhau = xacNhanMatKhau;
		this.verifycode = verifycode;
		this.avatar = avatar;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getXacNhanMatKhau() {
		return xacNhanMatKhau;
	}

	public void setXacNhanMatKhau(String xacNhanMatKhau) {
		this.xacNhanMatKhau = xacNhanMatKhau;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

}
