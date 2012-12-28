package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import pojos.LoaiTaiKhoan;
import pojos.TaiKhoan;

import util.HibernateUtil;

public class LoaiTaiKhoanDAO {
	public static List<LoaiTaiKhoan> layDanhSachLoaiTaiKhoan() {
        List<LoaiTaiKhoan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select ltk from LoaiTaiKhoan ltk order by ltk.tenLoaiTaiKhoan asc";
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
	
	public static LoaiTaiKhoan layThongTinLoaiTaiKhoan(long maLoaiTaiKhoan) {
		LoaiTaiKhoan loaiTaiKhoan = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	loaiTaiKhoan = (LoaiTaiKhoan) session.get(LoaiTaiKhoan.class, maLoaiTaiKhoan);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return loaiTaiKhoan;
    }
}
