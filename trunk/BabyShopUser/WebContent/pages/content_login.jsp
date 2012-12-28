<%-- 
    Document   : content_login
    Created on : Oct 29, 2012, 10:43:58 AM
    Author     : TrongKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="highlighted-title">Đăng nhập</h2>
<form id="frmLogin" action="#" method="post">
    <table>
        <tr>
            <td><input id="username" name="username" type="text" class="nice-textbox" style="width:230px;" placeholder="Tên đăng nhập"/></td>
        </tr>
        <tr>
            <td><input id="password" name="password" type="text" class="nice-textbox" style="width:230px;" placeholder="Mật khẩu"/></td>
        </tr>
        <tr>
            <td><input type="checkbox" class="checkbox" name="remember"/>
                Ghi nhớ đăng nhập <br/>
                <a href="#">Quên mật khẩu</a>|<a href="#">Đăng ký tài khoản</a>
            </td>

        </tr>
        <tr>
            <td><button id="btnLogin" class="rectangular-button">Đăng Nhập</button></td>
        </tr>
    </table>
</form>