<%-- 
    Document   : report-lib
    Created on : Nov 21, 2012, 3:31:43 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
                            function download(reportName)
                            {
                                var reportParamValue = ($("#selectThamSo_" + reportName).val());
                                var reportType = ($("#selectKieuFile_" + reportName).val());
                                if (reportName != "" && reportParamValue != -1 && reportType != -1)
                                {
                                    window.location.href = reportName + "?type=" + reportType + "&param=" + reportParamValue;
                                }
                                else
                                {
                                    alert('Xin chọn báo cáo cùng loại file và tham số mong muốn !');
                                }
                            }
</script>
