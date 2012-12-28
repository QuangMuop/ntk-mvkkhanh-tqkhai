/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import pojos.DoChoi;
import pojos.HinhAnhDoChoi;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author Khanh
 */
public class HinhAnhDoChoiDAO {
    @SuppressWarnings("unchecked")
	public static List<HinhAnhDoChoi> layHinhAnhTheoMaDoChoi(long maDoChoi)
    {
        List<HinhAnhDoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select s from HinhAnhDoChoi s left join fetch s.doChoi where s.doChoi.maDoChoi=" + maDoChoi + " and s.daXoa = false order by s.maHinhAnh asc";
            Query query = session.createQuery(hql);
            //query.setFirstResult(0);
            //query.setMaxResults(4);
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
	public static List<HinhAnhDoChoi> layDayDuHinhAnhTheoMaDoChoi(long maDoChoi)
    {
        List<HinhAnhDoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select s from HinhAnhDoChoi s left join fetch s.doChoi where s.doChoi.maDoChoi=" + maDoChoi + " order by s.maHinhAnh asc";
            Query query = session.createQuery(hql);
            //query.setFirstResult(0);
            //query.setMaxResults(4);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static HinhAnhDoChoi layHinhAnhTheoMa(long maHinhAnh) {
        HinhAnhDoChoi hadc = new HinhAnhDoChoi();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from HinhAnhDoChoi s left join fetch s.doChoi where s.maHinhAnh=" + maHinhAnh;
            Query query = session.createQuery(hql);
            hadc = (HinhAnhDoChoi) query.uniqueResult();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return hadc;
    }
    
    public static boolean themHinhAnhDoChoi(HinhAnhDoChoi hinhAnhDoChoi) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(hinhAnhDoChoi);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.refresh(hinhAnhDoChoi);
            session.close();
        }
        return true;
    }
    
    public static boolean capNhatHinhAnhDoChoi(HinhAnhDoChoi hadc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hadc);
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
}
