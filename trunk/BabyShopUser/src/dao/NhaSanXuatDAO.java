/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.AbstractDAO;
import java.util.List;
import pojos.DoChoi;
import pojos.NhaSanXuat;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TrongKhoa
 */
public class NhaSanXuatDAO extends AbstractDAO<NhaSanXuat, Long>
{

    @Override
    protected Class getPOJOClass()
    {
        return NhaSanXuat.class;
    }

    @Override
    protected NhaSanXuat markDeletedPOJO(NhaSanXuat pojo)
    {
        pojo.setDaXoa(true);
        return pojo;
    }

    @Override
    protected NhaSanXuat markAvailable(NhaSanXuat pojo)
    {
        pojo.setDaXoa(false);
        return pojo;
    }

    public int demSoLuongDoChoiTheoLoai(Long maNhaSanXuat)
    {
        Session ss = openSession();
        String hql = String.format("select count(*) from DoChoi dc where ((dc.nhaSanXuat.maNhaSanXuat =:maNhaSanXuat) and (dc.daXoa = false)) order by dc.maDoChoi asc");
        Query query = ss.createQuery(hql);
        query.setLong("maNhaSanXuat", maNhaSanXuat);
        long soLuong = (Long) query.uniqueResult();
        ss.close();
        return (int) soLuong;
    }

    public List<DoChoi> layDanhSachDoChoiTheoLoaiDoChoi(Long loaiNhaSanXuat, int page, int nRecordsPerPage)
    {
        Session ss = openSession();
        String hql = String.format("select dc from DoChoi dc where ((dc.nhaSanXuat.maNhaSanXuat =:loaiNhaSanXuat) and (dc.daXoa = false))");
        Query query = ss.createQuery(hql);
        query.setLong("loaiNhaSanXuat", loaiNhaSanXuat);
        query.setFirstResult((page - 1) * nRecordsPerPage);
        query.setMaxResults(nRecordsPerPage);
        List<DoChoi> dsDoChois = query.list();
        ss.close();
        return dsDoChois;
    }

    public int demSoLuongDoChoiTheoLoaiDoChoi(Long loaiNhaSanXuat)
    {
        Session session = openSession();
        String hql = String.format("select count(*) from DoChoi dc where ((dc.nhaSanXuat.maNhaSanXuat =:loaiNhaSanXuat) and (dc.daXoa = false))");
        Query query = session.createQuery(hql);
        query.setLong("loaiNhaSanXuat", loaiNhaSanXuat);
        int result = Integer.parseInt(query.uniqueResult().toString());
        session.close();
        
        return result;
    }
}
