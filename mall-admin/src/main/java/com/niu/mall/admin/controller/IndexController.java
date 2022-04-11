package com.niu.mall.admin.controller;

import com.niu.mall.common.api.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author Abel ZhaoKun
 * @date 2022/4/11 10:03
 */
@RequestMapping("/index")
@RestController
public class IndexController {
    @PostMapping("/login")
    public Result login(){
        return Result.failed("请求失败");
    }
}
