/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import pojos.NhaSanXuat;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

/**
 *
 * @author Khanh
 */
public class NhaSanXuatDAO {
    @SuppressWarnings("unchecked")
	public static List<NhaSanXuat> layDanhSachNhaSanXuat() {
        List<NhaSanXuat> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select nsx from NhaSanXuat nsx where nsx.daXoa = false order by nsx.tenNhaSanXuat asc";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    @SuppressWarnings("unchecked")
	public static List<NhaSanXuat> layDanhSachNhaSanXuat(int page, int nRecordsPerPage) {
        List<NhaSanXuat> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select nsx from NhaSanXuat nsx order by nsx.maNhaSanXuat asc";
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
    
    public static int demSoLuongNhaSanXuat()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from NhaSanXuat nsx";
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
    
    public static NhaSanXuat layThongTinNhaSanXuat(Long maNhaSanXuat) {
        NhaSanXuat nhaSanXuat = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            nhaSanXuat = (NhaSanXuat) session.get(NhaSanXuat.class, maNhaSanXuat);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return nhaSanXuat;
    }
    
    public static boolean themNhaSanXuat(NhaSanXuat nsx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(nsx);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.refresh(nsx);
            session.close();
        }
        return true;
    }
    
    public static boolean capNhatNhaSanXuat(NhaSanXuat nsx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nsx);
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
    
    public static boolean danhDauXoaNhaSanXuat(long maNhaSanXuat) {
        NhaSanXuat nsx = NhaSanXuatDAO.layThongTinNhaSanXuat(maNhaSanXuat);
        if(nsx == null)
        {
            return false;
        }
        nsx.setDaXoa(true);
        return capNhatNhaSanXuat(nsx);
    }
    
    public static boolean goDanhDauXoaNhaSanXuat(long maNhaSanXuat) {
    	NhaSanXuat nsx = NhaSanXuatDAO.layThongTinNhaSanXuat(maNhaSanXuat);
        if(nsx == null)
        {
            return false;
        }
        nsx.setDaXoa(false);
        return capNhatNhaSanXuat(nsx);
    }
    
    @SuppressWarnings("unchecked")
	public static List<NhaSanXuat> layDanhSachNhaSanXuatTheoTuKhoa(int page, int nRecordsPerPage, NhaSanXuat nsx, String strDaXoa) {
        List<NhaSanXuat> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(NhaSanXuat.class);
            crit.addOrder(Order.asc("maNhaSanXuat"));
            ganThuocTinhTimKiem(crit, nsx, strDaXoa);
            
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
    
    public static long demSoLuongNhaSanXuatTheoTuKhoa(NhaSanXuat nsx, String strDaXoa)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(NhaSanXuat.class);
            ganThuocTinhTimKiem(crit, nsx, strDaXoa);
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
    
    public static void ganThuocTinhTimKiem(Criteria crit, NhaSanXuat nsx, String strDaXoa )
    {
    	if(nsx.getMaNhaSanXuat() != -1 ){
            crit.add(Restrictions.eq("maNhaSanXuat", nsx.getMaNhaSanXuat()));
        }
        if(nsx.getTenNhaSanXuat() != ""){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("tenNhaSanXuat", "%" + nsx.getTenNhaSanXuat() + "%"));
        	or.add(Restrictions.ilike("tenNhaSanXuat", nsx.getTenNhaSanXuat() + "%"));
        	or.add(Restrictions.ilike("tenNhaSanXuat", "%" + nsx.getTenNhaSanXuat()  ));
        	or.add(Restrictions.ilike("tenNhaSanXuat", nsx.getTenNhaSanXuat()));
            crit.add(or);
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", nsx.isDaXoa()));
        }

    }
}
