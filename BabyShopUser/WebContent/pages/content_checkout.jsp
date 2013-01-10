<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>
<c:set var="accountsController" value="${requestScope.AccountsController}"/>

<c:if test="${result == 1}">
	<script>
		alert('Cám ơn bạn đã mua đồ chơi. Nhân viên sẽ liên hệ bạn trong thời gian sớm nhất.');
		window.location.href = "/BabyShopUser/home/index";
	</script>
</c:if>
<h2 class="highlighted-title">Thanh toán</h2>
<form:form modelAttribute="thanhToan" method="post"
		enctype="multipart/form-data" action="${accountsController}checkout-cart"
		name="frmCheckout" id="frmCheckout">

		<div id="billing-and-shipping">
			<div id="receiver-info">
				<h3>Thông tin người nhận</h3>				
				<table>
					<tr>
						<td>Họ tên người nhận *</td>
						<td>Công ti</td>
					</tr>
					<tr>
						<td><form:input path="nguoiNhan" type="text" id="hoTen"
								class="nice-textbox  width150" placeholder="Họ tên" /></td>
						<td><form:input path="congTy" type="text"
								class="nice-textbox width150" placeholder="Công ti (optional)" /></td>
					</tr>
					<tr>
						<td>Địa chỉ người nhận *</td>
					</tr>
					<tr>
						<td colspan="2"><form:input path="diaChiNhanChinh"
								id="diaChi" type="text" class="nice-textbox width325"
								placeholder="Địa chỉ người nhận" /></td>
					</tr>
					<tr>
						<td>Địa chỉ người nhận phụ</td>
					</tr>
					<tr>
						<td colspan="2"><form:input path="diaChiNhanPhu" type="text"
								class="nice-textbox width325"
								placeholder="Địa chỉ người nhận phụ (optional)" /></td>
					</tr>
					<tr>
						<td>Thành phố *</td>
						<td>Mã bưu chính *</td>
					</tr>
					<tr>
						<td><form:input path="thanhPho" type="text"
								class="nice-textbox width150" placeholder="Thành phố" /></td>
						<td><form:input path="maBuuChinh" type="text"
								class="nice-textbox width150" placeholder="Mã bưu chính" /></td>
					</tr>
					<tr>
						<td>Email *</td>
						<td>Số điện thoại *</td>
					</tr>
					<tr>
						<td><form:input path="email" type="text"
								class="nice-textbox width150" placeholder="Email" /></td>
						<td><form:input path="soDienThoai" type="text"
								class="nice-textbox width150" placeholder="Điện thoại" /></td>
					</tr>
				</table>
			</div>
			<div id="notes">
				<h3>Ghi chú</h3>
				<form:textarea path="ghiChu" name="notes"
					placeholder="Ghi chú (optional)"></form:textarea>
			</div>
			<div class="clear"></div>
		</div>
		<div id="oders">
			<h3>Sản phẩm bạn mua</h3>
			<table id="cart-items" class="my-nice-table">
				<tr>
					<th class="product-thumbnail">Ảnh</th>
					<th class="product-name">Đồ chơi</th>
					<th class="product-price">Đơn giá</th>
					<th class="product-quantity">Số lượng</th>
					<th class="product-subtotal">Thành tiền</th>
				</tr>
				
				<c:if test="${products.size() > 0 }">
					<c:forEach var="product" items="${products}" varStatus="status">
						<tr>
							<td class="product-thumbnail"><a href="#"><img width="90"
							height="90" alt=""
							src="${pageContext.request.contextPath}${toyImagesFolder}${product.getDoChoi().getHinhAnhHienThiChinh()}"></a></td>
							<td class="product-name"><a href="#">${product.getDoChoi().getTenDoChoi()}</a></td>
							<td class="product-price"><span class="amount">${product.getDoChoi().getGiaBanHienTai()}
									đ</span></td>
							<td class="product-quantity"><div class="quantity">
									<input id="product-${status.index}" maxlength="12" disabled="disabled" class="nice-textbox"
										size="4" value="${product.getSoLuongMua()}" />
								</div></td>
							<td class="product-subtotal"><span class="amount"><fmt:formatNumber
													value="${product.getTongTien()}" pattern="#,### VNĐ" /></span></td>
						</tr>
					</c:forEach>					
				</c:if>
				<tr>
					<td colspan="4" class="table-footer" align="right">Cộng</td>
					<td class="table-footer"><fmt:formatNumber value="${thanhTien}" pattern="#,### VNĐ" /></td>
				</tr>
				<tr>
					<td colspan="4" class="table-footer" align="right">Phí giao
						hàng</td>
					<td class="table-footer">Miễn phi</td>
				</tr>
				<tr>
					<td colspan="4" class="table-footer" align="right">Tổng thanh
						toán</td>
					<td class="table-footer"><fmt:formatNumber value="${thanhTien}" pattern="#,### VNĐ" /></td>
				</tr>
			</table>
		</div>
		<h3>Phương thức thanh toán</h3>
		<div id="payment">

			<input type="radio" class="radio-button" name="pay-method"
				onclick="show(1)" value="0" />Thanh toán trực tuyến <br /> <input
				type="radio" class="radio-button" name="pay-method"
				onclick="show(2)" value="0" checked="checked" />Thanh toán khi nhận
			hàng
		</div>
		<div class="clear"></div>
		<div id="proceed">
			<input type="submit" class="rectangular-button green"
				value="Thanh toán" id="offline" style="position: inherit;" />
		</div>
	</form:form>
	
	<form name="_xclick" action="https://www.sandbox.paypal.com/webscr"
				method="post">
				<input type="hidden" name="upload" value="1"> <input
					type="hidden" name="currency_code" value="USD" /> <input
					type="hidden" name="return" value="http://test.html/success" /> <input
					type="hidden" name="cancel_return" value="http://test.html/fail" />
				<input type="hidden" name="cmd" value="_cart" /> <input
					type="hidden" name="business"
					value="seller_1356618189_biz@gmail.com"> <input
					type="hidden" name="return"
					value="http://localhost:9080${accountsController}checkout-result?state=success">
				<input type="hidden" name="cancel_return"
					value="http://localhost:9080${accountsController}checkout-result?state=fail">
					
				<c:forEach var="product" items="${products}" varStatus="status">
					<input type="hidden" name="item_name_${status.index + 1}"
						value="${product.getDoChoi().getTenDoChoi()}">
					<input type="hidden" name="quantity_${status.index + 1}"
						value="${product.getSoLuongMua()}">
					<input type="hidden" name="amount_${status.index + 1}"
						value="${product.getDoChoi().getGiaBanHienTai()/20000}">
				</c:forEach>
				

				<input type="image"
					src="https://www.sandbox.paypal.com/en_US/i/btn/btn_buynowCC_LG.gif"
					border="0" name="submit"
					alt="PayPal - The safer, easier way to pay online!" id="online"
					style="visibility: hidden; position: relative;"
					onclick="checkoutPaypal()" /> <img alt="" border="0"
					src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif"
					width="1" height="1" />

			</form>