/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author TrongKhoa
 */
/**
 *
 * @author TrongKhoa
 * @param <T> POJO's Type to be returned
 * @param <P> Primary key type of POJO
 */
public abstract class AbstractDAO<T, P extends Serializable>
{

    protected abstract Class getPOJOClass();

    protected abstract T markDeletedPOJO(T pojo);

    protected abstract T markAvailable(T pojo);

    public Session openSession()
    {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public T get(P key) throws HibernateException
    {
        Session session = openSession();
        T p = (T) session.get(getPOJOClass(), key);
        session.close();
        return p;
    }

    public List<T> getList(Boolean includeDeleted) throws HibernateException
    {
        Session session = openSession();
        String hql = "";
        if (includeDeleted)
        {
            hql = String.format("select obj from %s obj", getPOJOClass().getName());
        }
        else
        {
            hql = String.format("select obj from %s obj where obj.daXoa = false", getPOJOClass().getName());
        }

        Query query = session.createQuery(hql);
        List<T> list = query.list();
        session.close();
        return list;
    }

    public void saveOrUpdate(T p) throws HibernateException
    {
        Session session = openSession();
        Transaction trans = session.beginTransaction();
        try
        {
            session.saveOrUpdate(p);
            trans.commit();
        }
        catch (HibernateException ex)
        {
            trans.rollback();
            throw ex;
        }
        finally
        {
            session.close();
        }
    }

    public void delete(T p) throws HibernateException
    {
        markDeletedPOJO(p);
        saveOrUpdate(p);
    }

    public void restore(T p) throws HibernateException
    {
        markAvailable(p);
        saveOrUpdate(p);
    }

    public void deleteForever(T p) throws HibernateException
    {
        Session session = openSession();
        Transaction trans = session.beginTransaction();
        try
        {
            session.delete(p);
            trans.commit();
        }
        catch (HibernateException ex)
        {
            trans.rollback();
            throw ex;
        }
        finally
        {
            session.close();
        }
    }

    /**
     *
     * @param n Numbers of POJOs to select
     * @param field Column in database to order
     * @param descending descending?
     * @return
     */
    public List<T> getTopNPOJOsOrderedByField(int n, String field, boolean descending)
    {
        Session session = openSession();
        String hql = String.format("select obj from %s obj where ((obj.%s != null) and (obj.daXoa = false)) order by obj.%s %s", getPOJOClass().getName(), field, field, descending ? "desc" : "");
        Query query = session.createQuery(hql).setMaxResults(n);
        List<T> list = query.list();
        session.close();
        return list;
    }

    public List<T> searchPOJOs(String col, String name, int page, int nRecordsPerPage)
    {
        List<T> results;
        Session session = openSession();
        Criteria crit = session.createCriteria(getPOJOClass());
        //crit.add(Restrictions.ilike(col, "%" + name + "%"));
        Disjunction or = Restrictions.disjunction();
    	or.add(Restrictions.ilike(col, "%" + name + "%"));
    	or.add(Restrictions.ilike(col, name + "%"));
    	or.add(Restrictions.ilike(col, "%" + name  ));
    	or.add(Restrictions.ilike(col, name));
        crit.add(or);
        crit.setFirstResult((page - 1) * nRecordsPerPage);
        crit.setMaxResults(nRecordsPerPage);
        results = crit.list();
        session.close();
        return results;
    }
    
    public long countRows(String col, String name)
    {
        Session session = openSession();
        Criteria crit = session.createCriteria(getPOJOClass());
        //crit.add(Restrictions.ilike(col, "%" + name + "%"));
        
        Disjunction or = Restrictions.disjunction();
    	or.add(Restrictions.ilike(col, "%" + name + "%"));
    	or.add(Restrictions.ilike(col, name + "%"));
    	or.add(Restrictions.ilike(col, "%" + name  ));
    	or.add(Restrictions.ilike(col, name));
        crit.add(or);
        
        return (long) (crit.setProjection(Projections.rowCount()).uniqueResult());
    }
}
