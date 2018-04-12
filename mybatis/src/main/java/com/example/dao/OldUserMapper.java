package com.example.dao;

import com.example.model.OldUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guodahai
 * @version 2018/4/12 下午4:43
 */
@Mapper
public interface OldUserMapper {

    OldUser queryById(Integer id);

}
