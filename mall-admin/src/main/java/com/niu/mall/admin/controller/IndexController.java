package com.niu.mall.admin.controller;

import com.niu.mall.common.api.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 登录接口
 *
 * @author Abel ZhaoKun
 * @date 2022/4/11 10:03
 */

@Controller
@RequestMapping(value = "index")
public class IndexController {
    @ResponseBody
    @GetMapping("/login")
    //改完代码以后  按ctrl+shift+F9   就不用重新启动了  就能自动编译新更改的代码
    public Result login(){
        return Result.failed("请求失败");
    }
    @ResponseBody
    @PostMapping("/test")
    public Result test(){
        return Result.failed("Test Failed");
    }
}
