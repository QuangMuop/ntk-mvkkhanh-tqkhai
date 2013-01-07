<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}" />
<c:set var="toysController" value="${requestScope.ToysController}"/>

<h2 class="highlighted-title">Giỏ hàng</h2>
<form method="post" action="#">
	<table id="cart-items" class="my-nice-table">
		<tr>
			<th class="product-remove"></th>
			<th class="product-thumbnail">Ảnh</th>
			<th class="product-name">Đồ chơi</th>
			<th class="product-price">Đơn giá (đ)</th>
			<th class="product-quantity">Số lượng</th>
			<th class="product-subtotal">Thành tiền (đ)</th>
		</tr>
		<c:forEach var="a" begin="0" end="${products.size() - 1}">
			<tr id="tr-${products.get(a).getDoChoi().getMaDoChoi()}">
				<td class="product-remove"><a
					onclick="remove(${products.get(a).getDoChoi().getMaDoChoi()})"
					href="#">X</a></td>
				<td class="product-thumbnail"><a href="#"><img width="90"
						height="90" alt=""
						src="${pageContext.request.contextPath}${toyImagesFolder}${products.get(a).getDoChoi().getHinhAnhHienThiChinh()}"></a></td>
				<td class="product-name"><a href="${toysController}detail?id=${products.get(a).getDoChoi().getMaDoChoi()}">${products.get(a).getDoChoi().getTenDoChoi()}</a></td>
				<td class="product-price">
						<span class="amount">
							<input type="hidden" id="product-price-${a}" value="${products.get(a).getDoChoi().getGiaBanHienTai()}" />
							<fmt:formatNumber value="${products.get(a).getDoChoi().getGiaBanHienTai()}" pattern="#,### VNĐ" />
						</span></td>
				<td class="product-quantity"><div class="quantity">
						<input id="product-${a}" maxlength="12" class="nice-textbox"
							size="4" value="${products.get(a).getSoLuongMua()}" />
					</div></td>
				<td class="product-subtotal"><span id="total-${a}" class="amount"><fmt:formatNumber
												value="${products.get(a).getTongTien()}" pattern="#,### VNĐ" />
						</span></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" class="table-footer"><div
					class="table-controls">

					<input onclick="checkout()" id="proceed" type="button"
						value="Thanh toán" class="rectangular-button green" /> <input
						onclick="update(${products.size()})"
						id="update-cart" type="button" value="Cập nhật giỏ hàng"
						class="rectangular-button" />
				</div></td>
		</tr>
	</table>
</form>
<div>
	<h2 class="highlighted-title">Tổng trị giá</h2>
	<table id="cart-totals" class="my-nice-table">
		<tr>
			<th>Cộng</th>
			<th>Phí giao hàng</th>
			<th>Tổng thanh toán</th>
		</tr>
		<tr>
			<td id="thanhTien"><fmt:formatNumber value="${thanhTien}" pattern="#,### VNĐ" /></td>
			<td>Miễn phí</td>
			<td id="thanhTien1"><fmt:formatNumber value="${thanhTien}" pattern="#,### VNĐ" /></td>
		</tr>
	</table>
</div>