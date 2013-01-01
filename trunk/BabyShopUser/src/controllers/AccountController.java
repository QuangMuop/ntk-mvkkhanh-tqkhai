package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.TaiKhoanDAO;

import pojos.Login;
import pojos.Product;
import pojos.TaiKhoan;

@SessionAttributes({ "account", "products"})
@Controller
public class AccountController {
	@RequestMapping(method = GET, value="/login")
	protected ModelAndView login(@ModelAttribute("login") Login login,
			BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
		TaiKhoan taiKhoan = new TaiKhoan();
		modelAndView.addObject("account", taiKhoan);


		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/submit-login")
	public ModelAndView submitLogin(@ModelAttribute("login") Login login,
			BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) throws UnsupportedEncodingException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");

		ModelAndView modelAndView = new ModelAndView();

		String maTaiKhoan = login.getUsername();
		String matKhau = login.getPassword();

		TaiKhoanDAO helper = new TaiKhoanDAO();
		TaiKhoan taiKhoan = helper.dangNhapTK(maTaiKhoan, matKhau);

		if (taiKhoan != null) {
			modelAndView.setViewName("redirect:/home/index");
			modelAndView.addObject("account", taiKhoan);
			ArrayList<Product> products = new ArrayList<Product>();
			modelAndView.addObject("products", products);
		} else {

			modelAndView.setViewName("login");
			modelAndView.addObject("state", 0);
		}

		return modelAndView;
	}
}
