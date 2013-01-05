/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import pojos.BinhLuan;
import pojos.TaiKhoan;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author TrongKhoa
 */
public class BinhLuanDAO extends AbstractDAO<BinhLuan, Long>
{

    @Override
    protected Class getPOJOClass()
    {
        return BinhLuan.class;
    }

    @Override
    protected BinhLuan markDeletedPOJO(BinhLuan pojo)
    {
        pojo.setDaXoa(true);
        return pojo;
    }

    @Override
    protected BinhLuan markAvailable(BinhLuan pojo)
    {
        pojo.setDaXoa(false);
        return pojo;
    }

    @Override
    public List<BinhLuan> getTopNPOJOsOrderedByField(int n, String field, boolean descending)
    {
        Session session = openSession();
        String hql = String.format("select obj from %s obj left join fetch obj.taiKhoan where ((obj.%s != null) and (obj.daXoa = false)) order by obj.%s %s", getPOJOClass().getName(), field, field, descending ? "desc" : "");
        Query query = session.createQuery(hql).setMaxResults(n);
        List<BinhLuan> list = query.list();
        session.close();
        return list;
    }

    public List<BinhLuan> getDsBinhLuanTheoMaDoChoi(Long maDoChoi)
    {
        Session session = openSession();
        String hql = "select bl from BinhLuan bl left join fetch bl.taiKhoan where bl.doChoi.maDoChoi=:maDoChoi";
        Query query = session.createQuery(hql);
        query.setLong("maDoChoi", maDoChoi);
        List<BinhLuan> list = query.list();
        session.close();

        return list;
    }
    
    public int demSoLuongBinhLuanTheoMaDoChoi(Long maDoChoi)
    {
    	int result = 0;
        Session session = openSession();
        try {
            String hql = "select count(*) from BinhLuan bl where bl.doChoi.maDoChoi=:maDoChoi";
            Query query = session.createQuery(hql);
            query.setLong("maDoChoi", maDoChoi);
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
