<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="avatarImagesFolder" value="${requestScope.AvatarImagesFolder}"/>
<c:set var="currentDate" value="${requestScope.currentDate}" />
<c:set var="monthList" value="${requestScope.monthList}" />
<c:set var="provinceList" value="${requestScope.provinceList}" />
<!-- Login Account -->
<c:set var="account" value="${sessionScope.account}" />

<h2 class="highlighted-title">Cập nhật thông tin tài khoản</h2>
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
			<div>
					<p>
						<font color="blue">CẬP NHẬT THÀNH CÔNG</font>
					</p>
				</div>
		</c:if>
	</div>
	<form:form modelAttribute="taiKhoanRegister" method="post" enctype="multipart/form-data"
		action="submit-update" name="frmUpdate" id="frmUpdate">
		<fieldset>
			<div class="div-register">
				<table>
				<tr>
                    <td><span class="myh3">Thông tin cá nhân</span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Ảnh đại diện </td>
                    <td><img id="avatar" style="width:50px; height:50px;padding:5px; border:1px solid #DDDDDD" src="${pageContext.request.contextPath}${avatarImagesFolder}${account.avatar}" /><input name="image" type="file" value="Thay đổi" class="rectangular-button"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Họ tên của bạn </td>
                    <c:if test="${account != null}">
                        <td><form:input path="hoTen" value="${account.getHoTen()}" type="text" name="fullname" id="fullname" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                        <c:if test="${account == null}">
                        <td><form:input path="hoTen" type="text" name="fullname" id="fullname" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Ngày sinh </td>
                    <td><form:select path="dd" name="dd" id="dropdown-select">>
                            <c:forEach var="i" begin="1" end="31" >
                                <c:if test="${account.getNgaySinh().getDate() == i}">
                                    <form:option selected="selected" value="${i}">&nbsp;${i}</form:option>
                                </c:if>
                                <c:if test="${account.getNgaySinh().getDate() != i}">
                                    <form:option value="${i}">&nbsp;${i}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                        <form:select path="mm" name="mm" id="dropdown-select">                            
                            <c:forEach var="i" begin="0" end="11">
                                <c:if test="${account.getNgaySinh().getMonth() == i}">
                                    <form:option selected="selected" value="${i+1}">&nbsp;${monthList.get(i)}</form:option>
                                </c:if>
                                <c:if test="${account.getNgaySinh().getMonth() != i}">
                                    <form:option value="${i+1}">&nbsp;${monthList.get(i)}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                        <form:select path="yy" name="yy" id="dropdown-select">
                            <c:forEach var="i" begin="1900" end="${currentDate.getYear() + 1900}" >
                                <c:if test="${account.getNgaySinh().getYear() + 1900 == i}">
                                    <form:option selected="selected" value="${i}">&nbsp;${i}</form:option>
                                </c:if>
                                <c:if test="${account.getNgaySinh().getYear() + 1900 != i}">
                                    <form:option value="${i}">&nbsp;${i}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Giới tính </td>
                    <td><form:select path="gioiTinh" name="gender" id="dropdown-select">
                            <c:if test="${account.getGioiTinh() == true}">
                                <form:option selected="selected" value="1">Nam</form:option>
                                <form:option value="2">Nữ</form:option>
                            </c:if>
                            <c:if test="${account.getGioiTinh() != true}">
                                <form:option value="1">Nam</form:option>
                                <form:option selected="selected" value="2">Nữ</form:option>
                            </c:if>                                                       
                        </form:select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Bạn sống tại </td>
                    <td><form:select path="thanhPho" name="place" id="dropdown-select">
                            <c:forEach var="i" begin="0" end="62">
                                <c:if test="${account.getThanhPho().trim().equals(provinceList.get(i))}">
                                    <form:option selected="selected" value="${provinceList.get(i)}">&nbsp;${provinceList.get(i)}</form:option>
                                </c:if>
                                <c:if test="${!account.getThanhPho().trim().equals(provincelist.get(i))}">
                                    <form:option value="${provinceList.get(i)}">&nbsp;${provinceList.get(i)}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Email </td>
                    <c:if test="${account != null}">
                        <td><form:input path="email" value="${account.getEmail()}" name="email" id="email" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                        <c:if test="${account == null}">
                        <td><form:input path="email" name="email" id="email" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">Điện thoại </td>
                    <c:if test="${account != null}">
                        <td><form:input path="dienThoai" value="${account.getDienThoai()}" name="phone" id="phone" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                        <c:if test="${account == null}">
                        <td><form:input path="dienThoai" name="phone" id="phone" maxlength="100" class="nice-textbox input280"/></td>
                        </c:if>
                    <td></td>
                </tr>
                <tr>
                    <td><span class="myh3">Cập nhật mật khẩu</span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
						<td align="right">Mật khẩu mới</td>
						<td><form:password path="matKhau" name="password"
								class="nice-textbox input280" /></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Xác nhận mật khẩu mới</td>
						<td><form:password path="xacNhanMatKhau"
								name="confirm_password" class="nice-textbox input280" /></td>
						<td></td>
					</tr>
                <tr>
                    <td colspan="3" align="center">
                        <input type="submit" value="Cập nhật" class="rectangular-button">
                </tr>
				</table>
			</div>
		</fieldset>
	</form:form>