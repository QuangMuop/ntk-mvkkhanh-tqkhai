<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tlds/captcha.tld" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="captchaPublicKey" value="${requestScope.captchaPublicKey}" />
<c:set var="captchaPrivateKey" value="${requestScope.captchaPrivateKey}" />

<h2 class="highlighted-title">Đăng ký thành viên</h2>
<h4>Những thông tin có đánh dấu (*) là bắt buộc</h4>
	<div align="center" class="div-register">
		<c:if test="${state == 0}">
			<c:forEach var="i" items="${info}">
				<div>
					<p>
						<font color="red">${i}</font>
					</p>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${state == 1}">
			<script>
				alert('Đăng ký thành công!');
			</script>
		</c:if>
	</div>
	<form:form modelAttribute="taiKhoanRegister" method="post"
		action="submit-register" name="frmRegister" id="frmRegister">
		<fieldset>
			<div class="div-register">
				<table>
					<tr>
						<td><span class="myh3">Thông tin cá nhân</span></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Họ tên của bạn</td>
						<td><form:input path="hoTen" name="inputHoTen"
								id="inputHoTen" maxlength="100" class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Ngày sinh</td>
						<td>
							<select id="dropdown-select" name="dd">
								<option value="-1">[Ngày]</option>
								<c:forEach var="i" begin="1" end="31" step="1">
									<option value="${i}">&nbsp;${i}</option>
								</c:forEach>
							</select>
							<select id="dropdown-select" name="mm">
								<option value="-1">[Tháng]</option>
								<option value="1">&nbsp;Tháng một</option>
								<option value="2">&nbsp;Tháng hai</option>
								<option value="3">&nbsp;Tháng ba</option>
								<option value="4">&nbsp;Tháng tư</option>
								<option value="5">&nbsp;Tháng năm</option>
								<option value="6">&nbsp;Tháng sáu</option>
								<option value="7">&nbsp;Tháng bảy</option>
								<option value="8">&nbsp;Tháng tám</option>
								<option value="9">&nbsp;Tháng chín</option>
								<option value="10">&nbsp;Tháng mười</option>
								<option value="11">&nbsp;Tháng mười một</option>
								<option value="12">&nbsp;Tháng mười hai</option>
							</select>
							<select id="dropdown-select" name="yy">
								<option value="-1">[Năm]</option>
								<c:forEach var="i" begin="1900" end="2012" step="1">
									<option value="${i}">&nbsp;${i}</option>
								</c:forEach>
							</select>
							</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Giới tính</td>
						<td>
							<select id="dropdown-select" name="gioiTinh">
								<option value="-1">[Giới tính]</option>
								<option value="1">Nam</option>
								<option value="2">Nữ</option>
							</select>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Bạn sống tại</td>
						<td>
							<select id="dropdown-select" name="thanhPho">
								<option value="-1">--Chọn thành phố--</option>
								<option value="An Giang">&nbsp;An Giang</option>
								<option value="Bà Rịa Vũng Tàu">&nbsp;Bà Rịa Vũng Tàu</option>
								<option value="Bắc Cạn">&nbsp;Bắc Cạn</option>
								<option value="Bắc Giang">&nbsp;Bắc Giang</option>
								<option value="Bạc Liêu">&nbsp;Bạc Liêu</option>
								<option value="Bắc Ninh">&nbsp;Bắc Ninh</option>
								<option value="Bến Tre">&nbsp;Bến Tre</option>
								<option value="Bình Định">&nbsp;Bình Định</option>
								<option value="Bình Dương">&nbsp;Bình Dương</option>
								<option value="Bình Phước">&nbsp;Bình Phước</option>
								<option value="Bình Thuận">&nbsp;Bình Thuận</option>
								<option value="Cà Mau">&nbsp;Cà Mau</option>
								<option value="Cần Thơ">&nbsp;Cần Thơ</option>
								<option value="Cao Bằng">&nbsp;Cao Bằng</option>
								<option value="Đà Nẵng">&nbsp;Đà Nẵng</option>
								<option value="Đăk Lăk">&nbsp;Đăk Lăk</option>
								<option value="Điện Biên">&nbsp;Điện Biên</option>
								<option value="Đồng Nai">&nbsp;Đồng Nai</option>
								<option value="Đồng Tháp">&nbsp;Đồng Tháp</option>
								<option value="Gia Lai">&nbsp;Gia Lai</option>
								<option value="Hà Đông">&nbsp;Hà Đông</option>
								<option value="Hà Giang">&nbsp;Hà Giang</option>
								<option value="Hạ Long">&nbsp;Hạ Long</option>
								<option value="Hà Nam">&nbsp;Hà Nam</option>
								<option value="Hà Nội">&nbsp;Hà Nội</option>
								<option value="Hà Tây">&nbsp;Hà Tây</option>
								<option value="Hà Tĩnh">&nbsp;Hà Tĩnh</option>
								<option value="Hải Dương">&nbsp;Hải Dương</option>
								<option value="Hải Phòng">&nbsp;Hải Phòng</option>
								<option value="Hồ Chí Minh">&nbsp;Hồ Chí Minh</option>
								<option value="Hòa Bình">&nbsp;Hòa Bình</option>
								<option value="Hưng Yên">&nbsp;Hưng Yên</option>
								<option value="Khánh Hòa">&nbsp;Khánh Hòa</option>
								<option value="Kiên Giang">&nbsp;Kiên Giang</option>
								<option value="KonTum">&nbsp;KonTum</option>
								<option value="Lai Châu">&nbsp;Lai Châu</option>
								<option value="Lâm Đồng">&nbsp;Lâm Đồng</option>
								<option value="Lạng Sơn">&nbsp;Lạng Sơn</option>
								<option value="Lào Cai">&nbsp;Lào Cai</option>
								<option value="Long An">&nbsp;Long An</option>
								<option value="Nam Định">&nbsp;Nam Định</option>
								<option value="Nghệ An">&nbsp;Nghệ An</option>
								<option value="Ninh Bình">&nbsp;Ninh Bình</option>
								<option value="Ninh Thuận">&nbsp;Ninh Thuận</option>
								<option value="Phú Thọ">&nbsp;Phú Thọ</option>
								<option value="Phú Yên">&nbsp;Phú Yên</option>
								<option value="Quảng Bình">&nbsp;Quảng Bình</option>
								<option value="Quảng Nam">&nbsp;Quảng Nam</option>
								<option value="Quảng Ngãi">&nbsp;Quảng Ngãi</option>
								<option value="Quảng Ninh">&nbsp;Quảng Ninh</option>
								<option value="Quảng Trị">&nbsp;Quảng Trị</option>
								<option value="Sóc Trăng">&nbsp;Sóc Trăng</option>
								<option value="Sơn La">&nbsp;Sơn La</option>
								<option value="Tây Ninh">&nbsp;Tây Ninh</option>
								<option value="Thái Bình">&nbsp;Thái Bình</option>
								<option value="Thái Nguyên">&nbsp;Thái Nguyên</option>
								<option value="Thanh Hóa">&nbsp;Thanh Hóa</option>
								<option value="Thừa Thiên Huế">&nbsp;Thừa Thiên Huế</option>
								<option value="Trà Vinh">&nbsp;Trà Vinh</option>
								<option value="Tuyên Quang">&nbsp;Tuyên Quang</option>
								<option value="Vĩnh Phúc">&nbsp;Vĩnh Long</option>
								<option value="">&nbsp;Vĩnh Phúc</option>
								<option value="Yên Bái">&nbsp;Yên Bái</option>
								<option value="Nơi khác">&nbsp;Nơi khác</option>
							</select>
							</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Email</td>
						<td><form:input path="email" name="inputemail"
								id="inputemail" maxlength="100" class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Điện thoại</td>
						<td><form:input path="dienThoai" name="inputphone"
								id="inputphone" maxlength="100" class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td><span class="myh3">Thông tin tài khoản</span></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Tên đăng nhập *</td>
						<td><form:input path="maTaiKhoan" name="inputusername"
								class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Mật khẩu *</td>
						<td><form:password path="matKhau" name="password"
								class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Xác nhận mật khẩu *</td>
						<td><form:password path="xacNhanMatKhau"
								name="confirm_password" class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Mã kiểm tra</td>
						<td>
							<captcha:captcha themeName="clean" publickey="${captchaPublicKey}" privatekey="${captchaPrivateKey}"/>
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" align="center">Bằng việc nhấn vào "Tạo tài
							khoản" là bạn đồng ý với <a href="#" target="_blank">Thỏa
								thuận sử dụng LEGO Shop</a>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit"
							value="Tạo tài khoản" class="rectangular-button" /></td>
					</tr>
				</table>
			</div>
		</fieldset>
	</form:form>