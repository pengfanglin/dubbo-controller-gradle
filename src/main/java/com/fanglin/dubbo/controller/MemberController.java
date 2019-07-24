package com.fanglin.dubbo.controller;

import com.fanglin.common.core.others.Ajax;
import com.fanglin.common.core.page.Page;
import com.fanglin.common.core.page.PageResult;
import com.fanglin.common.utils.WxUtils;
import com.fanglin.dubbo.template.api.MemberApi;
import com.fanglin.dubbo.template.model.MemberModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户控制器
 *
 * @author 彭方林
 * @version 1.0
 * @date 2019/4/3 14:15
 **/
@RestController
@RequestMapping("/member/")
@Api(value = "/member/", tags = {"用户"})
public class MemberController {
    @Reference
    MemberApi memberApi;

    @ApiOperation("测试")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "账号id", required = true)
    })
    @PostMapping("test")
    public Ajax test() {
        return Ajax.ok(WxUtils.getJsApiTicket("555"));
    }

    @ApiOperation("修改用户信息")
    @PostMapping("updateMember")
    public Ajax updateMember(MemberModel member) {
        memberApi.updateMember(member);
        return Ajax.ok();
    }

    @ApiOperation("用户详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "账号id", required = true)
    })
    @PostMapping("getMemberDetail")
    public Ajax<MemberModel> getMemberDetail(@RequestParam Integer id) {
        return Ajax.ok(memberApi.getMemberDetail(id));
    }

    @ApiOperation("用户列表")
    @PostMapping("getMemberList")
    public Ajax<PageResult<MemberModel>> getMemberList(Page page) {
        return Ajax.ok(memberApi.getMemberList(page));
    }
}
