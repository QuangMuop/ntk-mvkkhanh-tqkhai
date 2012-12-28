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
                        <h2>Thêm mới tài khoản</h2>
                        
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                    <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Thêm tài khoản thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không thêm được tài khoản !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="taiKhoan" action="submit" name="formAddAccount" id="formAddAccount" method="post" enctype="multipart/form-data">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã tài khoản</label>
                                            <div class="controls">
                                            	<form:input path="maTaiKhoan" id="inputMaTaiKhoan" name="inputMaTaiKhoan" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mật khẩu</label>
                                            <div class="controls">
                                            	<form:password path="matKhau" id="inputMatKhau" name="inputMatKhau" cssClass="input-xlarge"/>                                              
                                            </div>
                                        </div>
                                         <div class="control-group">
                                            <label class="control-label" for="select">Loại tài khoản</label>
                                            <div class="controls">
                                            	<form:select path="loaiTaiKhoan">
												   <c:forEach var="loaiTaiKhoan" items="${dsLoaiTaiKhoan}" varStatus="status">
                                                            <form:option value="${loaiTaiKhoan.maLoaiTaiKhoan}" label="${loaiTaiKhoan.tenLoaiTaiKhoan}" />
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>                                      
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình đại diện</label>
                                            <div class="controls">
                                            	<input name="avatar" id="inputHinhDaiDien" type="file"/>                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Họ tên</label>
                                            <div class="controls">
                                            	<form:input path="hoTen" id="inputHoTen" name="inputHoTen" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày sinh</label>
                                            <div class="controls">
                                            	<form:input path="ngaySinh" id="inputNgaySinh" name="inputNgaySinh" cssClass="datepicker input-small" data-date-format="yyyy-mm-dd"/>                        
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Thành phố</label>
                                            <div class="controls">
                                            	<form:input path="thanhPho" id="inputThanhPho" name="inputThanhPho" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Điện thoại</label>
                                            <div class="controls">
                                            	<form:input path="dienThoai" id="inputDienThoai" name="inputDienThoai" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giới tính</label>
                                            <div class="controls">
                                            	<form:radiobutton path="gioiTinh" id="inputGioiTinhNam" value="True" checked="checked"/>Nam <form:radiobutton path="gioiTinh" id="inputGioiTinhNu" value="False" />Nữ                                             
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Email</label>
                                            <div class="controls">
                                            	<form:input path="email" id="inputEmail" name="inputEmail" cssClass="input-xlarge" />                                               
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