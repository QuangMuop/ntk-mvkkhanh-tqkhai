<%-- 
    Document   : content_product_details
    Created on : Oct 29, 2012, 10:45:01 AM
    Author     : TrongKhoa
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="doChoi" value="${doChoi}" scope="page"/>
<c:set var="dsBinhLuan" value="${dsBinhLuan}" scope="page"/>
<c:set var="dsDoChoiLienQuan" value="${dsDoChoiLienQuan}" scope="page"/>
<c:set var="dsHinhAnhDoChoi" value="${dsHinhAnhDoChoi}" scope="page"/>
<c:set var="avatarImagesFolder" value="${requestScope.AvatarImagesFolder}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>
<c:set var="account" value="${sessionScope.account}" />
<div class="product">
    <div class="images">
        <div class="thumbnails">
        	<ul class='aviaslider' id="frontpage-slider">
        		<c:forEach var="hinhAnhDoChoi" items="${dsHinhAnhDoChoi}" varStatus="status">
	        		<li>
	        			<a href="${pageContext.request.contextPath}${toyImagesFolder}${hinhAnhDoChoi.hinhAnh}" rel="prettyPhoto" class="thumbnail" title="Chi tiết" ><img alt="woman" src="${pageContext.request.contextPath}${toyImagesFolder}${hinhAnhDoChoi.hinhAnh}" style="opacity: 1;"/></a> 
	        		</li>
        		</c:forEach>
        	</ul>
        </div>
    </div>
    <div class="summary">
        <h1 class="product-title">
            <c:out value="${doChoi.tenDoChoi}"/>
        </h1>
        <h3 class="product-category"> Loại đồ chơi: <a href="#">
                <c:out value="${doChoi.loaiDoChoi.tenLoaiDoChoi}"/>
            </a> </h3>
        <h3 class="product-price"> Giá bán:

            <c:if test="${doChoi.giamGia > 0}"><span class="discount"><fmt:formatNumber value="${doChoi.giaBanHienTai * doChoi.giamGia / 100}" pattern="#,### VNĐ" /></span></c:if> 
            <span class="amount"><fmt:formatNumber value="${doChoi.giaBanHienTai}" pattern="#,### VNĐ" /></span> </h3>
        <h3 class="product-availability"> Tình trạng: <span><c:out value="${doChoi.tinhTrang}"/></span> </h3>
        <h3 class="product-views-count"> Lượt xem: <span><c:out value="${doChoi.soLuongXem}"/></span> </h3>
        <h3 class="sales"> Số lượng đã bán: <span><c:out value="${doChoi.soLuongBan}"/></span> </h3>
        <form class="cart" method="post" action="do_choi.do?action=buy">
            <input type="hidden" name="product-id" value="${doChoi.maDoChoi}"/>
            <input maxlength="12" class="nice-textbox qty" size="4" value="1" name="quantity"/>
            <button class="rectangular-button green add-to-cart" type="submit">Hốt ngay</button>
        </form>
        <div id="tabs">
            <ul>
                <li> <a href="#tabs-1">Thông tin sản phẩm</a> </li>
                <li> <a id="nComments" href="#tabs-2">Nhận xét (<c:out value="${dsBinhLuan.size()}"/>)</a> </li>
            </ul>
            <div id="tabs-1">
                <h2>Mô tả sản phẩm</h2>
                <p><c:out value="${doChoi.moTa}"/></p>
                <h3 class="product-factory"> Nhà sản xuất: <a href="#"><c:out value="${doChoi.nhaSanXuat.tenNhaSanXuat}"/></a> </h3>
                <h3 class="product-madein"> Xuất xứ: <span><c:out value="${doChoi.xuatXu.tenXuatXu}"/></span> </h3>
                <div class="socials">
                    <h2>Chia sẻ mạng xã hội</h2>
                    <a href="#" class="socials facebook">facebook</a> </div>
            </div>
            <div id="tabs-2">
                <div class="comment-area">
                    <ul class="commentlist" id="commentlist">
                        <c:forEach var="bl" items="${dsBinhLuan}">
                            <li class="comment">
                                <div class="avatar32x32"> <img src="${pageContext.request.contextPath}${avatarImagesFolder}<c:out value="${bl.taiKhoan.avatar}"/>" alt="Avatar"/> </div>
                                <div class="comment-container">
                                    <p> Bởi <span class="user"><c:out value="${bl.taiKhoan.hoTen}"/></span> vào <span><fmt:formatDate value="${bl.ngayBinhLuan}" pattern="HH:mm dd/MM/yyyy"/></span> : </p>
                                    <div class="comment-text">
                                        <p><c:out value="${bl.noiDung}"/></p>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                                <div class="clear"></div>
                            </li>                           
                        </c:forEach>                      
                    </ul>
                    <ul class="commentlist" id="add-comment">
                    	 <c:if test="${account.getMaTaiKhoan() != null}">
								<li class="comment">
									<div class="avatar32x32">
										<img src="${pageContext.request.contextPath}${avatarImagesFolder}<c:out value="${account.avatar}"/>" alt="Avatar"/>
									</div>
									<div class="comment-container" style="padding: 3px;">
										<input id="comment-content" placeholder="Nhận xét của bạn..." class="nice-textbox" style="width: 270px;" />										
									</div>
									<div class="clear" ></div>
								</li>
						</c:if>
                    </ul>
                    <p>
							<c:if test="${account.getMaTaiKhoan() != null}">
								<c:set var="myAvatar" value="${pageContext.request.contextPath}${avatarImagesFolder}${account.avatar}" />
								<c:set var="myName" value="${account.hoTen}" />							
								<button onclick="sendComment(${doChoi.maDoChoi}, '${myAvatar}', '${myName}')" class="rectangular-button"
									>Thêm nhận xét</button>
							</c:if>
					</p>
                    <div id="review_form_wrapper" style="display: none;">
                        <div id="review_form">
                            <div id="respond">
                                <h3 id="reply-title"> Thêm nhận xét <small> <a style="display:none;" href="#" id="cancel-comment-reply-link">Hủy nhận xét</a> </small> </h3>
                                <form id="commentform" method="post" action="#">
                                    <p class="comment-form-rating">
                                        <label for="rating">Rating</label>
                                        <select id="rating" name="rating">
                                            <option value="5">Tuyệt vời </option>
                                            <option value="4">Tốt </option>
                                            <option value="3">Trung bình </option>
                                            <option value="2">Không tệ </option>
                                            <option value="1">Chán </option>
                                        </select>
                                    </p>
                                    <p class="comment-form-comment">
                                        <label for="comment">Nhận xét của bạn</label>
                                        <textarea aria-required="true" rows="8" cols="45" name="comment" id="comment"></textarea>
                                    </p>
                                    <p class="form-submit">
                                        <input type="submit" value="Submit Review" id="submit" name="submit"/>
                                        <input type="hidden" id="comment_post_ID" value="58" name="comment_post_ID"/>
                                        <input type="hidden" value="0" id="comment_parent" name="comment_parent"/>
                                    </p>
                                    <p style="display: none;">
                                        <input type="hidden" value="e9da68b536" name="akismet_comment_nonce" id="akismet_comment_nonce"/>
                                    </p>
                                </form>
                            </div>
                            <!-- #respond --> 
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="related-products">
    <h3 class="highlighted-title">Các sản phẩm liên quan khác</h3>
    <ul class="horizontal-product-list ribbon">
        <c:forEach var="doChoi" items="${dsDoChoiLienQuan}">
            <li> <a href="/BabyShopUser/toy/detail?id=${doChoi.maDoChoi}">
                    <div class="thumbnail"> 
                        <c:if test="${doChoi.giamGia > 0}">
                            <span class="on-sale"><c:out value="-${doChoi.giamGia} %"/></span>
                        </c:if>
                            <img alt="IMG" src="${pageContext.request.contextPath}${toyImagesFolder}<c:out value="${doChoi.hinhAnhHienThiChinh}"/>"/> </div>
                    <span class="product-price"><fmt:formatNumber value="${doChoi.giaBanHienTai}" pattern="#,### VNĐ" /></span> <span class="product-name"><c:out value="${doChoi.tenDoChoi}"/></span> </a> 
            </li>
        </c:forEach>
    </ul>
    <div class="clear"/>
</div>