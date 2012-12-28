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
                            <tr>
                            	<td>
                            		<select id="selectBaoCao" name="selectBaoCao" style="margin: 0 auto">
										<option selected="selected" value="-1">---Chọn báo cáo---</option>
                                            <c:forEach var="report" items="${listReport}" varStatus="status">                                           
                                                    <option value="${report.getName()}">${report.getDisplayName()}</option>
                                            </c:forEach>
                            		</select>
                            	</td>
                            	<td>
                            		<select id="selectKieuFile" name="selectKieuFile" style="margin: 0 auto">
										<option selected="selected" value="-1">---Chọn loại file---</option>                                         
                                            <option value="xls">EXCEL</option>
                                            <option value="pdf">PDF</option>
                                            <option value="html">HTML</option>
                                            <option value="csv">CSV</option>
                                            <option value="rtf">RTF</option>
                            		</select>
                            	</td>
                            	<td>
                            	<a class="button" href="javascript:download()">Download</a>
                            	</td>
                            </tr>
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