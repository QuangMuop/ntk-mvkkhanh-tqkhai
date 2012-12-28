<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="dsHoaDon" value="${requestScope.dsHoaDon}"/>
<c:set var="dsTrangThai" value="${requestScope.dsTrangThai}"/>
<c:set var="hoaDonQuery" value="${requestScope.hoaDonQuery}"/>
<c:set var="daXoaQuery" value="${requestScope.daXoaQuery}"/>
<c:set var="daThanhToanQuery" value="${requestScope.daThanhToanQuery}"/>
<c:set var="nResult" value="${nResult}"/>
<c:set var="nCartsInPage" value="${nCartsInPage}"/>
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
                        <h2>Danh sách hóa đơn</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
                        <div class="row-fluid">
								<div class="span6 divSearch">
									<div class="dataTables_filter">
									<form id="formSearchCart" name="formSearchCart" class="formSearch" method="get" action="search">
                                    <fieldset>
                                    	<input type="hidden" id="inputSearchPage" name="page" value="1"/>
                                    	<input type="text" id="inputSearchNgayLap" name="ngayLap" class="datepicker clearable inputSearchMedium" placeholder="Ngày lập..." value="<fmt:formatDate pattern='yyyy-MM-dd' value='${hoaDonQuery.ngayLap}' />"  />
                                    	<input type="text" id="inputSearchMaHoaDon" name="maHoaDon" class="inputSearchSmall" placeholder="ID..." value="${hoaDonQuery.maHoaDon==-1?'':hoaDonQuery.maHoaDon}"  />
                                        <input type="text" id="inputSearchTaiKhoan" name="taiKhoan" class="inputSearchMedium" placeholder="Tài khoản..." value="${hoaDonQuery.taiKhoan ==null?'':hoaDonQuery.taiKhoan.maTaiKhoan}"  />
                                        <select id="inputSearchTrangThai" name="trangThaiDonHang" class="selectSearchMedium">
											<c:if test="${hoaDonQuery.trangThaiDonHang == null}">
												<option selected="selected" value="-1">---Chọn trạng thái---</option>
	                                             <c:forEach var="trangThai" items="${dsTrangThai}" varStatus="status">                                           
	                                                     <option value="${trangThai.maTrangThaiDonHang}">${trangThai.tenTrangThaiDonHang}</option>
	                                             </c:forEach>
											</c:if>
											<c:if test="${hoaDonQuery.trangThaiDonHang != null}">
												<option value="-1">---Chọn trạng thái---</option>
	                                             <c:forEach var="trangThai" items="${dsTrangThai}" varStatus="status">                                           	                                                     	                                             
	                                             		<c:choose>
                                                            <c:when test="${hoaDonQuery.trangThaiDonHang.maTrangThaiDonHang == trangThai.maTrangThaiDonHang}">
                                                                 <option selected="selected" value="${trangThai.maTrangThaiDonHang}">${trangThai.tenTrangThaiDonHang}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <option value="${trangThai.maTrangThaiDonHang}">${trangThai.tenTrangThaiDonHang}</option>
                                                            </c:otherwise>
                                                        </c:choose>
	                                             </c:forEach>

											</c:if>
                                         </select>
                                         <select id="inputSearchDaThanhToan" name="daThanhToan" class="selectSearchMedium">
                                         	<option ${daThanhToanQuery == '' ? 'selected="selected"':''} value="">---Chọn đã thanh toán---</option>
                                         	<option ${daThanhToanQuery == 'true' ? 'selected="selected"':''} value="true">Rồi</option>
                                         	<option ${daThanhToanQuery == 'false' ? 'selected="selected"':''} value="false">Chưa</option>
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
                                        <th>Ngày lập</th>
                                        <th>Tài khoản</th>
                                        <th>Tổng tiền</th>
                                        <th>Trạng thái</th>
                                        <th>Đã thanh toán</th>
                                        <th>Đã xóa</th>
                                        <th>Lựa chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:if test="${dsHoaDon.size() != 0 }" >
                                    <c:forEach var="hoaDon" items="${dsHoaDon}" varStatus="status">
                                        <tr class="odd gradeX">
                                            <td><input type="checkbox" name="options[]" id="chkbox_${hoaDon.maHoaDon}" value="${hoaDon.maHoaDon}" /></td>
                                            <td><a href="detail?id=${hoaDon.maHoaDon}"><c:out value="${hoaDon.maHoaDon}"/></a></td>
                                            <c:if test="${status.index < 5}">
        	                                    <td><c:out value="${hoaDon.ngayLap}"/> <img src="${pageContext.request.contextPath}/shared-res/images/newest.png" alt="Newest" /></td>
                                            </c:if>
                                            <c:if test="${status.index >= 5}">
        	                                    <td><c:out value="${hoaDon.ngayLap}"/></td>
                                            </c:if>
                                            <td><a href="/BabyShop/account/detail?id=${hoaDon.taiKhoan.maTaiKhoan}"><c:out value="${hoaDon.taiKhoan.maTaiKhoan}"/></a></td>
                                            <td><fmt:formatNumber value="${hoaDon.tongTien}" pattern="#,### VNĐ" /></td>
                                            <td><c:out value="${hoaDon.trangThaiDonHang.tenTrangThaiDonHang}"/></td>
                                            <td>
                                                <c:if test="${hoaDon.daThanhToan == true}">
                                                    <span style="color: red" id="isDaThanhToan_${hoaDon.maHoaDon}"><c:out value="Rồi"/></span>
                                                </c:if>
                                                <c:if test="${hoaDon.daThanhToan == false}">
                                                    <span style="color: blue" id="isDaThanhToan_${hoaDon.maHoaDon}"><c:out value="Chưa"/></span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${hoaDon.daXoa == true}">
                                                    <span style="color: red" id="isDaXoa_${hoaDon.maHoaDon}"><c:out value="Rồi"/></span>
                                                </c:if>
                                                <c:if test="${hoaDon.daXoa == false}">
                                                    <span style="color: blue" id="isDaXoa_${hoaDon.maHoaDon}"><c:out value="Chưa"/></span>
                                                </c:if>
                                            </td>
                                            <td>                                           
                                                <a href="javascript:confirmDelete(${hoaDon.maHoaDon})" title="Đánh dấu xóa"><img src="${pageContext.request.contextPath}/shared-res/images/cross.png" alt="Delete" /></a>
                                                <a href="javascript:confirmReshow(${hoaDon.maHoaDon})" title="Gỡ xóa"><img src="${pageContext.request.contextPath}/shared-res/images/redo.png" alt="Redo" /></a> 
                                            </td>
                                        </tr>
                                    </c:forEach>
                                   </c:if>
                                   <c:if test="${dsHoaDon.size() == 0 }" >
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
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->