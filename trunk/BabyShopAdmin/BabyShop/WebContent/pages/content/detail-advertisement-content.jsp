<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="quangCao" value="${requestScope.quangCao}"/>

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
                                    <form:form modelAttribute="quangCao" action="update" name="formUpdateAdvertisement" id="formUpdateAdvertisement" method="post">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Vị trí</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${quangCao.position}</label>
                                                <form:hidden path="position" id="inputPosition" name="inputPosition" value="${quangCao.position}"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hình ảnh</label>
                                            <div class="controls">
                                            	<ul class="thumbnails">
                                            		<li class="span2"><a href="${quangCao.getLink()}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh gốc"><img id="hinh_dai_dien" alt="" style="width:200px; height:150px" src="${quangCao.link}" /></a></li>                              	                                            
                                            	</ul>
                                            	<br/>
                                           		<input name="link" id="inputLink" type="text" class="input-xlarge" value="${quangCao.link}"/> 
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày hết hạn</label>
                                            <div class="controls">
                                            	<input type="text" id="inputExpiredDate" name="expiredDate" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.expiredDate}' />"/>                              	                                            
                                            </div>
                                        </div>                                                                                                            
                                        <div class="control-group">
                                            <label class="control-label" for="input">Hiển thị</label>
                                            <div class="controls">
                                                <c:if test="${quangCao.isShow == true}">
                                                	<form:checkbox path="isShow" style="margin-top: 3px" value="true" name="chkHienThi" id="chkHienThi" checked="checked"/> 
                                                </c:if>
                                                <c:if test="${quangCao.isShow == false}">
                                                	<form:checkbox path="isShow" style="margin-top: 3px" value="false" name="chkHienThi" id="chkHienThi"/> 
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