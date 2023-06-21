package com.hl.travel.interceptor;

import com.hl.travel.constant.MessageConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class TravelInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestPath = request.getRequestURI();
        // 如果请求路径以 ".do" 结尾，则放行请求
        if (requestPath.endsWith(".do")) {
            //放行
            return true;
        }

        // 如果请求方法为 OPTIONS，则放行请求
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        //拦截重定向到无权限页面

        response.sendRedirect(MessageConstant.LOGIN_SUCCESS_URL+"/pages/error/404.html");

        return false;
    }
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求处理完成后执行的代码，即在视图渲染完成后执行
    }
    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // 在控制器方法执行之后，视图渲染之前执行的代码

    }
}
