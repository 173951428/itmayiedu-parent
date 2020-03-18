package com.itmayiedu.api.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itmayiedu.api.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity> {

    UserEntity getByUsername(String username);
    Integer selectByEmail(String email);
    Integer insertUser(String name,int age);
}
