/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import pojos.DoChoi;
import pojos.LoaiDoChoi;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author TrongKhoa
 */
public class LoaiDoChoiDAO extends AbstractDAO<LoaiDoChoi, Long>
{

    @Override
    protected Class getPOJOClass()
    {
        return LoaiDoChoi.class;
    }

    @Override
    protected LoaiDoChoi markDeletedPOJO(LoaiDoChoi pojo)
    {
        pojo.setDaXoa(true);
        return pojo;
    }

    @Override
    protected LoaiDoChoi markAvailable(LoaiDoChoi pojo)
    {
        pojo.setDaXoa(false);
        return pojo;
    }

    public List<DoChoi> layDanhSachDoChoiTheoLoaiDoChoi(Long loaiDoChoi, int page, int nRecordsPerPage)
    {
        Session ss = openSession();
        String hql = String.format("select dc from DoChoi dc where ((dc.loaiDoChoi.maLoaiDoChoi =:loaiDoChoi) and (dc.daXoa = false)) order by dc.maDoChoi asc");
        Query query = ss.createQuery(hql);
        query.setLong("loaiDoChoi", loaiDoChoi);
        query.setFirstResult((page - 1) * nRecordsPerPage);
        query.setMaxResults(nRecordsPerPage);
        List<DoChoi> dsDoChois = query.list();
        ss.close();
        return dsDoChois;
    }

    public int demSoLuongDoChoiTheoLoaiDoChoi(Long loaiDoChoi)
    {
        Session session = openSession();
        String hql = String.format("select count(*) from DoChoi dc where ((dc.loaiDoChoi.maLoaiDoChoi =:loaiDoChoi) and (dc.daXoa = false))");
        Query query = session.createQuery(hql);
        query.setLong("loaiDoChoi", loaiDoChoi);
        int result = Integer.parseInt(query.uniqueResult().toString());
        session.close();
        
        return result;
    }
}
