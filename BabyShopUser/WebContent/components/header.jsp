<%-- 
    Document   : header
    Created on : Oct 29, 2012, 10:31:08 AM
    Author     : TrongKhoa
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="dsLoaiDoChoi" value="${requestScope.dsLoaiDoChoi}" />
<c:set var="dsNhaSanXuat" value="${requestScope.dsNhaSanXuat}" />
<c:set var="homeController" value="${requestScope.HomeController}"/>
<c:set var="toysController" value="${requestScope.ToysController}"/>
<c:set var="accountsController" value="${requestScope.AccountsController}"/>
<!-- Login Account -->
<c:set var="account" value="${sessionScope.account}" />
<c:set var="products" value="${sessionScope.products}" />

<!-- TOPBAR -->
<div id="top">
	
    <div class="topbar-left">
        <p>Shop đồ chơi LEGO <a href="${homeController}/index">http://www.shoplego.vn</a> </p>
    </div>
    <!-- .topbar-left -->
    <div class="topbar-right">
        <ul class="topbar-member-area">
            <%@include file="/components/quick_login.jsp" %>
        </ul>
    </div>
    <!-- .topbar-right -->
    <div id="cart"> 
    	<a href="#"> <span class="minicart">3 sản phẩm </span> </a> 
    </div>
    <c:if test="${account.getMaTaiKhoan() != null}">
			<c:if test="${products.size() != 0}">
				<div id="cart">
					<a href="${accountsController}carts"> <span class="minicart">${products.size()} sản phẩm </span>
					</a>
				</div>
			</c:if>
			<c:if test="${products.size() == 0}">
				<div id="cart">
					<a href="${homeController}index"> <span class="minicart">0 sản phẩm </span>
					</a>
				</div>
			</c:if>
		</c:if>
		<c:if test="${account.getMaTaiKhoan() == null}">
			<div id="cart">
				<a href="${accountsController}login"> <span class="minicart">0 sản phẩm </span>
				</a>
			</div>
	</c:if>
    <!-- #cart --> 

</div>
<!-- END TOPBAR -->
<div class="group inner"> 
    <!-- START LOGO -->
    <div id="logo" class="group" > <a href="${homeController}index"> <img alt="Logo LEGO Shop" src="${pageContext.request.contextPath}/images/shared-images/logo/logo.png"> </a>
        <p class="logo-description">Luôn dành sự yêu thương tốt nhất cho các bé</p>
    </div>
    <!-- END LOGO --> 

    <!-- START SEARCHFORM -->
        <div id="quick-search-form">
            <input type="text" id="search-text" name="searchText" value="" placeholder="Tên đồ chơi...">
            <input type="button" value="" id="search-button" onclick="basicSearch('${homeController}')">          
        </div>
        <script>
        	function basicSearch(homeController)
        	{
            	//Initialiaze page = 1;
            	var trang = 1;
        		var searchText = $('#search-text').val();
        		var url = homeController + 'search?searchText=' + searchText + '&trang=' + trang;
        		document.location = url;
            }
        </script>
    <!-- END SEARCHFORM --> 
    <!-- START NAV -->
    <div class="group" id="nav">
        <ul class="level-1" id="menu-menu">
            <li> <a href="${homeController}index">trang chủ</a> </li>
            <li class="megamenu"> <a href="">mua sắm<span class="sf-sub-indicator"> »</span></a>
                <ul class="sub-menu">
                    <li> <a href="#">loại đồ chơi</a>
                        <ul class="sub-menu">
                            <!--XUẤT DANH SÁCH LOẠI ĐỒ CHƠI-->
                            <c:forEach var="ldc" items="${dsLoaiDoChoi}">
                                <li>
                                    <a href="${toysController}list?loaiDoChoi=${ldc.maLoaiDoChoi}">
                                        <c:out value="${ldc.tenLoaiDoChoi}"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li> <a href="#">nhà sản xuất</a>
                        <ul class="sub-menu">
                            <!--XUẤT DANH SÁCH NHÀ SẢN XUẤT-->
                            <c:forEach var="nxs" items="${dsNhaSanXuat}">
                                <li>
                                    <a href="${toysController}list?nhaSanXuat=${nxs.maNhaSanXuat}">
                                        <c:out value="${nxs.tenNhaSanXuat}"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li> <a href="#">giá</a>
                        <ul class="sub-menu">
                            <li><a href="do_choi.do?action=views_by_groups?minPrice=10000&maxPrice=99000">10,000đ - 99,000đ</a></li>
                            <li><a href="do_choi.do?action=views_by_groups?minPrice=100000&maxPrice=499000">100,000đ - 499,000đ</a></li>
                            <li><a href="do_choi.do?action=views_by_groups?minPrice=500000&maxPrice=999000">500,000đ - 999,000đ</a></li>
                            <li><a href="do_choi.do?action=views_by_groups?minPrice=1000000">&gt; 1,000,000đ</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li> <a href="${homeController}orderGuide">hướng dẫn<span class="sf-sub-indicator"> »</span></a>
                <ul class="sub-menu">
                    <li><a href="${homeController}orderGuide">đặt hàng</a></li>
                    <li><a href="${homeController}paymentGuide">thanh toán</a></li>
                </ul>
            </li>
            <li> <a href="${homeController}promotion">khuyến mãi<span class="sf-sub-indicator"> »</span></a>
                <ul class="sub-menu">
                    <li><a href="${homeController}promotion">sản phẩm khuyến mãi</a></li>
                    <li><a href="#">đăng ký nhận tin (Chưa có hoặc sẽ bỏ)</a></li>
                </ul>
            </li>
            <li> <a href="#">tài khoản<span class="sf-sub-indicator"> »</span></a>
                <ul class="sub-menu">
                	<c:if test="${account.getMaTaiKhoan() != null}">
                		<li><a href="${accountsController}update">thay đổi thông tin</a></li>
                		<li><a href="${accountsController}orders-history">lịch sử mua
									hàng </a></li>
						<li><a href="${accountsController}cart">giỏ hàng </a></li>
						<li><a href="${accountsController}login">đăng xuất </a></li>
                	</c:if>
                	<c:if test="${account.getMaTaiKhoan() == null}">
                		<li><a href="${accountsController}register">đăng ký </a></li>
						<li><a href="${accountsController}login">đăng nhập </a></li>
					</c:if>
                	<!--
                    <li><a href="#">thanh toán</a></li>
                    <li><a href="#">hóa đơn mua hàng</a></li>
                    <li> <a href="#">đổi mật khẩu </a></li>
                    <li><a href="#">thay đổi thông tin</a></li>
                    -->
                </ul>
            </li>
            <li> <a href="${homeController}contact">liên hệ</a></li>
        </ul>
    </div>
    <!-- END NAV --> 
</div>