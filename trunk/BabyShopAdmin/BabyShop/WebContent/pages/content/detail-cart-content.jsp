<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="hoaDon" value="${requestScope.hoaDon}"/>
<c:set var="dsLoaiTrangThaiDonHang" value="${requestScope.dsLoaiTrangThaiDonHang}"/>
<c:set var="dsChiTietHoaDon" value="${requestScope.dsChiTietHoaDon}"/>

<!-- Main page container -->
    <!-- Right (content) side -->
    <div class="content-block">
        <!-- InstanceBeginEditable name="Content" -->
        <div class="row">
            <!-- Data block -->
            <article class="span12 data-block">
                <div class="data-container">
                    <header>
                        <h2>Chi tiết hóa đơn - Click để chỉnh sửa <input type="checkbox" class="request-edit" value="done" /></h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane active" id="basic">
                            <div class="row-fluid">
                                <div class="span12">
                                     <c:if test="${kq != null && kq == true}">
                                            <div class="alert alert-success">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Cập nhật hóa đơn thành công !</strong>
                                            </div>
                                    </c:if>
                                    <c:if test="${kq != null && kq == false}">
                                            <div class="alert alert-error">
                                                <button class="close" data-dismiss="alert">×</button>
                                                <strong>Không cập nhật được hóa đơn !</strong>
                                            </div>
                                    </c:if>
                                    <h2>Danh sách sản phẩm</h2>
                                    <table class="datatable table table-striped table-bordered" id="example">
		                                <thead>
		                                    <tr>
		                                        <th>ID</th>
		                                        <th>Đồ chơi</th>
		                                        <th>Số lượng</th>
		                                        <th>Đơn giá</th>
		                                        <th>Ngày cập nhật</th>
		                                        <th>Đã xóa</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<c:if test="${dsChiTietHoaDon.size() != 0 }">
			                                    <c:forEach var="chiTietHoaDon" items="${dsChiTietHoaDon}" varStatus="status">
			                                        <tr class="odd gradeX">
			                                            <td><c:out value="${chiTietHoaDon.maChiTietHoaDon}"/></td>
			                                            <td><a href="/BabyShop/toy/detail?id=${chiTietHoaDon.doChoi.maDoChoi}"><c:out value="${chiTietHoaDon.doChoi.tenDoChoi}"/></a></td>
			                                            <td><c:out value="${chiTietHoaDon.soLuong}"/></td>
			                                            <td><fmt:formatNumber value="${chiTietHoaDon.donGia}" pattern="#,### VNĐ" /></td>
			                                            <td><c:out value="${chiTietHoaDon.ngayCapNhat}"/></td>
			                                            <td>
			                                                <c:if test="${chiTietHoaDon.daXoa == true}">
			                                                    <span style="color: red" id="isDaXoa_${chiTietHoaDon.maChiTietHoaDon}"><c:out value="Rồi"/></span>
			                                                </c:if>
			                                                <c:if test="${chiTietHoaDon.daXoa == false}">
			                                                    <span style="color: blue" id="isDaXoa_${chiTietHoaDon.maChiTietHoaDon}"><c:out value="Chưa"/></span>
			                                                </c:if>
			                                            </td>
			                                        </tr>
			                                    </c:forEach>
		                                    </c:if>
		                                    <c:if test="${dsChiTietHoaDon.size() == 0 }">
		                                    	<tr class="odd gradeX">
			                                   		<td colspan="6" style="text-align: center">Không tìm thấy dữ liệu</td>
			                                   	</tr>
		                                    </c:if>
		                                </tbody>
		                            </table>
                                    <form:form modelAttribute="hoaDon" action="update" name="formUpdateCart" id="formUpdateCart" method="post">    
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Mã hóa đơn</label>
                                            <div class="controls">
                                                <label class="control-label" for="input">${hoaDon.maHoaDon}</label>
                                                <form:hidden path="maHoaDon" id="inputMaHoaDon" name="inputMaHoaDon" value="${hoaDon.maHoaDon}" disabled="disabled"/>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tài khoản</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><a href="/BabyShop/account/detail?id=${hoaDon.taiKhoan.getMaTaiKhoan()}">${hoaDon.taiKhoan.getMaTaiKhoan()}</a></label>                                  	                                            
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="select">Trạng thái đơn hàng</label>
                                            <div class="controls">
                                                <form:select id="selectTrangThaiDonHang" name="selectTrangThaiDonHang" disabled="disabled" path="trangThaiDonHang">
												   <c:forEach var="trangThaiDonHang" items="${dsTrangThaiDonHang}" varStatus="status">
												   		<c:choose>
                                                            <c:when test="${trangThaiDonHang.maTrangThaiDonHang == hoaDon.trangThaiDonHang.maTrangThaiDonHang}">
                                                                 <form:option selected="selected" value="${trangThaiDonHang.maTrangThaiDonHang}" label="${trangThaiDonHang.tenTrangThaiDonHang}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                 <form:option value="${trangThaiDonHang.maTrangThaiDonHang}" label="${trangThaiDonHang.tenTrangThaiDonHang}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
												</form:select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày lập</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><fmt:formatDate pattern='yyyy-MM-dd' value='${hoaDon.ngayLap}' /></label>                      
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tên khách hàng</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input">${hoaDon.tenKhachHang}</label>                      
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Địa chỉ giao hàng</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input">${hoaDon.diaChiGiaoHang}</label>                      
                                            </div>
                                        </div>     
                                        <div class="control-group">
                                            <label class="control-label" for="input">Tổng tiền</label>
                                            <div class="controls">
                                            	<label class="control-label" for="input"><fmt:formatNumber value="${hoaDon.tongTien}" pattern="#,### VNĐ" /></label>                      
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Ngày cập nhật</label>
                                            <div class="controls">
                                            	<input type="text" id="inputNgayCapNhat" name="ngayCapNhat" class="datepicker input-small" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${hoaDon.ngayCapNhat}' />" disabled="disabled"/>                         
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã thanh toán</label>
                                            <div class="controls">
                                                <c:if test="${hoaDon.daThanhToan == true}">
                                                	<form:checkbox path="daThanhToan" style="margin-top: 3px" value="true" name="chkDaThanhToan" id="chkDaThanhToan" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${hoaDon.daThanhToan == false}">
                                                	<form:checkbox path="daThanhToan" style="margin-top: 3px" value="false" name="chkDaThanhToan" id="chkDaThanhToan" disabled="disabled"/> 
                                                </c:if>
                                                
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="input">Đã xóa</label>
                                            <div class="controls">
                                                <c:if test="${hoaDon.daXoa == true}">
                                                	<form:checkbox path="daXoa" style="margin-top: 3px" value="true" name="chkDaXoa" id="chkDaXoa" checked="checked" disabled="disabled"/> 
                                                </c:if>
                                                <c:if test="${hoaDon.daXoa == false}">
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