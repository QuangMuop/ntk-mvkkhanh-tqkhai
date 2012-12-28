/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import pojos.DoChoi;

import org.hibernate.*;
import util.HibernateUtil;

import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Khanh
 */
public class DoChoiDAO {

    public static List<DoChoi> layDanhSachDoChoi(int page, int nRecordsPerPage) {
        List<DoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //String hql = "SELECT dc FROM DoChoi dc";
            String hql = "select s from DoChoi s left join fetch s.loaiDoChoi left join fetch s.xuatXu left join fetch s.nhaSanXuat order by s.maDoChoi asc";
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
    
    @SuppressWarnings("unchecked")
	public static void demSoLuongDoChoiBanRaTrongNNgay(List<Date> listNgay, List<Long> listSoLuong, int soNgay)
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select hd.ngayLap, COUNT(ct.doChoi) from HoaDon hd, ChiTietHoaDon ct where (hd.maHoaDon = ct.hoaDon.maHoaDon) and (hd.daThanhToan = true) and (ct.daXoa = false) group by hd.ngayLap order by hd.ngayLap desc ";
            Query query = session.createQuery(hql);
            query.setMaxResults(soNgay);
            List<Object[]> result = query.list();
            for (int i = 0; i < result.size(); i++) {
                Date ngay = (Date) result.get(i)[0];
                Long soLuong = (Long)result.get(i)[1];
                listNgay.add(ngay);
                listSoLuong.add(soLuong);
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
    }
    
    public static List<DoChoi> topNDoChoiBanChayNhat(int quantity) {
        List<DoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from DoChoi s where (s.soLuongDaBan IS NOT NULL) order by s.soLuongDaBan desc";
            Query query = session.createQuery(hql);
            query.setMaxResults(quantity);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static int demSoLuongDoChoi()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from DoChoi dc";
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
    
    public static DoChoi layDoChoiTheoMa(long maDoChoi) {
        DoChoi dc = new DoChoi();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //String hql = "SELECT dc FROM DoChoi dc";
            String hql = "select s from DoChoi s left join fetch s.loaiDoChoi left join fetch s.xuatXu left join fetch s.nhaSanXuat where s.maDoChoi=" + maDoChoi;
            Query query = session.createQuery(hql);
            dc = (DoChoi) query.uniqueResult();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dc;
    }
    
    public static boolean themDoChoi(DoChoi dc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dc);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.refresh(dc);
            session.close();
        }
        return true;
    }

    public static boolean capNhatDoChoi(DoChoi dc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(dc);
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
    
    public static boolean danhDauXoaDoChoi(long maDoChoi) {
        DoChoi dc = DoChoiDAO.layDoChoiTheoMa(maDoChoi);
        if(dc == null)
        {
            return false;
        }
        dc.setDaXoa(true);
        return capNhatDoChoi(dc);
    }
    
    public static boolean goDanhDauXoaDoChoi(long maDoChoi) {
        DoChoi dc = DoChoiDAO.layDoChoiTheoMa(maDoChoi);
        if(dc == null)
        {
            return false;
        }
        dc.setDaXoa(false);
        return capNhatDoChoi(dc);
    }
    
    @SuppressWarnings("unchecked")
	public static List<DoChoi> layDanhSachDoChoiTheoTuKhoa(int page, int nRecordsPerPage, DoChoi dc, String strDaXoa) {
        List<DoChoi> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	Criteria crit = session.createCriteria(DoChoi.class);
            crit.addOrder(Order.asc("maDoChoi"));
            ganThuocTinhTimKiem(crit, dc, strDaXoa);
            
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
    
    public static long demSoLuongDoChoiTheoTuKhoa(DoChoi dc, String strDaXoa)
    {
    	long result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria crit = session.createCriteria(DoChoi.class);
            ganThuocTinhTimKiem(crit, dc, strDaXoa);
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
    
    public static void ganThuocTinhTimKiem(Criteria crit, DoChoi dc, String strDaXoa )
    {
    	if(dc.getMaDoChoi() != -1 ){
            crit.add(Restrictions.eq("maDoChoi", dc.getMaDoChoi()));
        }
        if(dc.getTenDoChoi() != ""){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("tenDoChoi", "%" + dc.getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenDoChoi", dc.getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenDoChoi", "%" + dc.getTenDoChoi()  ));
        	or.add(Restrictions.ilike("tenDoChoi", dc.getTenDoChoi()));
            crit.add(or);
        }
        if(dc.getLoaiDoChoi() != null){
            crit.add(Restrictions.eq("loaiDoChoi", dc.getLoaiDoChoi()));
        }
        if(dc.getTinhTrang() != ""){
        	crit.add(Restrictions.ilike("tinhTrang", dc.getTinhTrang()));
        }
        if(strDaXoa != "")
        {
        	crit.add(Restrictions.eq("daXoa", dc.isDaXoa()));
        }

    }
}
