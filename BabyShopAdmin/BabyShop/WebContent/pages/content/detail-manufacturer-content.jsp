<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="nhaSanXuat" value="${requestScope.nhaSanXuat}"/>

<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết nhà sản xuất - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật nhà sản xuất thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được nhà sản xuất !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="nhaSanXuat" action="update" name="formUpdateManufacturer" id="formUpdateManufacturer" method="post">      
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã nhà sản xuất</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${nhaSanXuat.maNhaSanXuat}</label>
                                                <form:hidden path="maNhaSanXuat" id="inputMaNhaSanXuat" name="inputMaNhaSanXuat" value="${nhaSanXuat.maNhaSanXuat}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên nhà sản xuất</label>
                                            <div class="controls">
                                            	<form:input path="tenNhaSanXuat" id="inputTenNhaSanXuat" name="inputTenNhaSanXuat" cssClass="input-xlarge" value="${nhaSanXuat.tenNhaSanXuat}" disabled="disabled"/>  
                                            </div>
                                        </div>
                                        
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày cập nhật</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayCapNhat" name="ngayCapNhat" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${nhaSanXuat.ngayCapNhat}' />" disabled="disabled"/>                                                                  
                                            </div>
                                        </div>                                      
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${nhaSanXuat.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${nhaSanXuat.daXoa == false}">
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