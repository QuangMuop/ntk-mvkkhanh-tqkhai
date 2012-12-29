<%-- 
    Document   : content_index
    Created on : Oct 29, 2012, 10:31:19 AM
    Author     : TrongKhoa
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="dsTopDoChoiMoiNhat" value="${requestScope.dsTopDoChoiMoiNhat}"/>
<c:set var="dsTopDoChoiBanChayNhat" value="${requestScope.dsTopDoChoiBanChayNhat}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>

<div id="primary" class="layout-sidebar-no">
    <div class="inner group"> 
        <!-- START CONTENT -->
        <div class="group" id="content">
            <div class="feature-products group">
                <div class="border-line"></div>
                <h2 class="highlighted-title">Đồ chơi đang khuyến mãi</h2>
                <div class="ribbon">
                    <div class="products-slider ribbon">
                        <ul class="products ribbon">
                            <!--XUẤT TOP 10 ĐỒ CHƠI MỚI NHẤT-->
                            <c:forEach var="dcmn" items="${dsTopDoChoiMoiNhat}">
                                <li class="product">
                                    <a href="/BabyShopUser/toy/detail?id=${dcmn.maDoChoi}">
                                        <div class="thumbnail">
                                            <c:if test="${dcmn.giamGia > 0}">
                                                <span class="on-sale"><c:out value="-${dcmn.giamGia} %"/></span>
                                            </c:if>

                                            <img alt="IMG" src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${dcmn.hinhAnhHienThiChinh}"/>"/>
                                            <div class="thumb-shadow"></div>
                                            <strong class="product-name">
                                                <c:out value="${dcmn.tenDoChoi}"/>
                                            </strong>    	
                                        </div>
                                        <span class="product-price">
                                        	<fmt:formatNumber value="${dcmn.giaBanHienTai}" pattern="#,### VNĐ" />
                                        </span>
                                    </a>
                                    <div class="buttons"><a href="#" class="add-to-cart">Mua ngay</a></div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="border-line"></div>
            </div>
        </div>
        <!-- END CONTENT --> 
    </div>
</div>