<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="dsBinhLuan" value="${requestScope.dsBinhLuan}"/>
<c:set var="binhLuanQuery" value="${requestScope.binhLuanQuery}"/>
<c:set var="daXoaQuery" value="${requestScope.daXoaQuery}"/>
<c:set var="kiemDuyetQuery" value="${requestScope.kiemDuyetQuery}"/>
<c:set var="nResult" value="${nResult}"/>
<c:set var="nCommentsInPage" value="${nCommentsInPage}"/>
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
                        <h2>Danh sách bình luận</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
                        	<div class="row-fluid">
								<div class="span6 divSearch">
									<div class="dataTables_filter">
									<form id="formSearchComment" name="formSearchComment" class="formSearch" method="get" action="search">
                                    <fieldset>
                                    	<input type="hidden" id="inputSearchPage" name="page" value="1"/>
                                    	<input type="text" id="inputSearchNgayBinhLuan" name="ngayBinhLuan" class="datepicker clearable inputSearchMedium" placeholder="Ngày post..." value="<fmt:formatDate pattern='yyyy-MM-dd' value='${binhLuanQuery.ngayBinhLuan}' />"  />
                                    	<input type="text" id="inputSearchMaBinhLuan" name="maBinhLuan" class="inputSearchSmall" placeholder="ID..." value="${binhLuanQuery.maBinhLuan==-1?'':binhLuanQuery.maBinhLuan}"  />
                                        <input type="text" id="inputSearchTaiKhoan" name="taiKhoan" class="inputSearchMedium" placeholder="Tài khoản..." value="${binhLuanQuery.taiKhoan ==null?'':binhLuanQuery.taiKhoan.maTaiKhoan}"  />
                                        <input type="text" id="inputSearchDoChoi" name="doChoi" class="inputSearchMedium" placeholder="Đồ chơi..." value="${binhLuanQuery.doChoi ==null?'':binhLuanQuery.doChoi.tenDoChoi}"  />                                    
                                         <select id="inputSearchDuyet" name="kiemDuyet" class="selectSearchMedium">
                                         	<option ${kiemDuyetQuery == '' ? 'selected="selected"':''} value="">---Chọn đã duyệt---</option>
                                         	<option ${kiemDuyetQuery == 'true' ? 'selected="selected"':''} value="true">Rồi</option>
                                         	<option ${kiemDuyetQuery == 'false' ? 'selected="selected"':''} value="false">Chưa</option>
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
                                        <th>Nội dung</th>
                                        <th>Ngày post</th>
                                        <th>Tài khoản</th>
                                        <th>Đồ chơi</th>
                                        <th>Đã duyệt</th>
                                        <th>Đã xóa</th>
                                        <th>Lựa chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:if test="${dsBinhLuan.size() != 0 }" >
                                    <c:forEach var="binhLuan" items="${dsBinhLuan}" varStatus="status">
                                        <tr class="odd gradeX">
                                            <td><input type="checkbox" name="options[]" id="chkbox_${binhLuan.maBinhLuan}" value="${binhLuan.maBinhLuan}" /></td>
                                            <td><a href="detail?id=${binhLuan.maBinhLuan}"><c:out value="${binhLuan.maBinhLuan}"/></a></td>
                                            <td>                                           
												<div class="comment-content" title='<c:out value="${binhLuan.noiDung}"/>' style="text-overflow:ellipsis;"><c:out value="${binhLuan.noiDung}"/></div>
                                            </td>
                                            <td><c:out value="${binhLuan.ngayBinhLuan}"/></td>
                                            <td><a href="/BabyShop/account/detail?id=${binhLuan.taiKhoan.getMaTaiKhoan()}"><c:out value="${binhLuan.taiKhoan.getMaTaiKhoan()}"/></a></td>
                                            <td><a href="/BabyShop/toy/detail?id=${binhLuan.doChoi.getMaDoChoi()}"><c:out value="${binhLuan.doChoi.getTenDoChoi()}"/></a></td>
                                            <td>
                                                <c:if test="${binhLuan.kiemDuyet == true}">
                                                    <span style="color: red" id="isDaDuyet_${binhLuan.maBinhLuan}"><c:out value="Rồi"/></span>
                                                </c:if>
                                                <c:if test="${binhLuan.kiemDuyet == false}">
                                                    <span style="color: blue" id="isDaDuyet_${binhLuan.maBinhLuan}"><c:out value="Chưa"/></span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${binhLuan.daXoa == true}">
                                                    <span style="color: red" id="isDaXoa_${binhLuan.maBinhLuan}"><c:out value="Rồi"/></span>
                                                </c:if>
                                                <c:if test="${binhLuan.daXoa == false}">
                                                    <span style="color: blue" id="isDaXoa_${binhLuan.maBinhLuan}"><c:out value="Chưa"/></span>
                                                </c:if>
                                            </td>
                                            <td>                                           
                                                <a href="javascript:confirmDelete(${binhLuan.maBinhLuan})" title="Đánh dấu xóa"><img src="${pageContext.request.contextPath}/shared-res/images/cross.png" alt="Delete" /></a>
                                                <a href="javascript:confirmReshow(${binhLuan.maBinhLuan})" title="Gỡ xóa"><img src="${pageContext.request.contextPath}/shared-res/images/redo.png" alt="Redo" /></a>
                                                <a href="javascript:confirmApprove(${binhLuan.maBinhLuan})" title="Duyệt bình luận"><img src="${pageContext.request.contextPath}/shared-res/images/approve.png" alt="Approve" /></a> 
                                            	<a href="javascript:confirmDisapprove(${binhLuan.maBinhLuan})" title="Gỡ duyệt bình luận"><img src="${pageContext.request.contextPath}/shared-res/images/disapprove.png" alt="Disapprove" /></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                   </c:if>
                                   <c:if test="${dsBinhLuan.size() == 0 }" >
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
                        <a class="button" href="javascript:calculateMultiDelete()">Xóa nhiều</a>
                        <a class="button" href="javascript:calculateMultiApprove()">Duyệt nhiều</a>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->