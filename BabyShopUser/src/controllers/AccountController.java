package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BinhLuanDAO;
import dao.ChiTietHoaDonDAO;
import dao.DoChoiDAO;
import dao.HoaDonDAO;
import dao.TaiKhoanDAO;

import pojos.BinhLuan;
import pojos.ChiTietHoaDon;
import pojos.DoChoi;
import pojos.HoaDon;
import pojos.LoaiTaiKhoan;
import pojos.Login;
import pojos.Product;
import pojos.TaiKhoan;
import pojos.TaiKhoanRegister;
import pojos.ThanhToan;
import pojos.TrangThaiDonHang;
import util.DateUtil;
import util.PagingHelper;

@SessionAttributes({ "account", "products", "thongTin"})
@Controller
public class AccountController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
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
		
		Date currentDate = DateUtil.getCurrentDate("yyyy-MM-dd");
		modelAndView.addObject("currentDate", currentDate);
		
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
			info.add("Mật khẩu và xác nhận mật khẩu không trùng khớp!");
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
	
	@RequestMapping(method = GET, value="/update")
	protected ModelAndView update(
			@ModelAttribute("login") Login login,
			@ModelAttribute("account") TaiKhoan account,
			@ModelAttribute("taiKhoanRegister") TaiKhoanRegister taiKhoanRegister,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getHoTen() != null) {
			modelAndView.setViewName("account_details");

			TaiKhoan taiKhoan = account;

			modelAndView.addObject("account", taiKhoan);
		
			Date currentDate = DateUtil.getCurrentDate("yyyy-MM-dd");
			modelAndView.addObject("currentDate", currentDate);
		} else {
			TaiKhoan taiKhoan = new TaiKhoan();
			modelAndView.addObject("account", taiKhoan);

			modelAndView.setViewName("login");

		}
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value="/submit-update")
	public ModelAndView submitUpdate(
			@ModelAttribute("taiKhoanRegister") TaiKhoanRegister taiKhoanRegister,
			BindingResult result,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@ModelAttribute("account") TaiKhoan account,
			HttpServletRequest arg0, HttpServletResponse arg1)
			throws IOException, MagicParseException, MagicMatchNotFoundException, MagicException {
		arg1.setContentType("text/html;charset=UTF-8");
		arg0.setCharacterEncoding("UTF-8");

		ModelAndView modelAndView = new ModelAndView();
		int state = 1;
		ArrayList<String> info = new ArrayList<String>();
		TaiKhoan taikhoan = account;
		TaiKhoanDAO helper = new TaiKhoanDAO();
		TaiKhoan oldTaiKhoan = (TaiKhoan)helper.get(taikhoan.getMaTaiKhoan());

		String fileName = "";     
        //Nếu user có update hình
		if(image.getSize() != 0){
			
			InputStream configStream = arg0
	                .getServletContext()
	                .getResourceAsStream("/WEB-INF/config/global-config.properties");
	        Properties _prop = new Properties();
	        _prop.load(configStream);
	        String avatarImagesFolder = _prop.getProperty("AvatarImagesFolder");
	        
			String mimeType = Magic.getMagicMatch(image.getBytes(), false).getMimeType();
			if (mimeType.startsWith("image")) {
				fileName = image.getOriginalFilename();
				taikhoan.setAvatar(fileName);
				
				try{ 
					File newFiles= new File(arg0.getSession().getServletContext().getRealPath(avatarImagesFolder), fileName); 
					FileUtils.writeByteArrayToFile(newFiles,image.getBytes());
					} catch(IOException e){ 
						e.printStackTrace();
					} 
			}
			else
			{
				info.add("Ảnh đại diện phải là hình: có đuôi dạng .jpg, .png, .jpeg !");
				state = 0;
			}
		}
		else
		{
			taikhoan.setAvatar(oldTaiKhoan.getAvatar());
		}

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

		taikhoan.setNgayCapNhat(new Date());

		LoaiTaiKhoan loaitaikhoan = new LoaiTaiKhoan(2);
		taikhoan.setLoaiTaiKhoan(loaitaikhoan);
		
		// Get password info
		String password = taiKhoanRegister.getMatKhau();
		// Check password valid
		if (!password.isEmpty()) {
			// Get confirm password
			String confirm_password = taiKhoanRegister.getXacNhanMatKhau();

			// Check password & confirm password same
			if (!password.equals(confirm_password)) {
				info.add("Mật khẩu và xác nhận mật khẩu không trùng khớp!");
				state = 0;
			}
			else
			{
				taikhoan.setMatKhau(password);
			}
		}
		else
		{
			taikhoan.setMatKhau(oldTaiKhoan.getMatKhau());
		}

		
		
		if(state == 0)
		{	
			Date currentDate = DateUtil.getCurrentDate("yyyy-MM-dd");
			modelAndView.addObject("currentDate", currentDate);
			modelAndView.addObject("state", state);
			modelAndView.addObject("info", info);
			modelAndView.setViewName("account_details");
		}
		else
		{
			helper.saveOrUpdate(taikhoan);	
			Date currentDate = DateUtil.getCurrentDate("yyyy-MM-dd");
			modelAndView.addObject("currentDate", currentDate);
			modelAndView.addObject("account", taikhoan);
			modelAndView.addObject("state", state);
			modelAndView.setViewName("account_details");
		}

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add-comment", produces = "application/json")
	protected @ResponseBody String addComment(@ModelAttribute("account") TaiKhoan account,
			@RequestBody String message, HttpServletRequest arg0,
			HttpServletResponse arg1) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode noiDungNode = rootNode.path("noiDung");
		JsonNode maDoChoiNode = rootNode.path("maDoChoi");
		JsonNode thoiGianNode = rootNode.path("thoiGian");
		long maDoChoi = maDoChoiNode.longValue();
		String noiDung = noiDungNode.textValue();
		String thoiGian = thoiGianNode.textValue();

		BinhLuan binhLuan = new BinhLuan();
		DoChoiDAO doChoiDAO = new DoChoiDAO();
		DoChoi doChoi = doChoiDAO.get(maDoChoi);
		binhLuan.setDoChoi(doChoi);
		binhLuan.setDaXoa(false);
		binhLuan.setKiemDuyet(false);
		binhLuan.setNgayBinhLuan(DateUtil.convertStringToDate(thoiGian, "HH:mm yyyy-MM-dd"));
		binhLuan.setNoiDung(noiDung);
		binhLuan.setTaiKhoan(account);

		BinhLuanDAO commentHelper = new BinhLuanDAO();
		commentHelper.saveOrUpdate(binhLuan);
		int nComments = commentHelper.demSoLuongBinhLuanTheoMaDoChoi(maDoChoi);
		Map<String,Object> messageResult = new HashMap<String,Object>(); 
        messageResult.put("result", "1");
        messageResult.put("nComments", nComments);
        return mapper.writeValueAsString(messageResult);      
	}
	
	@RequestMapping(method = GET, value="/orders-history")
	protected ModelAndView ordersHistory(@ModelAttribute("login") Login login,
			@ModelAttribute("account") TaiKhoan account,
			@RequestParam(value = "trang", required = false) String strTrang,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getMaTaiKhoan() != null) {
			modelAndView.setViewName("orders_history");
			TaiKhoan taiKhoan = account;

			HoaDonDAO hoaDonHelper = new HoaDonDAO();
			ChiTietHoaDonDAO chiTietHoaDonHelper = new ChiTietHoaDonDAO();

			ArrayList<String> ngayMua = new ArrayList<String>();
			ArrayList<String> tenDoChoi = new ArrayList<String>();
			ArrayList<String> donGia = new ArrayList<String>();
			ArrayList<String> soLuong = new ArrayList<String>();
			ArrayList<String> maDonHang = new ArrayList<String>();
			ArrayList<String> trangThai = new ArrayList<String>();
			ArrayList<String> maDoChoi = new ArrayList<String>();
			
			int trang = 1;
			int soLuongDonHangTrenTrang = 5;
			int soLuongKetQua = 0;
			if(strTrang != null && strTrang != "")
			{
				trang = Integer.parseInt(strTrang);
			}
			List<ChiTietHoaDon> dsHoaDon = hoaDonHelper.layDS(taiKhoan
					.getMaTaiKhoan(), trang, soLuongDonHangTrenTrang);
			soLuongKetQua = hoaDonHelper.demSoLuongDonHangTheoTaiKhoan(taiKhoan.getMaTaiKhoan());
			int soLuongTrang;
			if (soLuongKetQua % soLuongDonHangTrenTrang != 0)
	        {
				soLuongTrang = 1;
	        }
			else
			{
				soLuongTrang = soLuongKetQua / soLuongDonHangTrenTrang;
				if ((soLuongKetQua % soLuongDonHangTrenTrang) != 0)
		        {
					soLuongTrang++;
		        }
			}
			
			modelAndView.addObject("soLuongKetQua", soLuongKetQua);
	        modelAndView.addObject("soLuongTrang", soLuongTrang);
	        List<Integer> pageNumbers = PagingHelper.PagingCaculator(trang, soLuongDonHangTrenTrang, soLuongKetQua);
	        modelAndView.addObject("pageNumbers", pageNumbers);
			
			
			for (int i = 0; i < dsHoaDon.size(); i++) {
				ChiTietHoaDon cthd = dsHoaDon.get(i);

				ngayMua.add(cthd.getHoaDon().getNgayLap().toString());
				donGia.add(cthd.getDonGia().toString());
				soLuong.add(cthd.getSoLuong() + "");
				maDonHang.add(cthd.getHoaDon().getMaHoaDon() + "");
				if (cthd.getHoaDon().getDaThanhToan()) {
					trangThai.add("Hoàn tất");
				} else {
					trangThai.add("Chưa hoàn tất");
				}

				tenDoChoi.add(chiTietHoaDonHelper
						.layCTHD(cthd.getMaChiTietHoaDon()).getDoChoi()
						.getTenDoChoi());
				maDoChoi.add(chiTietHoaDonHelper
						.layCTHD(cthd.getMaChiTietHoaDon()).getDoChoi()
						.getMaDoChoi() + "");
			}

			modelAndView.addObject("ngayMua", ngayMua);
			modelAndView.addObject("donGia", donGia);
			modelAndView.addObject("soLuong", soLuong);
			modelAndView.addObject("maDonHang", maDonHang);
			modelAndView.addObject("trangThai", trangThai);
			modelAndView.addObject("tenDoChoi", tenDoChoi);
			modelAndView.addObject("maDoChoi", maDoChoi);
		} else {
			TaiKhoan taiKhoan = new TaiKhoan();
			modelAndView.addObject("account", taiKhoan);

			modelAndView.setViewName("login");

		}

		return modelAndView;
	}
	
	//Thêm đồ chơi vào giỏ hàng
	@RequestMapping(method = RequestMethod.POST, value="/add-products")
	protected ModelAndView addProducts(
			@ModelAttribute("sanPham") Product product, BindingResult result,
			@ModelAttribute("products") ArrayList<Product> products,
			@RequestParam(value = "maDoChoi", required = false) String strMaDoChoi,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (strMaDoChoi != null && strMaDoChoi != "") {
			long maDoChoi = Long.parseLong(strMaDoChoi);
			DoChoiDAO doChoiDAO = new DoChoiDAO();
			DoChoi doChoi = doChoiDAO.get(maDoChoi);
			
			boolean isDaCo = false;
			for(int i = 0; i< products.size(); i++)
			{
				if(products.get(i).getDoChoi().getMaDoChoi() == doChoi.getMaDoChoi())
				{
					int soLuongMuaCu = products.get(i).getSoLuongMua();
					int soLuongMuaMoi = soLuongMuaCu + product.getSoLuongMua();
					products.get(i).setSoLuongMua(soLuongMuaMoi);
					products.get(i).setTongTien(soLuongMuaMoi * doChoi.getGiaBanHienTai().intValueExact());
					isDaCo = true;
				}
			}
			if(isDaCo == false)
			{
				product.setDoChoi(doChoi);
				long tongTien = doChoi.getGiaBanHienTai().intValueExact()
						* product.getSoLuongMua();
				product.setTongTien(tongTien);
				products.add(product);
			}
			DoChoi dc = new DoChoi();
			modelAndView.addObject("products", products);
			modelAndView.addObject("doChoi", dc);

			long thanhTien = 0;
			for (int i = 0; i < products.size(); i++) {
				thanhTien = thanhTien + products.get(i).getTongTien();
			}

			modelAndView.addObject("thanhTien", thanhTien);
		}

		modelAndView.setViewName("carts");

		return modelAndView;
	}
	
	//Xem giỏ hàng
	@RequestMapping(method = GET, value="/carts")
	protected ModelAndView carts(@ModelAttribute("login") Login login,
			@ModelAttribute("account") TaiKhoan account,
			@ModelAttribute("products") ArrayList<Product> products,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getMaTaiKhoan() != null) {
			if (products.size() != 0) {
				modelAndView.setViewName("carts");
				modelAndView.addObject("account", account);
				modelAndView.addObject("products", products);

				long thanhTien = 0;
				for (int i = 0; i < products.size(); i++) {
					thanhTien = thanhTien + products.get(i).getTongTien();
				}

				modelAndView.addObject("thanhTien", thanhTien);
			} else {
				modelAndView.setViewName("index");
			}
		} else {
			TaiKhoan taiKhoan = new TaiKhoan();
			modelAndView.addObject("account", taiKhoan);

			modelAndView.setViewName("login");

		}

		return modelAndView;
	}
	
	//Cập nhật giỏ hàng
	@RequestMapping(method = RequestMethod.POST, value="/update-cart",produces = "application/json")
	protected @ResponseBody
	String updateCart(@ModelAttribute("products") ArrayList<Product> products,
			@RequestBody String message, HttpServletRequest arg0,
			HttpServletResponse arg1) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> listNode = mapper.readValue(message,
				new TypeReference<List<JsonNode>>() {
				});

		if (products.size() == listNode.size()) {
			for (int i = 0; i < listNode.size(); i++) {
				JsonNode soLuongNode = listNode.get(i);

				JsonNode valueNode = soLuongNode.path("soLuong");
				String soLuong = valueNode.asText();

				products.get(i).setSoLuongMua(Integer.parseInt(soLuong));

				long tongTien = Integer.parseInt(soLuong)
						* Long.parseLong(products.get(i).getDoChoi()
								.getGiaBanHienTai()
								+ "");
				products.get(i).setTongTien(tongTien);
			}
		}

		arg0.getSession().setAttribute("products", products);
		return mapper.writeValueAsString("ABC");
	}
	
	//Xóa 1 đồ chơi trong giỏ hàng
	@RequestMapping(method = RequestMethod.POST, value = "/remove-toy", produces = "application/json")
	protected @ResponseBody
	String removeToy(@ModelAttribute("products") ArrayList<Product> products,
			@RequestBody String message, HttpServletRequest arg0,
			HttpServletResponse arg1) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(message);
		JsonNode doChoiNode = rootNode.path("maDoChoi");
		long maDoChoi = doChoiNode.longValue();
		
		for (int j = 0; j < products.size(); j++) {
			if (maDoChoi == products.get(j).getDoChoi().getMaDoChoi()) {
				products.remove(j);
			}
		}
		long thanhTien = 0;
		for (int i = 0; i < products.size(); i++) {
			thanhTien = thanhTien + products.get(i).getTongTien();
		}
		
		Map<String,Object> messageResult = new HashMap<String,Object>(); 
        messageResult.put("minicart", products.size());
        messageResult.put("thanhTien", thanhTien);
        return mapper.writeValueAsString(messageResult);    
	}
	
	//Dắt tới trang thanh toán
	@RequestMapping(method = GET, value="/checkout")
	protected ModelAndView checkout(@ModelAttribute("login") Login login,
			@ModelAttribute("thanhToan") ThanhToan thanhToan,
			BindingResult result,
			@ModelAttribute("products") ArrayList<Product> products,
			@ModelAttribute("account") TaiKhoan account,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getMaTaiKhoan() != null) {
			modelAndView.setViewName("checkout");

			modelAndView.addObject("account", account);
			modelAndView.addObject("products", products);

			long thanhTien = 0;
			for (int i = 0; i < products.size(); i++) {
				thanhTien = thanhTien + products.get(i).getTongTien();
			}

			modelAndView.addObject("thanhTien", thanhTien);
		} else {
			TaiKhoan taiKhoan = new TaiKhoan();
			modelAndView.addObject("account", taiKhoan);

			modelAndView.setViewName("login");

		}

		return modelAndView;
	}
	
	//Thanh toán khi nhận hàng
	@RequestMapping(method = RequestMethod.POST, value="/checkout-cart")
	protected ModelAndView checkoutCart(@ModelAttribute("login") Login login,
			@ModelAttribute("thanhToan") ThanhToan thanhToan,
			BindingResult result,
			@ModelAttribute("products") ArrayList<Product> products,
			@ModelAttribute("account") TaiKhoan account,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();

		if (account.getMaTaiKhoan() != null) {
			modelAndView.setViewName("checkout");
			modelAndView.addObject("account", account);
			modelAndView.addObject("products", products);

			long thanhTien = 0;
			for (int i = 0; i < products.size(); i++) {
				thanhTien = thanhTien + products.get(i).getTongTien();
			}

			modelAndView.addObject("thanhTien", thanhTien);

			HoaDon hoaDon = new HoaDon();
			hoaDon.setDaThanhToan(false);
			hoaDon.setDaXoa(false);
			hoaDon.setDiaChiGiaoHang(thanhToan.getDiaChiNhanChinh());
			hoaDon.setNgayCapNhat(new Date());
			hoaDon.setNgayLap(new Date());
			hoaDon.setTaiKhoan(account);
			hoaDon.setTenKhachHang(thanhToan.getNguoiNhan());
			hoaDon.setTongTien(BigDecimal.valueOf(thanhTien));
			hoaDon.setTrangThaiDonHang(new TrangThaiDonHang(1, "Đã hoàn tất"));

			HoaDonDAO hoaDonHelper = new HoaDonDAO();
			ChiTietHoaDonDAO chiTietHoaDonHelper = new ChiTietHoaDonDAO();

			hoaDonHelper.saveOrUpdate(hoaDon);

			for (int i = 0; i < products.size(); i++) {
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();

				chiTietHoaDon.setDaXoa(false);
				chiTietHoaDon.setDoChoi(products.get(0).getDoChoi());
				chiTietHoaDon.setDonGia(BigDecimal.valueOf(products.get(0)
						.getTongTien()));
				chiTietHoaDon.setHoaDon(hoaDon);
				chiTietHoaDon.setNgayCapNhat(new Date());
				chiTietHoaDon.setSoLuong(products.get(0).getSoLuongMua());

				chiTietHoaDonHelper.saveOrUpdate(chiTietHoaDon);
				
				//Cập nhật số lượng đã bán và tồn
				DoChoi doChoi = products.get(0).getDoChoi();
				int oldSoLuongDaBan = doChoi.getSoLuongDaBan();
				int oldSoLuongTon = doChoi.getSoLuongTon();
				int newSoLuongDaBan = oldSoLuongDaBan + products.get(0).getSoLuongMua();
				int newSoLuongTon = oldSoLuongTon - products.get(0).getSoLuongMua();
				doChoi.setSoLuongDaBan(newSoLuongDaBan);
				doChoi.setSoLuongTon(newSoLuongTon);
				DoChoiDAO doChoiHelper = new DoChoiDAO();
				doChoiHelper.saveOrUpdate(doChoi);
			}
			modelAndView.addObject("result", 1);
			
			//Xóa giỏ hàng
			products.clear();
			modelAndView.addObject("products", products);
		} else {
			TaiKhoan taiKhoan = new TaiKhoan();
			modelAndView.addObject("account", taiKhoan);

			modelAndView.setViewName("login");

		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkout-paypal", produces = "application/json")
	protected @ResponseBody
	String checkoutPaypal(@ModelAttribute("account") TaiKhoan account,
			@ModelAttribute("products") ArrayList<Product> products,
			@RequestBody String message, HttpServletRequest arg0,
			HttpServletResponse arg1) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readValue(message,
				new TypeReference<JsonNode>() {
				});

		JsonNode soLuongNode = node;
		JsonNode valueNode = soLuongNode.path("hoTen");
		String hoTen = valueNode.asText();
		valueNode = soLuongNode.path("diaChi");
		String diaChi = valueNode.asText();

		ThanhToan thanhToan = new ThanhToan();
		thanhToan.setDiaChiNhanChinh(diaChi);
		thanhToan.setNguoiNhan(hoTen);

		arg0.getSession().setAttribute("thongTin", thanhToan);
		return mapper.writeValueAsString("ABC");
	}
	
	@RequestMapping(method = GET, value="/checkout-result",params = { "state" })
	protected ModelAndView checkoutPaypalResult(
			@ModelAttribute("thongTin") ThanhToan thanhToan,
			BindingResult result, @RequestParam(value = "state") String state,
			@ModelAttribute("products") ArrayList<Product> products,
			@ModelAttribute("account") TaiKhoan account,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		ModelAndView modelAndView = new ModelAndView();
		long thanhTien = 0;
		for (int i = 0; i < products.size(); i++) {
			thanhTien = thanhTien + products.get(i).getTongTien();
		}

		if (state.compareTo("success") == 0) {
			HoaDon hoaDon = new HoaDon();
			hoaDon.setDaThanhToan(true);
			hoaDon.setDaXoa(false);
			hoaDon.setDiaChiGiaoHang(thanhToan.getDiaChiNhanChinh());
			hoaDon.setNgayCapNhat(new Date());
			hoaDon.setNgayLap(new Date());
			hoaDon.setTaiKhoan(account);
			hoaDon.setTenKhachHang(thanhToan.getNguoiNhan());
			hoaDon.setTongTien(BigDecimal.valueOf(thanhTien));
			hoaDon.setTrangThaiDonHang(new TrangThaiDonHang(1, "Đã hoàn tất"));

			HoaDonDAO hoaDonHelper = new HoaDonDAO();
			ChiTietHoaDonDAO chiTietHoaDonHelper = new ChiTietHoaDonDAO();

			hoaDonHelper.saveOrUpdate(hoaDon);

			for (int i = 0; i < products.size(); i++) {
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();

				chiTietHoaDon.setDaXoa(false);
				chiTietHoaDon.setDoChoi(products.get(0).getDoChoi());
				chiTietHoaDon.setDonGia(BigDecimal.valueOf(products.get(0)
						.getTongTien()));
				chiTietHoaDon.setHoaDon(hoaDon);
				chiTietHoaDon.setNgayCapNhat(new Date());
				chiTietHoaDon.setSoLuong(products.get(0).getSoLuongMua());

				chiTietHoaDonHelper.saveOrUpdate(chiTietHoaDon);
				
				//Cập nhật số lượng đã bán và tồn
				DoChoi doChoi = products.get(0).getDoChoi();
				int oldSoLuongDaBan = doChoi.getSoLuongDaBan();
				int oldSoLuongTon = doChoi.getSoLuongTon();
				int newSoLuongDaBan = oldSoLuongDaBan + products.get(0).getSoLuongMua();
				int newSoLuongTon = oldSoLuongTon - products.get(0).getSoLuongMua();
				doChoi.setSoLuongDaBan(newSoLuongDaBan);
				doChoi.setSoLuongTon(newSoLuongTon);
				DoChoiDAO doChoiHelper = new DoChoiDAO();
				doChoiHelper.saveOrUpdate(doChoi);
			}

			
			//Xóa giỏ hàng
			products.clear();
			modelAndView.addObject("products", products);
			modelAndView.setViewName("redirect:/home/index");
		} else {

			modelAndView.setViewName("checkout");
			modelAndView.addObject("products", products);
			modelAndView.addObject("thanhTien", thanhTien);
		}

		modelAndView.addObject("account", account);
		return modelAndView;
	}
}
