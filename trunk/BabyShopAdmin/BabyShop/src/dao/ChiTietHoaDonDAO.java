package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import pojos.ChiTietHoaDon;
import util.HibernateUtil;

public class ChiTietHoaDonDAO {
	@SuppressWarnings("unchecked")
	public static List<ChiTietHoaDon> layDanhSachChiTietHoaDonTheoMaHoaDon(long maHoaDon) {
        List<ChiTietHoaDon> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select cthd from ChiTietHoaDon cthd left join fetch cthd.hoaDon left join fetch cthd.doChoi where cthd.hoaDon.maHoaDon = " + maHoaDon + " order by cthd.maChiTietHoaDon asc";
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
	
	public static int demSoLuongChiTietHoaDon()
    {
    	int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from ChiTietHoaDon cthd";
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
}
