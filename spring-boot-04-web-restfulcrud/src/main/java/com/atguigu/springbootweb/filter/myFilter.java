package com.atguigu.springbootweb.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by JHW on 2021/8/27.
 */
public class myFilter implements Filter {
    // 初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    // 过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("myFilter proecss...");
        filterChain.doFilter(request,response);
    }

    // 销毁
    @Override
    public void destroy() {

    }
}
