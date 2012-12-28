package pojos;

import java.math.BigDecimal;
import java.util.Date;

public class DoChoiUpload {
	private long maDoChoi;
    private String tenDoChoi;
    private String loaiDoChoi;
    private String nhaSanXuat;
    private String xuatXu;
    private String hinhAnh;
    private BigDecimal giaBanBanDau;
    private BigDecimal giaBanHienTai;
    private Integer giamGia;
    private String tinhTrang;
    private Integer soLuongBan;
    private Integer soLuongXem;
    private Integer soLuongTon;
    private Integer soLuongDaBan;
    private String ngayTiepNhan;
    private String ngayCapNhat;
    private String moTa;
    private boolean daXoa;





    public DoChoiUpload()
    {
    }


    public DoChoiUpload(long maDoChoi, String loaiDoChoi, String nhaSanXuat,
    		String xuatXu, String tenDoChoi, String moTa, BigDecimal giaBanBanDau, BigDecimal giaBanHienTai,
            Integer soLuongBan, Integer soLuongXem, Integer soLuongTon, Integer soLuongDaBan, Integer giamGia, String tinhTrang, String ngayTiepNhan, String ngayCapNhat, String hinhAnh, boolean daXoa)
    {
        this.maDoChoi = maDoChoi;
        this.tenDoChoi = tenDoChoi;
        this.loaiDoChoi = loaiDoChoi;
        this.nhaSanXuat = nhaSanXuat;
        this.xuatXu = xuatXu;
        this.hinhAnh = hinhAnh;
        this.giaBanBanDau = giaBanBanDau;
        this.giaBanHienTai = giaBanHienTai;
        this.giamGia = giamGia;
        this.tinhTrang = tinhTrang;
        this.soLuongBan = soLuongBan;
        this.soLuongXem = soLuongXem;
        this.soLuongTon = soLuongTon;
        this.soLuongDaBan = soLuongDaBan;
        this.ngayTiepNhan = ngayTiepNhan;
        this.ngayCapNhat = ngayCapNhat;
        this.moTa = moTa;
        this.daXoa = daXoa;


    }

    public long getMaDoChoi()
    {
        return this.maDoChoi;
    }

    public void setMaDoChoi(long maDoChoi)
    {
        this.maDoChoi = maDoChoi;
    }
    
    public String getTenDoChoi()
    {
        return this.tenDoChoi;
    }

    public void setTenDoChoi(String tenDoChoi)
    {
        this.tenDoChoi = tenDoChoi;
    }


    public String getLoaiDoChoi()
    {
        return this.loaiDoChoi;
    }

    public void setLoaiDoChoi(String loaiDoChoi)
    {
        this.loaiDoChoi = loaiDoChoi;
    }

    public String getNhaSanXuat()
    {
        return this.nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat)
    {
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getXuatXu()
    {
        return this.xuatXu;
    }

    public void setXuatXu(String xuatXu)
    {
        this.xuatXu = xuatXu;
    }
    
    
    public String getHinhAnh()
    {
        return this.hinhAnh;
    }

    public void setHinhAnh(String hinhAnh)
    {
        this.hinhAnh = hinhAnh;
    }
   
    public BigDecimal getGiaBanBanDau()
    {
        return this.giaBanBanDau;
    }

    public void setGiaBanBanDau(BigDecimal giaBanBanDau)
    {
        this.giaBanBanDau = giaBanBanDau;
    }
    
    public BigDecimal getGiaBanHienTai()
    {
        return this.giaBanHienTai;
    }

    public void setGiaBanHienTai(BigDecimal giaBanHienTai)
    {
        this.giaBanHienTai = giaBanHienTai;
    }
    
    public Integer getGiamGia()
    {
        return this.giamGia;
    }

    public void setGiamGia(Integer giamGia)
    {
        this.giamGia = giamGia;
    }
    
    public String getTinhTrang()
    {
        return this.tinhTrang;
    }

    public void setTinhTrang(String tinhTrang)
    {
        this.tinhTrang = tinhTrang;
    }
    
    public String getMoTa()
    {
        return this.moTa;
    }
    
    public Integer getSoLuongBan()
    {
        return this.soLuongBan;
    }

    public void setSoLuongBan(Integer soLuongBan)
    {
        this.soLuongBan = soLuongBan;
    }
    
    public Integer getSoLuongXem()
    {
        return this.soLuongXem;
    }

    public void setSoLuongXem(Integer soLuongXem)
    {
        this.soLuongXem = soLuongXem;
    }
    
    public Integer getSoLuongTon()
    {
        return this.soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon)
    {
        this.soLuongTon = soLuongTon;
    }

    public Integer getSoLuongDaBan()
    {
        return this.soLuongDaBan;
    }

    public void setSoLuongDaBan(Integer soLuongDaBan)
    {
        this.soLuongDaBan = soLuongDaBan;
    }
    
    public String getNgayTiepNhan()
    {
        return this.ngayTiepNhan;
    }

    public void setNgayTiepNhan(String ngayTiepNhan)
    {
        this.ngayTiepNhan = ngayTiepNhan;
    }

    public String getNgayCapNhat()
    {
        return this.ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat)
    {
        this.ngayCapNhat = ngayCapNhat;
    }
    
    public void setMoTa(String moTa)
    {
        this.moTa = moTa;
    }
    
    public boolean isDaXoa()
    {
        return this.daXoa;
    }

    public void setDaXoa(boolean daXoa)
    {
        this.daXoa = daXoa;
    }
}
