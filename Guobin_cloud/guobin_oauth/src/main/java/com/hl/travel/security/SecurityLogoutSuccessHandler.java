package com.hl.travel.security;


import com.hl.travel.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component("securityLogoutSuccessHandler")
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request , HttpServletResponse response , Authentication authentication) throws IOException,
            ServletException {
        // 清除登录的session
        this.removeSesion(request);

        response.sendRedirect(MessageConstant.LOGIN_SUCCESS_URL+"/login.html");

    }

    /**
     * 移除登录用户的session
     * @param request
     */
    private void removeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 手动让系统中的session失效 。
        session.invalidate();
    }

}

