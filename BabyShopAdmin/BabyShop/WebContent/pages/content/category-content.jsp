<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dsLoaiDoChoi" value="${requestScope.dsLoaiDoChoi}"/>
<c:set var="loaiDoChoiQuery" value="${requestScope.loaiDoChoiQuery}"/>
<c:set var="nResult" value="${nResult}"/>
<c:set var="nCategoriesInPage" value="${nCategoriesInPage}"/>
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
                        <h2>Danh sách loại đồ chơi</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
                        	<div class="row-fluid">
								<div class="span6 divSearch">
									<div class="dataTables_filter">
									<form id="formSearchCategory" name="formSearchCategory" class="formSearch" method="get" action="search">
                                    <fieldset>
                                    	<input type="hidden" id="inputSearchPage" name="page" value="1"/>
										<input type="text" id="inputSearchMaLoaiDoChoi" name="maLoaiDoChoi" class="inputSearchSmall" placeholder="ID..." value="${loaiDoChoiQuery.maLoaiDoChoi==-1?'':loaiDoChoiQuery.maLoaiDoChoi}"  />
										<input type="text" id="inputSearchTenLoaiDoChoi" name="tenLoaiDoChoi" class="inputSearchLarge" placeholder="Tên loại đồ chơi..." value="${loaiDoChoiQuery.tenLoaiDoChoi}" />
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
                                        <th>Tên loại đồ chơi</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Đã xóa</th>
                                        <th>Lựa chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:if test="${dsLoaiDoChoi.size() != 0 }">
	                                    <c:forEach var="loaiDoChoi" items="${dsLoaiDoChoi}" varStatus="status">
	                                        <tr class="odd gradeX">
	                                            <td><input type="checkbox" name="options[]" id="chkbox_${loaiDoChoi.maLoaiDoChoi}" value="${loaiDoChoi.maLoaiDoChoi}" /></td>
	                                            <td><a href="detail?id=${loaiDoChoi.maLoaiDoChoi}"><c:out value="${loaiDoChoi.maLoaiDoChoi}"/></a></td>
	                                            <td><a href="detail?id=${loaiDoChoi.maLoaiDoChoi}"><c:out value="${loaiDoChoi.tenLoaiDoChoi}"/></a></td>
	                                            <td><c:out value="${loaiDoChoi.ngayCapNhat}"/></td>
	                                            <td>
	                                                <c:if test="${loaiDoChoi.daXoa == true}">
	                                                    <span style="color: red" id="isDaXoa_${loaiDoChoi.maLoaiDoChoi}"><c:out value="Rồi"/></span>
	                                                </c:if>
	                                                <c:if test="${loaiDoChoi.daXoa == false}">
	                                                    <span style="color: blue" id="isDaXoa_${loaiDoChoi.maLoaiDoChoi}"><c:out value="Chưa"/></span>
	                                                </c:if>
	                                            </td>
	                                            <td>                                                                                            
	                                                <a href="javascript:confirmDelete(${loaiDoChoi.maLoaiDoChoi})" title="Đánh dấu xóa"><img src="${pageContext.request.contextPath}/shared-res/images/cross.png" alt="Delete" /></a>
	                                                <a href="javascript:confirmReshow(${loaiDoChoi.maLoaiDoChoi})" title="Gỡ xóa"><img src="${pageContext.request.contextPath}/shared-res/images/redo.png" alt="Redo" /></a> 
	                                            </td>
	                                        </tr>
	                                    </c:forEach>
                                    </c:if>
                                    <c:if test="${dsLoaiDoChoi.size() == 0 }">
                                    	<tr class="odd gradeX">
	                                   		<td colspan="6" style="text-align: center">Không tìm thấy dữ liệu</td>
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