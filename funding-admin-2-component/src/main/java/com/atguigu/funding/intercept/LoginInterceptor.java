package com.atguigu.funding.intercept;

import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.util.CrowdFundingConstant;
import com.atguigu.funding.util.CrowdFundingUtils;
import com.google.gson.Gson;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    public LoginInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute(CrowdFundingConstant.ATTR_NAME_LOGIN_ADMIN);
        if (admin == null){
            //进一步判断当前的请求是否是异步请求
            boolean checkAsyncRequest = CrowdFundingUtils.checkAsyncRequest(request);
            if (checkAsyncRequest){ // 如果是异步请求
                ResultEntity<String> fail = ResultEntity.fail(ResultEntity.NO_DATA, CrowdFundingConstant.MESSAGE_ACCESS_DENIED);
                Gson gson = new Gson();
                String failJson = gson.toJson(fail);
                //设置响应消息头
                response.setContentType("application/json;charset=UTF-8");
                //将json字符串设置为响应内容
                response.getWriter().write(failJson);
                //return false表示不放行，必须先再次登陆才能操作
                return false;  //兼容异步请求。当异步请求的时候，如果session无效就会走一步代码
            }
            request.setAttribute(CrowdFundingConstant.ATTR_NAME_MESSAGE,CrowdFundingConstant.MESSAGE_ACCESS_DENIED);
            request.getRequestDispatcher("/WEB-INF/admin-login.jsp").forward(request,response);  //只能响应同步请求，异步请求就不能响应了，所以得必须兼容异步请求
            return false; //如果没有登陆，则返回登陆页面
        }
        //如果已经登陆了，那么就放行
        return true;
    }

}
