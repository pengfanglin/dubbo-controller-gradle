package com.fanglin.dubbo.security;

import com.fanglin.common.core.others.Ajax;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理注销成功
 * @author zhoukebo
 * @date 2018/9/4
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    @Qualifier("ajaxObjectMapper")
    ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        objectMapper.writeValue(response.getOutputStream(), Ajax.ok("注销成功"));
    }
}
