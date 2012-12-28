package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;
import util.PagingHelper;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;


@Controller
public class ManufacturerController {
	
	//De bind String thành Date
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 
		}
	
	@RequestMapping(method = GET, params={"page"}) 
    protected ModelAndView list(@RequestParam(value = "page") String strPage, 
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer");
		modelAndView.addObject("currentMenu", "manufacturerList");
		
		int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);
        
        int nManufacturersInPage = 10;
        modelAndView.addObject("nManufacturersInPage", nManufacturersInPage);
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuat(page, nManufacturersInPage);
        
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        
        int nResult = NhaSanXuatDAO.demSoLuongNhaSanXuat();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nManufacturersInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nManufacturersInPage;
	        if ((nResult % nManufacturersInPage) != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nManufacturersInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
		
		return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("nhaSanXuat") NhaSanXuat nhaSanXuat, 
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-manufacturer");
        modelAndView.addObject("currentMenu", "manufacturerDetail");
        
        long id = 1;
        if (strId != "")
        {
        	id = Long.parseLong(strId);
        }
        
        NhaSanXuat nsx = NhaSanXuatDAO.layThongTinNhaSanXuat(id);

        
        modelAndView.addObject("nhaSanXuat", nsx);
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("nhaSanXuat") NhaSanXuat nhaSanXuat, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-manufacturer");
        modelAndView.addObject("currentMenu", "manufacturerDetail");
              
        NhaSanXuat oldNhaSanXuat = NhaSanXuatDAO.layThongTinNhaSanXuat(nhaSanXuat.getMaNhaSanXuat());
        if(nhaSanXuat.getNgayCapNhat() == null)
		{
        	nhaSanXuat.setNgayCapNhat(oldNhaSanXuat.getNgayCapNhat());
		}
        
        boolean kq = NhaSanXuatDAO.capNhatNhaSanXuat(nhaSanXuat);
       
        
        if(kq == true)
        {
        	modelAndView.addObject("nhaSanXuat", nhaSanXuat);
            modelAndView.addObject("kq", true);
        }
        else
        {
        	modelAndView.addObject("kq", false);
        }
        return modelAndView;
    }
	
	@RequestMapping(method = GET) 
	protected ModelAndView add(@ModelAttribute("nhaSanXuat") NhaSanXuat nhaSanXuat, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentMenu", "manufacturerAdd");
        modelAndView.setViewName("add-manufacturer");
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("nhaSanXuat") NhaSanXuat nhaSanXuat, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-manufacturer");
        modelAndView.addObject("currentMenu", "manufacturerAdd");
        
        
        nhaSanXuat.setDaXoa(false);
        
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DATE);
        String today = year + "-" + month + "-" + day;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
		try {
			currentDate = dateFormat.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nhaSanXuat.setNgayCapNhat(currentDate);
        boolean kq = NhaSanXuatDAO.themNhaSanXuat(nhaSanXuat);
          
        if(kq == true)
        {
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
		JsonNode nhaSanXuatNode = rootNode.path("maNhaSanXuat");
		long maNhaSanXuat = nhaSanXuatNode.longValue();
		
        boolean status = NhaSanXuatDAO.danhDauXoaNhaSanXuat(maNhaSanXuat);
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
		JsonNode nhaSanXuatNode = rootNode.path("maNhaSanXuat");
		long maNhaSanXuat = nhaSanXuatNode.longValue();
		
		boolean status = NhaSanXuatDAO.goDanhDauXoaNhaSanXuat(maNhaSanXuat);
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
			JsonNode nhaSanXuatNode = listNode.get(i);
			
			JsonNode valueNode = nhaSanXuatNode.path("maNhaSanXuat");
			long maNhaSanXuat = valueNode.longValue();
			
			boolean status = NhaSanXuatDAO.danhDauXoaNhaSanXuat(maNhaSanXuat);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maNhaSanXuat", maNhaSanXuat);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maNhaSanXuat", maNhaSanXuat);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = GET, params = { "maNhaSanXuat", "tenNhaSanXuat",
			"daXoa", "page" })
	protected ModelAndView search(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "maNhaSanXuat", required = true) String strMaNhaSanXuat,
			@RequestParam(value = "tenNhaSanXuat", required = true) String tenNhaSanXuat,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer");
		modelAndView.addObject("currentMenu", "manufacturerList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);
        int nManufacturersInPage = 10;
        modelAndView.addObject("nManufacturersInPage", nManufacturersInPage);
        
        //Chuan bi doi tuong NhaSanXuat de dua vo search
        NhaSanXuat nhaSanXuatQuery = new NhaSanXuat();
        long maNhaSanXuat = -1;
        boolean daXoa = false;
        if(strMaNhaSanXuat != "")
        {
        	maNhaSanXuat = Long.parseLong(strMaNhaSanXuat);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	nhaSanXuatQuery.setDaXoa(daXoa);
        }
              
        nhaSanXuatQuery.setMaNhaSanXuat(maNhaSanXuat);
        nhaSanXuatQuery.setTenNhaSanXuat(tenNhaSanXuat);
        nhaSanXuatQuery.setDaXoa(daXoa);
        
        //Tìm kiếm
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuatTheoTuKhoa(page, nManufacturersInPage, nhaSanXuatQuery, strDaXoa);
        
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        
        long nResult = NhaSanXuatDAO.demSoLuongNhaSanXuatTheoTuKhoa(nhaSanXuatQuery, strDaXoa);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nManufacturersInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nManufacturersInPage;
	        if (nResult % nManufacturersInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nManufacturersInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);    
        
        
        
        //Show lại các input search
        String searchContent = String.format("&maNhaSanXuat=%s&tenNhaSanXuat=%s&daXoa=%s", maNhaSanXuat, tenNhaSanXuat, strDaXoa);
        modelAndView.addObject("searchContent", searchContent);
        modelAndView.addObject("nhaSanXuatQuery", nhaSanXuatQuery);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        
        return modelAndView;
 
    }
}
