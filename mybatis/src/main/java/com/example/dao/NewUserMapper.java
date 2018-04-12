package com.example.dao;

import com.example.model.NewUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guodahai
 * @version 2018/4/12 下午4:45
 */
@Mapper
public interface NewUserMapper {

    NewUser queryById(Integer id);

}
