<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dsTaiKhoan" value="${requestScope.dsTaiKhoan}"/>
<c:set var="dsLoaiTaiKhoan" value="${requestScope.dsLoaiTaiKhoan}"/>
<c:set var="taiKhoanQuery" value="${requestScope.taiKhoanQuery}"/>
<c:set var="nResult" value="${nResult}"/>
<c:set var="nAccountsInPage" value="${nAccountsInPage}"/>
<c:set var="page" value="${page}"/>
<c:set var="nPages" value="${nPages}"/>
<c:set var="pageNumbers" value="${pageNumbers}"/>
<c:set var="daXoaQuery" value="${daXoaQuery}"/>
<c:set var="daBanQuery" value="${daBanQuery}"/>

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
                        <h2>Danh sách tài khoản</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
                        	<div class="row-fluid">
								<div class="span6 divSearch">
									<div class="dataTables_filter">
									<form id="formSearchAccount" name="formSearchAccount" class="formSearch" method="get" action="search">
                                    <fieldset>
                                    	<input type="hidden" id="inputSearchPage" name="page" value="1"/>
										<input type="text" id="inputSearchMaTaiKhoan" name="maTaiKhoan" class="inputSearchMedium" placeholder="Mã tài khoản..." value="${taiKhoanQuery.maTaiKhoan==''?'':taiKhoanQuery.maTaiKhoan}"  />									
										<select id="inputSearchLoaiTaiKhoan" name="loaiTaiKhoan" class="selectSearchMedium">
											<c:if test="${taiKhoanQuery.loaiTaiKhoan == null}">
												<option selected="selected" value="-1">---Chọn loại tài khoản---</option>
	                                             <c:forEach var="loaiTaiKhoan" items="${dsLoaiTaiKhoan}" varStatus="status">                                           
	                                                     <option value="${loaiTaiKhoan.maLoaiTaiKhoan}">${loaiTaiKhoan.tenLoaiTaiKhoan}</option>
	                                             </c:forEach>
											</c:if>
											<c:if test="${taiKhoanQuery.loaiTaiKhoan != null}">
												<option value="-1">---Chọn loại tài khoản---</option>
	                                             <c:forEach var="loaiTaiKhoan" items="${dsLoaiTaiKhoan}" varStatus="status">                                           	                                                     	                                             
	                                             		<c:choose>
                                                            <c:when test="${taiKhoanQuery.loaiTaiKhoan.maLoaiTaiKhoan == loaiTaiKhoan.maLoaiTaiKhoan}">
                                                                 <option selected="selected" value="${loaiTaiKhoan.maLoaiTaiKhoan}">${loaiTaiKhoan.tenLoaiTaiKhoan}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <option value="${loaiTaiKhoan.maLoaiTaiKhoan}">${loaiTaiKhoan.tenLoaiTaiKhoan}</option>
                                                            </c:otherwise>
                                                        </c:choose>
	                                             </c:forEach>

											</c:if>
                                         </select>
                                         <select id="inputSearchDaXoa" name="daXoa" class="selectSearchSmall">
                                         	<option ${daXoaQuery == '' ? 'selected="selected"':''} value="">---Chọn đã xóa---</option>
                                         	<option ${daXoaQuery == 'true' ? 'selected="selected"':''} value="true">Rồi</option>
                                         	<option ${daXoaQuery == 'false' ? 'selected="selected"':''} value="false">Chưa</option>
                                         </select>
                                         <select id="inputSearchDaBan" name="daBan" class="selectSearchSmall">
                                         	<option ${daBanQuery == '' ? 'selected="selected"':''} value="">---Chọn đã ban---</option>
                                         	<option ${daBanQuery == 'true' ? 'selected="selected"':''} value="true">Rồi</option>
                                         	<option ${daBanQuery == 'false' ? 'selected="selected"':''} value="false">Chưa</option>
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
                                        <th>STT</th>
                                        <th>Mã tài khoản</th>
                                        <th>Loại tài khoản</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Đã xóa</th>
                                        <th>Ban/Unban</th>
                                        <th>Lựa chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:if test="${dsTaiKhoan.size() != 0}">
                                    	<c:forEach var="taiKhoan" items="${dsTaiKhoan}" varStatus="status">
	                                        <tr class="odd gradeX">
	                                            <td><input type="checkbox" name="options[]" id="chkbox_${taiKhoan.maTaiKhoan}" value="${taiKhoan.maTaiKhoan}" /></td>
	                                            <td><c:out value="${status.index +1 }"/></td>
	                                            <td><a href="detail?id=${taiKhoan.maTaiKhoan}"><c:out value="${taiKhoan.maTaiKhoan}"/></a></td>
	                                            <td><c:out value="${taiKhoan.loaiTaiKhoan.tenLoaiTaiKhoan}"/></td>
	                                            <td><c:out value="${taiKhoan.ngayCapNhat}"/></td>
	                                            <td>
	                                                <c:if test="${taiKhoan.daXoa == true}">
	                                                    <span style="color: red" id="isDaXoa_${taiKhoan.maTaiKhoan}"><c:out value="Rồi"/></span>
	                                                </c:if>
	                                                <c:if test="${taiKhoan.daXoa == false}">
	                                                    <span style="color: blue" id="isDaXoa_${taiKhoan.maTaiKhoan}"><c:out value="Chưa"/></span>
	                                                </c:if>
	                                            </td>
	                                            <td>                                            	                                            
	                                                <a href="javascript:confirmBan('${taiKhoan.maTaiKhoan}')" title="Khóa tài khoản"><img src="${pageContext.request.contextPath}/shared-res/images/ban.png" alt="Ban" /></a>
	                                                <a href="javascript:confirmUnban('${taiKhoan.maTaiKhoan}')" title="Gỡ khóa"><img src="${pageContext.request.contextPath}/shared-res/images/unban.png" alt="Unban" /></a> 
	                                                 <c:if test="${taiKhoan.daBan == true}">
	                                                    <span style="color: red" id="isDaKhoa_${taiKhoan.maTaiKhoan}"><c:out value="Rồi"/></span>
	                                                </c:if>
	                                                <c:if test="${taiKhoan.daBan == false}">
	                                                    <span style="color: blue" id="isDaKhoa_${taiKhoan.maTaiKhoan}"><c:out value="Chưa"/></span>
	                                                </c:if> 
	                                            </td>
	                                            <td>                                              
	                                                <a href="javascript:confirmDelete('${taiKhoan.maTaiKhoan}')" title="Đánh dấu xóa"><img src="${pageContext.request.contextPath}/shared-res/images/cross.png" alt="Delete" /></a>
	                                                <a href="javascript:confirmReshow('${taiKhoan.maTaiKhoan}')" title="Gỡ xóa"><img src="${pageContext.request.contextPath}/shared-res/images/redo.png" alt="Redo" /></a> 
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    </c:if>
                                    <c:if test="${dsTaiKhoan.size() == 0}">
                                    	<tr class="odd gradeX">
	                                   		<td colspan="8" style="text-align: center">Không tìm thấy dữ liệu</td>
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
                        <a class="button" href="javascript:calculateMultiDelete()">Xóa nhiều</a>
                        <a class="button" href="javascript:calculateMultiBan()">Khóa nhiều</a>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->