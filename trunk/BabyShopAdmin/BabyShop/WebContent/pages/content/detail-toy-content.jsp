<%-- 
    Document   : detail-toy-content
    Created on : Oct 30, 2012, 3:09:38 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="doChoi" value="${requestScope.doChoi}"/>
<c:set var="dsLoaiDoChoi" value="${requestScope.dsLoaiDoChoi}"/>
<c:set var="dsXuatXu" value="${requestScope.dsXuatXu}"/>
<c:set var="dsNhaSanXuat" value="${requestScope.dsNhaSanXuat}"/>
<c:set var="dsHinhAnhDoChoi" value="${requestScope.dsHinhAnhDoChoi}"/>
<c:set var="dsTenHinhAnhDoChoi" value="${requestScope.dsTenHinhAnhDoChoi}"/>
<c:set var="toyImagesFolder" value="${requestScope.ToyImagesFolder}"/>
<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết đồ chơi - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật đồ chơi thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được đồ chơi !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="doChoiUpload" action="update" name="formUpdateToy" id="formUpdateToy" method="post">      
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã đồ chơi</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${doChoi.maDoChoi}</label>
                                                <form:hidden path="maDoChoi" id="inputMaDoChoi" name="inputMaDoChoi" value="${doChoi.maDoChoi}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên đồ chơi</label>
                                            <div class="controls">
                                            	<form:input path="tenDoChoi" id="inputTenDoChoi" name="inputTenDoChoi" cssClass="input-xlarge" value="${doChoi.tenDoChoi}" disabled="disabled"/>  
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Loại đồ chơi</label>
                                            <div class="controls">
                                                <form:select id="selectLoaiDoChoi" name="selectLoaiDoChoi" disabled="disabled" path="loaiDoChoi">
												   <form:option value="-1" label="Chọn loại đồ chơi" />
												   <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">
												   		<c:choose>
                                                            <c:when test="${loaiDoChoi.maLoaiDoChoi == doChoi.loaiDoChoi.maLoaiDoChoi}">
                                                                 <form:option selected="selected" value="${loaiDoChoi.maLoaiDoChoi}" label="${loaiDoChoi.tenLoaiDoChoi}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <form:option value="${loaiDoChoi.maLoaiDoChoi}" label="${loaiDoChoi.tenLoaiDoChoi}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Nhà sản xuất</label>
                                            <div class="controls">
                                            	<form:select id="selectNhaSanXuat" name="selectNhaSanXuat" path="nhaSanXuat">
												   <form:option value="-1" label="Chọn nhà sản xuất" />
												   <c:forEach var="nhaSanXuat" items="${dsNhaSanXuat}" varStatus="status">
												   		<c:choose>
                                                            <c:when test="${nhaSanXuat.maNhaSanXuat == doChoi.nhaSanXuat.maNhaSanXuat}">
                                                                 <form:option selected="selected" value="${nhaSanXuat.maNhaSanXuat}" label="${nhaSanXuat.tenNhaSanXuat}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <form:option value="${nhaSanXuat.maNhaSanXuat}" label="${nhaSanXuat.tenNhaSanXuat}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Xuất xứ</label>
                                            <div class="controls">
                                            	<form:select id="selectXuatXu" name="selectXuatXu" disabled="disabled" path="xuatXu">
												   <form:option value="-1" label="Chọn xuất xứ" />
												   <c:forEach var="xuatXu" items="${dsXuatXu}" varStatus="status">
												   		<c:choose>
                                                            <c:when test="${xuatXu.maXuatXu == doChoi.xuatXu.maXuatXu}">
                                                                 <form:option selected="selected" value="${xuatXu.maXuatXu}" label="${xuatXu.tenXuatXu}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <form:option value="${xuatXu.maXuatXu}" label="${xuatXu.tenXuatXu}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình ảnh hiển thị</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input">Hình ảnh chính</label>
                                            	<ul class="thumbnails">
                                            		<li class="span2"><a href="${pageContext.request.contextPath}${toyImagesFolder}${doChoi.hinhAnhHienThiChinh}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh hiển thị ngoài trang chủ" ><img id="hinh_chinh" alt="" style="width:160px; height:120px" src="${pageContext.request.contextPath}${toyImagesFolder}${doChoi.hinhAnhHienThiChinh}" /></a></li>
                                            	</ul>
                                            	<span class="help-block">Hình ảnh chính lấy từ folder products/small ở server</span>
                                            	<label class="control-label" for="input">Hình ảnh phụ</label>
                                                <ul class="thumbnails">
                                                    <c:forEach var="hinhAnhDoChoi" items="${dsHinhAnhDoChoi}" varStatus="status">
                                                        <li class="span2"><a href="${pageContext.request.contextPath}${toyImagesFolder}${hinhAnhDoChoi.hinhAnh}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh chi tiết sản phẩm"><img id="hinh_${status.index}" alt="" style="width:119px; height:82px" src="${pageContext.request.contextPath}${toyImagesFolder}${hinhAnhDoChoi.hinhAnh}" /></a></li>
                                                    </c:forEach>
                                                </ul>
                                                <span class="help-block">Hình ảnh phụ lấy từ folder products/big ở server</span>
                                                <label class="control-label" for="input">Thiết lập hình ảnh</label>												
												<textarea cols="4" rows="4" style="width: 300px; height: 60px; resize: none" class="input-xlarge" name="hinhAnh" id="textareaHinhAnh" disabled="disabled"><c:out value="${dsTenHinhAnhDoChoi}"/></textarea>
                                            	<span class="help-block">Nhập tên hình ảnh theo cú pháp: [TenHinh.jpg][TenHinh.png][...]</span>
                                            	<span class="help-block">Hình đầu tiên sẽ là hình ảnh chính, từ hình 2 trở đi là các hình ảnh phụ</span>
                                            	<span class="help-block">Ví dụ: [a.jpg](Hình ảnh chính)[a.jpg][b.jpg](Các hình ảnh phụ)</span>
                                            	<span class="help-block">Nếu muốn đánh dấu xóa hình cũ thì cú pháp sau: [DELETE]</span>		                                                                                             
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giá bán ban đầu</label>
                                            <div class="controls">
                                            	<form:input path="giaBanBanDau" id="inputGiaBanDau" name="inputGiaBanDau" value="${doChoi.giaBanBanDau}" disabled="disabled" /> VND
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giá bán hiện tại</label>
                                            <div class="controls">
                                            	<form:input path="giaBanHienTai" id="inputGiaHienTai" name="inputGiaHienTai" value="${doChoi.giaBanHienTai}" disabled="disabled" /> VND
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giảm giá</label>
                                            <div class="controls">
                                            	<form:input path="giamGia" id="inputGiamGia" name="inputGiamGia" value="${doChoi.giamGia}" disabled="disabled" /> %
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tình trạng</label>
                                            <div class="controls">
                                            	<form:input path="tinhTrang" id="inputTinhTrang" name="inputTinhTrang" value="${doChoi.tinhTrang}" disabled="disabled" />
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Số lượng bán</label>
                                            <div class="controls">
                                            	<form:input path="soLuongBan" id="inputSoLuongBan" name="inputSoLuongBan" value="${doChoi.soLuongBan}" disabled="disabled"/>                                   
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Số lượng xem</label>
                                            <div class="controls">
                                            	<form:input path="soLuongXem" id="inputSoLuongXem" name="inputSoLuongXem" value="${doChoi.soLuongXem}" disabled="disabled"/>                                              
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Số lượng tồn</label>
                                            <div class="controls">
                                            	<form:input path="soLuongTon" id="inputSoLuongTon" name="inputSoLuongTon" value="${doChoi.soLuongTon}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Số lượng đã bán</label>
                                            <div class="controls">
                                            	<form:input path="soLuongDaBan" id="inputSoLuongDaBan" name="inputSoLuongDaBan" value="${doChoi.soLuongDaBan}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày tiếp nhận</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayTiepNhan" name="ngayTiepNhan" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${doChoi.ngayTiepNhan}' />" disabled="disabled"/>                                              
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày cập nhật</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayCapNhat" name="ngayCapNhat" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${doChoi.ngayCapNhat}' />" disabled="disabled"/>                        
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="textarea">Mô tả</label>
                                            <div class="controls">
                                            	<textarea cols="4" rows="4" style="width: 514px; height: 138px; resize: none" class="input-xlarge" name="moTa" id="textareaMoTa" disabled="disabled"><c:out value="${doChoi.moTa}"/></textarea>                                                                     
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${doChoi.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${doChoi.daXoa == false}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="false" name="chkDaXoa" id="chkDaXoa" disabled="disabled"/> 
                                                </c:if>                                              
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <button id="btnLuu" class="button btn-large" type="submit" disabled="disabled">Lưu xuống</button>
                                        </div>
                                    </fieldset>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->
