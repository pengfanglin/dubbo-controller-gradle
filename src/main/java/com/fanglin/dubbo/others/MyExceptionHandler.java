package com.fanglin.dubbo.others;

import com.fanglin.common.core.others.DefaultExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author 彭方林
 * @version 1.0
 * @date 2019/4/3 14:16
 **/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler extends DefaultExceptionHandler {
    public MyExceptionHandler() {
        super();
    }
}
