package pojos;
// Generated Oct 30, 2012 9:28:39 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * TuKhoaDoChoi generated by hbm2java
 */
public class TuKhoaDoChoi  implements java.io.Serializable {


     private long maTuKhoa;
     private DoChoi doChoi;
     private String tuKhoa;
     private Date ngayCapNhat;

    public TuKhoaDoChoi() {
    }

	
    public TuKhoaDoChoi(long maTuKhoa, DoChoi doChoi) {
        this.maTuKhoa = maTuKhoa;
        this.doChoi = doChoi;
    }
    public TuKhoaDoChoi(long maTuKhoa, DoChoi doChoi, String tuKhoa, Date ngayCapNhat) {
       this.maTuKhoa = maTuKhoa;
       this.doChoi = doChoi;
       this.tuKhoa = tuKhoa;
       this.ngayCapNhat = ngayCapNhat;
    }
   
    public long getMaTuKhoa() {
        return this.maTuKhoa;
    }
    
    public void setMaTuKhoa(long maTuKhoa) {
        this.maTuKhoa = maTuKhoa;
    }
    public DoChoi getDoChoi() {
        return this.doChoi;
    }
    
    public void setDoChoi(DoChoi doChoi) {
        this.doChoi = doChoi;
    }
    public String getTuKhoa() {
        return this.tuKhoa;
    }
    
    public void setTuKhoa(String tuKhoa) {
        this.tuKhoa = tuKhoa;
    }
    public Date getNgayCapNhat() {
        return this.ngayCapNhat;
    }
    
    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }




}


