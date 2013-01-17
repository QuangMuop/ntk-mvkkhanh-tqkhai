package pojos;

// Generated Nov 29, 2012 10:41:24 AM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * XuatXu generated by hbm2java
 */
public class XuatXu implements java.io.Serializable
{

    private long maXuatXu;
    private String tenXuatXu;
    private Boolean daXoa;
    private Set<DoChoi> doChois = new HashSet<DoChoi>(0);

    public XuatXu()
    {
    }

    public XuatXu(long maXuatXu)
    {
        this.maXuatXu = maXuatXu;
    }

    public XuatXu(long maXuatXu, String tenXuatXu, Boolean daXoa,
            Set<DoChoi> doChois)
    {
        this.maXuatXu = maXuatXu;
        this.tenXuatXu = tenXuatXu;
        this.daXoa = daXoa;
        this.doChois = doChois;
    }

    public long getMaXuatXu()
    {
        return this.maXuatXu;
    }

    public void setMaXuatXu(long maXuatXu)
    {
        this.maXuatXu = maXuatXu;
    }

    public String getTenXuatXu()
    {
        return this.tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu)
    {
        this.tenXuatXu = tenXuatXu;
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