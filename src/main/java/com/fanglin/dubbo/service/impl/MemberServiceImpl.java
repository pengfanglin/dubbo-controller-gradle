package com.fanglin.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fanglin.common.core.page.Page;
import com.fanglin.common.core.page.PageResult;
import com.fanglin.dubbo.service.MemberServiceI;
import com.fanglin.dubbo.template.api.MemberApi;
import com.fanglin.dubbo.template.model.MemberModel;
import org.springframework.stereotype.Service;


/**
 * 用户服务实现类
 *
 * @author 彭方林
 * @version 1.0
 * @date 2019/4/3 14:16
 **/
@Service
public class MemberServiceImpl implements MemberServiceI {

    @Reference
    MemberApi memberApi;

    @Override
    public MemberModel getMemberDetail(Integer memberId) {
        return memberApi.getMemberDetail(memberId);
    }

    @Override
    public PageResult<MemberModel> getMemberList(Page page) {
        return memberApi.getMemberList(page);
    }

    @Override
    public int updateMember(MemberModel member) {
        return memberApi.updateMember(member);
    }

}
