<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listReport" value="${requestScope.listReport}"/>

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
                        <h2>Danh sách các báo cáo</h2>
                    </header>
                    <section class="tab-content">
                        <table class="report">
                        	<c:forEach var="report" items="${listReport}" varStatus="status">
                        		<tr>
                        			<td>${report.getDisplayName()}</td>
                        			<td>
                        				<select id="selectThamSo_${report.getName()}" name="selectThamSo_${report.getName()}" style="margin: 0 auto">
										<option selected="selected" value="-1">---Chọn tham số ${report.getParamName()}---</option>                                         
                                             <c:forEach var="paramValue" items="${report.getParamValues()}" varStatus="status">                                           
                                                    <option value="${paramValue}">${paramValue}</option>
                                            </c:forEach>
                            			</select>
                        			</td>
                        			<td>
	                        			<select id="selectKieuFile_${report.getName()}" name="selectKieuFile_${report.getName()}" style="margin: 0 auto">
											<option selected="selected" value="-1">---Chọn loại file---</option>                                         
	                                            <option value="xls">EXCEL</option>
	                                            <option value="pdf">PDF</option>
	                                            <option value="html">HTML</option>
	                                            <option value="csv">CSV</option>
	                                            <option value="rtf">RTF</option>
	                            		</select>
                            		</td>
                            		<td>
                            			<a class="button" href="javascript:download('${report.getName()}')">Download</a>
                            		</td>
                        		</tr>
                        	</c:forEach>     
                        </table>
                    </section>
                </div>
            </article>

            <!-- Data block --> 
        </div>
        <!-- Grid row -->
        <!-- InstanceEndEditable -->
    </div>
<!-- Main page container -->