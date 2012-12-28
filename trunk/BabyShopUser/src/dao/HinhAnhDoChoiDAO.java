package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojos.HinhAnhDoChoi;

public class HinhAnhDoChoiDAO extends AbstractDAO<HinhAnhDoChoi, Long> {

	@Override
	protected Class getPOJOClass() {
		return HinhAnhDoChoi.class;
	}

	@Override
	protected HinhAnhDoChoi markDeletedPOJO(HinhAnhDoChoi pojo) {
		pojo.setDaXoa(true);
		return pojo;
	}

	@Override
	protected HinhAnhDoChoi markAvailable(HinhAnhDoChoi pojo) {
		pojo.setDaXoa(false);
		return pojo;
	}
	
	public List<HinhAnhDoChoi> getDsHinhAnhTheoMaDoChoi(Long maDoChoi)
    {
        Session session = openSession();
        String hql = "select hadc from HinhAnhDoChoi hadc where hadc.doChoi.maDoChoi=:maDoChoi";
        Query query = session.createQuery(hql);
        query.setLong("maDoChoi", maDoChoi);
        List<HinhAnhDoChoi> list = query.list();
        session.close();

        return list;
    }

}
