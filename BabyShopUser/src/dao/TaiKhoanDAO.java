package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.TaiKhoan;
import util.HibernateUtil;

public class TaiKhoanDAO extends AbstractDAO{

	@Override
	protected Class getPOJOClass() {
		return TaiKhoan.class;
	}

	@Override
	protected Object markDeletedPOJO(Object pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object markAvailable(Object pojo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TaiKhoan dangNhapTK(String maTaiKhoan, String matKhau) {
        List<TaiKhoan> ds;

        Session session = openSession();
        String hql = "select tk from TaiKhoan tk where tk.maTaiKhoan=:maTaiKhoan";

        Query query = session.createQuery(hql);
        query.setString("maTaiKhoan", maTaiKhoan);
        
        ds = query.list();
        session.close();

        if (ds.size() == 1) {
            if (ds.get(0).getMatKhau().equals(matKhau)) {
                return ds.get(0);
            }
        }

        return null;
    }
	
	public boolean dangKyTK(TaiKhoan taikhoan) {
        Session session = openSession();		
        Transaction tx = session.beginTransaction();
        session.save(taikhoan);
        tx.commit();
        session.close();
        return false;
    }

}
