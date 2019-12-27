package com.ocean.template;

import java.util.List;

/**
 * 订单处理service
 *
 * @author 郭大海
 */
public interface PackageOrderService {

    /**
     * 处理包裹订单
     *
     * @param packageOrderList 包裹订单list
     */
    void process(List<PackageOrder> packageOrderList);
}


