package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import pojos.TrangThaiDonHang;
import util.HibernateUtil;

public class TrangThaiDonHangDAO {
	@SuppressWarnings("unchecked")
	public static List<TrangThaiDonHang> layDanhSachTrangThaiDonHang() {
        List<TrangThaiDonHang> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select ttdh from TrangThaiDonHang ttdh order by ttdh.maTrangThaiDonHang asc";
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
	
	public static TrangThaiDonHang layThongTinTrangThaiDonHang(long maTrangThaiDonHang) {
		TrangThaiDonHang ttdh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	ttdh = (TrangThaiDonHang) session.get(TrangThaiDonHang.class, maTrangThaiDonHang);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return ttdh;
    }
}
