/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pojos.DoChoi;
import pojos.DoChoiQuery;
import util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author TrongKhoa
 */
public class DoChoiDAO extends AbstractDAO<DoChoi, Long>
{

    @Override
    protected Class getPOJOClass()
    {
        return DoChoi.class;
    }

    @Override
    protected DoChoi markDeletedPOJO(DoChoi pojo)
    {
        pojo.setDaXoa(true);
        return pojo;
    }

    @Override
    protected DoChoi markAvailable(DoChoi pojo)
    {
        pojo.setDaXoa(false);
        return pojo;
    }

    @Override
    public DoChoi get(Long key) throws HibernateException
    {
        DoChoi doChoi = null;
        Session ss = openSession();
        try
        {
            String hql = "select dc from DoChoi dc left join fetch dc.loaiDoChoi left join fetch dc.xuatXu left join fetch dc.nhaSanXuat where dc.maDoChoi=:maDoChoi";
            Query query = ss.createQuery(hql);
            query.setLong("maDoChoi", key);
            doChoi = (DoChoi) query.uniqueResult();
        }
        catch (HibernateException ex)
        {
            System.err.println(ex);
            throw ex;
        }
        finally
        {
            ss.close();
        }
        return doChoi;
    }

    public List<DoChoi> getRelatedDoChois(DoChoi doChoi, int n)
    {
        List<DoChoi> dsDoChoiLienQuan = new ArrayList<DoChoi>();
        Session ss = openSession();
        try
        {
            String hql = "select dc from DoChoi dc where dc.loaiDoChoi.maLoaiDoChoi=:maLoaiDoChoi";
            Query query = ss.createQuery(hql);
            query.setLong("maLoaiDoChoi", doChoi.getLoaiDoChoi().getMaLoaiDoChoi());
            query.setMaxResults(n);
            dsDoChoiLienQuan = query.list();
        }
        catch (HibernateException ex)
        {
            System.err.println(ex);
            throw ex;
        }
        finally
        {
            ss.close();
        }
        return dsDoChoiLienQuan;
    }
    
    public List<DoChoi> advancedSearchDoChoi(int page, int nRecordsPerPage, DoChoiQuery doChoiQuery)
    {
    	List<DoChoi> dsDoChoiLienQuan = new ArrayList<DoChoi>();
    	Session ss = openSession();
    	try {
        	Criteria crit = ss.createCriteria(DoChoi.class);
            crit.addOrder(Order.asc("giaBanHienTai"));
            searchByInstanceCriteria(crit, doChoiQuery);
            
            crit.setFirstResult((page - 1) * nRecordsPerPage);
            crit.setMaxResults(nRecordsPerPage);
            dsDoChoiLienQuan = crit.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            ss.close();
        }
        return dsDoChoiLienQuan;
    }
    
    public long countAdvancedSearchDoChoi(DoChoiQuery doChoiQuery)
    {
    	long result = 0;
    	Session ss = openSession();
        try {
            Criteria crit = ss.createCriteria(DoChoi.class);
            searchByInstanceCriteria(crit, doChoiQuery);
            crit.setProjection(Projections.rowCount());
            result =  (long)crit.uniqueResult();
            
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
        	ss.close();
        }
        return result;
    }
    
    public void searchByInstanceCriteria(Criteria crit, DoChoiQuery doChoiQuery)
    {
        if(doChoiQuery.getTenDoChoi() != "" && doChoiQuery.getTenDoChoi() != null){
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.ilike("tenDoChoi", "%" + doChoiQuery.getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenDoChoi", doChoiQuery.getTenDoChoi() + "%"));
        	or.add(Restrictions.ilike("tenDoChoi", "%" + doChoiQuery.getTenDoChoi()  ));
        	or.add(Restrictions.ilike("tenDoChoi", doChoiQuery.getTenDoChoi()));
            crit.add(or);
        }
        if(doChoiQuery.getLoaiDoChoi() != null){
            crit.add(Restrictions.eq("loaiDoChoi", doChoiQuery.getLoaiDoChoi()));
        }
        if(doChoiQuery.getNhaSanXuat() != null){
            crit.add(Restrictions.eq("nhaSanXuat", doChoiQuery.getNhaSanXuat()));
        }
        if(doChoiQuery.getGiaMin() != -1 && doChoiQuery.getGiaMax() != -1 && doChoiQuery.getGiaMin() != -2 && doChoiQuery.getGiaMax() != -2)
        {
        	Disjunction or = Restrictions.disjunction();
        	or.add(Restrictions.eq("giaBanHienTai", BigDecimal.valueOf(doChoiQuery.getGiaMin())));
        	or.add(Restrictions.eq("giaBanHienTai", BigDecimal.valueOf(doChoiQuery.getGiaMax())));
        	or.add(Restrictions.between("giaBanHienTai", BigDecimal.valueOf(doChoiQuery.getGiaMin()), BigDecimal.valueOf(doChoiQuery.getGiaMax())));
        	crit.add(or);
        }
        if( (doChoiQuery.getGiaMin() == -1 || doChoiQuery.getGiaMin() == -2 ) && (doChoiQuery.getGiaMax() != -1 && doChoiQuery.getGiaMax() != -2))
        {
        	crit.add(Restrictions.le("giaBanHienTai", BigDecimal.valueOf(doChoiQuery.getGiaMax())));
        }
        if((doChoiQuery.getGiaMin() != -1 && doChoiQuery.getGiaMin() != -2) && (doChoiQuery.getGiaMax() == -1 || doChoiQuery.getGiaMax() == -2 ))
        {
        	crit.add(Restrictions.ge("giaBanHienTai", BigDecimal.valueOf(doChoiQuery.getGiaMin())));
        }
        crit.add(Restrictions.eq("daXoa", false));
    }
    
    public List<DoChoi> layTop10DoChoiMoiNhat()
    {	
    	List<DoChoi> ds = null;
        Session session = openSession();
        try {
        	Criteria crit = session.createCriteria(DoChoi.class);
            crit.add(Restrictions.eq("daXoa", Boolean.FALSE));
            crit.add(Restrictions.isNotNull("ngayTiepNhan"));
            crit.add(Restrictions.ilike("tinhTrang", "Còn hàng"));
            crit.addOrder(Order.desc("ngayTiepNhan"));
            crit.setFirstResult(0);
            crit.setMaxResults(10);
            ds = crit.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public List<DoChoi> layTop10DoChoiBanChayNhat()
    {	
    	List<DoChoi> ds = null;
        Session session = openSession();
        try {
        	Criteria crit = session.createCriteria(DoChoi.class);
            crit.add(Restrictions.eq("daXoa", Boolean.FALSE));
            crit.add(Restrictions.isNotNull("ngayTiepNhan"));
            crit.add(Restrictions.ilike("tinhTrang", "Còn hàng"));
            crit.addOrder(Order.desc("soLuongDaBan"));
            crit.setFirstResult(0);
            crit.setMaxResults(10);
            ds = crit.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}