/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dao.BinhLuanDAO;
import dao.LoaiDoChoiDAO;
import dao.NhaSanXuatDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import pojos.BinhLuan;
import pojos.LoaiDoChoi;
import pojos.NhaSanXuat;

/**
 *
 * @author TrongKhoa
 */
public class PageContentFilter implements Filter
{
    
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public PageContentFilter()
    {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("PageContentFilter:DoBeforeProcessing");
        }
        
        LoaiDoChoiDAO loaiDoChoiDAO = new LoaiDoChoiDAO();
        NhaSanXuatDAO nhaSanXuatDAO = new NhaSanXuatDAO();
        BinhLuanDAO binhLuanDAO = new BinhLuanDAO();
        
        List<LoaiDoChoi> dsLoaiDoChoi = loaiDoChoiDAO.getList(false);
        request.setAttribute("dsLoaiDoChoi", dsLoaiDoChoi);
        
        List<NhaSanXuat> dsNhaSanXuat = nhaSanXuatDAO.getList(false);
        request.setAttribute("dsNhaSanXuat", dsNhaSanXuat);
        
        List<BinhLuan> dsBinhLuanMoiNhat = binhLuanDAO.getTopNPOJOsOrderedByField(3, "ngayBinhLuan", true);
        request.setAttribute("dsBinhLuanMoiNhat", dsBinhLuanMoiNhat);
        
        ArrayList<String> dsThang = new ArrayList<String>();
		dsThang.add("Tháng một");
		dsThang.add("Tháng hai");
		dsThang.add("Tháng ba");
		dsThang.add("Tháng bốn");
		dsThang.add("Tháng năm");
		dsThang.add("Tháng sáu");
		dsThang.add("Tháng bảy");
		dsThang.add("Tháng tám");
		dsThang.add("Tháng chín");
		dsThang.add("Tháng mười");
		dsThang.add("Tháng mười một");
		dsThang.add("Tháng mười hai");

		request.setAttribute("monthList", dsThang);
		
		ArrayList<String> dsTinh = new ArrayList<String>();
		dsTinh.add("An Giang");
		dsTinh.add("Bà Rịa Vũng Tàu");
		dsTinh.add("Bắc Cạn");
		dsTinh.add("Bắc Giang");
		dsTinh.add("Bạc Liêu");
		dsTinh.add("Bắc Ninh");
		dsTinh.add("Bến Tre");
		dsTinh.add("Bình Định");
		dsTinh.add("Bình Dương");
		dsTinh.add("Bình Phước");
		dsTinh.add("Bình Thuận");
		dsTinh.add("Cà Mau");
		dsTinh.add("Cần Thơ");
		dsTinh.add("Cao Bằng");
		dsTinh.add("Đà Nẵng");
		dsTinh.add("Đăk Lăk");
		dsTinh.add("Điện Biên");
		dsTinh.add("Đồng Nai");
		dsTinh.add("Đồng Tháp");
		dsTinh.add("Gia Lai");
		dsTinh.add("Hà Đông");
		dsTinh.add("Hà Giang");
		dsTinh.add("Hạ Long");
		dsTinh.add("Hà Nam");
		dsTinh.add("Hà Nội");
		dsTinh.add("Hà Tây");
		dsTinh.add("Hà Tĩnh");
		dsTinh.add("Hải Dương");
		dsTinh.add("Hải Phòng");
		dsTinh.add("Hồ Chí Minh");
		dsTinh.add("Hòa Bình");
		dsTinh.add("Hưng Yên");
		dsTinh.add("Khánh Hòa");
		dsTinh.add("Kiên Giang");
		dsTinh.add("KonTum");
		dsTinh.add("Lai Châu");
		dsTinh.add("Lâm Đồng");
		dsTinh.add("Lạng Sơn");
		dsTinh.add("Lào Cai");
		dsTinh.add("Long An");
		dsTinh.add("Nam Định");
		dsTinh.add("Nghệ An");
		dsTinh.add("Ninh Bình");
		dsTinh.add("Ninh Thuận");
		dsTinh.add("Phú Thọ");
		dsTinh.add("Phú Yên");
		dsTinh.add("Quảng Bình");
		dsTinh.add("Quảng Nam");
		dsTinh.add("Quảng Ngãi");
		dsTinh.add("Quảng Ninh");
		dsTinh.add("Quảng Trị");
		dsTinh.add("Sóc Trăng");
		dsTinh.add("Tây Ninh");
		dsTinh.add("Thái Bình");
		dsTinh.add("Thái Nguyên");
		dsTinh.add("Thanh Hóa");
		dsTinh.add("Thừa Thiên Huế");
		dsTinh.add("Tiền Giang");
		dsTinh.add("Trà Vinh");
		dsTinh.add("Tuyên Quang");
		dsTinh.add("Vĩnh Long");
		dsTinh.add("Vĩnh Phúc");
		dsTinh.add("Yên Bái");
		dsTinh.add("Nơi Khác");

		request.setAttribute("provinceList", dsTinh);
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        if (debug)
        {
            log("PageContentFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.

        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */

        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException
    {
        
        if (debug)
        {
            log("PageContentFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try
        {
            chain.doFilter(request, response);
        }
        catch (Throwable t)
        {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null)
        {
            if (problem instanceof ServletException)
            {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException)
            {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy()
    {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig)
    {        
        this.filterConfig = filterConfig;
        if (filterConfig != null)
        {
            if (debug)
            {                
                log("PageContentFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("PageContentFilter()");
        }
        StringBuffer sb = new StringBuffer("PageContentFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response)
    {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals(""))
        {
            try
            {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
        else
        {
            try
            {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
    }
    
    public static String getStackTrace(Throwable t)
    {
        String stackTrace = null;
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
        catch (Exception ex)
        {
        }
        return stackTrace;
    }
    
    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);        
    }
}
