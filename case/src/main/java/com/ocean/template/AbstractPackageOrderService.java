package com.ocean.template;

import java.util.List;

/**
 * 包裹订单合单处理
 *
 * @author 郭大海
 */
@SuppressWarnings("all")
public abstract class AbstractPackageOrderService implements PackageOrderService {
    /**
     * 处理包裹订单流程
     * @param packageOrderList 包裹订单list
     */
    @Override
    public void process(List<PackageOrder> packageOrderList) {
        checkPackageOrder(packageOrderList);
        matchPackageOrder(packageOrderList);
        generatePerformOrder(packageOrderList);
    }

    /**
     * 检验包裹订单
     * @param packageOrderList 包裹订单list
     */
    void checkPackageOrder(List<PackageOrder> packageOrderList) {
        System.out.println("同样的检验");
    }

    /**
     * 匹配关联信息
     * @param packageOrderList 包裹订单list
     */
    abstract void matchPackageOrder(List<PackageOrder> packageOrderList);
    /**
     * 生成相关履约单
     * @param packageOrderList 包裹订单list
     */
    abstract void generatePerformOrder(List<PackageOrder> packageOrderList);
}
