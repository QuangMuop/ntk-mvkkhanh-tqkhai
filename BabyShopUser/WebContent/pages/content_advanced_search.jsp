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
<c:set var="searchContent" value="${searchContent}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>
<h2>Tìm kiếm đồ chơi nâng cao</h2>
<form action="advancedSearch" method="get" id="a-search-form">
    <div class="advanced-search-div">
    	<input type="hidden" id="inputSearchPage" name="trang" value="1"/>
    
		<input type="text" id="inputSearchTenDoChoi" name="tenDoChoi" class="nice-textbox large" placeholder="Tên đồ chơi..." value="${doChoiQuery.tenDoChoi}" />
        <select id="inputSearchLoaiDoChoi" name="loaiDoChoi" class="medium">
			<c:if test="${doChoiQuery.loaiDoChoi == null}">
				<option selected="selected" value="-1">---Loại đồ chơi---</option>
                 <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">                                           
                         <option value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
                 </c:forEach>
			</c:if>
			<c:if test="${doChoiQuery.loaiDoChoi != null}">
				<option value="-1">---Loại đồ chơi---</option>
                 <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">                                           	                                                     	                                             
                 		<c:choose>
                               <c:when test="${doChoiQuery.loaiDoChoi.maLoaiDoChoi == loaiDoChoi.maLoaiDoChoi}">
                                    <option selected="selected" value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
                               </c:when>
                               <c:otherwise>
                                    <option value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
                               </c:otherwise>
                           </c:choose>
                 </c:forEach>

			</c:if>
        </select>
        <select id="inputSearchNhaSanXuat" name="nhaSanXuat" class="small">
			<c:if test="${doChoiQuery.nhaSanXuat == null}">
				<option selected="selected" value="-1">---Nhà sản xuất---</option>
                 <c:forEach var="nhaSanXuat" items="${dsNhaSanXuat}" varStatus="status">                                           
                         <option value="${nhaSanXuat.maNhaSanXuat}">${nhaSanXuat.tenNhaSanXuat}</option>
                 </c:forEach>
			</c:if>
			<c:if test="${doChoiQuery.nhaSanXuat != null}">
				<option value="-1">---Nhà sản xuất---</option>
                 <c:forEach var="nhaSanXuat" items="${dsNhaSanXuat}" varStatus="status">                                           	                                                     	                                             
                 		<c:choose>
                               <c:when test="${doChoiQuery.nhaSanXuat.maNhaSanXuat == nhaSanXuat.maNhaSanXuat}">
                                    <option selected="selected" value="${nhaSanXuat.maNhaSanXuat}">${nhaSanXuat.tenNhaSanXuat}</option>
                               </c:when>
                               <c:otherwise>
                                    <option value="${nhaSanXuat.maNhaSanXuat}">${nhaSanXuat.tenNhaSanXuat}</option>
                               </c:otherwise>
                           </c:choose>
                 </c:forEach>

			</c:if>
        </select>
        <select id="inputSearchGiaMin" name="giaMin" class="small">
        	<option ${doChoiQuery.giaMin == -2 ? 'selected="selected"':''} value="">---Giá thấp nhất---</option>
        	<option ${doChoiQuery.giaMin == -1 ? 'selected="selected"':''} value="NoLimit">Không có</option>
        	<option ${doChoiQuery.giaMin == 50000 ? 'selected="selected"':''} value="50000">50.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 100000 ? 'selected="selected"':''} value="100000">100.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 200000 ? 'selected="selected"':''} value="200000">200.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 500000 ? 'selected="selected"':''} value="500000">500.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 800000 ? 'selected="selected"':''} value="800000">800.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 1000000 ? 'selected="selected"':''} value="1000000">1.000.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 1500000 ? 'selected="selected"':''} value="1500000">1.500.000 VNĐ</option>
        	<option ${doChoiQuery.giaMin == 2000000 ? 'selected="selected"':''} value="2000000">2.000.000 VNĐ</option>
        </select>
        <select id="inputSearchGiaMax" name="giaMax" class="small">
        	<option ${doChoiQuery.giaMax ==-2 ? 'selected="selected"':''} value="">---Giá cao nhất---</option>
        	<option ${doChoiQuery.giaMax ==50000 ? 'selected="selected"':''} value="50000">50.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==100000 ? 'selected="selected"':''} value="100000">100.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==200000 ? 'selected="selected"':''} value="200000">200.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==500000 ? 'selected="selected"':''} value="500000">500.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==800000 ? 'selected="selected"':''} value="800000">800.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==1000000 ? 'selected="selected"':''} value="1000000">1.000.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==1500000 ? 'selected="selected"':''} value="1500000">1.500.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==2000000 ? 'selected="selected"':''} value="2000000">2.000.000 VNĐ</option>
        	<option ${doChoiQuery.giaMax ==-1 ? 'selected="selected"':''} value="NoLimit">Không giới hạn</option>
        </select>
        <button id="btnAdvancedSearch" type="submit" class="rectangular-button">Tìm</button>
        <div class="clear"></div>
    </div>
</form>

<div id="search-results">
    <div class="products-by-group group">
        <h2 class="highlighted-title">Kết quả tìm kiếm:</h2>
        <p>
            Tìm thấy <span class="n-results"><c:out value="${soLuongKetQua}"/></span> kết quả.

        </p>
        
        <div class="horizontal-product-list">
            <ul>
                <c:forEach var="doChoi" items="${dsDoChoiTimKiem}">
                    <li><a href="/BabyShopUser/toy/detail?id=${doChoi.maDoChoi}">
                            <div class="thumbnail">
                                <c:if test="${doChoi.giamGia > 0}"><span class="on-sale"><c:out value="-${doChoi.giamGia} %"/></span></c:if>
                                <img alt="IMG" src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${doChoi.hinhAnhHienThiChinh}"/>"/></div>
                            <span class="product-price">
                              		<c:if test="${doChoi.tinhTrang == 'Còn hàng' }">
                              			<fmt:formatNumber value="${doChoi.giaBanHienTai}" pattern="#,### VNĐ" />
                              		</c:if>
                              		<c:if test="${doChoi.tinhTrang == 'Hết hàng'}">
                              			<strike style='color:black'>
						    				<span style='color:red'><b>Hết hàng</b></span>
						  				</strike>
                              		</c:if>
                              		<c:if test="${doChoi.tinhTrang == 'Ngừng bán'}">
                              			<strike style='color:black'>
						    				<span style='color:red'><b>Ngừng bán</b></span>
						 				 </strike>
                              		</c:if>
                              </span>
                            <span class="product-name"><c:out value="${doChoi.tenDoChoi}"/></span></a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <c:if test="${soLuongTrang > 1}">
            <div class="pagination"> 
                <a href="advancedSearch?trang=1${searchContent}">Trang đầu</a>     
                <c:forEach var="tra" items="${pageNumbers}">
                    <c:if test="${tra == trang}">
                        <span class="selected"><c:out value="${tra}"/></span> 
                    </c:if>
                    <c:if test="${tra != trang}">
                        <a href="advancedSearch?trang=${tra}${searchContent}">
                            <c:out value="${tra}"/>
                        </a> 
                    </c:if>
                </c:forEach>

                <a href="advancedSearch?trang=${soLuongTrang}${searchContent}">Trang cuối</a> 
            </div>
        </c:if>
        <div class="border-line"></div>
    </div>    
</div>