package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojos.QuangCao;

public class QuangCaoDAO extends AbstractDAO
{

    @Override
    protected Class getPOJOClass()
    {
        return QuangCao.class;
    }

    @Override
    protected Object markDeletedPOJO(Object pojo)
    {
        return null;
    }

    @Override
    protected Object markAvailable(Object pojo)
    {
        return null;
    }

    public List<QuangCao> LayDanhSachQuangCaoHienTai()
    {
        Session session = openSession();
        Date nowDate = Calendar.getInstance().getTime();
        String hql = "";
        hql = String.format("select obj from %s obj where obj.coHieuLuc = true and obj.batDau <= :nowDate and obj.ketThuc >= :nowDate",
                getPOJOClass().getName());
        //and obj.ketThuc <= :nowDate
        Query query = session.createQuery(hql);
        query.setDate("nowDate", nowDate);
        List<QuangCao> list = query.list();
        session.close();
        return list;
    }
    
    public boolean tangSoLuotClickQuangCao(long maQuangCao) {
    	QuangCao qc = (QuangCao)this.get(maQuangCao);
        if(qc == null)
        {
            return false;
        }
        
        qc.setSoLuongClick(qc.getSoLuongClick() + 1);
        this.saveOrUpdate(qc);
        return true;
    }
}
