//package com.hl.travel.security;
//
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Date;
//
//@Component("securityLogoutSuccessHandler")
//@Slf4j
//public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request , HttpServletResponse response , Authentication authentication) throws IOException,
//            ServletException {
//
//        // 清除登录的session
//        this.removeSesion(request);
//        // 记录登出日志
//        if (null != authentication) {
//            this.saveLog(request, authentication);
//        }
////        String originHeader = request.getHeader("Origin");
////        ResponseUtils.addResponseHeader(response, applicationConfig.getOrigins(), originHeader);
////        RestResult result = new RestResult(ResultCode.SUCCESS.getCode(),"退出成功");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter writer = response.getWriter();
////        writer.print(result.toJson());
//        writer.flush();
//    }
//
//    /**
//     * 移除登录用户的session
//     * @param request
//     */
//    private void removeSesion(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        // 手动让系统中的session失效 。
//        session.invalidate();
//    }
//
//    /**
//     * 记录登出日志
//     * @param request
//     */
//    private void saveLog(HttpServletRequest request, Authentication authentication) {
//
//    }
//}
//
