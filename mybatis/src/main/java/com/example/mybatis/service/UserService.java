package com.example.mybatis.service;

import com.example.mybatis.model.NewUser;
import com.example.mybatis.model.OldUser;

/**
 * @author guodahai
 * @version 2018/4/12 下午4:51
 */
public interface UserService {
    /**
     * 查询新库用户信息
     *
     * @param id
     * @return
     */
    public NewUser queryNewUser(Integer id);

    /**
     * 查询旧库用户信息
     *
     * @param id
     * @return
     */
    public OldUser queryOldUser(Integer id);

}
