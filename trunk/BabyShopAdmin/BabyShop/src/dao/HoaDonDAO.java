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

import pojos.HoaDon;
import util.HibernateUtil;

public class HoaDonDAO {
	@SuppressWarnings("unchecked")
	public static List<HoaDon> layDanhSachHoaDon(int page, int nRecordsPerPage) {
        List<HoaDon> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select hd from HoaDon hd left join fetch hd.taiKhoan left join fetch hd.trangThaiDonHang order by hd.ngayLap desc, hd.maHoaDon asc";
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
		
	public static int demSoLuongHoaDon()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from HoaDon hd";
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
	
	public static HoaDon layThongTinHoaDon(long maHoaDon) {
		HoaDon hoaDon = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	hoaDon = (HoaDon) session.get(HoaDon.class, maHoaDon);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return hoaDon;
    }
	
	public static boolean capNhatHoaDon(HoaDon hd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hd);
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
	
	public static boolean danhDauXoaHoaDon(long maHoaDon) {
		HoaDon hd = HoaDonDAO.layThongTinHoaDon(maHoaDon);
        if(hd == null)
        {
            return false;
        }
        hd.setDaXoa(true);
        return capNhatHoaDon(hd);
    }
    
    public static boolean goDanhDauXoaHoaDon(long maHoaDon) {
    	HoaDon hd = HoaDonDAO.layThongTinHoaDon(maHoaDon);
        if(hd == null)
        {
            return false;
        }
        hd.setDaXoa(false);
        return capNhatHoaDon(hd);
    }
    
    @SuppressWarnings("unchecked")
	public static List<HoaDon> layDanhSachHoaDonTheoTuKhoa(int page, int nRecordsPerPage, HoaDon hd, String strDaXoa, String strDaThanhToan) {
        List<HoaDon> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(HoaDon.class);
            crit.addOrder(Order.desc("ngayLap"));
            crit.addOrder(Order.asc("maHoaDon"));
            ganThuocTinhTimKiem(crit, hd, strDaXoa, strDaThanhToan);
            
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
    
    public static long demSoLuongHoaDonTheoTuKhoa(HoaDon hd, String strDaXoa, String strDaThanhToan)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(HoaDon.class);
            ganThuocTinhTimKiem(crit, hd, strDaXoa, strDaThanhToan);
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
    
    public static void ganThuocTinhTimKiem(Criteria crit, HoaDon hd, String strDaXoa, String strDaThanhToan)
    {
    	if(hd.getMaHoaDon() != -1 ){
            crit.add(Restrictions.eq("maHoaDon", hd.getMaHoaDon()));
        }
        if(hd.getNgayLap() != null){
            crit.add(Restrictions.eq("ngayLap", hd.getNgayLap()));
        }
        if(hd.getTaiKhoan() != null){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", "%" + hd.getTaiKhoan().getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", hd.getTaiKhoan().getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", "%" + hd.getTaiKhoan().getMaTaiKhoan()  ));
        	or.add(Restrictions.ilike("taiKhoan.maTaiKhoan", hd.getTaiKhoan().getMaTaiKhoan()));
            crit.add(or);
        }
        if(hd.getTrangThaiDonHang() != null){
            crit.add(Restrictions.eq("trangThaiDonHang", hd.getTrangThaiDonHang()));
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", hd.isDaXoa()));
        }
        if(strDaThanhToan != "")
        {
        	crit.add(Restrictions.eq("daThanhToan", hd.getDaThanhToan()));
        }
    }
}
