package com.fanglin.dubbo.controller;


import com.fanglin.common.core.others.Ajax;
import com.fanglin.common.core.page.Page;
import com.fanglin.common.utils.WxUtils;
import com.fanglin.dubbo.service.MemberServiceI;
import com.fanglin.dubbo.template.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MemberController {
    @Autowired
    MemberServiceI memberService;

    @PostMapping("test")
    public Ajax test() {
        return Ajax.ok(WxUtils.getJsApiTicket("555"));
    }

    @PostMapping("updateMember")
    public Ajax updateMember(MemberModel member) {
        if (memberService.updateMember(member) > 0) {
            return Ajax.ok("修改成功");
        } else {
            return Ajax.error("修改失败");
        }
    }

    @PostMapping("getMemberDetail")
    public Ajax getMemberDetail(@RequestParam("memberId") Integer memberId) {
        return Ajax.ok(memberService.getMemberDetail(memberId));
    }

    @PostMapping("getMemberList")
    public Ajax getMemberList(Page page) {
        return Ajax.ok(memberService.getMemberList(page));
    }
}
