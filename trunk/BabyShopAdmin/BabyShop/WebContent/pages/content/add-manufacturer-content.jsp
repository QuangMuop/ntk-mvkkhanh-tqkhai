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
                        <h2>Thêm mới nhà sản xuất</h2>
                        
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                    <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Thêm nhà sản xuất thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không thêm được nhà sản xuất !</strong>
                                            </div>
                                    </c:if>
                                    <form:form modelAttribute="nhaSanXuat" action="submit" name="formAddManufacturer" id="formAddManufacturer" method="post">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên nhà sản xuất</label>
                                            <div class="controls">
                                            	<form:input path="tenNhaSanXuat" id="inputTenNhaSanXuat" name="inputTenNhaSanXuat" cssClass="input-xlarge" />                                               
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