<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="taiKhoan" value="${requestScope.taiKhoan}"/>
<c:set var="dsLoaiTaiKhoan" value="${requestScope.dsLoaiTaiKhoan}"/>
<c:set var="avatarImagesFolder" value="${requestScope.AvatarImagesFolder}"/>


<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết tài khoản - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật tài khoản thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được tài khoản !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="taiKhoan" action="update" name="formUpdateAccount" id="formUpdateAccount" method="post" enctype="multipart/form-data">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã tài khoản</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${taiKhoan.maTaiKhoan}</label>
                                                <form:hidden path="maTaiKhoan" id="inputMaTaiKhoan" name="inputMaTaiKhoan" value="${taiKhoan.maTaiKhoan}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mật khẩu</label>
                                            <div class="controls">
                                            	<input type="password" value="${taiKhoan.matKhau}" class="input-xlarge" disabled="disabled" />
                                            	<form:hidden path="matKhau" id="inputMatKhau" name="inputMatKhau" value="${taiKhoan.matKhau}" disabled="disabled"/>                                              
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Loại tài khoản</label>
                                            <div class="controls">
                                                <form:select id="selectLoaiTaiKhoan" name="selectLoaiTaiKhoan" disabled="disabled" path="loaiTaiKhoan">
												   <c:forEach var="loaiTaiKhoan" items="${dsLoaiTaiKhoan}" varStatus="status">
												   		<c:choose>
                                                            <c:when test="${loaiTaiKhoan.maLoaiTaiKhoan == taiKhoan.loaiTaiKhoan.maLoaiTaiKhoan}">
                                                                 <form:option selected="selected" value="${loaiTaiKhoan.maLoaiTaiKhoan}" label="${loaiTaiKhoan.tenLoaiTaiKhoan}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <form:option value="${loaiTaiKhoan.maLoaiTaiKhoan}" label="${loaiTaiKhoan.tenLoaiTaiKhoan}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>                                    
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình đại diện</label>
                                            <div class="controls">
                                            <ul class="thumbnails">
                                            <li class="span2"><a href="${pageContext.request.contextPath}${avatarImagesFolder}${taiKhoan.avatar}" rel="prettyPhoto" class="thumbnail" title="Ảnh đại diện"><img id="hinh_dai_dien" alt="" style="width:120px; height:160px" src="${pageContext.request.contextPath}${avatarImagesFolder}${taiKhoan.avatar}" /></a>  </li>
                                            </ul>                                                                                     	                                        	
                                           	<br/>
                                           	<input name="avatar" id="inputHinhDaiDien" type="file" disabled="disabled"/>                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Họ tên</label>
                                            <div class="controls">
                                            	<form:input path="hoTen" id="inputHoTen" name="inputHoTen" value="${taiKhoan.hoTen}" disabled="disabled" cssClass="input-xlarge"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày sinh</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgaySinh" name="ngaySinh" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${taiKhoan.ngaySinh}' />" disabled="disabled"/>                                            
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Thành phố</label>
                                            <div class="controls">
                                            	<form:input path="thanhPho" id="inputThanhPho" name="inputThanhPho" value="${taiKhoan.thanhPho}" disabled="disabled" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Điện thoại</label>
                                            <div class="controls">
                                            	<form:input path="dienThoai" id="inputDienThoai" name="inputDienThoai" value="${taiKhoan.dienThoai}" disabled="disabled" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Giới tính</label>
                                            <div class="controls">
                                            	<c:if test="${taiKhoan.gioiTinh == true}">
                                            		<form:radiobutton path="gioiTinh" id="inputGioiTinhNam" value="True" checked="checked" disabled="disabled"/>Nam <form:radiobutton path="gioiTinh" id="inputGioiTinhNu" value="False" disabled="disabled"/>Nữ
                                            	</c:if>
                                            	<c:if test="${taiKhoan.gioiTinh == false}">
                                            		<form:radiobutton path="gioiTinh" id="inputGioiTinhNam" value="True" disabled="disabled"/>Nam <form:radiobutton path="gioiTinh" id="inputGioiTinhNu" value="False"  checked="checked"  disabled="disabled"/>Nữ
                                            	</c:if>                                           	                                             
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Email</label>
                                            <div class="controls">
                                            	<form:input path="email" id="inputEmail" name="inputEmail" value="${taiKhoan.email}" disabled="disabled" cssClass="input-xlarge" />                                               
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày cập nhật</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayCapNhat" name="ngayCapNhat" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${taiKhoan.ngayCapNhat}' />" disabled="disabled"/>                         
                                            </div>
                                        </div> 
										<div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${taiKhoan.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${taiKhoan.daXoa == false}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="false" name="chkDaXoa" id="chkDaXoa" disabled="disabled"/> 
                                                </c:if>
                                                
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã ban</label>
                                            <div class="controls">
                                                <c:if test="${taiKhoan.daBan == true}">
                                                	<form:checkbox path="daBan" style="margin-top: 3px" value="true" name="chkDaBan" id="chkDaBan" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${taiKhoan.daBan == false}">
                                                	<form:checkbox path="daBan" style="margin-top: 3px" value="false" name="chkDaBan" id="chkDaBan" disabled="disabled"/> 
                                                </c:if>
                                                
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