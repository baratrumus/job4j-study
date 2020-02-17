package servlets.userapp;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(req, resp);
        } else if ((!request.getRequestURI().contains("items/echo"))
                && (!request.getRequestURI().contains("items/upload"))
                && (!request.getRequestURI().contains("items/download"))
                //&& (!request.getRequestURI().contains("html"))
                && (!request.getRequestURI().contains("items/images"))
                && (!request.getRequestURI().contains("items/user"))) {
            HttpSession session = request.getSession();
           // synchronized (session) {
            if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) resp).sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
            }
            chain.doFilter(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
