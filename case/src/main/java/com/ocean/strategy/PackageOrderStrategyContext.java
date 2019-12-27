package com.ocean.strategy;

import com.ocean.template.PackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 订单包裹处理策略管理器
 *
 * @author 郭大海
 */
@SuppressWarnings("all")
@Component
public class PackageOrderStrategyContext {
    /**
     * 存储所有实现PackageOrderStrategy接口的Bean
     */
    private final Map<String, PackageOrderStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 注入所有实现了Strategy接口的Bean
     */
    @Autowired
    public PackageOrderStrategyContext(Map<String, PackageOrderStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    /**
     * 处理订单包裹流程
     */
    public void process(String strategy, List<PackageOrder> packageOrderList) {
        strategyMap.get(strategy).process(packageOrderList);
    }
}


