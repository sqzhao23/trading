package filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "BackServletFilter")
public class BackServletFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String contextPath=request.getServletContext().getContextPath();
        String uri = request.getRequestURI();
        uri =StringUtils.remove(uri, contextPath);
        if(uri.startsWith("/admin_")){
            String servletPath = StringUtils.substringBetween(uri,"_", "_") + "Servlet";
            String method = StringUtils.substringAfterLast(uri,"_" );
            request.setAttribute("method", method);
            req.getRequestDispatcher("/" + servletPath).forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
