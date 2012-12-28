<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="loaiDoChoi" value="${requestScope.loaiDoChoi}"/>


<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết loại đồ chơi - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật loại đồ chơi thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được loại đồ chơi !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="loaiDoChoi" action="update" name="formUpdateCategory" id="formUpdateCategory" method="post">      
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã loại đồ chơi</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${loaiDoChoi.maLoaiDoChoi}</label>
                                                <form:hidden path="maLoaiDoChoi" id="inputMaLoaiDoChoi" name="inputMaLoaiDoChoi" value="${loaiDoChoi.maLoaiDoChoi}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên loại đồ chơi</label>
                                            <div class="controls">
                                            	<form:input path="tenLoaiDoChoi" id="inputTenLoaiDoChoi" name="inputTenLoaiDoChoi" cssClass="input-xlarge" value="${loaiDoChoi.tenLoaiDoChoi}" disabled="disabled"/>  
                                            </div>
                                        </div>
                                        
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày cập nhật</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayCapNhat" name="ngayCapNhat" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${loaiDoChoi.ngayCapNhat}' />" disabled="disabled"/>                     
                                            </div>
                                        </div>                                      
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${loaiDoChoi.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${loaiDoChoi.daXoa == false}">
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