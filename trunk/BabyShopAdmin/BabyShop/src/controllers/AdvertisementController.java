package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;


import pojos.QuangCao;



import dao.QuangCaoDAO;

@Controller
public class AdvertisementController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
	}
	
	@RequestMapping(method = GET)
	protected ModelAndView list(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("advertisement");
		modelAndView.addObject("currentMenu", "advertisementList");

		// Get Advertisements List
		InputStream advertisementMapping = arg0.getServletContext()
				.getResourceAsStream("/resources/advertisementsMapping.xml");

		List<QuangCao> listQuangCao = QuangCaoDAO
				.layDanhSachQuangCao(advertisementMapping);
		modelAndView.addObject("listQuangCao", listQuangCao);
		return modelAndView;
	}

	@RequestMapping(method = GET, params = { "position" })
	protected ModelAndView detail(
			@RequestParam(value = "position") String strPosition,
			@ModelAttribute("quangCao") QuangCao quangCao,
			HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-advertisement");
		modelAndView.addObject("currentMenu", "advertisementDetail");

		if (strPosition != "") {
			// Get Advertisement Object
			InputStream advertisementMapping = arg0
					.getServletContext()
					.getResourceAsStream("/resources/advertisementsMapping.xml");

			QuangCao qc = QuangCaoDAO.layThongTinQuangCao(advertisementMapping,
					strPosition);
			modelAndView.addObject("quangCao", qc);
		} else {
			modelAndView.addObject("quangCao", null);
		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("quangCao") QuangCao quangCao,
			BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException, JDOMException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-advertisement");
		modelAndView.addObject("currentMenu", "advertisementDetail");
		
		InputStream advertisementMapping = arg0
				.getServletContext()
				.getResourceAsStream("/resources/advertisementsMapping.xml");
		String outputURL = arg0.getSession().getServletContext().getRealPath("/resources/") + "/advertisementsMapping.xml";
		Boolean kq = QuangCaoDAO.capNhatQuangCao(advertisementMapping, outputURL, quangCao.getPosition(), quangCao);
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
}
