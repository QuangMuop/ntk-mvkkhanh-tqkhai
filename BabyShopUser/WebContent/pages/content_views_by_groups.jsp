<%-- 
    Document   : content_views_by_groups
    Created on : Oct 29, 2012, 10:59:48 AM
    Author     : TrongKhoa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="dsLoaiDoChoi" value="${requestScope.dsLoaiDoChoi}"/>
<c:set var="dsNhaSanXuat" value="${requestScope.dsNhaSanXuat}"/>
<c:set var="loaiDoChoi" value="${requestScope.loaiDoChoi}"/>
<c:set var="nhaSanXuat" value="${requestScope.nhaSanXuat}"/>
<c:set var="dsDoChoi" value="${requestScope.dsDoChoi}"/>
<c:set var="soLuongKetQua" value="${soLuongKetQua}"/>
<c:set var="soLuongDoChoiTrenTrang" value="${soLuongDoChoiTrenTrang}"/>
<c:set var="trang" value="${trang}"/>
<c:set var="soLuongTrang" value="${soLuongTrang}"/>
<c:set var="pageNumbers" value="${pageNumbers}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>

<div id="primary" class="layout-sidebar-no">
    <div class="inner group"> 
        <!-- START CONTENT -->
        <div class="group" id="content">
            <div class="products-by-group group">
                <h2 class="highlighted-title">
                    <c:out value="${loaiDoChoi.tenLoaiDoChoi}"/>
                    <c:out value="${nhaSanXuat.tenNhaSanXuat}"/>
                </h2>
                <div class="horizontal-product-list">
                    <ul>
                        <c:forEach var="doChoi" items="${dsDoChoi}">
                            <li><a href="detail?id=${doChoi.maDoChoi}">
                                    <div class="thumbnail">
                                        <c:if test="${doChoi.giamGia > 0}">
                                            <span class="on-sale"><c:out value="-${doChoi.giamGia} %"/></span>
                                        </c:if>
                                        <img alt="IMG" src="${pageContext.request.contextPath}<c:out value="${doChoi.hinhAnhHienThiChinh}"/>"/></div>
                                    <span class="product-price"><fmt:formatNumber value="${doChoi.giaBanHienTai}" pattern="#,### VNĐ" /></span><span class="product-name"><c:out value="${doChoi.tenDoChoi}"/></span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <c:if test="${soLuongTrang > 1}">
                    <div class="pagination"> 
                        <a href="list?loaiDoChoi=${loaiDoChoi.maLoaiDoChoi}&nhaSanXuat=${nhaSanXuat.maNhaSanXuat}&trang=1">Trang đầu</a>     
                        <c:forEach var="tra" items="${pageNumbers}">
                            <c:if test="${tra == trang}">
                                <span class="selected"><c:out value="${tra}"/></span> 
                            </c:if>
                            <c:if test="${tra != trang}">
                                <a href="list?loaiDoChoi=${loaiDoChoi.maLoaiDoChoi}&nhaSanXuat=${nhaSanXuat.maNhaSanXuat}&trang=${tra}">
                                    <c:out value="${tra}"/>
                                </a> 
                            </c:if>
                        </c:forEach>

                        <a href="list?loaiDoChoi=${loaiDoChoi.maLoaiDoChoi}&nhaSanXuat=${nhaSanXuat.maNhaSanXuat}&trang=${soLuongTrang}">Trang cuối</a> 
                    </div>
                </c:if>
                <div class="border-line"></div>
            </div>

            <div class="side-bar">
                <h3>Xem theo</h3>
                <ul class="product-categories">

                    	<li class="cat-item">
	                        <span>Loại đồ chơi</span> <span class="count">(<c:out value="${dsLoaiDoChoi.size()}"/>)</span>
	                        <ul class="children">
	                            <c:forEach var="ldc" items="${dsLoaiDoChoi}">
	                                <li class="cat-item">
	                                    <a href="list?loaiDoChoi=${ldc.maLoaiDoChoi}">
	                                        <c:out value="${ldc.tenLoaiDoChoi}"/>
	                                    </a> 
	                                </li>
	                             </c:forEach>
	                        </ul>
						</li>
                </ul>
 
				<ul class="product-manufacturers">
	                <li class="cat-item">
	                    <span>Nhà sản xuất</span> <span class="count">(<c:out value="${dsNhaSanXuat.size()}"/>)</span>
	                    <ul class="children">
	                        <c:forEach var="nxs" items="${dsNhaSanXuat}">
	                            <li class="cat-item">
	                                <a href="list?nhaSanXuat=${nxs.maNhaSanXuat}">
	                                    <c:out value="${nxs.tenNhaSanXuat}"/>
	                                </a> 
	
	                            </li>
	                        </c:forEach>
	                    </ul>
	                </li>
				</ul>
            </div>

        </div>
        <!-- END CONTENT --> 
        <!-- START LATEST NEWS --> 
        <!-- END LATEST NEWS --> 
        <!-- START EXTRA CONTENT --> 
        <!-- END EXTRA CONTENT --> 
    </div>
</div>