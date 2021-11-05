package com.example.demoJavaWebAll11.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("EncodingFilter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("EncodingFilter.doFilter +++++ start");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("EncodingFilter.doFilter +++++ end");
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("EncodingFilter.destroy");
    }
}
