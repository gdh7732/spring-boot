package com.ocean.template;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合作商户包裹订单
 *
 * @author 郭大海
 */
@Service("partnerPackageOrderService")
public class PartnerPackageOrderService extends AbstractPackageOrderService {
    @Override
    void checkPackageOrder(List<PackageOrder> packageOrderList) {
        System.out.println("合作商户包裹订单，检验包裹订单");
    }

    @Override
    void matchPackageOrder(List<PackageOrder> packageOrderList) {
        System.out.println("合作商户包裹订单，匹配包裹订单相关数据");
    }

    @Override
    void generatePerformOrder(List<PackageOrder> packageOrderList) {
        System.out.println("合作商户包裹订单，生成相应履约单");
    }
}

