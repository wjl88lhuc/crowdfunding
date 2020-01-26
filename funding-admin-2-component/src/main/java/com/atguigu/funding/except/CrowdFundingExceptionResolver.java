package com.atguigu.funding.except;

import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.util.CrowdFundingConstant;
import com.atguigu.funding.util.CrowdFundingUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowdFundingExceptionResolver {
    /**
     * value = Exception.class 表示捕获什么样类型（或者说什么级别）的异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView catchException(Exception excpt, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //对当前的请求检查，判断是否是异步请求
        boolean checkAsyncRequest = CrowdFundingUtils.checkAsyncRequest(request);
        if (checkAsyncRequest){//如果是异步请求

            String exceptionClassName = excpt.getClass().getName();
            String exceptionMessage = CrowdFundingConstant.EXCEPTION_TO_MESSAGEMAP.get(exceptionClassName);

            if (exceptionMessage == null){
                exceptionMessage = "系统位置错误";
            }

            ResultEntity<String> resultEntity = ResultEntity.fail(ResultEntity.NO_DATA, exceptionMessage);
            Gson gson = new Gson();
            String resultJson = gson.toJson(resultEntity);

            //将 resultJson 作为响应数据返回给服务器
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(resultJson);

            return null;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",excpt);
        modelAndView.setViewName("system-error");
        return modelAndView;
    }
}
