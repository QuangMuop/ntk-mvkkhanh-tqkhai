package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojos.ChiTietHoaDon;
import pojos.HoaDon;
import util.HibernateUtil;

public class HoaDonDAO extends AbstractDAO<HoaDon, Long>{

	@Override
	protected Class getPOJOClass() {
		// TODO Auto-generated method stub
		return HoaDon.class;
	}

	@Override
	protected HoaDon markDeletedPOJO(HoaDon pojo) {
		return null;
	}

	@Override
	protected HoaDon markAvailable(HoaDon pojo) {
		return null;
	}
	
	public List<ChiTietHoaDon> layDS(String maTaiKhoan, int page, int nRecordsPerPage) {
		List<ChiTietHoaDon> ds;

		Session session = openSession();

		String hql_hd = "from ChiTietHoaDon cthd left join fetch cthd.hoaDon where cthd.hoaDon.taiKhoan.maTaiKhoan=:maTaiKhoan and cthd.hoaDon.daXoa=:trangThai order by cthd.hoaDon.ngayLap desc";// "from HoaDon hd left join fetch hd.chiTietHoaDons where hd.taiKhoan.maTaiKhoan=:maTaiKhoan and hd.daXoa=:trangThai";
		Query query = session.createQuery(hql_hd);
		query.setString("maTaiKhoan", maTaiKhoan);
		query.setBoolean("trangThai", false);
		query.setFirstResult((page - 1) * nRecordsPerPage);
        query.setMaxResults(nRecordsPerPage);
		ds = query.list();
		
		session.close();

		return ds;
	}
	
	public int demSoLuongDonHangTheoTaiKhoan(String maTaiKhoan)
    {
        Session session = openSession();
        String hql = String.format("select count(*) from ChiTietHoaDon cthd where cthd.hoaDon.taiKhoan.maTaiKhoan=:maTaiKhoan and cthd.hoaDon.daXoa=:trangThai");
        Query query = session.createQuery(hql);
        query.setString("maTaiKhoan", maTaiKhoan);
		query.setBoolean("trangThai", false);
        int result = Integer.parseInt(query.uniqueResult().toString());
        session.close();
        
        return result;
    }

}
