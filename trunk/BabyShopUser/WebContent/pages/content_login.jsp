<%-- 
    Document   : content_login
    Created on : Oct 29, 2012, 10:43:58 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h2 class="highlighted-title">Đăng nhập</h2>
	<form:form modelAttribute="login" id="frmLogin" action="submit-login" method="post">
		<fieldset>
			<table>
				<tr>
					<td><form:input path="username" id="inputusername" name="inputusername"
							class="nice-textbox" style="width: 230px;"
							placeholder="Tên đăng nhập" /></td>
				</tr>
				<tr>
					<td>
					<input type="password" name="password" class="nice-textbox" style="width: 230px;" placeholder="Mật khẩu" />
				</tr>
				<!--
				<tr>
					<td><form:checkbox class="checkbox" path="remember"
							name="remember" /> Ghi nhớ đăng nhập <br /> <a href="#">Quên
							mật khẩu</a>|<a href="/BabyShop/babyShop/register">Đăng ký tài khoản</a></td>
				</tr>
				-->
				<c:if test="${state == 0}">
					<tr align="center">
						<td><font color="red"><strong><b>Tên tài
										khoản hoặc password không chính xác!</b></strong></font></td>
					</tr>
				</c:if>

				<tr>
					<td><button id="btnLogin" type="submit"
							class="rectangular-button">Đăng Nhập</button></td>
				</tr>
			</table>
		</fieldset>
	</form:form>