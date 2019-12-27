package com.ocean.strategy;

import com.ocean.template.PackageOrder;

import java.util.List;

/**
 * 包裹订单处理策略接口
 *
 * @author 郭大海
 */
public interface PackageOrderStrategy {

    /**
     * 处理包裹订单
     *
     * @param packageOrderList 包裹订单list
     */
    void process(List<PackageOrder> packageOrderList);
}


