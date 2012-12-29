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
<!--BEGIN .sidler-->
<div class="slider_elegant group inner" id="slider">
    <ul class="group">
        <li class="group">
            <div class="slider-featured group"> 
                <img width="960" height="338" alt="IMG" src="${pageContext.request.contextPath}/images/homepage/002.jpg"> 
            </div>
            <div class="slider-caption caption-right">
                <h2>Búp bê barbie 2012</h2>
                <h4></h4>
                <p>Barbie là tên và là thương hiệu của một loại búp bê nổi tiếng, được tập đoàn Mattel trưng bày lần đầu tiên vào ngày 9 tháng 3 năm 1959 trong Hội chợ American Toy Fair tại New York. Từ đó, búp bê Barbie trở thành 1 món đồ chơi bán chạy nhất lịch sử và phá bỏ quan điểm rằng "búp bê chỉ dành cho các bé gái".</p>
                <p><span style="font-size:24px;" class="special-font">prices from <span style="font-size:42px;">$45</span></span></p>
            </div>
        </li>
        <li class="group">
            <div class="slider-featured group"> <img width="960" height="338" alt="IMG" src="${pageContext.request.contextPath}/images/homepage/001.jpg"> </div>
            <div class="slider-caption caption-right">
                <h2>Bộ xếp hình LEGO</h2>
                <h4></h4>
                <p>Từ khi bắt đầu sản xuất các viên gạch nhựa, tập đoàn Lego đã tung ra thị trường hàng ngàn bộ lego với các chủ đề phong phú, bao gồm: lego thị trấn và thành phố, lego không gian, lego hải tăc, lego xe cộ, lego robot, lego khủng long, lego tàu hỏa, lego Viking, lego lâu đài, lego khám phá đại dương, lego miền tây hoang dã.</p>
            </div>
        </li>
        <li class="group">
            <div class="slider-featured group"> <img width="960" height="338" alt="IMG" src="${pageContext.request.contextPath}/images/homepage/003.jpg"> </div>
            <div class="slider-caption caption-right" style="right: -360px;">
                <h2>Máy bay trực thăng điều khiển từ xa</h2>
                <h4></h4>
                <p>Máy bay điều khiển từ xa không chỉ là một món đồ chơi đơn thuần mà còn là công cụ kích thích khả năng sáng tạo của trẻ em rất tốt, phù hợp với mọi lứa tuổi. Sau những giờ học tập căng thẳng ở trường lớp, món đồ chơi này sẽ giúp trẻ thư giãn ngay tại nhà mà không phải đi đâu xa</p>
            </div>
        </li>
    </ul>
</div>
<script type="text/javascript">      
    var     yiw_slider_type = 'elegant',
    yiw_slider_elegant_easing = null,
    yiw_slider_elegant_fx = 'fade',
    yiw_slider_elegant_speed = 500,
    yiw_slider_elegant_timeout = 5000,
    yiw_slider_elegant_caption_speed = 500;
</script>
<!--END .slider-->

<div id="ad-left" align="center" style="width: 140px; height: 100%; position: fixed; top: 50px; left: 0px;">
<a href="#" onClick="">
	<img width="100px" src="/BabyShopUser/uploads/ads/ad5.jpg"/>
</a>

</div>
<div id="ad-right" align="center" style="width: 140px; height: 100%; position: fixed; top: 50px; right: 0px;">
<a href="#" onClick="">
	<img width="100px" src="/BabyShopUser/uploads/ads/ad6.jpg"/>
</a>

</div>

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
                <h2 class="highlighted-title">Đồ chơi bán chạy nhất</h2>
                <div class="ribbon">
                    <div class="products-slider ribbon">
                        <ul class="products ribbon">
                            <!--XUẤT TOP 10 ĐỒ CHƠI BÁN CHẠY NHẤT-->
                            <c:forEach var="dcbcn" items="${dsTopDoChoiBanChayNhat}">
                                <li class="product">
                                    <a href="/BabyShopUser/toy/detail?id=${dcbcn.maDoChoi}">
                                        <div class="thumbnail">
                                            <c:if test="${dcbcn.giamGia > 0}">
                                                <span class="on-sale"><c:out value="-${dcbcn.giamGia} %"/></span>
                                            </c:if>

                                            <img alt="IMG" src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${dcbcn.hinhAnhHienThiChinh}"/>"/>
                                            <div class="thumb-shadow"></div>
                                            <strong class="product-name">
                                                <c:out value="${dcbcn.tenDoChoi}"/>
                                            </strong>    	
                                        </div>
                                        <span class="product-price">
                                        	<fmt:formatNumber value="${dcbcn.giaBanHienTai}" pattern="#,### VNĐ" />
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