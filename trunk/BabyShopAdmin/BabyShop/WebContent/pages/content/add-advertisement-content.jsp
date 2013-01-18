<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Thêm quảng cáo</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Thêm quảng cáo thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không thêm được quảng cáo !</strong>
                                            </div>
                                    </c:if>                                   
                                    <form:form modelAttribute="quangCao" action="submit" name="formAddAdvertisement" id="formAddAdvertisement" method="post" enctype="multipart/form-data">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên quảng cáo</label>
                                            <div class="controls">
                                                <form:input path="tenQuangCao" id="inputTenQuangCao" name="inputTenQuangCao" cssClass="input-xlarge" value="${quangCao.tenQuangCao}"/>                 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mô tả</label>
                                            <div class="controls">
                                                <textarea cols="4" rows="4" style="width: 514px; height: 138px; resize: none" class="input-xlarge" name="moTa" id="textareaMoTa"><c:out value="${quangCao.moTa}"/></textarea>                 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình ảnh</label>
                                            <div class="controls">
                                            	<input name="hinhAnh" id="inputHinhAnh" type="file"/>                                          		 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Vị trí</label>
                                            <div class="controls">
                                                <form:select path="viTri">
                                                	<form:option value="top" label="top" />
                                                	<form:option value="left" label="left" />
                                                	<form:option value="right" label="right" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày bắt đầu</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayBatDau" name="batDau" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.batDau}' />"/>                              	                                            
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày kết thúc</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayKetThuc" name="ketThuc" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.ketThuc}' />"/>                              	                                            
                                            </div>
                                        </div>                                                                                                            
                                        <div class="control-group">
                                            <label class="control-label" for="input">Có hiệu lực ?</label>
                                            <div class="controls">
                                                <form:checkbox path="coHieuLuc" style="margin-top: 3px" value="false" name="chkCoHieuLuc" id="chkCoHieuLuc"/>                                          
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