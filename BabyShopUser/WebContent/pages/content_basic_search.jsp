<%-- 
    Document   : content_basic_search
    Created on : Oct 29, 2012, 10:58:54 AM
    Author     : TrongKhoa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="searchText" value="${searchText}"/>
<c:set var="soLuongKetQua" value="${soLuongKetQua}"/>
<c:set var="dsDoChoiTimKiem" value="${dsDoChoiTimKiem}"/>
<c:set var="soLuongDoChoiTrenTrang" value="${soLuongDoChoiTrenTrang}"/>
<c:set var="trang" value="${trang}"/>
<c:set var="soLuongTrang" value="${soLuongTrang}"/>
<c:set var="pageNumbers" value="${pageNumbers}"/>
<h2>Tìm kiếm đồ chơi</h2>
<form action="#" method="get" id="search-form">
    <div class="search-div">
        <input type="hidden" name="action" value="basicSearch"/>
        <input type="text" name="searchText" placeholder="Tên đồ chơi" value="${searchText}" class="nice-textbox"/>
        <button id="btnSearch" class="rectangular-button">Tìm Kiếm</button>
        <a href="goAdvancedSearch"> Tìm kiếm nâng cao</a>
        <div class="clear"></div>
    </div>
</form>

<div id="search-results">
    <div class="products-by-group group">
        <h2 class="highlighted-title">Kết quả tìm kiếm:</h2>
        <p>
            Tìm thấy <span class="n-results"><c:out value="${soLuongKetQua}"/></span> kết quả cho từ khóa: <c:out value="${searchText}"/>

        </p>
        <p>
            Xếp theo:<a href="#"> Giá cao đến thấp</a>|<a href="#"> Giá thấp đến cao</a>|<a href="#"> Mức độ phổ biến</a>|<a href="#"> Mới nhất</a>
        </p>

        <div class="horizontal-product-list">
            <ul>
                <c:forEach var="doChoi" items="${dsDoChoiTimKiem}">
                    <li><a href="/BabyShopUser/toy/detail?id=${doChoi.maDoChoi}">
                            <div class="thumbnail">
                                <c:if test="${doChoi.giamGia > 0}"><span class="on-sale"><c:out value="-${doChoi.giamGia} %"/></span></c:if>
                                <img alt="IMG" src="${pageContext.request.contextPath}<c:out value="${doChoi.hinhAnhHienThiChinh}"/>"/></div>
                            <span class="product-price"><fmt:formatNumber value="${doChoi.giaBanHienTai}" pattern="#,### VNĐ" /></span><span class="product-name"><c:out value="${doChoi.tenDoChoi}"/></span></a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <c:if test="${soLuongTrang > 1}">
            <div class="pagination"> 
                <a href="search?searchText=${searchText}&trang=1">Trang đầu</a>     
                <c:forEach var="tra" items="${pageNumbers}">
                    <c:if test="${tra == trang}">
                        <span class="selected"><c:out value="${tra}"/></span> 
                    </c:if>
                    <c:if test="${tra != trang}">
                        <a href="search?searchText=${searchText}&trang=${tra}">
                            <c:out value="${tra}"/>
                        </a> 
                    </c:if>
                </c:forEach>

                <a href="search?searchText=${searchText}&trang=${soLuongTrang}">Trang cuối</a> 
            </div>
        </c:if>
        <div class="border-line"></div>
    </div>    
</div>