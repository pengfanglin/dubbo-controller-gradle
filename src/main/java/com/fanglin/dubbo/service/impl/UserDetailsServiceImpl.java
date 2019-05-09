package com.fanglin.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fanglin.common.utils.OthersUtils;
import com.fanglin.dubbo.security.Token;
import com.fanglin.dubbo.template.api.MemberApi;
import com.fanglin.dubbo.template.model.MemberModel;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 彭方林
 * @date 2019/4/3 18:45
 * @version 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference
    MemberApi memberApi;

    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberModel member=memberApi.getMemberByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        //比如用户账号已停用判断。
        if ("1".equals(member.getIsDisable())) {
            throw new DisabledException("账号已禁用");
        }
        Token token = new Token()
            .setUsername(member.getUsername())
            .setPassword(member.getPassword())
            .setId(member.getMemberId())
            .setDisable("1".equals(member.getIsDisable()))
            .setTokenTime(new Date());
        //查询该用户所拥有的角色+权限
        String authInfo= memberApi.getAuthInfo(member.getRoleIds());
        if(!OthersUtils.isEmpty(authInfo)){
            List<GrantedAuthority> grantedAuthorities=new LinkedList<>();
            for(String role:authInfo.split(",")){
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
            token.setAuthorities(grantedAuthorities);
        }
        return token;
    }
}
