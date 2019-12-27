package com.ocean.service.impl;

import com.ocean.dao.NewUserMapper;
import com.ocean.dao.OldUserMapper;
import com.ocean.model.NewUser;
import com.ocean.model.OldUser;
import com.ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guodahai
 * @version 2018/4/12 下午4:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private NewUserMapper newUserMapper;

    @Autowired
    private OldUserMapper oldUserMapper;

    @Override
    public NewUser queryNewUser(Integer id) {
        return newUserMapper.queryById(id);
    }

    @Override
    public OldUser queryOldUser(Integer id) {
        return oldUserMapper.queryById(id);
    }
}
