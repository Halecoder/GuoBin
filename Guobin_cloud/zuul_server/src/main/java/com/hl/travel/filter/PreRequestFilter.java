package com.hl.travel.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 描述：     记录请求时间
 */
@Component
public class PreRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //过滤器的类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();

        // 去除后缀.do逻辑
        if (requestURI.endsWith(".do")) {
            String newPath = requestURI.substring(0, requestURI.lastIndexOf(".do"));
            ctx.setRequest(new HttpServletRequestWrapper(request) {
                @Override
                public String getRequestURI() {
                    return newPath;
                }
            });
        }else{
            //直接拒绝
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            ctx.setResponseBody("非法请求");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }

        // 请求头默认会被zuul过滤掉，需要手动设置
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                ctx.addZuulRequestHeader(name, values);
            }
        }

        return null;
    }
}