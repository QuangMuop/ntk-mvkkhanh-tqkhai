package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.TaiKhoanDAO;

import pojos.LoaiTaiKhoan;
import pojos.Login;
import pojos.Product;
import pojos.TaiKhoan;
import pojos.TaiKhoanRegister;
import util.DateUtil;

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
	
	@RequestMapping(method = GET, value="/register")
	protected ModelAndView register(
			@ModelAttribute("taiKhoanRegister") TaiKhoanRegister taiKhoanRegister,
			BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value="/submit-register")
	protected ModelAndView submitRegister(@ModelAttribute("login") Login login,
			@ModelAttribute("taiKhoanRegister") TaiKhoanRegister taiKhoanRegister,
			BindingResult result, HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException
	{
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<String> info = new ArrayList<String>();
		String view = "login";

		TaiKhoanDAO helper = new TaiKhoanDAO();
		TaiKhoan taikhoan = new TaiKhoan();
		
		String hoTen = taiKhoanRegister.getHoTen();
		taikhoan.setHoTen(hoTen);
		
		int dd = Integer.parseInt(taiKhoanRegister.getDd());
		int mm = Integer.parseInt(taiKhoanRegister.getMm());
		int yy = Integer.parseInt(taiKhoanRegister.getYy());
		
		String strNgaySinh = yy + "-" + mm + "-" + dd;
		Date ngaySinh = DateUtil.convertStringToDate(strNgaySinh, "yyyy-MM-dd");
		taikhoan.setNgaySinh(ngaySinh);

		// Get gender info
		int gender = Integer.parseInt(taiKhoanRegister.getGioiTinh());
		if (gender == 1) {
			taikhoan.setGioiTinh(Boolean.TRUE);
		} else {
			taikhoan.setGioiTinh(Boolean.FALSE);
		}

		// Get city info
		String place = taiKhoanRegister.getThanhPho();
		taikhoan.setThanhPho(place);

		// Get email info
		String email = taiKhoanRegister.getEmail();
		taikhoan.setEmail(email);

		// Get phone info
		String phone = taiKhoanRegister.getDienThoai();
		taikhoan.setDienThoai(phone);

		// Get username info
		String username = taiKhoanRegister.getMaTaiKhoan();
		taikhoan.setMaTaiKhoan(username);

		// Check username valid
		if((TaiKhoan)helper.get(taiKhoanRegister.getMaTaiKhoan()) != null)
		{
			info.add("Tên đăng nhập này đã có người sử dụng");
			view = "register";
		}
		if (username.isEmpty()) {
			info.add("Tên đăng nhập không được để trống!");
			view = "register";
		}
		
		
		// Get password info
		String password = taiKhoanRegister.getMatKhau();
		taikhoan.setMatKhau(password);

		// Check password valid
		if (password.isEmpty()) {
			info.add("Mật khẩu không được để trống!");
			view = "register";
		}

		// Get confirm password
		String confirm_password = taiKhoanRegister.getXacNhanMatKhau();

		// Check password & confirm password same
		if (!password.equals(confirm_password)) {
			info.add("Mật khẩu không chính xác!");
			view = "register";
		}
		
		//Verify code
		InputStream configStream = arg0
                .getServletContext()
                .getResourceAsStream("/WEB-INF/config/global-config.properties");
		Properties _prop = new Properties();
        _prop.load(configStream);
        String captchaPrivateKey = _prop.getProperty("CaptchaPrivateKey");
        
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();  
        reCaptcha.setPrivateKey(captchaPrivateKey);  
          
        String remoteAddr = arg0.getRemoteAddr();  
        String challengeField = arg0.getParameter("recaptcha_challenge_field");  
        String responseField = arg0.getParameter("recaptcha_response_field");  
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challengeField, responseField);  
  
        if(!reCaptchaResponse.isValid())  
        {  
        	info.add("Mã xác nhận không chính xác!");
			view = "register"; 
        }  
          
		taikhoan.setNgayCapNhat(new Date());
		taikhoan.setDaXoa(Boolean.FALSE);
		taikhoan.setDaBan(Boolean.FALSE);

		LoaiTaiKhoan loaitaikhoan = new LoaiTaiKhoan(2);
		taikhoan.setLoaiTaiKhoan(loaitaikhoan);

		if (!view.equals("register")) {
			helper.dangKyTK(taikhoan);
			modelAndView.setViewName(view);
			modelAndView.addObject("state", 1);
		} else {
			modelAndView.setViewName(view);
			modelAndView.addObject("info", info);
			modelAndView.addObject("state", 0);
		}

		return modelAndView;
	}
}
