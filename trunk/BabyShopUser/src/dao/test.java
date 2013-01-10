package dao;

import java.util.Date;
import java.util.List;

import pojos.DoChoi;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoChoiDAO doChoiDAO = new DoChoiDAO();
		DoChoi doChoi_1869 = doChoiDAO.get(1869L);
		doChoi_1869.setNgayTiepNhan(new Date("2013/01/09"));
		doChoiDAO.saveOrUpdate(doChoi_1869);
		
        List<DoChoi> dsTopDoChoiMoiNhat = doChoiDAO.layTop10DoChoiMoiNhat();
        System.out.println("-----Bắt đầu in mã đồ chơi---------------");
        for(int i = 0 ; i < dsTopDoChoiMoiNhat.size(); i++)
        {
        	System.out.println(dsTopDoChoiMoiNhat.get(i).getMaDoChoi());
        	System.out.println(dsTopDoChoiMoiNhat.get(i).getNgayTiepNhan());
        }
        System.out.println("-----In ra đồ chơi mã 1869---------------");
        System.out.println(doChoiDAO.get(1869L).getNgayTiepNhan());
	}

}
