package com.ocean.template;

import com.ocean.CaseApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseApplication.class)
public class PackageOrderServiceTest {

    @Resource
    private PackageOrderService mallPackageOrderService;

    @Resource
    private PackageOrderService partnerPackageOrderService;

    /**
     * 巢鲜厨订单包裹处理
     */
    @Test
    public void mallPackageOrder() {
        mallPackageOrderService.process(Lists.newArrayList());
    }

    /**
     * 合作商户订单包裹处理
     */
    @Test
    public void partnerPackageOrder() {
        partnerPackageOrderService.process(Lists.newArrayList());
    }
}



