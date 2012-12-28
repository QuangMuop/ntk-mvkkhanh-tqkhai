<%-- 
    Document   : report-lib
    Created on : Nov 21, 2012, 3:31:43 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
                            function download()
                            {
                                var reportName = ($("#selectBaoCao").val());
                                var reportType = ($("#selectKieuFile").val());
                                if (reportName != -1 && reportType != -1)
                                {
                                    window.location.href = reportName + "?type=" + reportType;
                                }
                                else
                                {
                                    alert('Xin chọn báo cáo hoặc loại file mong muốn');
                                }
                            }
</script>
