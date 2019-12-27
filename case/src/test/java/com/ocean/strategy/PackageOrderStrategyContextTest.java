package com.ocean.strategy;

import com.ocean.CaseApplication;
import com.ocean.template.PackageOrder;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseApplication.class)
public class PackageOrderStrategyContextTest {

    @Autowired
    private PackageOrderStrategyContext packageOrderStrategyConText;


    @Test
    public void test() {
        List<PackageOrder> packageOrderList = Lists.newArrayList();
        String strategy = "mallPackageOrderStrategy";
        process(strategy, packageOrderList);
        System.out.println("===================分割线===================");
        strategy = "partnerPackageOrderStrategy";
        process(strategy, packageOrderList);
    }

    /**
     * 订单包裹处理
     */
    private void process(String strategy, List<PackageOrder> packageOrderList) {
        packageOrderStrategyConText.process(strategy, packageOrderList);
    }




}