<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="binhLuan" value="${requestScope.binhLuan}"/>

<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết bình luận - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật bình luận thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được bình luận !</strong>
                                            </div>
                                    </c:if>                                   
                                    <form:form modelAttribute="binhLuan" action="update" name="formUpdateComment" id="formUpdateComment" method="post">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã bình luận</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${binhLuan.maBinhLuan}</label>
                                                <form:hidden path="maBinhLuan" id="inputMaBinhLuan" name="inputMaBinhLuan" value="${binhLuan.maBinhLuan}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tài khoản</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><a href="/BabyShop/account/detail?id=${binhLuan.taiKhoan.getMaTaiKhoan()}">${binhLuan.taiKhoan.getMaTaiKhoan()}</a></label>                                  	                                            
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đồ chơi</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><a href="/BabyShop/toy/detail?id=${binhLuan.doChoi.getMaDoChoi()}">${binhLuan.doChoi.getTenDoChoi()}</a></label>                                  	                                            
                                            </div>
                                        </div>                                      
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày post</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><fmt:formatDate pattern='yyyy-MM-dd' value='${binhLuan.ngayBinhLuan}' /></label>                      
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Nội dung</label>
                                            <div class="controls">
                                            	<textarea cols="4" rows="4" style="width: 514px; height: 138px; resize: none" class="input-xlarge" name="noiDung" id="textareaNoiDung" disabled="disabled"><c:out value="${binhLuan.noiDung}"/></textarea>                      
                                            </div>
                                        </div>                                     
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã duyệt</label>
                                            <div class="controls">
                                                <c:if test="${binhLuan.kiemDuyet == true}">
                                                	<form:checkbox path="kiemDuyet" style="margin-top: 3px" value="true" name="chkKiemDuyet" id="chkKiemDuyet" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${binhLuan.kiemDuyet == false}">
                                                	<form:checkbox path="kiemDuyet" style="margin-top: 3px" value="false" name="chkKiemDuyet" id="chkKiemDuyet" disabled="disabled"/> 
                                                </c:if>
                                                
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${binhLuan.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${binhLuan.daXoa == false}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="false" name="chkDaXoa" id="chkDaXoa" disabled="disabled"/> 
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