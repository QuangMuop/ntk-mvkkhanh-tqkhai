package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.ChiTietHoaDon;
import pojos.HoaDon;
import pojos.LoaiTaiKhoan;
import pojos.TaiKhoan;
import pojos.TrangThaiDonHang;
import propertyeditor.CartStatusPropertyEditor;
import util.DateUtil;
import util.PagingHelper;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.LoaiTaiKhoanDAO;
import dao.TaiKhoanDAO;
import dao.TrangThaiDonHangDAO;

@Controller
public class CartController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TrangThaiDonHang.class, new CartStatusPropertyEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
	}
	
	@RequestMapping(method = GET, params={"page"}) 
    protected ModelAndView list(@RequestParam(value = "page") String strPage, 
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cart");
        modelAndView.addObject("currentMenu", "cartList");
        
        int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);
        
        int nCartsInPage = 10;
        modelAndView.addObject("nCartsInPage", nCartsInPage);
        List<HoaDon> dsHoaDon = HoaDonDAO.layDanhSachHoaDon(page, nCartsInPage);
        
        modelAndView.addObject("dsHoaDon", dsHoaDon);
        
        int nResult = HoaDonDAO.demSoLuongHoaDon();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nCartsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCartsInPage;
	        if ((nResult % nCartsInPage) != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCartsInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        List<TrangThaiDonHang> dsTrangThai = TrangThaiDonHangDAO.layDanhSachTrangThaiDonHang();
        modelAndView.addObject("dsTrangThai", dsTrangThai);
        
        return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("hoaDon") HoaDon hoaDon, 
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-cart");
        modelAndView.addObject("currentMenu", "cartDetail");
        
        long id = 1;
        if (strId != "")
        {
        	id = Long.parseLong(strId);
        }
    	HoaDon hd = HoaDonDAO.layThongTinHoaDon(id);
    	List<TrangThaiDonHang> dsTrangThaiDonHang = TrangThaiDonHangDAO.layDanhSachTrangThaiDonHang();
    	List<ChiTietHoaDon> dsChiTietHoaDon = ChiTietHoaDonDAO.layDanhSachChiTietHoaDonTheoMaHoaDon(hd.getMaHoaDon());
        modelAndView.addObject("hoaDon", hd);
        modelAndView.addObject("dsTrangThaiDonHang", dsTrangThaiDonHang);
        modelAndView.addObject("dsChiTietHoaDon", dsChiTietHoaDon);
         
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	 public ModelAndView update(@ModelAttribute("hoaDon") HoaDon hoaDon, BindingResult result, HttpServletRequest arg0,
	    		HttpServletResponse arg1) throws IOException{
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-cart");
		modelAndView.addObject("currentMenu", "cartDetail");
             
		HoaDon oldHoaDon = HoaDonDAO.layThongTinHoaDon(hoaDon.getMaHoaDon());
		if(hoaDon.getTrangThaiDonHang() != null)
		{
			oldHoaDon.setTrangThaiDonHang(hoaDon.getTrangThaiDonHang());
		}
		if(hoaDon.getNgayCapNhat() != null)
		{
			oldHoaDon.setNgayCapNhat(hoaDon.getNgayCapNhat());
		}
		oldHoaDon.setDaThanhToan(hoaDon.getDaThanhToan());
		oldHoaDon.setDaXoa(hoaDon.isDaXoa());

		
       boolean kq = HoaDonDAO.capNhatHoaDon(oldHoaDon);
      
       
       if(kq == true)
       {
	       	modelAndView.addObject("hoaDon", oldHoaDon);
	        modelAndView.addObject("kq", true);
	          
       }
       else
       {
       		modelAndView.addObject("kq", false);
       }
       List<TrangThaiDonHang> dsTrangThaiDonHang = TrangThaiDonHangDAO.layDanhSachTrangThaiDonHang();
       List<ChiTietHoaDon> dsChiTietHoaDon = ChiTietHoaDonDAO.layDanhSachChiTietHoaDonTheoMaHoaDon(oldHoaDon.getMaHoaDon());
       modelAndView.addObject("dsTrangThaiDonHang", dsTrangThaiDonHang);
       modelAndView.addObject("dsChiTietHoaDon", dsChiTietHoaDon);
       return modelAndView;
   }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String delete(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode hoaDonNode = rootNode.path("maHoaDon");
		long maHoaDon = hoaDonNode.longValue();
		
        boolean status = HoaDonDAO.danhDauXoaHoaDon(maHoaDon);
    	Map<String,Object> messageResult = new HashMap<String,Object>(); 
        if(status == true)
        {
        	messageResult.put("result", "1");
        }
        else
        {
        	messageResult.put("result", "0");
        }
        return mapper.writeValueAsString(messageResult);       
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String reshow(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode hoaDonNode = rootNode.path("maHoaDon");
		long maHoaDon = hoaDonNode.longValue();
		
        boolean status = HoaDonDAO.goDanhDauXoaHoaDon(maHoaDon);
    	Map<String,Object> messageResult = new HashMap<String,Object>(); 
        if(status == true)
        {
        	messageResult.put("result", "1");
        	
        }
        else
        {
        	messageResult.put("result", "0");
        }
        return mapper.writeValueAsString(messageResult);       
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String multiDelete(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		List<Map<String, Object>> listMessageResult = new ArrayList<Map<String, Object>>();
		
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> listNode = mapper.readValue(message, new TypeReference<List<JsonNode>>(){});
		for(int i = 0; i < listNode.size(); i++)
		{
			JsonNode hoaDonNode = listNode.get(i);
			
			JsonNode valueNode = hoaDonNode.path("maHoaDon");
			long maHoaDon = valueNode.longValue();
			
			boolean status = HoaDonDAO.danhDauXoaHoaDon(maHoaDon);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maHoaDon", maHoaDon);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maHoaDon", maHoaDon);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = GET, params = { "maHoaDon", "ngayLap", "taiKhoan", "daThanhToan", "daXoa", "page" })
	protected ModelAndView search(
			@RequestParam(value = "maHoaDon", required = true) String strMaHoaDon,
			@RequestParam(value = "ngayLap", required = true) String strNgayLap,
			@RequestParam(value = "taiKhoan", required = true) String strMaTaiKhoan,
			@RequestParam(value = "trangThaiDonHang", required = true) String strMaTrangThaiDonHang,
			@RequestParam(value = "daThanhToan", required = true) String strDaThanhToan,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			@RequestParam(value = "page", required = true) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cart");
		modelAndView.addObject("currentMenu", "cartList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);

        int nCartsInPage = 10;
        modelAndView.addObject("nCartsInPage", nCartsInPage);
        
        
        //Chuan bi doi tuong HoaDon de dua vo search
        HoaDon hoaDonQuery = new HoaDon();
        long maHoaDon = -1;
        long maTrangThaiDonHang = -1;
        boolean daThanhToan = false;
        boolean daXoa = false;
        if(strMaHoaDon != "")
        {
        	maHoaDon = Long.parseLong(strMaHoaDon);
        }
        if(strNgayLap != "")
        {
        	Date ngayLap = DateUtil.convertStringToDate(strNgayLap, "yyyy-MM-dd");
        	hoaDonQuery.setNgayLap(ngayLap);
        }
        if(strMaTaiKhoan != "")
        {
        	TaiKhoan tk = new TaiKhoan();
        	tk.setMaTaiKhoan(strMaTaiKhoan);
        	hoaDonQuery.setTaiKhoan(tk);
        }
        if(strMaTrangThaiDonHang != "")
        {
        	maTrangThaiDonHang = Long.parseLong(strMaTrangThaiDonHang);
        }
        if(strDaThanhToan != "")
        {
        	daThanhToan = Boolean.parseBoolean(strDaThanhToan);
        	hoaDonQuery.setDaThanhToan(daThanhToan);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	hoaDonQuery.setDaXoa(daXoa);
        }
        TrangThaiDonHang ttdh = TrangThaiDonHangDAO.layThongTinTrangThaiDonHang(maTrangThaiDonHang);
        hoaDonQuery.setTrangThaiDonHang(ttdh);
        hoaDonQuery.setMaHoaDon(maHoaDon);
                  
        //Tìm kiếm
        List<HoaDon> dsHoaDon = HoaDonDAO.layDanhSachHoaDonTheoTuKhoa(page, nCartsInPage, hoaDonQuery, strDaXoa, strDaThanhToan);
        modelAndView.addObject("dsHoaDon", dsHoaDon);
        
        long nResult = HoaDonDAO.demSoLuongHoaDonTheoTuKhoa(hoaDonQuery, strDaXoa, strDaThanhToan);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nCartsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCartsInPage;
	        if (nResult % nCartsInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCartsInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);         
        
        String searchContent = String.format("&maHoaDon=%s&ngayLap=%s&taiKhoan=%s&trangThaiDonHang=%s&daThanhToan=%s&daXoa=%s", strMaHoaDon, strNgayLap, strMaTaiKhoan, maTrangThaiDonHang, strDaThanhToan, strDaXoa);
        modelAndView.addObject("hoaDonQuery", hoaDonQuery);
        modelAndView.addObject("searchContent", searchContent);
        List<TrangThaiDonHang> dsTrangThai = TrangThaiDonHangDAO.layDanhSachTrangThaiDonHang();
        modelAndView.addObject("dsTrangThai", dsTrangThai);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        modelAndView.addObject("daThanhToanQuery", strDaThanhToan);
        return modelAndView;
 
    }	
}
