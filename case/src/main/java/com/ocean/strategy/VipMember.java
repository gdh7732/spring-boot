package com.ocean.strategy;

import org.springframework.stereotype.Service;

/**
 * 普通会员
 *
 * @author 郭大海
 */
@Service("vipMember")
public class VipMember implements Strategy {

    @Override
    public Double discount() {
        //普通会员，95折
        return 0.95;
    }
}

