package com.niu.mall.admin.controller;

import com.niu.mall.admin.service.UmsResourceService;
import com.niu.mall.mbg.po.UmsResourcePo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(Runner.class)
class UmsResourceControllerTest {
    @Autowired
    private UmsResourceService resourceService;
    @Autowired
    private UmsResourceService service;

    @Test
    void ServiceTest1() {
        List<UmsResourcePo> list = resourceService.list();
        for (UmsResourcePo resourcePo : list) {
            System.out.println("resourcePo = " + resourcePo.toString());
        }
    }

    @Test
    void create() {
    }

    @Test
    void updateById() {
    }

    @Test
    void getBuId() {
    }

    @Test
    void delete() {
    }

    @Test
    void list() {
    }

    @Test
    void listAll() {

    }
}