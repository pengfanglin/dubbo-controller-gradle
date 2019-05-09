package com.fanglin.dubbo.others;

import com.alibaba.dubbo.rpc.RpcException;
import com.fanglin.common.core.others.Ajax;
import com.fanglin.common.core.others.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * 全局异常捕获
 *
 * @author 彭方林
 * @version 1.0
 * @date 2019/4/3 14:16
 **/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 必传参数未传异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Ajax handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return Ajax.error("【" + e.getParameterName() + "】必传，参数类型:【" + e.getParameterType() + "】");
    }

    /**
     * spring security无权限异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public Ajax handleAccessDeniedException() {
        return Ajax.status(false,403,"无权限");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    public Ajax handleValidateException(ValidateException e) {
        return Ajax.error(e.getMessage());
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Ajax handleBindException(BindException e) {
        //获取异常内容
        FieldError fieldError = e.getFieldError();
        Class clazz = e.getFieldType(fieldError.getField());
        String hopeType;
        switch (clazz.getSimpleName()) {
            case "Integer":
            case "int":
            case "Long":
            case "long":
                hopeType = "整数";
                break;
            case "Boolean":
            case "boolean":
                hopeType = "布尔类型";
                break;
            case "Double":
            case "double":
            case "Float":
            case "float":
                hopeType = "小数";
                break;
            case "Date":
                hopeType = "日期";
                break;
            default:
                hopeType = clazz.getSimpleName();
                break;
        }
        //用户输入的内容
        Object input = fieldError.getRejectedValue();
        return Ajax.error("【" + input.toString() + "】格式非法,请输入【" + hopeType + "】");
    }

    /**
     * 默认异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Ajax handleException(Exception e) {
        //提取错误信息
        String error;
        if (e.getCause() != null) {
            error = e.getCause().getMessage() == null ? "空指针异常" : e.getCause().getMessage();
        } else {
            error = e.getMessage() == null ? "空指针异常" : e.getMessage();
        }
        log.warn(e.getMessage());
        //打印堆栈信息
        e.printStackTrace();
        return Ajax.error(error);
    }

    /**
     * RPC异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Ajax handleRpcException(RpcException e) {
        log.warn(e.getMessage());
        return Ajax.error("rpc异常");
    }
}
