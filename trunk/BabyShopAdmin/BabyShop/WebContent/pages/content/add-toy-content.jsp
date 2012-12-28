<%-- 
    Document   : add-toy-content
    Created on : Oct 30, 2012, 12:06:38 PM
    Author     : Khanh
--%>

<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Thêm mới đồ chơi</h2>
                        
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                    <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Thêm đồ chơi thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không thêm được đồ chơi !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="doChoiUpload" action="submit" name="formAddToy" id="formAddToy" method="post">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên đồ chơi</label>
                                            <div class="controls">
                                            	<form:input path="tenDoChoi" id="inputTenDoChoi" name="inputTenDoChoi" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Loại đồ chơi</label>
                                            <div class="controls">
                                            	<form:select path="loaiDoChoi">
												   <form:option value="-1" label="Chọn loại đồ chơi" />
												   <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">
                                                            <form:option value="${loaiDoChoi.maLoaiDoChoi}" label="${loaiDoChoi.tenLoaiDoChoi}" />
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Nhà sản xuất</label>
                                            <div class="controls">
                                            	<form:select path="nhaSanXuat">
												   <form:option value="-1" label="Chọn nhà sản xuất" />
												   <c:forEach var="nhaSanXuat" items="${dsNhaSanXuat}" varStatus="status">
                                                            <form:option value="${nhaSanXuat.maNhaSanXuat}" label="${nhaSanXuat.tenNhaSanXuat}" />
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Xuất xứ</label>
                                            <div class="controls">
                                            	<form:select path="xuatXu">
												   <c:forEach var="xuatXu" items="${dsXuatXu}" varStatus="status">
                                                            <form:option value="${xuatXu.maXuatXu}" label="${xuatXu.tenXuatXu}" />
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="textarea">Hình ảnh</label>
                                            <div class="controls">
                                            	<form:textarea path="hinhAnh" id="textareaHinhAnh" name="textareaHinhanh" cssClass="input-xlarge" rows="4" cols="4" style="width: 300px; height: 60px; resize: none"/>
                                            	<span class="help-block">Nhập tên hình ảnh theo cú pháp: [TenHinh.jpg][TenHinh.png][...]</span>
                                            	<span class="help-block">Hình đầu tiên sẽ là hình ảnh chính, từ hình 2 trở đi là các hình ảnh phụ</span>
                                            	<span class="help-block">Ví dụ: [a.jpg](Hình ảnh chính)[a.jpg][b.jpg](Các hình ảnh phụ)</span>
                                            	<span class="help-block">Hình ảnh chính lấy từ folder products/small ở server</span>
                                            	<span class="help-block">Hình ảnh phụ lấy từ folder products/big ở server</span>
                                            </div>
                                        </div>               
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giá bán</label>
                                            <div class="controls">
                                            	<form:input path="giaBanBanDau" id="inputGiaBanDau" name="inputGiaBanDau"/> VND
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giảm giá</label>
                                            <div class="controls">
                                            	<form:input path="giamGia" id="inputGiamGia" name="inputGiamGia"/> %
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Số lượng</label>
                                            <div class="controls">
                                            	<form:input path="soLuongBan" id="inputSoLuongBan" name="inputSoLuongBan"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày tiếp nhận</label>
                                            <div class="controls">
                                            	<form:input path="ngayTiepNhan" id="inputNgayTiepNhan" name="inputNgayTiepNhan" cssClass="datepicker input-small" data-date-format="yyyy-mm-dd"/>                                              
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="textarea">Mô tả</label>
                                            <div class="controls">
                                            	<form:textarea path="moTa" id="textareaMoTa" name="textareaMota" cssClass="input-xlarge" rows="4" cols="4" style="width: 514px; height: 138px; resize: none"/>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <button id="btnLuu" class="button btn-large" type="submit">Lưu xuống</button>
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
