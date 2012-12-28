package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojos.BinhLuan;
import pojos.DoChoi;
import pojos.HinhAnhDoChoi;
import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;
import util.PagingHelper;
import dao.BinhLuanDAO;
import dao.DoChoiDAO;
import dao.HinhAnhDoChoiDAO;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;

@Controller
public class ToyController {
	private DoChoiDAO doChoiDAO = new DoChoiDAO();
    private BinhLuanDAO binhLuanDao = new BinhLuanDAO();
    private HinhAnhDoChoiDAO hinhAnhDoChoiDAO = new HinhAnhDoChoiDAO();
    
	@RequestMapping(method = GET, value = "/detail", params = { "id" })
	protected ModelAndView detail(
			@RequestParam(value = "id") String strId,
			HttpServletRequest arg0,
			HttpServletResponse arg1) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product_details");
		
		long maDoChoi = 1;
        if (strId != "")
        {
        	maDoChoi = Long.parseLong(strId);    	              	
        }
        
        DoChoi doChoi = doChoiDAO.get(maDoChoi);
        List<BinhLuan> dsBinhLuan = binhLuanDao.getDsBinhLuanTheoMaDoChoi(maDoChoi);
        List<DoChoi> dsDoChoiLienQuan = doChoiDAO.getRelatedDoChois(doChoi, 5);
        List<HinhAnhDoChoi> dsHinhAnhDoChoi = hinhAnhDoChoiDAO.getDsHinhAnhTheoMaDoChoi(maDoChoi);
        
        modelAndView.addObject("doChoi", doChoi);
        modelAndView.addObject("dsBinhLuan", dsBinhLuan);
        modelAndView.addObject("dsDoChoiLienQuan", dsDoChoiLienQuan);
        modelAndView.addObject("dsHinhAnhDoChoi", dsHinhAnhDoChoi);
        
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value = "/list")
	protected ModelAndView list(
			@RequestParam(value = "loaiDoChoi", required = false) String strLoaiDoChoi,
			@RequestParam(value = "nhaSanXuat", required = false) String strNhaSanXuat,
			@RequestParam(value = "trang", required = false) String strTrang,
			HttpServletRequest arg0,
			HttpServletResponse arg1) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views_by_groups");
		
		int trang = 1;
		int soLuongDoChoiTrenTrang = 20;
		int soLuongKetQua = 0;
		if(strTrang != null && strTrang != "")
		{
			trang = Integer.parseInt(strTrang);
		}
		if(strLoaiDoChoi != null && strLoaiDoChoi != "")
		{
			long maLoaiDoChoi = Long.parseLong(strLoaiDoChoi);
			LoaiDoChoiDAO loaiDoChoiDAO = new LoaiDoChoiDAO();
            LoaiDoChoi loaiDoChoi = loaiDoChoiDAO.get(maLoaiDoChoi);          
            List<DoChoi> dsDoChoi = loaiDoChoiDAO.layDanhSachDoChoiTheoLoaiDoChoi(maLoaiDoChoi, trang, soLuongDoChoiTrenTrang);
            soLuongKetQua = loaiDoChoiDAO.demSoLuongDoChoiTheoLoaiDoChoi(maLoaiDoChoi);
            
            modelAndView.addObject("loaiDoChoi", loaiDoChoi);
            modelAndView.addObject("dsDoChoi", dsDoChoi);
		}
		
		if(strNhaSanXuat != null && strNhaSanXuat != "")
		{
			long maNhaSanXuat = Long.parseLong(strNhaSanXuat);
			NhaSanXuatDAO nhaSanXuatDAO = new NhaSanXuatDAO();
            NhaSanXuat nhaSanXuat = nhaSanXuatDAO.get(maNhaSanXuat);
            List<DoChoi> dsDoChoi = nhaSanXuatDAO.layDanhSachDoChoiTheoLoaiDoChoi(maNhaSanXuat, trang, soLuongDoChoiTrenTrang);
            soLuongKetQua = nhaSanXuatDAO.demSoLuongDoChoiTheoLoaiDoChoi(maNhaSanXuat);
            
            modelAndView.addObject("nhaSanXuat", nhaSanXuat);
            modelAndView.addObject("dsDoChoi", dsDoChoi);
		}
		
		modelAndView.addObject("soLuongKetQua", soLuongKetQua);
		modelAndView.addObject("trang", trang);
		modelAndView.addObject("soLuongDoChoiTrenTrang", soLuongDoChoiTrenTrang);

		int soLuongTrang;
		if (soLuongKetQua % soLuongDoChoiTrenTrang != 0)
        {
			soLuongTrang = 1;
        }
		else
		{
			soLuongTrang = soLuongKetQua / soLuongDoChoiTrenTrang;
			if ((soLuongKetQua % soLuongDoChoiTrenTrang) != 0)
	        {
				soLuongTrang++;
	        }
		}
        
        modelAndView.addObject("soLuongTrang", soLuongTrang);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(trang, soLuongDoChoiTrenTrang, soLuongKetQua);
        modelAndView.addObject("pageNumbers", pageNumbers);
		
		return modelAndView;
	}
}
