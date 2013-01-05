<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="toysController" value="${requestScope.ToysController}"/>
<c:set var="soLuongKetQua" value="${soLuongKetQua}"/>
<c:set var="soLuongDonHangTrenTrang" value="${soLuongDonHangTrenTrang}"/>
<c:set var="trang" value="${trang}"/>
<c:set var="soLuongTrang" value="${soLuongTrang}"/>
<c:set var="pageNumbers" value="${pageNumbers}"/>

<h2 class="highlighted-title">Lịch sử mua hàng</h2>
    <p> Số đơn hàng: <span class="n-results">${soLuongKetQua}</span> </p>
    <table id="orders-history" class="my-nice-table">
        <tr>
            <th class="id">STT</th>
            <th class="order-date">Ngày mua</th>
            <th class="product-name">Đồ chơi</th>
            <th class="product-price">Đơn giá</th>
            <th class="product-quantity">Số lượng</th>
            <th class="bill">Mã đơn hàng</th>
            <th class="status">Trạng thái</th>
        </tr>
        <c:if test="${soLuong.size() != 0}">
            <c:forEach var="a" begin="0" end="${soLuong.size() - 1}">
                <tr>
                    <td class="STT">${a + 1}</td>
                    <td class="order-date">${ngayMua.get(a)}</td>
                    <td class="product-name"><a href="${toysController}detail?id=${maDoChoi.get(a)}">${tenDoChoi.get(a)}</a></td>
                    <td class="product-price"><span class="amount"><fmt:formatNumber value="${donGia.get(a)}" pattern="#,### VNĐ" /></span></td>
                    <td class="product-quantity">${soLuong.get(a)}</td>
                    <td><a href="#">${maDonHang.get(a)}</a></td>
                    <td class="status">${trangThai.get(a)}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${soLuong.size() == 0}">
        	<tr>
        		<td colspan="7" style="text-align: center">Chưa có</td>
        	</tr>
        </c:if>       
    </table>
    <c:if test="${soLuongTrang > 1}">
                    <div class="pagination"> 
                        <a href="orders-history?trang=1">Trang đầu</a>     
                        <c:forEach var="tra" items="${pageNumbers}">
                            <c:if test="${tra == trang}">
                                <span class="selected"><c:out value="${tra}"/></span> 
                            </c:if>
                            <c:if test="${tra != trang}">
                                <a href="orders-history?trang=${tra}">
                                    <c:out value="${tra}"/>
                                </a> 
                            </c:if>
                        </c:forEach>

                        <a href="orders-history?trang=${soLuongTrang}">Trang cuối</a> 
                    </div>
    </c:if>