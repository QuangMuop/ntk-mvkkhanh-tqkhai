<%-- 
    Document   : footer
    Created on : Oct 29, 2012, 10:31:12 AM
    Author     : TrongKhoa
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="dsBinhLuanMoiNhat" value="${requestScope.dsBinhLuanMoiNhat}"/>
<c:set var="avatarImagesFolder" value="${requestScope.AvatarImagesFolder}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>
<div class="group inner footer_cols_4">
    <div class="widget">
        <h3>Bình luận mới</h3>
        <div class="recent-comments group">
            <c:forEach var="bl" items="${dsBinhLuanMoiNhat}">
                <div class="recent-comment group">
                    <div class="user-avatar"><img alt="Comment" src="${pageContext.request.contextPath}${avatarImagesFolder}<c:out value="${bl.taiKhoan.avatar}"/>"></div>
                    <a><c:out value="${bl.noiDung}"/></a>
                    <p class="post-date"><fmt:formatDate value="${bl.ngayBinhLuan}" pattern="HH:mm dd/MM/yyyy"/></p>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="widget">
        <h3>Thanh Toán</h3>
        <div class="pay-methods">
            <p>Paypal</p>
            <p>Vietcombank</p>
            <p>DongA</p>
        </div>
    </div>
    <div class="widget">
        <h3>Liên Kết</h3>
        <div class="menu-widget-footer-container">
            <ul class="menu" id="menu-widget-footer">
                <li class="menu-2-cols"><a href="trang_chu.do">Trang Chủ</a></li>
                <li class="menu-2-cols"><a href="do_choi.do?action=views_by_groups">Mua sắm</a></li>
                <li class="menu-2-cols"><a href="#">Quy định</a></li>
                <li class="menu-2-cols"><a href="#">Hỗ trợ</a></li>
                <li class="menu-2-cols"><a href="#">Tin khyến mãi</a></li>
                <li class="menu-2-cols"><a href="#">Liên hệ</a></li>
            </ul>
        </div>
    </div>
    <div class="widget widget-best-products">
        <h3>Sản Phẩm Tốt Nhất</h3>
        <ul class="product-list">
            <li> <a href="#"> <img alt="Product" src="${pageContext.request.contextPath}/images/shared-images/products/shoes.jpg"> Đồ chơi </a><span class="amount">160,000 VNĐ</span> </li>
            <li> <a href="#"> <img alt="Product" src="${pageContext.request.contextPath}/images/shared-images/products/shoes.jpg"> Đồ chơi </a> <span class="amount">43,000 VNĐ</span> </li>
            <li> <a href="#"> <img alt="Product" src="${pageContext.request.contextPath}/images/shared-images/products/shoes.jpg"> Đồ chơi </a> <span class="amount">470,000 VNĐ</span> </li>
        </ul>
    </div>
</div>