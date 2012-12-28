package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojos.DoChoi;
import pojos.DoChoiQuery;
import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;
import util.PagingHelper;

import dao.DoChoiDAO;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;

@Controller
public class HomeController {
	private DoChoiDAO doChoiDAO = new DoChoiDAO();
	
	@RequestMapping(method = GET, value="/index")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		List<DoChoi> dsTopDoChoiMoiNhat = doChoiDAO.getTopNPOJOsOrderedByField(10, "ngayTiepNhan", false);
		List<DoChoi> dsTopDoChoiBanChayNhat = doChoiDAO.getTopNPOJOsOrderedByField(10, "soLuongDaBan", false);
		
		modelAndView.addObject("dsTopDoChoiMoiNhat", dsTopDoChoiMoiNhat);
		modelAndView.addObject("dsTopDoChoiBanChayNhat", dsTopDoChoiBanChayNhat);
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value = "/search", params = {"trang", "searchText"})
	protected ModelAndView search(
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "trang", required = false) String strTrang,
			HttpServletRequest arg0,
			HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("basic_search");
		
		int trang = 1;
        if (strTrang != null && strTrang != "")
        {
            trang = Integer.parseInt(strTrang);
        }
        modelAndView.addObject("trang", trang);
        modelAndView.addObject("searchText", searchText);

        long soLuongKetQua = doChoiDAO.countRows("tenDoChoi", searchText);
        int soLuongDoChoiTrenTrang = 20;
        modelAndView.addObject("soLuongKetQua", soLuongKetQua);
        modelAndView.addObject("soLuongDoChoiTrenTrang", soLuongDoChoiTrenTrang);
        
        if (soLuongKetQua == 0)
        {
        	modelAndView.addObject("dsDoChoiTimKiem", null);
        	modelAndView.addObject("pageNumbers", null);
        	modelAndView.addObject("soLuongTrang", 0);
            return modelAndView;
        }   
        
        long soLuongTrang;
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

        List<DoChoi> dsDoChoiTimKiem = doChoiDAO.searchPOJOs("tenDoChoi", searchText, trang, soLuongDoChoiTrenTrang);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(trang, soLuongDoChoiTrenTrang, soLuongKetQua);
        modelAndView.addObject("dsDoChoiTimKiem", dsDoChoiTimKiem);
        modelAndView.addObject("pageNumbers", pageNumbers);
        
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value = "/goAdvancedSearch")
	protected ModelAndView goAdvancedSearch() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("advanced_search");
		
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value="/advancedSearch", params = { "tenDoChoi",
			"loaiDoChoi", "nhaSanXuat", "giaMin", "giaMax", "trang" })
	protected ModelAndView advancedSearch(
			@RequestParam(value = "trang", required = true) Integer trang,
			@RequestParam(value = "tenDoChoi", required = true) String tenDoChoi,
			@RequestParam(value = "loaiDoChoi", required = true) String strMaLoaiDoChoi,
			@RequestParam(value = "nhaSanXuat", required = true) String strMaNhaSanXuat,
			@RequestParam(value = "giaMin", required = true) String strGiaMin,
			@RequestParam(value = "giaMax", required = true) String strGiaMax,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("advanced_search");
		
		//Thiết lập
		if(trang == null)
		{
			trang = 1;
		}
        
        modelAndView.addObject("trang", trang);

        int soLuongDoChoiTrenTrang = 20;
        modelAndView.addObject("soLuongDoChoiTrenTrang", soLuongDoChoiTrenTrang);
        
        DoChoiQuery doChoiQuery = new DoChoiQuery();
        long maLoaiDoChoi = -1;
        long maNhaSanXuat = -1;
        long giaMin = -2; //undefined
        long giaMax = -2; //undefined
        if(strMaLoaiDoChoi != "")
        {
        	maLoaiDoChoi = Long.parseLong(strMaLoaiDoChoi);
        }
        if(strMaNhaSanXuat != "")
        {
        	maNhaSanXuat = Long.parseLong(strMaNhaSanXuat);
        }
        LoaiDoChoiDAO ldcDAO = new LoaiDoChoiDAO();
        NhaSanXuatDAO nsxDAO = new NhaSanXuatDAO();
        LoaiDoChoi ldc = ldcDAO.get(maLoaiDoChoi);
        NhaSanXuat nsx = nsxDAO.get(maNhaSanXuat);
        if(strGiaMin != "" && !strGiaMin.equalsIgnoreCase("NoLimit"))
        {
        	giaMin = Long.parseLong(strGiaMin);
        }
        else if (strGiaMin.equalsIgnoreCase("NoLimit"))
        {
        	giaMin = -1;
        }
        
        if(strGiaMax != "" && !strGiaMax.equalsIgnoreCase("NoLimit"))
        {
        	giaMax = Long.parseLong(strGiaMax);
        }
        
        else if (strGiaMax.equalsIgnoreCase("NoLimit"))
        {
        	giaMax = -1;
        }
        
        if( (giaMin >= giaMax ) && (giaMin != -1) && (giaMax != -1))
        {
        	giaMin = -1;
        	giaMax = -1;
        }
        
        doChoiQuery.setTenDoChoi(tenDoChoi);
        doChoiQuery.setLoaiDoChoi(ldc);
        doChoiQuery.setNhaSanXuat(nsx);
        doChoiQuery.setGiaMin(giaMin);
        doChoiQuery.setGiaMax(giaMax);
        
        //Tìm kiếm
        List<DoChoi> dsDoChoiTimKiem = doChoiDAO.advancedSearchDoChoi(trang, soLuongDoChoiTrenTrang, doChoiQuery);      
        
        long soLuongKetQua = doChoiDAO.countAdvancedSearchDoChoi(doChoiQuery);
        modelAndView.addObject("soLuongKetQua", soLuongKetQua);
                     
        if (soLuongKetQua == 0)
        {
        	modelAndView.addObject("dsDoChoiTimKiem", null);
        	modelAndView.addObject("pageNumbers", null);
        	modelAndView.addObject("soLuongTrang", 0);
        	modelAndView.addObject("doChoiQuery", doChoiQuery);
            return modelAndView;
        }     
        
        long soLuongTrang;
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
        modelAndView.addObject("dsDoChoiTimKiem", dsDoChoiTimKiem);
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        //Show lại các input search
        String searchContent = String.format("&tenDoChoi=%s&loaiDoChoi=%s&nhaSanXuat=%s&giaMin=%s&giaMax=%s", tenDoChoi, maLoaiDoChoi, maNhaSanXuat, strGiaMin, strGiaMax);
        modelAndView.addObject("doChoiQuery", doChoiQuery);
        modelAndView.addObject("searchContent", searchContent);
		
		return modelAndView;
	}
}
