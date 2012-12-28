<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="listQuangCao" value="${requestScope.listQuangCao}"/>

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
	                                        <th>STT</th>
	                                        <th>Vị trí</th>
	                                        <th style="width:300px">Hình ảnh</th>
	                                        <th>Ngày hết hạn</th>
	                                        <th>Hiển thị</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                 <c:if test="${listQuangCao.size() != 0 }" >
	                                    <c:forEach var="quangCao" items="${listQuangCao}" varStatus="status">
	                                        <tr class="odd gradeX">
	                                            <td><c:out value="${status.index + 1}"/></td>
	                                            <td><a href="detail?position=${quangCao.getPosition()}"><c:out value="${quangCao.getPosition()}"/></a></td>
	                                            <td style="width:300px">
	                                            	<a href="${quangCao.getLink()}" rel="prettyPhoto" class="thumbnail" title="Hình ảnh gốc"><img id="hinh_dai_dien" alt="" style="width:200px; height:150px" src="${quangCao.getLink()}" /></a>
	                                            </td>
	                                            <td><fmt:formatDate pattern='yyyy-MM-dd' value='${quangCao.getExpiredDate()}' /></td>
	                                            <td>
	                                                <c:if test="${quangCao.getIsShow() == true}">
	                                                    <span style="color: red" id="isShow_${status.index + 1}"><c:out value="Có"/></span>
	                                                </c:if>
	                                                <c:if test="${quangCao.getIsShow() == false}">
	                                                    <span style="color: blue" id="isShow_${status.index + 1}"><c:out value="Không"/></span>
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