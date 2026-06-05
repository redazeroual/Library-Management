package com.bibliotheque.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// @WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getServletPath();


        boolean isPublicPage = path.equals("/login.html") || path.equals("/signup.html");
        boolean isPublicApi = path.equals("/api/login") || path.equals("/api/register");

        boolean isResource = path.contains("/css/") ||
                path.contains("/js/") ||
                path.contains("/assets/") ||
                path.endsWith(".png") ||
                path.endsWith(".jpg") ||
                path.endsWith(".svg") ||
                path.endsWith(".ico");


        if (isPublicPage || isPublicApi || isResource) {
            // On laisse passer vers la page demandée
            chain.doFilter(request, response);
            return;
        }


        boolean isLoggedIn = (session != null && session.getAttribute("currentUser") != null);

        if (isLoggedIn) {

            chain.doFilter(request, response);
        } else {

            res.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}