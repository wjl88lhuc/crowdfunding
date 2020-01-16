package com.atguigu.funding.except;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CrowdFundingExceptionResolver {
    /**
     * value = Exception.class 表示捕获什么样类型（或者说什么级别）的异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView catchException(Exception excpt){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",excpt);
        modelAndView.setViewName("system-error");
        return modelAndView;
    }
}
