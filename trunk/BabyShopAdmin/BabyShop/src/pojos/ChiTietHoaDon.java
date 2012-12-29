package pojos;

// Generated Nov 29, 2012 10:41:24 AM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * ChiTietHoaDon generated by hbm2java
 */
public class ChiTietHoaDon implements java.io.Serializable
{

    private long maChiTietHoaDon;
    private DoChoi doChoi;
    private HoaDon hoaDon;
    private int soLuong;
    private BigDecimal donGia;
    private Date ngayCapNhat;
    private boolean daXoa;

    public ChiTietHoaDon()
    {
    }

    public ChiTietHoaDon(long maChiTietHoaDon, DoChoi doChoi, HoaDon hoaDon,
            int soLuong, BigDecimal donGia, boolean daXoa)
    {
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.doChoi = doChoi;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.daXoa = daXoa;
    }

    public ChiTietHoaDon(long maChiTietHoaDon, DoChoi doChoi, HoaDon hoaDon,
            int soLuong, BigDecimal donGia, Date ngayCapNhat, boolean daXoa)
    {
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.doChoi = doChoi;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayCapNhat = ngayCapNhat;
        this.daXoa = daXoa;
    }

    public long getMaChiTietHoaDon()
    {
        return this.maChiTietHoaDon;
    }

    public void setMaChiTietHoaDon(long maChiTietHoaDon)
    {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }

    public DoChoi getDoChoi()
    {
        return this.doChoi;
    }

    public void setDoChoi(DoChoi doChoi)
    {
        this.doChoi = doChoi;
    }

    public HoaDon getHoaDon()
    {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon)
    {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong()
    {
        return this.soLuong;
    }

    public void setSoLuong(int soLuong)
    {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia()
    {
        return this.donGia;
    }

    public void setDonGia(BigDecimal donGia)
    {
        this.donGia = donGia;
    }

    public Date getNgayCapNhat()
    {
        return this.ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat)
    {
        this.ngayCapNhat = ngayCapNhat;
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