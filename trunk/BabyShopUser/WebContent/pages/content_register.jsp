<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tlds/captcha.tld" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="captchaPublicKey" value="${requestScope.captchaPublicKey}" />
<c:set var="captchaPrivateKey" value="${requestScope.captchaPrivateKey}" />
<c:set var="currentDate" value="${requestScope.currentDate}" />
<c:set var="monthList" value="${requestScope.monthList}" />
<c:set var="provinceList" value="${requestScope.provinceList}" />

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
								<c:forEach var="i" begin="0" end="11">
									<option value="${i+1}">&nbsp;${monthList.get(i)}</option>
								</c:forEach>								
							</select>
							<select id="dropdown-select" name="yy">
								<option value="-1">[Năm]</option>
								<c:forEach var="i" begin="1900" end="${currentDate.getYear() + 1900}" step="1">
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
								<c:forEach var="i" begin="0" end="62">
									<option value="${provinceList.get(i)}">&nbsp;${provinceList.get(i)}</option>
								</c:forEach>								
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