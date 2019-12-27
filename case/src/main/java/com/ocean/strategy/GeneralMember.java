package com.ocean.strategy;

import org.springframework.stereotype.Service;

/**
 * 普通用户
 *
 * @author 郭大海
 */
@Service("generalMember")
public class GeneralMember implements Strategy {

    @Override
    public Double discount() {
        //普通用户原价，没有折扣
        return 1.00;
    }
}

