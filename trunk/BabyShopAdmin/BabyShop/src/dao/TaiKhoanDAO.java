package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.DoChoi;
import pojos.LoaiDoChoi;
import pojos.TaiKhoan;
import util.HibernateUtil;

public class TaiKhoanDAO {
	
	@SuppressWarnings("unchecked")
	public static List<TaiKhoan> layDanhSachTaiKhoan(int page, int nRecordsPerPage) {
        List<TaiKhoan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select tk from TaiKhoan tk left join fetch tk.loaiTaiKhoan order by tk.maTaiKhoan asc";
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
	
	public static int demSoLuongTaiKhoan()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from TaiKhoan tk";
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
	
	public static TaiKhoan layThongTinTaiKhoan(String maTaiKhoan) {
		TaiKhoan taiKhoan = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	taiKhoan = (TaiKhoan) session.get(TaiKhoan.class, maTaiKhoan);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return taiKhoan;
    }
	
	public static boolean themTaiKhoan(TaiKhoan tk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tk);
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
	
	public static boolean capNhatTaiKhoan(TaiKhoan tk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(tk);
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
	
	public static boolean danhDauXoaTaiKhoan(String maTaiKhoan) {
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(maTaiKhoan);
        if(tk == null)
        {
            return false;
        }
        tk.setDaXoa(true);
        return capNhatTaiKhoan(tk);
    }
    
    public static boolean goDanhDauXoaTaiKhoan(String maTaiKhoan) {
    	TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(maTaiKhoan);
        if(tk == null)
        {
            return false;
        }
        tk.setDaXoa(false);
        return capNhatTaiKhoan(tk);
    }
    
    public static boolean danhDauKhoaTaiKhoan(String maTaiKhoan) {
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(maTaiKhoan);
        if(tk == null)
        {
            return false;
        }
        
        tk.setDaBan(true);
        return capNhatTaiKhoan(tk);
    }
    
    public static boolean goDanhDauKhoaTaiKhoan(String maTaiKhoan) {
		TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(maTaiKhoan);
        if(tk == null)
        {
            return false;
        }
        
        tk.setDaBan(false);
        return capNhatTaiKhoan(tk);
    }
    
    @SuppressWarnings("unchecked")
	public static List<TaiKhoan> layDanhSachTaiKhoanTheoTuKhoa(int page, int nRecordsPerPage, TaiKhoan tk, String strDaXoa, String strDaBan) {
        List<TaiKhoan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(TaiKhoan.class);
            crit.addOrder(Order.asc("maTaiKhoan"));
            ganThuocTinhTimKiem(crit, tk, strDaXoa, strDaBan);
            
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
    
    public static long demSoLuongTaiKhoanTheoTuKhoa(TaiKhoan tk, String strDaXoa, String strDaBan)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(TaiKhoan.class);
            ganThuocTinhTimKiem(crit, tk, strDaXoa, strDaBan);
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
    
    public static void ganThuocTinhTimKiem(Criteria crit, TaiKhoan tk, String strDaXoa, String strDaBan )
    {
    	if(tk.getMaTaiKhoan() != "" ){
    		Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("maTaiKhoan", "%" + tk.getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("maTaiKhoan", tk.getMaTaiKhoan() + "%"));
        	or.add(Restrictions.ilike("maTaiKhoan", "%" + tk.getMaTaiKhoan()  ));
        	or.add(Restrictions.ilike("maTaiKhoan", tk.getMaTaiKhoan()));
            crit.add(or);
        }
        if(tk.getLoaiTaiKhoan() != null){
            crit.add(Restrictions.eq("loaiTaiKhoan", tk.getLoaiTaiKhoan()));
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", tk.getDaXoa()));
        }
        if(strDaBan != "")
        {
        	crit.add(Restrictions.eq("daBan", tk.getDaBan()));
        }

    }
    
    
}
