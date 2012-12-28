/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import pojos.LoaiDoChoi;

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
public class LoaiDoChoiDAO {
    @SuppressWarnings("unchecked")
	public static List<LoaiDoChoi> layDanhSachLoaiDoChoi() {
        List<LoaiDoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select ldc from LoaiDoChoi ldc where ldc.daXoa = false order by ldc.tenLoaiDoChoi asc";
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
	public static List<LoaiDoChoi> layDanhSachLoaiDoChoi(int page, int nRecordsPerPage) {
        List<LoaiDoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select ldc from LoaiDoChoi ldc order by ldc.maLoaiDoChoi asc";
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
    
    public static LoaiDoChoi layThongTinLoaiDoChoi(Long maLoaiDoChoi) {
        LoaiDoChoi loaiDoChoi = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            loaiDoChoi = (LoaiDoChoi) session.get(LoaiDoChoi.class, maLoaiDoChoi);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return loaiDoChoi;
    }
    
    public static int demSoLuongLoaiDoChoi()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from LoaiDoChoi ldc";
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
    
    public static boolean themLoaiDoChoi(LoaiDoChoi ldc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ldc);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.refresh(ldc);
            session.close();
        }
        return true;
    }
    
    public static boolean capNhatLoaiDoChoi(LoaiDoChoi ldc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(ldc);
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
    
    public static boolean danhDauXoaLoaiDoChoi(long maLoaiDoChoi) {
        LoaiDoChoi ldc = LoaiDoChoiDAO.layThongTinLoaiDoChoi(maLoaiDoChoi);
        if(ldc == null)
        {
            return false;
        }
        ldc.setDaXoa(true);
        return capNhatLoaiDoChoi(ldc);
    }
    
    public static boolean goDanhDauXoaLoaiDoChoi(long maLoaiDoChoi) {
    	LoaiDoChoi ldc = LoaiDoChoiDAO.layThongTinLoaiDoChoi(maLoaiDoChoi);
        if(ldc == null)
        {
            return false;
        }
        ldc.setDaXoa(false);
        return capNhatLoaiDoChoi(ldc);
    }
    
    @SuppressWarnings("unchecked")
	public static List<LoaiDoChoi> layDanhSachLoaiDoChoiTheoTuKhoa(int page, int nRecordsPerPage, LoaiDoChoi ldc, String strDaXoa) {
        List<LoaiDoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(LoaiDoChoi.class);
            crit.addOrder(Order.asc("maLoaiDoChoi"));
            ganThuocTinhTimKiem(crit, ldc, strDaXoa);
            
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
    
    public static long demSoLuongLoaiDoChoiTheoTuKhoa(LoaiDoChoi ldc, String strDaXoa)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(LoaiDoChoi.class);
            ganThuocTinhTimKiem(crit, ldc, strDaXoa);
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
    
    public static void ganThuocTinhTimKiem(Criteria crit, LoaiDoChoi ldc, String strDaXoa )
    {
    	if(ldc.getMaLoaiDoChoi() != -1 ){
            crit.add(Restrictions.eq("maLoaiDoChoi", ldc.getMaLoaiDoChoi()));
        }
        if(ldc.getTenLoaiDoChoi() != ""){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("tenLoaiDoChoi", "%" + ldc.getTenLoaiDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenLoaiDoChoi", ldc.getTenLoaiDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenLoaiDoChoi", "%" + ldc.getTenLoaiDoChoi()  ));
        	or.add(Restrictions.ilike("tenLoaiDoChoi", ldc.getTenLoaiDoChoi()));
            crit.add(or);
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", ldc.getDaXoa()));
        }

    }
}
