<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="listQuangCao" value="${requestScope.listQuangCao}"/>
<c:set var="adImagesFolder" value="${requestScope.AdImagesFolder}"/>

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
                        <h2>Danh sách quảng cáo</h2>
                    </header>
                    <section class="tab-content">
                        <div class="tab-pane dataTables_wrapper" id="dynamic">
	                        <table class="datatable table table-striped table-bordered" id="example">
	                                <thead>
	                                    <tr>
	                                        <th>ID</th>
	                                        <th>Tên</th>
	                                        <th style="width:300px">Hình ảnh</th>
	                                        <th>Vị trí</th>
	                                        <th>Ngày bắt đầu</th>
	                                        <th>Ngày kết thúc</th>
	                                        <th>Lượt click</th>
	                                        <th>Hiệu lực</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                 <c:if test="${listQuangCao.size() != 0 }" >
	                                    <c:forEach var="quangCao" items="${listQuangCao}" varStatus="status">
	                                        <tr class="odd gradeX">
	                                            <td><c:out value="${quangCao.maQuangCao}"/></td>
	                                            <td><a href="detail?id=${quangCao.maQuangCao}"><c:out value="${quangCao.tenQuangCao}"/></a></td>
	                                            <td style="width:300px">
	                                            	<a href="${pageContext.request.contextPath}${adImagesFolder}${quangCao.hinhAnh}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh gốc"><img id="hinh_dai_dien" alt="" style="width:200px; height:150px" src="${pageContext.request.contextPath}${adImagesFolder}${quangCao.hinhAnh}" /></a>
	                                            </td>
	                                            <td><c:out value="${quangCao.viTri}"/></td>	                                            
	                                            <td><fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.batDau}' /></td>
	                                            <td><fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.ketThuc}' /></td>
	                                            <td><c:out value="${quangCao.soLuongClick}"/></td>	 
	                                            <td>
	                                                <c:if test="${quangCao.coHieuLuc == true}">
	                                                    <span style="color: red" id="isHieuLuc_${quangCao.maQuangCao}"><c:out value="Có"/></span>
	                                                </c:if>
	                                                <c:if test="${quangCao.coHieuLuc == false}">
	                                                    <span style="color: blue" id="isHieuLuc_${quangCao.maQuangCao}"><c:out value="Không"/></span>
	                                                </c:if>
	                                            </td>
	                                        </tr>
	                                    </c:forEach>
	                                   </c:if>
	                                   <c:if test="${listQuangCao.size() == 0 }" >
		                                   	<tr class="odd gradeX">
		                                   		<td colspan="6" style="text-align: center">Không tìm thấy dữ liệu</td>
		                                   	</tr>
	                                   </c:if>
	                                </tbody>
	                            </table>
                        </div>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->