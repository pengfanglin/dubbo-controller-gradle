package com.fanglin.dubbo.service;

import com.fanglin.common.core.page.Page;
import com.fanglin.common.core.page.PageResult;
import com.fanglin.dubbo.template.model.MemberModel;


/**
 * 用户服务接口
 *
 * @author 彭方林
 * @version 1.0
 * @date 2019/4/3 14:13
 **/
public interface MemberServiceI {
    /**
     * 获取用户信息
     *
     * @param memberId 用户id
     * @return
     */
    MemberModel getMemberDetail(Integer memberId);

    /**
     * 获取用户列表
     *
     * @param page 分页对象
     * @return
     */
    PageResult<MemberModel> getMemberList(Page page);

    /**
     * 修改用户信息
     *
     * @param member 用户对象
     * @return
     */
    int updateMember(MemberModel member);
}
