package pojos;

// Generated Nov 29, 2012 10:41:24 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LoaiDoChoi generated by hbm2java
 */
public class LoaiDoChoi implements java.io.Serializable
{

    private long maLoaiDoChoi;
    private String tenLoaiDoChoi;
    private Date ngayCapNhat;
    private Boolean daXoa;
    private Set<DoChoi> doChois = new HashSet<DoChoi>(0);

    public LoaiDoChoi()
    {
    }

    public LoaiDoChoi(long maLoaiDoChoi)
    {
        this.maLoaiDoChoi = maLoaiDoChoi;
    }

    public LoaiDoChoi(long maLoaiDoChoi, String tenLoaiDoChoi,
            Date ngayCapNhat, Boolean daXoa, Set<DoChoi> doChois)
    {
        this.maLoaiDoChoi = maLoaiDoChoi;
        this.tenLoaiDoChoi = tenLoaiDoChoi;
        this.ngayCapNhat = ngayCapNhat;
        this.daXoa = daXoa;
        this.doChois = doChois;
    }

    public long getMaLoaiDoChoi()
    {
        return this.maLoaiDoChoi;
    }

    public void setMaLoaiDoChoi(long maLoaiDoChoi)
    {
        this.maLoaiDoChoi = maLoaiDoChoi;
    }

    public String getTenLoaiDoChoi()
    {
        return this.tenLoaiDoChoi;
    }

    public void setTenLoaiDoChoi(String tenLoaiDoChoi)
    {
        this.tenLoaiDoChoi = tenLoaiDoChoi;
    }

    public Date getNgayCapNhat()
    {
        return this.ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat)
    {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Boolean getDaXoa()
    {
        return this.daXoa;
    }

    public void setDaXoa(Boolean daXoa)
    {
        this.daXoa = daXoa;
    }

    public Set<DoChoi> getDoChois()
    {
        return this.doChois;
    }

    public void setDoChois(Set<DoChoi> doChois)
    {
        this.doChois = doChois;
    }

}
