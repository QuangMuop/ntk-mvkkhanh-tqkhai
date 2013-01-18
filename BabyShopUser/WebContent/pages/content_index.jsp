<%-- 
    Document   : content_index
    Created on : Oct 29, 2012, 10:31:19 AM
    Author     : TrongKhoa
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="dsTopDoChoiMoiNhat"
	value="${requestScope.dsTopDoChoiMoiNhat}" />
<c:set var="dsTopDoChoiBanChayNhat"
	value="${requestScope.dsTopDoChoiBanChayNhat}" />
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}" />
<c:set var="adsImagesFolder" value="${requestScope.AdsImagesFolder}" />
<c:set var="dsQuangCao" value="${requestScope.DSQuangCao}" />
<c:set var="toysController" value="${requestScope.ToysController}"/>
<c:set var="account" value="${sessionScope.account}" />

<!--BEGIN .sidler-->
<div class="slider_elegant group inner" id="slider">
	<ul class="group">
		<c:forEach var="qc" items="${dsQuangCao}">
			<c:if test="${qc.viTri == 'top'}">
				<li class="group">
					<div class="slider-featured group">
						<img width="960" height="338" alt="IMG"
							src="${pageContext.request.contextPath}${adsImagesFolder}<c:out value="${qc.hinhAnh}"/>">
					</div>
					<div class="slider-caption caption-right">
						<h2><c:out value="${qc.tenQuangCao}"/></h2>
						<h4></h4>
						<p><c:out value="${qc.moTa}"/></p>
						<p>
							<span style="font-size: 24px;" class="special-font"><span style="font-size: 42px;"></span>
							</span>
						</p>
					</div>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
	var yiw_slider_type = 'elegant', yiw_slider_elegant_easing = null, yiw_slider_elegant_fx = 'fade', yiw_slider_elegant_speed = 500, yiw_slider_elegant_timeout = 5000, yiw_slider_elegant_caption_speed = 500;
</script>
<!--END .slider-->

<div id="primary" class="layout-sidebar-no">
	<div class="inner group">
		<!-- START CONTENT -->
		<div class="group" id="content">
			<div class="feature-products group">
				<div class="border-line"></div>
				<h2 class="highlighted-title">Đồ chơi mới nhất</h2>
				<div class="ribbon">
					<div class="products-slider ribbon">
						<ul class="products ribbon">
							<!--XUẤT TOP 10 ĐỒ CHƠI MỚI NHẤT-->
							<c:forEach var="dcmn" items="${dsTopDoChoiMoiNhat}">
								<li class="product"><a
									href="${toysController}detail?id=${dcmn.maDoChoi}">
										<div class="thumbnail">
											<c:if test="${dcmn.giamGia > 0}">
												<span class="on-sale"><c:out
														value="-${dcmn.giamGia} %" /></span>
											</c:if>

											<img alt="IMG"
												src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${dcmn.hinhAnhHienThiChinh}"/>" />
											<div class="thumb-shadow"></div>
											<strong class="product-name"> <c:out
													value="${dcmn.tenDoChoi}" />
											</strong>
										</div> <span class="product-price"> <fmt:formatNumber
												value="${dcmn.giaBanHienTai}" pattern="#,### VNĐ" />
									</span>
								</a>
									<div class="buttons">
										<c:if test="${account.getMaTaiKhoan() != null}">
											<a href="${toysController}detail?id=${dcmn.maDoChoi}" class="add-to-cart">Mua ngay</a>
										</c:if>
										<c:if test="${account.getMaTaiKhoan() == null}">
											<a href="${toysController}detail?id=${dcmn.maDoChoi}" class="add-to-cart">Xem</a>
										</c:if>
									</div></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="border-line"></div>
				<h2 class="highlighted-title">Đồ chơi bán chạy nhất</h2>
				<div class="ribbon">
					<div class="products-slider ribbon">
						<ul class="products ribbon">
							<!--XUẤT TOP 10 ĐỒ CHƠI BÁN CHẠY NHẤT-->
							<c:forEach var="dcbcn" items="${dsTopDoChoiBanChayNhat}">
								<li class="product"><a
									href="${toysController}detail?id=${dcbcn.maDoChoi}">
										<div class="thumbnail">
											<c:if test="${dcbcn.giamGia > 0}">
												<span class="on-sale"><c:out
														value="-${dcbcn.giamGia} %" /></span>
											</c:if>

											<img alt="IMG"
												src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${dcbcn.hinhAnhHienThiChinh}"/>" />
											<div class="thumb-shadow"></div>
											<strong class="product-name"> <c:out
													value="${dcbcn.tenDoChoi}" />
											</strong>
										</div> <span class="product-price"> <fmt:formatNumber
												value="${dcbcn.giaBanHienTai}" pattern="#,### VNĐ" />
									</span>
								</a>
									<div class="buttons">
										<a href="#" class="add-to-cart">Mua ngay</a>
									</div></li>
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