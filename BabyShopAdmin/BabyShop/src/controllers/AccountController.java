package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.DoChoi;
import pojos.LoaiDoChoi;
import pojos.LoaiTaiKhoan;
import pojos.NhaSanXuat;
import pojos.TaiKhoan;
import propertyeditor.AccountTypePropertyEditor;
import util.DateUtil;
import util.PagingHelper;
import dao.LoaiDoChoiDAO;
import dao.LoaiTaiKhoanDAO;
import dao.NhaSanXuatDAO;
import dao.TaiKhoanDAO;

@Controller
public class AccountController{
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(LoaiTaiKhoan.class, new AccountTypePropertyEditor());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
	}
	
	@RequestMapping(method = GET, params={"page"}) 
    protected ModelAndView list(@RequestParam(value = "page") String strPage, 
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("account");
        modelAndView.addObject("currentMenu", "accountList");
        
        int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);
        
        int nAccountsInPage = 10;
        modelAndView.addObject("nAccountsInPage", nAccountsInPage);
        List<TaiKhoan> dsTaiKhoan = TaiKhoanDAO.layDanhSachTaiKhoan(page, nAccountsInPage);
        
        modelAndView.addObject("dsTaiKhoan", dsTaiKhoan);
        
        int nResult = TaiKhoanDAO.demSoLuongTaiKhoan();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nAccountsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nAccountsInPage;
	        if ((nResult % nAccountsInPage) != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nAccountsInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
        modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
        
        return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan, 
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-account");
        modelAndView.addObject("currentMenu", "accountDetail");
        
        if (strId != "")
        {
        	TaiKhoan tk = TaiKhoanDAO.layThongTinTaiKhoan(strId);
        	List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
            modelAndView.addObject("taiKhoan", tk);
            modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
        }
        else
        {
        	modelAndView.addObject("taiKhoan", null);
        }
              
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	 public ModelAndView update(@RequestParam(value="avatar",required=false) MultipartFile avatar, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan, BindingResult result, HttpServletRequest arg0,
	    		HttpServletResponse arg1) throws IOException{
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-account");
        modelAndView.addObject("currentMenu", "accountDetail");
              
		TaiKhoan oldTaiKhoan = TaiKhoanDAO.layThongTinTaiKhoan(taiKhoan.getMaTaiKhoan());
        
        String fileName = "";     
        //Nếu user có update hình
		if(avatar.getSize() != 0){
			fileName = avatar.getOriginalFilename();
			
			taiKhoan.setAvatar(fileName);
			
			try{ 
				File newFiles= new File(arg0.getSession().getServletContext().getRealPath("/uploads/avatars/"), fileName); 
				FileUtils.writeByteArrayToFile(newFiles,avatar.getBytes());
				} catch(IOException e){ 
					e.printStackTrace();
				} 
		}
		else
		{
			taiKhoan.setAvatar(oldTaiKhoan.getAvatar());
		}
		if(taiKhoan.getNgayCapNhat() == null)
		{
			taiKhoan.setNgayCapNhat(oldTaiKhoan.getNgayCapNhat());
		}
		if(taiKhoan.getNgaySinh() == null)
		{
			taiKhoan.setNgaySinh(oldTaiKhoan.getNgaySinh());
		}
		
        boolean kq = TaiKhoanDAO.capNhatTaiKhoan(taiKhoan);
       
        
        if(kq == true)
        {
        	modelAndView.addObject("taiKhoan", taiKhoan);
        	List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
            modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
            modelAndView.addObject("kq", true);
        }
        else
        {
        	modelAndView.addObject("kq", false);
        }
        return modelAndView;
    }
	
	@RequestMapping(method = GET) 
	protected ModelAndView add(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentMenu", "accountAdd");
        modelAndView.setViewName("add-account");
        
        List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
        modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam(value="avatar",required=false) MultipartFile avatar, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws IOException{
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-account");
        modelAndView.addObject("currentMenu", "accountAdd");
        
    
        String fileName = "";
        
        //Nếu user có up hình
		if(avatar.getSize() != 0){
			fileName = avatar.getOriginalFilename();
			
			taiKhoan.setAvatar(fileName);
			
			try{ 
				File newFiles= new File(arg0.getSession().getServletContext().getRealPath("/uploads/avatars/"), fileName); 
				FileUtils.writeByteArrayToFile(newFiles,avatar.getBytes());
				} catch(IOException e){ 
					e.printStackTrace();
				} 
		}	
		
		taiKhoan.setDaXoa(false);
		taiKhoan.setDaBan(false);
		taiKhoan.setNgayCapNhat(DateUtil.getCurrentDate("yyyy-MM-dd"));
		
		boolean kq = TaiKhoanDAO.themTaiKhoan(taiKhoan);
        
        if(kq == true)
        {
            modelAndView.addObject("kq", true);
            
            List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
            modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
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
		JsonNode taiKhoanNode = rootNode.path("maTaiKhoan");
		String maTaiKhoan = taiKhoanNode.textValue();
		
        boolean status = TaiKhoanDAO.danhDauXoaTaiKhoan(maTaiKhoan);
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
		JsonNode taiKhoanNode = rootNode.path("maTaiKhoan");
		String maTaiKhoan = taiKhoanNode.textValue();
		
        boolean status = TaiKhoanDAO.goDanhDauXoaTaiKhoan(maTaiKhoan);
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
			JsonNode taiKhoanNode = listNode.get(i);
			
			JsonNode valueNode = taiKhoanNode.path("maTaiKhoan");
			String maTaiKhoan = valueNode.textValue();
			
			boolean status = TaiKhoanDAO.danhDauXoaTaiKhoan(maTaiKhoan);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maTaiKhoan", maTaiKhoan);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maTaiKhoan", maTaiKhoan);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    protected @ResponseBody String ban(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode taiKhoanNode = rootNode.path("maTaiKhoan");
		String maTaiKhoan = taiKhoanNode.textValue();
		
        boolean status = TaiKhoanDAO.danhDauKhoaTaiKhoan(maTaiKhoan);
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
    protected @ResponseBody String unban(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode taiKhoanNode = rootNode.path("maTaiKhoan");
		String maTaiKhoan = taiKhoanNode.textValue();
		
        boolean status = TaiKhoanDAO.goDanhDauKhoaTaiKhoan(maTaiKhoan);
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
    protected @ResponseBody String multiBan(@RequestBody String message,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		List<Map<String, Object>> listMessageResult = new ArrayList<Map<String, Object>>();
		
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> listNode = mapper.readValue(message, new TypeReference<List<JsonNode>>(){});
		for(int i = 0; i < listNode.size(); i++)
		{
			JsonNode taiKhoanNode = listNode.get(i);
			
			JsonNode valueNode = taiKhoanNode.path("maTaiKhoan");
			String maTaiKhoan = valueNode.textValue();
			
			boolean status = TaiKhoanDAO.danhDauKhoaTaiKhoan(maTaiKhoan);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maTaiKhoan", maTaiKhoan);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maTaiKhoan", maTaiKhoan);
	        }
	        listMessageResult.add(messageResult);
	        
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = GET, params = { "maTaiKhoan", "loaiTaiKhoan",
			"daXoa", "daBan", "page" })
	protected ModelAndView search(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "maTaiKhoan", required = true) String maTaiKhoan,
			@RequestParam(value = "loaiTaiKhoan", required = true) String strMaLoaiTaiKhoan,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			@RequestParam(value = "daBan", required = true) String strDaBan,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("account");
		modelAndView.addObject("currentMenu", "accountList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);

        int nAccountsInPage = 10;
        modelAndView.addObject("nAccountsInPage", nAccountsInPage);
        
        
        //Chuan bi doi tuong TaiKhoan de dua vo search
        TaiKhoan taiKhoanQuery = new TaiKhoan();
        long maLoaiTaiKhoan = -1;
        boolean daXoa = false;
        boolean daBan = false;
        if(strMaLoaiTaiKhoan != "")
        {
        	maLoaiTaiKhoan = Long.parseLong(strMaLoaiTaiKhoan);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	taiKhoanQuery.setDaXoa(daXoa);
        }
        if(strDaBan != "")
        {
        	daBan = Boolean.parseBoolean(strDaBan);
        	taiKhoanQuery.setDaBan(daBan);
        }
        LoaiTaiKhoan ltk = LoaiTaiKhoanDAO.layThongTinLoaiTaiKhoan(maLoaiTaiKhoan);
        
        
        taiKhoanQuery.setMaTaiKhoan(maTaiKhoan);
        taiKhoanQuery.setLoaiTaiKhoan(ltk);
        
        //Tìm kiếm
        List<TaiKhoan> dsTaiKhoan = TaiKhoanDAO.layDanhSachTaiKhoanTheoTuKhoa(page, nAccountsInPage, taiKhoanQuery, strDaXoa, strDaBan);
        
        modelAndView.addObject("dsTaiKhoan", dsTaiKhoan);
        
        long nResult = TaiKhoanDAO.demSoLuongTaiKhoanTheoTuKhoa(taiKhoanQuery, strDaXoa, strDaBan);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nAccountsInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nAccountsInPage;
	        if (nResult % nAccountsInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nAccountsInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);         
        
        List<LoaiTaiKhoan> dsLoaiTaiKhoan = LoaiTaiKhoanDAO.layDanhSachLoaiTaiKhoan();
        String searchContent = String.format("&maTaiKhoan=%s&loaiTaiKhoan=%s&daXoa=%s&daBan=%s", maTaiKhoan, maLoaiTaiKhoan, strDaXoa, strDaBan);
        modelAndView.addObject("dsLoaiTaiKhoan", dsLoaiTaiKhoan);
        modelAndView.addObject("taiKhoanQuery", taiKhoanQuery);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        modelAndView.addObject("daBanQuery", strDaBan);
        modelAndView.addObject("searchContent", searchContent);
        
        return modelAndView;
 
    }	
}
