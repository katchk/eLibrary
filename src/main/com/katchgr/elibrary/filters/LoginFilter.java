package com.katchgr.elibrary.filters;

import com.katchgr.elibrary.beans.LoginBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginBean loginBean = (LoginBean)((HttpServletRequest)servletRequest).getSession().getAttribute("loginBean");

        if(loginBean == null || !loginBean.isLoggedIn()){
            String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
            ((HttpServletResponse)servletResponse).sendRedirect(contextPath + "/login.xhtml");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
