package com.niu.mall.admin;

import com.niu.mall.security.component.DynamicSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author lihaojie
 * @date 2023/04/06 21:54
 **/
@SpringBootTest
@RunWith(Runner.class)
class DynamicSecurityServiceTest {
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @Test
    void test() {
        Map<String, ConfigAttribute> stringConfigAttributeMap = dynamicSecurityService.loadDataSource();
        System.out.println("stringConfigAttributeMap = " + stringConfigAttributeMap.toString());
    }
}
