package pojos;

// Generated Dec 29, 2012 11:05:35 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * QuangCao generated by hbm2java
 */
public class QuangCao implements java.io.Serializable
{

    private long maQuangCao;
    private String tenQuangCao;
    private String hinhAnh;
    private String viTri;
    private Date batDau;
    private Date ketThuc;
    private Integer soLuongClick;
    private boolean coHieuLuc;

    public QuangCao()
    {
    }

    public QuangCao(long maQuangCao, boolean coHieuLuc)
    {
        this.maQuangCao = maQuangCao;
        this.coHieuLuc = coHieuLuc;
    }

    public QuangCao(long maQuangCao, String tenQuangCao, String hinhAnh,
            String viTri, Date batDau, Date ketThuc, Integer soLuongClick,
            boolean coHieuLuc)
    {
        this.maQuangCao = maQuangCao;
        this.tenQuangCao = tenQuangCao;
        this.hinhAnh = hinhAnh;
        this.viTri = viTri;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.soLuongClick = soLuongClick;
        this.coHieuLuc = coHieuLuc;
    }

    public long getMaQuangCao()
    {
        return this.maQuangCao;
    }

    public void setMaQuangCao(long maQuangCao)
    {
        this.maQuangCao = maQuangCao;
    }

    public String getTenQuangCao()
    {
        return this.tenQuangCao;
    }

    public void setTenQuangCao(String tenQuangCao)
    {
        this.tenQuangCao = tenQuangCao;
    }

    public String getHinhAnh()
    {
        return this.hinhAnh;
    }

    public void setHinhAnh(String hinhAnh)
    {
        this.hinhAnh = hinhAnh;
    }

    public String getViTri()
    {
        return this.viTri;
    }

    public void setViTri(String viTri)
    {
        this.viTri = viTri;
    }

    public Date getBatDau()
    {
        return this.batDau;
    }

    public void setBatDau(Date batDau)
    {
        this.batDau = batDau;
    }

    public Date getKetThuc()
    {
        return this.ketThuc;
    }

    public void setKetThuc(Date ketThuc)
    {
        this.ketThuc = ketThuc;
    }

    public Integer getSoLuongClick()
    {
        return this.soLuongClick;
    }

    public void setSoLuongClick(Integer soLuongClick)
    {
        this.soLuongClick = soLuongClick;
    }

    public boolean isCoHieuLuc()
    {
        return this.coHieuLuc;
    }

    public void setCoHieuLuc(boolean coHieuLuc)
    {
        this.coHieuLuc = coHieuLuc;
    }

}
