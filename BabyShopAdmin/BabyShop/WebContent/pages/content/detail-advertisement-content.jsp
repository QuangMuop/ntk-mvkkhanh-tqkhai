<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="quangCao" value="${requestScope.quangCao}"/>
<c:set var="adImagesFolder" value="${requestScope.AdImagesFolder}"/>

<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết quảng cáo - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật quảng cáo thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được quảng cáo !</strong>
                                            </div>
                                    </c:if>                                   
                                    <form:form modelAttribute="quangCao" action="update" name="formUpdateAdvertisement" id="formUpdateAdvertisement" method="post" enctype="multipart/form-data">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã quảng cáo</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${quangCao.maQuangCao}</label>
                                                <form:hidden path="maQuangCao" id="inputMaQuangCao" name="inputMaQuangCao" value="${quangCao.maQuangCao}"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên quảng cáo</label>
                                            <div class="controls">
                                                <form:input path="tenQuangCao" id="inputTenQuangCao" name="inputTenQuangCao" cssClass="input-xlarge" value="${quangCao.tenQuangCao}" disabled="disabled"/>                 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mô tả</label>
                                            <div class="controls">
                                                <textarea cols="4" rows="4" style="width: 514px; height: 138px; resize: none" class="input-xlarge" name="moTa" id="textareaMoTa" disabled="disabled"><c:out value="${quangCao.moTa}"/></textarea>                 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình ảnh</label>
                                            <div class="controls">
                                            	<ul class="thumbnails">
                                            		<li class="span2"><a href="${pageContext.request.contextPath}${adImagesFolder}${quangCao.hinhAnh}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh gốc"><img id="hinh_dai_dien" alt="" style="width:200px; height:150px" src="${pageContext.request.contextPath}${adImagesFolder}${quangCao.hinhAnh}" /></a></li>                              	                                            
                                            	</ul>
                                            	<br/>
                                            	<input name="hinhAnh" id="inputHinhAnh" type="file" disabled="disabled"/>                                          		 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Vị trí</label>
                                            <div class="controls">
                                                <label class="control-label" for="input"><b>${quangCao.viTri}</b></label>
                                                <form:hidden path="viTri" id="inputViTri" name="inputViTri" value="${quangCao.viTri}"/>
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
                                                <c:if test="${quangCao.coHieuLuc == true}">
                                                	<form:checkbox path="coHieuLuc" style="margin-top: 3px" value="true" name="chkCoHieuLuc" id="chkCoHieuLuc" checked="checked"/> 
                                                </c:if>
                                                <c:if test="${quangCao.coHieuLuc == false}">
                                                	<form:checkbox path="coHieuLuc" style="margin-top: 3px" value="false" name="chkCoHieuLuc" id="chkCoHieuLuc"/> 
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