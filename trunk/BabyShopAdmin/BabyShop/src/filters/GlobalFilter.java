package filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class GlobalFilter
 */
@WebFilter("/GlobalFilter")
public class GlobalFilter implements Filter
{

    /**
     * Default constructor.
     */
    public GlobalFilter()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        // place your code here

        InputStream configStream = request
                .getServletContext()
                .getResourceAsStream("/WEB-INF/config/global-config.properties");
        Properties _prop = new Properties();
        _prop.load(configStream);
        
        //Đường dẫn tới thư mục chứa hình ảnh đồ chơi và avatar
        String toyImagesFolder = _prop.getProperty("ToyImagesFolder");
        String avatarImagesFolder = _prop.getProperty("AvatarImagesFolder");
        request.setAttribute("ToyImagesFolder", toyImagesFolder);
        request.setAttribute("AvatarImagesFolder", avatarImagesFolder);
                  
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException
    {
        // TODO Auto-generated method stub
    }

}