package controllers;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.DoChoi;
import pojos.DoChoiUpload;
import pojos.HinhAnhDoChoi;
import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;
import pojos.XuatXu;
import util.PagingHelper;
import util.StringUtil;
import dao.DoChoiDAO;
import dao.HinhAnhDoChoiDAO;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;
import dao.XuatXuDAO;

@Controller
public class ToyController {
	
	@RequestMapping(method = GET, params={"page"}) 
    protected ModelAndView list(@RequestParam(value = "page") String strPage, 
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentMenu", "toyList");
        modelAndView.setViewName("toy");
        
        int page = 1;
        if (strPage != "")
        {
        	page = Integer.parseInt(strPage);
        }
        
        modelAndView.addObject("page", page);

        int nToysInPage = 10;
        modelAndView.addObject("nToysInPage", nToysInPage);
        
        List<DoChoi> dsDoChoi = DoChoiDAO.layDanhSachDoChoi(page, nToysInPage);
        
        modelAndView.addObject("dsDoChoi", dsDoChoi);
        
        int nResult = DoChoiDAO.demSoLuongDoChoi();
        modelAndView.addObject("nResult", nResult);

        int nPages;
        if(nResult / nToysInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nToysInPage;
	        if (nResult % nToysInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nToysInPage, nResult);
        modelAndView.addObject("pagingFor", "list");
        modelAndView.addObject("pageNumbers", pageNumbers);
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        
        return modelAndView;
    }
	
	@RequestMapping(method = GET, params={"id"})
	protected ModelAndView detail(@RequestParam(value = "id") String strId, @ModelAttribute("doChoiUpload") DoChoiUpload doChoiUpload, 
			BindingResult result,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-toy");
        modelAndView.addObject("currentMenu", "toyDetail");
        
        int id = 1;
        if (strId != "")
        {
        	id = Integer.parseInt(strId);
        }
        
        DoChoi dc = DoChoiDAO.layDoChoiTheoMa(id);
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        List<XuatXu> dsXuatXu = XuatXuDAO.layDanhSachXuatXu();
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuat();
        List<HinhAnhDoChoi> dsHinhAnhDoChoi = HinhAnhDoChoiDAO.layDayDuHinhAnhTheoMaDoChoi(id);
        
        String dsTenHinhAnhDoChoi ="";
        String hinhAnhHienThiChinh = "";
        if(dc.getHinhAnhHienThiChinh() != null && dc.getHinhAnhHienThiChinh() != "")
        {
            dsTenHinhAnhDoChoi += "[" + dc.getHinhAnhHienThiChinh() + "]";
        }
        else
        {
        	hinhAnhHienThiChinh = "default.jpg";
        	dc.setHinhAnhHienThiChinh(hinhAnhHienThiChinh);
        }
        
        if(dsHinhAnhDoChoi.size() != 0)
        {
	        for(HinhAnhDoChoi hadc : dsHinhAnhDoChoi)
	        {
	        	dsTenHinhAnhDoChoi += "[" + hadc.getHinhAnh() + "]";
	        	
	        }
        }
        else
        {
        	HinhAnhDoChoi hadc = new HinhAnhDoChoi();
        	String hinhAnhDoChoi = "default.jpg";
        	hadc.setHinhAnh(hinhAnhDoChoi);
        	dsHinhAnhDoChoi.add(hadc);
        }

        
        modelAndView.addObject("doChoi", dc);
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        modelAndView.addObject("dsXuatXu", dsXuatXu);
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        modelAndView.addObject("dsHinhAnhDoChoi", dsHinhAnhDoChoi);
        modelAndView.addObject("dsTenHinhAnhDoChoi", dsTenHinhAnhDoChoi);
        
        return modelAndView;
	}
	
	@RequestMapping(method = GET) 
	protected ModelAndView add(@ModelAttribute("doChoiUpload") DoChoiUpload doChoiUpload, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentMenu", "toyAdd");
        modelAndView.setViewName("add-toy");
        
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        List<XuatXu> dsXuatXu = XuatXuDAO.layDanhSachXuatXu();
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuat();
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        modelAndView.addObject("dsXuatXu", dsXuatXu);
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        
        
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("doChoiUpload") DoChoiUpload doChoiUpload, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-toy");
        modelAndView.addObject("currentMenu", "toyAdd");
             
        List<String> dsHinhAnh = StringUtil.FindMatches(doChoiUpload.getHinhAnh(), "\\[([^\\]]*)\\]");     
        Long maLoaiDoChoi = Long.parseLong(doChoiUpload.getLoaiDoChoi());
        Long maNhaSanXuat = Long.parseLong(doChoiUpload.getNhaSanXuat());
        Long maXuatXu = Long.parseLong(doChoiUpload.getXuatXu());
        
        String tenDoChoi = doChoiUpload.getTenDoChoi();
        String hinhAnhHienThiChinh = "";
        if(dsHinhAnh.size() > 0 && dsHinhAnh != null)
        {
        	hinhAnhHienThiChinh = dsHinhAnh.get(0);
        }
        String moTa = doChoiUpload.getMoTa();
        BigDecimal giaBanBanDau = doChoiUpload.getGiaBanBanDau();
        BigDecimal giaBanHienTai = giaBanBanDau;
        int soLuongBan =  doChoiUpload.getSoLuongBan();
        int soLuongXem =  0;
        String tinhTrang = "Còn hàng";
        Integer giamGia = doChoiUpload.getGiamGia();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date ngayTiepNhan = new Date();
        Date ngayCapNhat = new Date();
		try {
			ngayTiepNhan = df.parse(doChoiUpload.getNgayTiepNhan());
			ngayCapNhat = ngayTiepNhan;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int soLuongTon = soLuongBan;
        int soLuongDaBan = 0;
        boolean daXoa = false;
        
        LoaiDoChoi loaiDoChoi = LoaiDoChoiDAO.layThongTinLoaiDoChoi(maLoaiDoChoi);
        NhaSanXuat nhaSanXuat = NhaSanXuatDAO.layThongTinNhaSanXuat(maNhaSanXuat);
        XuatXu xuatXu = XuatXuDAO.layThongTinXuatXu(maXuatXu);
        
        
        //Add new toy
        DoChoi newDoChoi = new DoChoi();
        newDoChoi.setLoaiDoChoi(loaiDoChoi);
        newDoChoi.setNhaSanXuat(nhaSanXuat);
        newDoChoi.setXuatXu(xuatXu);
        newDoChoi.setTenDoChoi(tenDoChoi);
        newDoChoi.setHinhAnhHienThiChinh(hinhAnhHienThiChinh);
        newDoChoi.setMoTa(moTa);
        newDoChoi.setGiaBanBanDau(giaBanBanDau);
        newDoChoi.setGiaBanHienTai(giaBanHienTai);
        newDoChoi.setSoLuongBan(soLuongBan);
        newDoChoi.setSoLuongXem(soLuongXem);
        newDoChoi.setTinhTrang(tinhTrang);
        newDoChoi.setGiamGia(giamGia);
        newDoChoi.setNgayTiepNhan(ngayTiepNhan);
        newDoChoi.setSoLuongTon(soLuongTon);
        newDoChoi.setSoLuongDaBan(soLuongDaBan);
        newDoChoi.setNgayCapNhat(ngayCapNhat);
        newDoChoi.setDaXoa(daXoa);
        boolean kq = DoChoiDAO.themDoChoi(newDoChoi);
       
        //Add toy's images
        for(int i = 1; i < dsHinhAnh.size(); i++)
        {
        	String hinhAnhPhu = dsHinhAnh.get(i);
        	HinhAnhDoChoi hadc = new HinhAnhDoChoi();
        	hadc.setDoChoi(newDoChoi);
        	hadc.setHinhAnh(hinhAnhPhu);
        	hadc.setDaXoa(false);
        	HinhAnhDoChoiDAO.themHinhAnhDoChoi(hadc);
        }
                
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        List<XuatXu> dsXuatXu = XuatXuDAO.layDanhSachXuatXu();
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuat();
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        modelAndView.addObject("dsXuatXu", dsXuatXu);
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        
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
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("doChoiUpload") DoChoiUpload doChoiUpload, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail-toy");
        modelAndView.addObject("currentMenu", "toyDetail");
             
        List<String> dsHinhAnh = StringUtil.FindMatches(doChoiUpload.getHinhAnh(), "\\[([^\\]]*)\\]");     
        Long maLoaiDoChoi = Long.parseLong(doChoiUpload.getLoaiDoChoi());
        Long maNhaSanXuat = Long.parseLong(doChoiUpload.getNhaSanXuat());
        Long maXuatXu = Long.parseLong(doChoiUpload.getXuatXu());
        
        long maDoChoi = doChoiUpload.getMaDoChoi();
        String tenDoChoi = doChoiUpload.getTenDoChoi();
        LoaiDoChoi loaiDoChoi = LoaiDoChoiDAO.layThongTinLoaiDoChoi(maLoaiDoChoi);
        NhaSanXuat nhaSanXuat = NhaSanXuatDAO.layThongTinNhaSanXuat(maNhaSanXuat);
        XuatXu xuatXu = XuatXuDAO.layThongTinXuatXu(maXuatXu);
        String hinhAnhHienThiChinh = "";
        if(dsHinhAnh.size() > 0 && dsHinhAnh != null)
        {
        	hinhAnhHienThiChinh = dsHinhAnh.get(0);
        }
        BigDecimal giaBanBanDau = doChoiUpload.getGiaBanBanDau();
        BigDecimal giaBanHienTai = giaBanBanDau;
        Integer giamGia = doChoiUpload.getGiamGia();
        String tinhTrang = doChoiUpload.getTinhTrang();
        int soLuongBan =  doChoiUpload.getSoLuongBan();
        int soLuongXem =  doChoiUpload.getSoLuongXem();
        int soLuongTon = doChoiUpload.getSoLuongTon();
        int soLuongDaBan = doChoiUpload.getSoLuongDaBan();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date ngayTiepNhan = new Date();
        Date ngayCapNhat = new Date();
		try {
			ngayTiepNhan = df.parse(doChoiUpload.getNgayTiepNhan());
			ngayCapNhat = df.parse(doChoiUpload.getNgayCapNhat());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String moTa = doChoiUpload.getMoTa();
        boolean daXoa = doChoiUpload.isDaXoa();
        

        
        
        //Update toy
        DoChoi oldDoChoi = DoChoiDAO.layDoChoiTheoMa(maDoChoi);
        oldDoChoi.setTenDoChoi(tenDoChoi);
        oldDoChoi.setLoaiDoChoi(loaiDoChoi);
        oldDoChoi.setNhaSanXuat(nhaSanXuat);
        oldDoChoi.setXuatXu(xuatXu);
        oldDoChoi.setHinhAnhHienThiChinh(hinhAnhHienThiChinh);
        oldDoChoi.setGiaBanBanDau(giaBanBanDau);
        oldDoChoi.setGiaBanHienTai(giaBanHienTai);
        oldDoChoi.setGiamGia(giamGia);
        oldDoChoi.setTinhTrang(tinhTrang);
        oldDoChoi.setSoLuongBan(soLuongBan);
        oldDoChoi.setSoLuongXem(soLuongXem);
        oldDoChoi.setSoLuongTon(soLuongTon);
        oldDoChoi.setSoLuongDaBan(soLuongDaBan);
        oldDoChoi.setNgayTiepNhan(ngayTiepNhan);
        oldDoChoi.setNgayCapNhat(ngayCapNhat);
        oldDoChoi.setMoTa(moTa);
        oldDoChoi.setDaXoa(daXoa);
        boolean kq = DoChoiDAO.capNhatDoChoi(oldDoChoi);
       
        //Update toy's images
        List<HinhAnhDoChoi> oldListHADC = HinhAnhDoChoiDAO.layDayDuHinhAnhTheoMaDoChoi(maDoChoi);
        if(oldListHADC.size() != 0) //Nếu đã có hình cũ
        {
        	/*
	        for(int i = 0; i < oldListHADC.size(); i++)
	        {
	        	HinhAnhDoChoi oldHADC = oldListHADC.get(i);
	        	
	        	if(dsHinhAnh.get(i+1) != "DELETE")
	        	{
		        	String hinhAnhPhu = "/images/shared-images/products/big/" + dsHinhAnh.get(i+1);
		        	oldHADC.setHinhAnh(hinhAnhPhu);
	        	}
	        	else
	        	{
		        	oldHADC.setDaXoa(true);
	        	}
	        	HinhAnhDoChoiDAO.capNhatHinhAnhDoChoi(oldHADC);
	        }
	        */
        	if(dsHinhAnh.size() > 1 && dsHinhAnh != null) //Nếu có thêm hình mới và từ 2 bức hình trở lên
        	{
	        	for(int i = 1; i < dsHinhAnh.size(); i++)
	            {
	        		if(oldListHADC.size() > (i-1)) //Nếu hình cũ vẫn có
	        		{
		        		HinhAnhDoChoi oldHADC = oldListHADC.get(i-1);
	        			if(!dsHinhAnh.get(i).toString().equalsIgnoreCase("DELETE"))
			        	{
				        	String hinhAnhPhu = dsHinhAnh.get(i);
				        	oldHADC.setHinhAnh(hinhAnhPhu);
				        	oldHADC.setDaXoa(false);
			        	}
	        			else
	    	        	{
	        				String hinhAnhPhu = "DELETE";
				        	oldHADC.setHinhAnh(hinhAnhPhu);
	    		        	oldHADC.setDaXoa(true);
	    	        	}
	        			HinhAnhDoChoiDAO.capNhatHinhAnhDoChoi(oldHADC);
	        		}
	        		else  //Lúc này là trường hợp bổ sung thêm hình mới
	        		{
	        			if(!dsHinhAnh.get(i).toString().equalsIgnoreCase("DELETE"))
			        	{
	        				String hinhAnhPhu = dsHinhAnh.get(i);
	    	            	HinhAnhDoChoi hadc = new HinhAnhDoChoi();
	    	            	hadc.setDoChoi(oldDoChoi);
	    	            	hadc.setHinhAnh(hinhAnhPhu);
	    	            	hadc.setDaXoa(false);
	    	            	HinhAnhDoChoiDAO.themHinhAnhDoChoi(hadc);
			        	}
	        		}
	        		
	            }
        	}
        }
        else //Nếu hình ảnh cũ chưa có
        {
        	if(dsHinhAnh.size() > 1 && dsHinhAnh != null)
        	{
	        	for(int i = 1; i < dsHinhAnh.size(); i++)
	            {
	            	String hinhAnhPhu = dsHinhAnh.get(i);
	            	HinhAnhDoChoi hadc = new HinhAnhDoChoi();
	            	hadc.setDoChoi(oldDoChoi);
	            	hadc.setHinhAnh(hinhAnhPhu);
	            	hadc.setDaXoa(false);
	            	HinhAnhDoChoiDAO.themHinhAnhDoChoi(hadc);
	            }
        	}
        }
                
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        List<XuatXu> dsXuatXu = XuatXuDAO.layDanhSachXuatXu();
        List<NhaSanXuat> dsNhaSanXuat = NhaSanXuatDAO.layDanhSachNhaSanXuat();
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        modelAndView.addObject("dsXuatXu", dsXuatXu);
        modelAndView.addObject("dsNhaSanXuat", dsNhaSanXuat);
        
        if(kq == true)
        {
        	modelAndView.addObject("doChoi", oldDoChoi);
        	
        	List<HinhAnhDoChoi> dsHinhAnhDoChoi = HinhAnhDoChoiDAO.layDayDuHinhAnhTheoMaDoChoi(oldDoChoi.getMaDoChoi());
            String dsTenHinhAnhDoChoi ="";
            dsTenHinhAnhDoChoi += "[" + oldDoChoi.getHinhAnhHienThiChinh() + "]";
            for(HinhAnhDoChoi hadc : dsHinhAnhDoChoi)
            {
            	dsTenHinhAnhDoChoi += "[" + hadc.getHinhAnh() + "]";
            }
        	
            modelAndView.addObject("dsTenHinhAnhDoChoi", dsTenHinhAnhDoChoi);
            modelAndView.addObject("dsHinhAnhDoChoi", dsHinhAnhDoChoi);
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
		JsonNode doChoiNode = rootNode.path("maDoChoi");
		long maDoChoi = doChoiNode.longValue();
		
        boolean status = DoChoiDAO.danhDauXoaDoChoi(maDoChoi);
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
		JsonNode doChoiNode = rootNode.path("maDoChoi");
		long maDoChoi = doChoiNode.longValue();
		
		boolean status = DoChoiDAO.goDanhDauXoaDoChoi(maDoChoi);
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
			JsonNode doChoiNode = listNode.get(i);
			
			JsonNode valueNode = doChoiNode.path("maDoChoi");
			long maDoChoi = valueNode.longValue();
			
			boolean status = DoChoiDAO.danhDauXoaDoChoi(maDoChoi);
			Map<String,Object> messageResult = new HashMap<String,Object>(); 
	        if(status == true)
	        {
	        	messageResult.put("result", "1");
	        	messageResult.put("maDoChoi", maDoChoi);
	        }
	        else
	        {
	        	messageResult.put("result", "0");
	        	messageResult.put("maDoChoi", maDoChoi);
	        }
	        listMessageResult.add(messageResult);
		}
		
		return mapper.writeValueAsString(listMessageResult);
        
    }
	
	@RequestMapping(method = GET, params = { "maDoChoi", "tenDoChoi",
			"loaiDoChoi", "tinhTrang", "daXoa", "page" })
	protected ModelAndView search(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "maDoChoi", required = true) String strMaDoChoi,
			@RequestParam(value = "tenDoChoi", required = true) String tenDoChoi,
			@RequestParam(value = "loaiDoChoi", required = true) String strMaLoaiDoChoi,
			@RequestParam(value = "tinhTrang", required = true) String tinhTrang,
			@RequestParam(value = "daXoa", required = true) String strDaXoa,
			HttpServletRequest arg0, HttpServletResponse arg1) 
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("toy");
		modelAndView.addObject("currentMenu", "toyList");
		
		//Thiết lập
		if(page == null)
		{
			page = 1;
		}
        
        modelAndView.addObject("page", page);

        int nToysInPage = 10;
        modelAndView.addObject("nToysInPage", nToysInPage);
        
        //Chuan bi doi tuong DoChoi de dua vo search
        DoChoi doChoiQuery = new DoChoi();
        long maDoChoi = -1;
        long maLoaiDoChoi = -1;
        boolean daXoa = false;
        if(strMaDoChoi != "")
        {
        	maDoChoi = Long.parseLong(strMaDoChoi);
        }
        if(strMaLoaiDoChoi != "")
        {
        	maLoaiDoChoi = Long.parseLong(strMaLoaiDoChoi);
        }
        if(strDaXoa != "")
        {
        	daXoa = Boolean.parseBoolean(strDaXoa);
        	doChoiQuery.setDaXoa(daXoa);
        }
        LoaiDoChoi ldc = LoaiDoChoiDAO.layThongTinLoaiDoChoi(maLoaiDoChoi);
        
        
        doChoiQuery.setMaDoChoi(maDoChoi);
        doChoiQuery.setTenDoChoi(tenDoChoi);
        doChoiQuery.setLoaiDoChoi(ldc);
        doChoiQuery.setTinhTrang(tinhTrang);

        //Tìm kiếm
        List<DoChoi> dsDoChoi = DoChoiDAO.layDanhSachDoChoiTheoTuKhoa(page, nToysInPage, doChoiQuery, strDaXoa);
        
        modelAndView.addObject("dsDoChoi", dsDoChoi);
        
        long nResult = DoChoiDAO.demSoLuongDoChoiTheoTuKhoa(doChoiQuery, strDaXoa);
        modelAndView.addObject("nResult", nResult);
        
        //Phân trang
        long nPages;
        if(nResult / nToysInPage == 0)
        {
        	nPages = 1;
        }
        else
        {
	        nPages = nResult / nToysInPage;
	        if (nResult % nToysInPage != 0)
	        {
	        	nPages++;
	        }
        }
        modelAndView.addObject("nPages", nPages);
        List<Integer> pageNumbers = PagingHelper.PagingCaculator(page, nToysInPage, nResult);
        modelAndView.addObject("pagingFor", "search");
        modelAndView.addObject("pageNumbers", pageNumbers);
        
        //Show lại các input search
        List<LoaiDoChoi> dsLoaiDoChoi = LoaiDoChoiDAO.layDanhSachLoaiDoChoi();
        String searchContent = String.format("&maDoChoi=%s&tenDoChoi=%s&loaiDoChoi=%s&tinhTrang=%s&daXoa=%s", maDoChoi, tenDoChoi, maLoaiDoChoi, tinhTrang, strDaXoa);
        modelAndView.addObject("dsLoaiDoChoi", dsLoaiDoChoi);
        modelAndView.addObject("doChoiQuery", doChoiQuery);
        modelAndView.addObject("daXoaQuery", strDaXoa);
        modelAndView.addObject("searchContent", searchContent);
        
        return modelAndView;
 
    }
}
