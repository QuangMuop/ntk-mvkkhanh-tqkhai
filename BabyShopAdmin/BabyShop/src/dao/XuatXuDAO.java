/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import pojos.XuatXu;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Khanh
 */
public class XuatXuDAO {
    public static List<XuatXu> layDanhSachXuatXu() {
        List<XuatXu> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //String hql = "SELECT dc FROM DoChoi dc";
            String hql="select s from XuatXu s";
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
    
    public static XuatXu layThongTinXuatXu(Long maXuatXu) {
        XuatXu xuatXu = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            xuatXu = (XuatXu) session.get(XuatXu.class, maXuatXu);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return xuatXu;
    }
}
