package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;


import pojos.LoaiTaiKhoan;
import pojos.QuangCao;
import pojos.QuangCaoOld;
import pojos.TaiKhoan;



import dao.LoaiTaiKhoanDAO;
import dao.QuangCaoDAO;
import dao.TaiKhoanDAO;

@Controller
public class AdvertisementController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(method = GET)
	protected ModelAndView list(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("advertisement");
		modelAndView.addObject("currentMenu", "advertisementList");

		List<QuangCao> listQuangCao = QuangCaoDAO.layDanhSachQuangCao();
		modelAndView.addObject("listQuangCao", listQuangCao);
		return modelAndView;
	}
	
	@RequestMapping(method = GET, params = { "id" })
	protected ModelAndView detail(
			@RequestParam(value = "id") String strId,
			@ModelAttribute("quangCao") QuangCao quangCao,
			HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-advertisement");
		modelAndView.addObject("currentMenu", "advertisementDetail");

		long id = 1;
        if (strId != "")
        {
        	id = Long.parseLong(strId);
        }
        
        QuangCao qc = QuangCaoDAO.layThongTinQuangCao(id);
        modelAndView.addObject("quangCao", qc);
        
		return modelAndView;
	}
 	
	@RequestMapping(method = RequestMethod.POST, value="/update")
	public ModelAndView update(@RequestParam(value="hinhAnh",required=false) MultipartFile hinhAnh, @ModelAttribute("quangCao") QuangCao quangCao, BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException, JDOMException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-advertisement");
		modelAndView.addObject("currentMenu", "advertisementDetail");
		
		QuangCao oldQuangCao = QuangCaoDAO.layThongTinQuangCao(quangCao.getMaQuangCao());
		String fileName = "";     
        //Nếu user có update hình
		if(hinhAnh.getSize() != 0){
			fileName = hinhAnh.getOriginalFilename();
			
			quangCao.setHinhAnh(fileName);
			
			try{ 
				File newFiles= new File(arg0.getSession().getServletContext().getRealPath("/uploads/ads/"), fileName); 
				FileUtils.writeByteArrayToFile(newFiles,hinhAnh.getBytes());
				} catch(IOException e){ 
					e.printStackTrace();
				} 
		}
		else
		{
			quangCao.setHinhAnh(oldQuangCao.getHinhAnh());
		}
		if(quangCao.getBatDau() == null)
		{
			quangCao.setBatDau(oldQuangCao.getBatDau());
		}
		if(quangCao.getKetThuc() == null)
		{
			quangCao.setKetThuc(oldQuangCao.getKetThuc());
		}
		
        boolean kq = QuangCaoDAO.capNhatQuangCao(quangCao);
       
        
        if(kq == true)
        {
        	modelAndView.addObject("quangCao", quangCao);
            modelAndView.addObject("kq", true);
        }
        else
        {
        	modelAndView.addObject("kq", false);
        }
		
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value="/add") 
	protected ModelAndView add(@ModelAttribute("quangCao") QuangCao quangCao, BindingResult result, HttpServletRequest arg0,
    		HttpServletResponse arg1)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentMenu", "advertisementAdd");
        modelAndView.setViewName("add-advertisement");
                
        return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/submit")
	public ModelAndView submit(@RequestParam(value="hinhAnh",required=false) MultipartFile hinhAnh, @ModelAttribute("quangCao") QuangCao quangCao, BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException, JDOMException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("add-advertisement");
		modelAndView.addObject("currentMenu", "advertisementAdd");
		
		String fileName = "";     
        //Nếu user có update hình
		if(hinhAnh.getSize() != 0){
			fileName = hinhAnh.getOriginalFilename();
			
			quangCao.setHinhAnh(fileName);
			
			try{ 
				File newFiles= new File(arg0.getSession().getServletContext().getRealPath("/uploads/ads/"), fileName); 
				FileUtils.writeByteArrayToFile(newFiles,hinhAnh.getBytes());
				} catch(IOException e){ 
					e.printStackTrace();
				} 
		}
		quangCao.setSoLuongClick(0);
		
		boolean kq = QuangCaoDAO.themQuangCao(quangCao);
		
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
}
