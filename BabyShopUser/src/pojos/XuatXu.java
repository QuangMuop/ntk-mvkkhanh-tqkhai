package pojos;
// Generated Oct 30, 2012 9:28:39 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * XuatXu generated by hbm2java
 */
public class XuatXu  implements java.io.Serializable {


     private long maXuatXu;
     private String tenXuatXu;
     private Boolean daXoa;
     private Set doChois = new HashSet(0);

    public XuatXu() {
    }

	
    public XuatXu(long maXuatXu) {
        this.maXuatXu = maXuatXu;
    }
    public XuatXu(long maXuatXu, String tenXuatXu, Boolean daXoa, Set doChois) {
       this.maXuatXu = maXuatXu;
       this.tenXuatXu = tenXuatXu;
       this.daXoa = daXoa;
       this.doChois = doChois;
    }
   
    public long getMaXuatXu() {
        return this.maXuatXu;
    }
    
    public void setMaXuatXu(long maXuatXu) {
        this.maXuatXu = maXuatXu;
    }
    public String getTenXuatXu() {
        return this.tenXuatXu;
    }
    
    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }
    public Boolean getDaXoa() {
        return this.daXoa;
    }
    
    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }
    public Set getDoChois() {
        return this.doChois;
    }
    
    public void setDoChois(Set doChois) {
        this.doChois = doChois;
    }




}

