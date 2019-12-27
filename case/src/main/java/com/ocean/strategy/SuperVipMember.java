package com.ocean.strategy;

import org.springframework.stereotype.Service;

/**
 * 超级会员
 *
 * @author 郭大海
 */
@Service("superVipMember")
public class SuperVipMember implements Strategy {

    @Override
    public Double discount() {
        //超级会员，88折
        return 0.88;
    }
}

