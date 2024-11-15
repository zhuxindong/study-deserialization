package com.zxd.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zxd.pojo.CommonResult;
import com.alibaba.fastjson.JSON;

public class CustomLoginFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 对浏览器的预检请求放行
        if (request instanceof HttpServletRequest
            && ((HttpServletRequest) request).getMethod().equalsIgnoreCase("OPTIONS")){
            return true;
        }

        // 刷新会话过期时间
        SecurityUtils.getSubject().getSession().setTimeout(86400000);

        return super.isAccessAllowed(request,response,mappedValue);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        setResponse((HttpServletRequest) request,httpServletResponse);
        Subject subject = SecurityUtils.getSubject();
        if (null == subject || null == subject.getPrincipal()){
            CommonResult result = new CommonResult();
            result.setCode("-1");
            result.setMsg("用户未登录或登录超时");
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        }else {
            subject.logout();
            CommonResult result = new CommonResult();
            result.setCode("-1");
            result.setMsg("用户未登录或登录超时");
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        }
        return false;
    }



    private boolean isAjaxRequest(ServletRequest request){
        String header = ((HttpServletRequest)request).getHeader("X-Request-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)){
            return true;
        }
        return false;
    }




    public void setResponse(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setContentType("application/json;charset=UTF-8");
    }





}

