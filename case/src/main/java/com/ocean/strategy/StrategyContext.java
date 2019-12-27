package com.ocean.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略管理器
 *
 * @author 郭大海
 */
@SuppressWarnings("all")
@Component
public class StrategyContext {
    /**
     * 存储所有实现Strategy接口的Bean
     * key:beanName
     * value：实现Strategy接口Bean
     */
    private final Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 注入所有实现了Strategy接口的Bean
     *
     * @param strategyMap
     */
    @Autowired
    public StrategyContext(Map<String, Strategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    /**
     * 计算价格
     *
     * @param memberLevel 会员等级
     * @return 折扣后的价格
     */
    public Double discount(String memberLevel) {
        if (!StringUtils.isEmpty(memberLevel)) {
            return strategyMap.get(memberLevel).discount();
        }
        return null;
    }
}


