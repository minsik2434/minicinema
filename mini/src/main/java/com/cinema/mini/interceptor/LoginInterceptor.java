package com.cinema.mini.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(SessionConst.SESSION_NAME)==null){
            response.sendRedirect("/member/login?RedirectURL="+requestURI);
            return false;
        }
        return true;
    }
}
