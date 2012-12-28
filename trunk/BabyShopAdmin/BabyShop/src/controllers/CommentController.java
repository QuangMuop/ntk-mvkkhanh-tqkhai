package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

import pojos.BinhLuan;
import pojos.ChiTietHoaDon;
import pojos.DoChoi;
import pojos.HoaDon;
import pojos.TaiKhoan;
import pojos.TrangThaiDonHang;
import util.DateUtil;
import util.PagingHelper;
import dao.BinhLuanDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.TrangThaiDonHangDAO;


@Controller
public class CommentController {
	@RequestMapping(method = GET, params={"page"}) 
    protected ModelAndView list(@RequestParam(value = "page") String strPage, 
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("comment");
        modelAndView.addObject("currentMenu", "commentList");
        
        int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);
        
        int nCommentsInPage = 10;
        modelAndView.addObject("nCartsInPage", nCommentsInPage);
        List<BinhLuan> dsBinhLuan = BinhLuanDAO.layDanhSachBinhLuan(page, nCommentsInPage);
        
        modelAndView.addObject("dsBinhLuan", dsBinhLuan);
        
        int nResult = BinhLuanDAO.demSoLuongBinhLuan();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nCommentsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCommentsInPage;
	        if ((nResult % nCommentsInPage) != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCommentsInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
           
        return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("binhLuan") BinhLuan binhLuan,
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-comment");
        modelAndView.addObject("currentMenu", "commentDetail");
        
        long id = 1;
        if (strId != "")
        {
        	id = Long.parseLong(strId);
        }
    	BinhLuan bl = BinhLuanDAO.layThongTinBinhLuan(id);
        modelAndView.addObject("binhLuan", bl);
         
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	 public ModelAndView update(@ModelAttribute("binhLuan") BinhLuan binhLuan, BindingResult result, HttpServletRequest arg0,
	    		HttpServletResponse arg1) throws IOException{
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-comment");
		modelAndView.addObject("currentMenu", "commentDetail");
            
		BinhLuan oldBinhLuan = BinhLuanDAO.layThongTinBinhLuan(binhLuan.getMaBinhLuan());
		if(binhLuan.getNoiDung() != null)
		{
			oldBinhLuan.setNoiDung(binhLuan.getNoiDung());
		}
		oldBinhLuan.setKiemDuyet(binhLuan.isKiemDuyet());
		oldBinhLuan.setDaXoa(binhLuan.isDaXoa());

		
      boolean kq = BinhLuanDAO.capNhatBinhLuan(oldBinhLuan);
     
      
      if(kq == true)
      {
	       	modelAndView.addObject("binhLuan", oldBinhLuan);
	        modelAndView.addObject("kq", true);
	          
      }
      else
      {
      		modelAndView.addObject("kq", false);
      }

      return modelAndView;
  }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String delete(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode binhLuanNode = rootNode.path("maBinhLuan");
		long maBinhLuan = binhLuanNode.longValue();
		
        boolean status = BinhLuanDAO.danhDauXoaBinhLuan(maBinhLuan);
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
		JsonNode binhLuanNode = rootNode.path("maBinhLuan");
		long maBinhLuan = binhLuanNode.longValue();
		
        boolean status = BinhLuanDAO.goDanhDauXoaBinhLuan(maBinhLuan);
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
			JsonNode binhLuanNode = listNode.get(i);
			
			JsonNode valueNode = binhLuanNode.path("maBinhLuan");
			long maBinhLuan = valueNode.longValue();
			
			boolean status = BinhLuanDAO.danhDauXoaBinhLuan(maBinhLuan);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maBinhLuan", maBinhLuan);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maBinhLuan", maBinhLuan);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);        
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String approve(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode binhLuanNode = rootNode.path("maBinhLuan");
		long maBinhLuan = binhLuanNode.longValue();
		
        boolean status = BinhLuanDAO.danhDauDuyetBinhLuan(maBinhLuan);
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
    protected @ResponseBody String disapprove(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode binhLuanNode = rootNode.path("maBinhLuan");
		long maBinhLuan = binhLuanNode.longValue();
		
        boolean status = BinhLuanDAO.goDanhDauDuyetBinhLuan(maBinhLuan);
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
    protected @ResponseBody String multiApprove(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		List<Map<String, Object>> listMessageResult = new ArrayList<Map<String, Object>>();
		
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> listNode = mapper.readValue(message, new TypeReference<List<JsonNode>>(){});
		for(int i = 0; i < listNode.size(); i++)
		{
			JsonNode binhLuanNode = listNode.get(i);
			
			JsonNode valueNode = binhLuanNode.path("maBinhLuan");
			long maBinhLuan = valueNode.longValue();
			
			boolean status = BinhLuanDAO.danhDauDuyetBinhLuan(maBinhLuan);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maBinhLuan", maBinhLuan);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maBinhLuan", maBinhLuan);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);        
    }
	
	@RequestMapping(method = GET, params = { "maBinhLuan", "ngayBinhLuan", "taiKhoan", "doChoi", "kiemDuyet", "daXoa", "page" })
	protected ModelAndView search(
			@RequestParam(value = "maBinhLuan", required = true) String strMaBinhLuan,
			@RequestParam(value = "ngayBinhLuan", required = true) String strNgayBinhLuan,
			@RequestParam(value = "taiKhoan", required = true) String strMaTaiKhoan,
			@RequestParam(value = "doChoi", required = true) String strTenDoChoi,
			@RequestParam(value = "kiemDuyet", required = true) String strKiemDuyet,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			@RequestParam(value = "page", required = true) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("comment");
		modelAndView.addObject("currentMenu", "commentList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);

        int nCommentsInPage = 10;
        modelAndView.addObject("nCommentsInPage", nCommentsInPage);
        
        
        //Chuan bi doi tuong BinhLuan de dua vo search
        BinhLuan binhLuanQuery = new BinhLuan();
        long maBinhLuan = -1;
        boolean kiemDuyet = false;
        boolean daXoa = false;
        if(strMaBinhLuan != "")
        {
        	maBinhLuan = Long.parseLong(strMaBinhLuan);
        }
        if(strNgayBinhLuan != "")
        {
        	Date ngayBinhLuan = DateUtil.convertStringToDate(strNgayBinhLuan, "yyyy-MM-dd");
        	binhLuanQuery.setNgayBinhLuan(ngayBinhLuan);
        }
        if(strMaTaiKhoan != "")
        {
        	TaiKhoan tk = new TaiKhoan();
        	tk.setMaTaiKhoan(strMaTaiKhoan);
        	binhLuanQuery.setTaiKhoan(tk);
        }
        if(strTenDoChoi != "")
        {
        	DoChoi dc = new DoChoi();
        	dc.setTenDoChoi(strTenDoChoi);
        	binhLuanQuery.setDoChoi(dc);
        }
        if(strKiemDuyet != "")
        {
        	kiemDuyet = Boolean.parseBoolean(strKiemDuyet);
        	binhLuanQuery.setKiemDuyet(kiemDuyet);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	binhLuanQuery.setDaXoa(daXoa);
        }
        binhLuanQuery.setMaBinhLuan(maBinhLuan);
                  
        //Tìm kiếm
        List<BinhLuan> dsBinhLuan = BinhLuanDAO.layDanhSachBinhLuanTheoTuKhoa(page, nCommentsInPage, binhLuanQuery, strDaXoa, strKiemDuyet);
        modelAndView.addObject("dsBinhLuan", dsBinhLuan);
        
        long nResult = BinhLuanDAO.demSoLuongBinhLuanTheoTuKhoa(binhLuanQuery, strDaXoa, strKiemDuyet);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nCommentsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCommentsInPage;
	        if (nResult % nCommentsInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCommentsInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);         
        
        String searchContent = String.format("&maBinhLuan=%s&ngayBinhLuan=%s&taiKhoan=%s&doChoi=%s&kiemDuyet=%s&daXoa=%s", strMaBinhLuan, strNgayBinhLuan, strMaTaiKhoan, strTenDoChoi, strKiemDuyet, strDaXoa);
        modelAndView.addObject("binhLuanQuery", binhLuanQuery);
        modelAndView.addObject("searchContent", searchContent);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        modelAndView.addObject("kiemDuyetQuery", strKiemDuyet);
        return modelAndView;
 
    }	
}
