<%-- 
    Document   : toy-content
    Created on : Oct 29, 2012, 10:32:26 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dsDoChoi" value="${requestScope.dsDoChoi}"/>
<c:set var="dsLoaiDoChoi" value="${requestScope.dsLoaiDoChoi}"/>
<c:set var="doChoiQuery" value="${requestScope.doChoiQuery}"/>
<c:set var="nResult" value="${nResult}"/>
<c:set var="nToysInPage" value="${nToysInPage}"/>
<c:set var="page" value="${page}"/>
<c:set var="nPages" value="${nPages}"/>
<c:set var="pageNumbers" value="${pageNumbers}"/>

<!-- Main page container -->

    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->        
        <!-- Grid row -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Danh sách đồ chơi</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
                        	<div class="row-fluid">
								<div class="span6 divSearch">
									<div class="dataTables_filter">
									<form id="formSearchToy" name="formSearchToy" class="formSearch" method="get" action="search">
                                    <fieldset>
                                    	<input type="hidden" id="inputSearchPage" name="page" value="1"/>
										<input type="text" id="inputSearchMaDoChoi" name="maDoChoi" class="inputSearchSmall" placeholder="ID..." value="${doChoiQuery.maDoChoi==-1?'':doChoiQuery.maDoChoi}"  />
										<input type="text" id="inputSearchTenDoChoi" name="tenDoChoi" class="inputSearchMedium" placeholder="Tên đồ chơi..." value="${doChoiQuery.tenDoChoi}" />
										<select id="inputSearchLoaiDoChoi" name="loaiDoChoi" class="selectSearchMedium">
											<c:if test="${doChoiQuery.loaiDoChoi == null}">
												<option selected="selected" value="-1">---Chọn loại đồ chơi---</option>
	                                             <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">                                           
	                                                     <option value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
	                                             </c:forEach>
											</c:if>
											<c:if test="${doChoiQuery.loaiDoChoi != null}">
												<option value="-1">---Chọn loại đồ chơi---</option>
	                                             <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">                                           	                                                     	                                             
	                                             		<c:choose>
                                                            <c:when test="${doChoiQuery.loaiDoChoi.maLoaiDoChoi == loaiDoChoi.maLoaiDoChoi}">
                                                                 <option selected="selected" value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <option value="${loaiDoChoi.maLoaiDoChoi}">${loaiDoChoi.tenLoaiDoChoi}</option>
                                                            </c:otherwise>
                                                        </c:choose>
	                                             </c:forEach>

											</c:if>
                                         </select>
                                         <select id="inputSearchTinhTrang" name="tinhTrang" class="selectSearchMedium">
                                         	<option ${doChoiQuery.tinhTrang =='' ? 'selected="selected"':''} value="">---Chọn tình trạng---</option>
                                         	<option ${doChoiQuery.tinhTrang =='Hết hàng' ? 'selected="selected"':''} value="Hết hàng">Hết hàng</option>
                                         	<option ${doChoiQuery.tinhTrang =='Còn hàng' ? 'selected="selected"':''} value="Còn hàng">Còn hàng</option>
                                         	<option ${doChoiQuery.tinhTrang =='Ngưng bán' ? 'selected="selected"':''} value="Ngưng bán">Ngưng bán</option>
                                         </select>
                                         <select id="inputSearchDaXoa" name="daXoa" class="selectSearchSmall">
                                         	<option ${daXoaQuery == '' ? 'selected="selected"':''} value="">---Chọn đã xóa---</option>
                                         	<option ${daXoaQuery == 'true' ? 'selected="selected"':''} value="true">Rồi</option>
                                         	<option ${daXoaQuery == 'false' ? 'selected="selected"':''} value="false">Chưa</option>
                                         </select>
                                        <button id="btnTim" class="button" type="submit">Tìm kiếm</button>
										</fieldset>
									</form>
									</div>
								</div>
							</div>
                            <table class="datatable table table-striped table-bordered" id="example">
                                <thead>
                                    <tr>
                                        <th><input class="check-all" name="checkall" type="checkbox" /></th>
                                        <th>ID</th>
                                        <th>Tên đồ chơi</th>
                                        <th>Loại đồ chơi</th>
                                        <th>Ngày tiếp nhận</th>
                                        <th>Số lượng đã bán</th>
                                        <th>Tình trạng</th>
                                        <th>Đã xóa</th>
                                        <th>Lựa chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:if test="${dsDoChoi.size() != 0 }">
                                    	<c:forEach var="doChoi" items="${dsDoChoi}" varStatus="status">
	                                        <tr class="odd gradeX">
	                                            <td><input type="checkbox" name="options[]" id="chkbox_${doChoi.maDoChoi}" value="${doChoi.maDoChoi}" /></td>
	                                            <td><a href="detail?id=${doChoi.maDoChoi}"><c:out value="${doChoi.maDoChoi}"/></a></td>
	                                            <td><a href="detail?id=${doChoi.maDoChoi}"><c:out value="${doChoi.tenDoChoi}"/></a></td>
	                                            <td><a href="/BabyShop/category/detail?id=${doChoi.loaiDoChoi.maLoaiDoChoi}"><c:out value="${doChoi.loaiDoChoi.tenLoaiDoChoi}"/></a></td>
	                                            <td><c:out value="${doChoi.ngayTiepNhan}"/></td>
	                                            <td><c:out value="${doChoi.soLuongDaBan}"/></td>
	                                            <td><c:out value="${doChoi.tinhTrang}"/></td>
	                                            <td>
	                                                <c:if test="${doChoi.daXoa == true}">
	                                                    <span style="color: red" id="isDaXoa_${doChoi.maDoChoi}"><c:out value="Rồi"/></span>
	                                                </c:if>
	                                                <c:if test="${doChoi.daXoa == false}">
	                                                    <span style="color: blue" id="isDaXoa_${doChoi.maDoChoi}"><c:out value="Chưa"/></span>
	                                                </c:if>
	                                            </td>
	                                            <td>                                                                                                
	                                                <a href="javascript:confirmDelete(${doChoi.maDoChoi})" title="Đánh dấu xóa"><img src="${pageContext.request.contextPath}/shared-res/images/cross.png" alt="Delete" /></a>
	                                                <a href="javascript:confirmReshow(${doChoi.maDoChoi})" title="Gỡ xóa"><img src="${pageContext.request.contextPath}/shared-res/images/redo.png" alt="Redo" /></a> 
	                                            </td>
	                                        </tr>
	                                    </c:forEach>
                                    </c:if>
                                    <c:if test="${dsDoChoi.size() == 0 }">
                                    <tr class="odd gradeX">
	                                   		<td colspan="9" style="text-align: center">Không tìm thấy dữ liệu</td>
	                                   	</tr>
                                    </c:if>
                                </tbody>
                            </table>
                            <c:if test="${nPages > 1}">
	                            <div class="row-fluid">
	                            	<div class="span6">
										<div id="example_info" class="dataTables_info">Trang ${page}/${nPages} - Tổng cộng: ${nResult} kết quả</div>
									</div>
	                            	<div class="span6">
	                            		<div class="dataTables_paginate paging_bootstrap pagination">
	                            			<ul>
	                            			 	<c:forEach var="tra" items="${pageNumbers}">
	                            					<c:if test="${tra == page}">
	                            						<li class="active">
	                            							<c:if test="${pagingFor == 'list'}">
	                            								<a href="list?page=${tra}"><c:out value="${tra}"/></a>
	                            							</c:if>
	                            							<c:if test="${pagingFor == 'search'}">
	                            								<a href="search?page=${tra}${searchContent}"><c:out value="${tra}"/></a>
	                            							</c:if>
	                            						</li>
	                            					</c:if>
	                            					<c:if test="${tra != page}">
	                            						<li >
	                            							<c:if test="${pagingFor == 'list'}">
	                            								<a href="list?page=${tra}"><c:out value="${tra}"/></a>
	                            							</c:if>
	                            							<c:if test="${pagingFor == 'search'}">
	                            								<a href="search?page=${tra}${searchContent}"><c:out value="${tra}"/></a>
	                            							</c:if>
	                            						</li>
	                            					</c:if>
	                            				</c:forEach>	
	                            			</ul>
	                            		</div>
	                            	</div>
	                            </div>
                            </c:if>
                        </div>
                        <a class="button" href="add">Thêm mới</a>
                        <a class="button" href="javascript:calculate()">Xóa nhiều</a>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->
