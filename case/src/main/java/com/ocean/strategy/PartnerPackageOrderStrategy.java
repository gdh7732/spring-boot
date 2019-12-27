package com.ocean.strategy;

import com.ocean.template.PackageOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合作商户包裹订单
 *
 * @author 郭大海
 */
@Service("partnerPackageOrderStrategy")
public class PartnerPackageOrderStrategy implements PackageOrderStrategy {

    @Override
    public void process(List<PackageOrder> packageOrderList) {
        //1、检验包裹
        checkPackageOrder(packageOrderList);
        //2、匹配包裹订单相关数据
        matchPackageOrder(packageOrderList);
        //3、生成相应履约单
        generatePerformOrder(packageOrderList);
        //4、发送邮件给合作商户
        sendEmail(packageOrderList);
    }

    private void checkPackageOrder(List<PackageOrder> packageOrderList) {
        System.out.println("检验包裹");
    }

    private void matchPackageOrder(List<PackageOrder> packageOrderList) {
        System.out.println("巢鲜厨电商平台包裹订单，匹配包裹订单相关数据");
    }

    private void generatePerformOrder(List<PackageOrder> packageOrderList) {
        System.out.println("巢鲜厨电商平台包裹订单，生成相应履约单");
    }

    private void sendEmail(List<PackageOrder> packageOrderList) {
        System.out.println("发送邮件通知合作商户");
    }
}




