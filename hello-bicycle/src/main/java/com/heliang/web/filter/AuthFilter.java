package com.heliang.web.filter;

import com.heliang.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private String [] excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excluded = filterConfig.getInitParameter("excluded");
        if (excluded != null && !excluded.equals("")){
            excludedPaths = excluded.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!isExcludeRequest(request)) {
            String requestURI = request.getRequestURI();
            String[] uris = requestURI.substring(1).split("/");
            if (!uris[uris.length - 1].contains("login") && !uris[uris.length - 1].contains("register")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("online");
                if (user == null) {
                    response.sendRedirect("login.jsp");
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isExcludeRequest(HttpServletRequest request) {
        if(null != excludedPaths && excludedPaths.length > 0) {
            String uri = request.getRequestURI();
            for (String excludedUri : excludedPaths) {
                if (excludedUri.startsWith("*.")) {
                    if(uri.endsWith(excludedUri.substring(1))){
                        return true;
                    }
                } else if (excludedUri.endsWith("/*")) {
                    if(!excludedUri.startsWith("/")) {
                        excludedUri = "/" + excludedUri;
                    }
                    String prefix = request.getContextPath() + excludedUri.substring(0, excludedUri.length() - 1);
                    if(uri.startsWith(prefix)) {
                        return true;
                    }
                }
                // 全路径匹配
                else {
                    if(!excludedUri.startsWith("/")) {
                        excludedUri = "/" + excludedUri;
                    }
                    String targetUri = request.getContextPath() + excludedUri;
                    if(uri.equals(targetUri)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
