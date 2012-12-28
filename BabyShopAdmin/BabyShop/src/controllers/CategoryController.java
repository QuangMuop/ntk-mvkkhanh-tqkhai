package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import pojos.DoChoi;
import pojos.DoChoiUpload;
import pojos.HinhAnhDoChoi;
import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;
import pojos.TaiKhoan;
import pojos.XuatXu;
import util.PagingHelper;
import util.StringUtil;
import dao.DoChoiDAO;
import dao.HinhAnhDoChoiDAO;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;
import dao.TaiKhoanDAO;
import dao.XuatXuDAO;



@Controller
public class CategoryController {
	
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
        modelAndView.setViewName("category");
        modelAndView.addObject("currentMenu", "categoryList");
        
        int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);
        
        int nCategoriesInPage = 10;
        modelAndView.addObject("nCategoriesInPage", nCategoriesInPage);
                
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi(page, nCategoriesInPage);
        
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        
        int nResult = LoaiDoChoiDAO.demSoLuongLoaiDoChoi();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nCategoriesInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCategoriesInPage;
	        if ((nResult % nCategoriesInPage) != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCategoriesInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("loaiDoChoi") LoaiDoChoi loaiDoChoi, 
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-category");
        modelAndView.addObject("currentMenu", "categoryDetail");
        
        long id = 1;
        if (strId != "")
        {
        	id = Long.parseLong(strId);
        }
        
        LoaiDoChoi ldc = LoaiDoChoiDAO.layThongTinLoaiDoChoi(id);

        
        modelAndView.addObject("loaiDoChoi", ldc);
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("loaiDoChoi") LoaiDoChoi loaiDoChoi, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-category");
        modelAndView.addObject("currentMenu", "categoryDetail");
              
        LoaiDoChoi oldLoaiDoChoi = LoaiDoChoiDAO.layThongTinLoaiDoChoi(loaiDoChoi.getMaLoaiDoChoi());
        if(loaiDoChoi.getNgayCapNhat() == null)
		{
        	loaiDoChoi.setNgayCapNhat(oldLoaiDoChoi.getNgayCapNhat());
		}
                
        boolean kq = LoaiDoChoiDAO.capNhatLoaiDoChoi(loaiDoChoi);
       
        
        if(kq == true)
        {
        	modelAndView.addObject("loaiDoChoi", loaiDoChoi);
            modelAndView.addObject("kq", true);
        }
        else
        {
        	modelAndView.addObject("kq", false);
        }
        return modelAndView;
    }
	
	@RequestMapping(method = GET) 
	protected ModelAndView add(@ModelAttribute("loaiDoChoi") LoaiDoChoi loaiDoChoi, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentMenu", "categoryAdd");
        modelAndView.setViewName("add-category");
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("loaiDoChoi") LoaiDoChoi loaiDoChoi, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-category");
        modelAndView.addObject("currentMenu", "categoryAdd");
        
        
        loaiDoChoi.setDaXoa(false);
        
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
        loaiDoChoi.setNgayCapNhat(currentDate);
        boolean kq = LoaiDoChoiDAO.themLoaiDoChoi(loaiDoChoi);
          
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
		JsonNode loaiDoChoiNode = rootNode.path("maLoaiDoChoi");
		long maLoaiDoChoi = loaiDoChoiNode.longValue();
		
        boolean status = LoaiDoChoiDAO.danhDauXoaLoaiDoChoi(maLoaiDoChoi);
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
		JsonNode loaiDoChoiNode = rootNode.path("maLoaiDoChoi");
		long maLoaiDoChoi = loaiDoChoiNode.longValue();
		
		boolean status = LoaiDoChoiDAO.goDanhDauXoaLoaiDoChoi(maLoaiDoChoi);
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
			JsonNode loaiDoChoiNode = listNode.get(i);
			
			JsonNode valueNode = loaiDoChoiNode.path("maLoaiDoChoi");
			long maLoaiDoChoi = valueNode.longValue();
			
			boolean status = LoaiDoChoiDAO.danhDauXoaLoaiDoChoi(maLoaiDoChoi);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maLoaiDoChoi", maLoaiDoChoi);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maLoaiDoChoi", maLoaiDoChoi);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = GET, params = { "maLoaiDoChoi", "tenLoaiDoChoi",
			"daXoa", "page" })
	protected ModelAndView search(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "maLoaiDoChoi", required = true) String strMaLoaiDoChoi,
			@RequestParam(value = "tenLoaiDoChoi", required = true) String tenLoaiDoChoi,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("category");
		modelAndView.addObject("currentMenu", "categoryList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);
        int nCategoriesInPage = 10;
        modelAndView.addObject("nCategoriesInPage", nCategoriesInPage);
        
        //Chuan bi doi tuong LoaiDoChoi de dua vo search
        LoaiDoChoi loaiDoChoiQuery = new LoaiDoChoi();
        long maLoaiDoChoi = -1;
        boolean daXoa = false;
        if(strMaLoaiDoChoi != "")
        {
        	maLoaiDoChoi = Long.parseLong(strMaLoaiDoChoi);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	loaiDoChoiQuery.setDaXoa(daXoa);
        }
              
        loaiDoChoiQuery.setMaLoaiDoChoi(maLoaiDoChoi);
        loaiDoChoiQuery.setTenLoaiDoChoi(tenLoaiDoChoi);
        loaiDoChoiQuery.setDaXoa(daXoa);
        
        //Tìm kiếm
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoiTheoTuKhoa(page, nCategoriesInPage, loaiDoChoiQuery, strDaXoa);
        
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        
        long nResult = LoaiDoChoiDAO.demSoLuongLoaiDoChoiTheoTuKhoa(loaiDoChoiQuery, strDaXoa);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nCategoriesInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nCategoriesInPage;
	        if (nResult % nCategoriesInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nCategoriesInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        //Show lại các input search
        String searchContent = String.format("&maLoaiDoChoi=%s&tenLoaiDoChoi=%s&daXoa=%s", maLoaiDoChoi, tenLoaiDoChoi, strDaXoa);
        modelAndView.addObject("searchContent", searchContent);
        modelAndView.addObject("loaiDoChoiQuery", loaiDoChoiQuery);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        
        return modelAndView;
 
    }	
}
