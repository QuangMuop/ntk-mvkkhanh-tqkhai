package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.BinhLuan;
import util.HibernateUtil;

public class BinhLuanDAO {
	@SuppressWarnings("unchecked")
	public static List<BinhLuan> layDanhSachBinhLuan(int page, int nRecordsPerPage) {
        List<BinhLuan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select bl from BinhLuan bl left join fetch bl.taiKhoan left join fetch bl.doChoi order by bl.ngayBinhLuan desc, bl.maBinhLuan asc";
            Query query = session.createQuery(hql);
            query.setFirstResult((page - 1) * nRecordsPerPage);
            query.setMaxResults(nRecordsPerPage);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
	
	public static int demSoLuongBinhLuan()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from BinhLuan bl";
            Query query = session.createQuery(hql);

            result = Integer.parseInt(query.uniqueResult().toString());
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }
	
	public static BinhLuan layThongTinBinhLuan(long maBinhLuan) {
		BinhLuan binhLuan = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	binhLuan = (BinhLuan) session.get(BinhLuan.class, maBinhLuan);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return binhLuan;
    }
	
	public static boolean capNhatBinhLuan(BinhLuan bl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(bl);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }
	
	public static boolean danhDauXoaBinhLuan(long maBinhLuan) {
		BinhLuan bl = BinhLuanDAO.layThongTinBinhLuan(maBinhLuan);
        if(bl == null)
        {
            return false;
        }
        bl.setDaXoa(true);
        return capNhatBinhLuan(bl);
    }
    
    public static boolean goDanhDauXoaBinhLuan(long maBinhLuan) {
    	BinhLuan bl = BinhLuanDAO.layThongTinBinhLuan(maBinhLuan);
        if(bl == null)
        {
            return false;
        }
        bl.setDaXoa(false);
        return capNhatBinhLuan(bl);
    }
    
    public static boolean danhDauDuyetBinhLuan(long maBinhLuan) {
		BinhLuan bl = BinhLuanDAO.layThongTinBinhLuan(maBinhLuan);
        if(bl == null)
        {
            return false;
        }
        bl.setKiemDuyet(true);
        return capNhatBinhLuan(bl);
    }
    
    public static boolean goDanhDauDuyetBinhLuan(long maBinhLuan) {
    	BinhLuan bl = BinhLuanDAO.layThongTinBinhLuan(maBinhLuan);
        if(bl == null)
        {
            return false;
        }
        bl.setKiemDuyet(false);
        return capNhatBinhLuan(bl);
    }
    
    @SuppressWarnings("unchecked")
	public static List<BinhLuan> layDanhSachBinhLuanTheoTuKhoa(int page, int nRecordsPerPage, BinhLuan bl, String strDaXoa, String strKiemDuyet) {
        List<BinhLuan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(BinhLuan.class);
            crit.addOrder(Order.desc("ngayBinhLuan"));
            crit.addOrder(Order.asc("maBinhLuan"));
            ganThuocTinhTimKiem(crit, bl, strDaXoa, strKiemDuyet);
            
            crit.setFirstResult((page - 1) * nRecordsPerPage);
            crit.setMaxResults(nRecordsPerPage);
            ds = crit.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static long demSoLuongBinhLuanTheoTuKhoa(BinhLuan bl, String strDaXoa, String strKiemDuyet)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(BinhLuan.class);
            ganThuocTinhTimKiem(crit, bl, strDaXoa, strKiemDuyet);
            crit.setProjection(Projections.rowCount());
            result =  (long)crit.uniqueResult();
            
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }
    
    public static void ganThuocTinhTimKiem(Criteria crit, BinhLuan bl, String strDaXoa, String strKiemDuyet)
    {
    	if(bl.getMaBinhLuan() != -1 ){
            crit.add(Restrictions.eq("maBinhLuan", bl.getMaBinhLuan()));
        }
        if(bl.getNgayBinhLuan() != null){
            crit.add(Restrictions.eq("ngayBinhLuan", bl.getNgayBinhLuan()));
        }
        if(bl.getTaiKhoan() != null){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", "%" + bl.getTaiKhoan().getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", bl.getTaiKhoan().getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", "%" + bl.getTaiKhoan().getMaTaiKhoan()  ));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", bl.getTaiKhoan().getMaTaiKhoan()));
            crit.add(or);
        }
        if(bl.getDoChoi() != null){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("doChoi.tenDoChoi", "%" + bl.getDoChoi().getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("doChoi.tenDoChoi", bl.getDoChoi().getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("doChoi.tenDoChoi", "%" + bl.getDoChoi().getTenDoChoi()  ));
        	or.add(Restrictions.ilike("doChoi.tenDoChoi", bl.getDoChoi().getTenDoChoi()));
            crit.add(or);
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", bl.isDaXoa()));
        }
        if(strKiemDuyet != "")
        {
        	crit.add(Restrictions.eq("kiemDuyet", bl.isKiemDuyet()));
        }
    }
}
