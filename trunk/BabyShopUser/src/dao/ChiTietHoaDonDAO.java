package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojos.ChiTietHoaDon;
import util.HibernateUtil;

public class ChiTietHoaDonDAO extends AbstractDAO<ChiTietHoaDon, Long>{

	@Override
	protected Class getPOJOClass() {
		// TODO Auto-generated method stub
		return ChiTietHoaDon.class;
	}

	@Override
	protected ChiTietHoaDon markDeletedPOJO(ChiTietHoaDon pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ChiTietHoaDon markAvailable(ChiTietHoaDon pojo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ChiTietHoaDon layCTHD(long maChiTietHoaDon) {
        List<ChiTietHoaDon> dshd;

        Session session = openSession();
        String hql = "select cthd from ChiTietHoaDon cthd where cthd.maChiTietHoaDon=:maChiTietHoaDon";

        Query query = session.createQuery(hql);
        query.setLong("maChiTietHoaDon", maChiTietHoaDon);

        dshd = query.list();
        session.close();

        return dshd.get(0);
    }

}
