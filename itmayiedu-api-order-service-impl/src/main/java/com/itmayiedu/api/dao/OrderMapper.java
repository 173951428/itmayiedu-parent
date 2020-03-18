package com.itmayiedu.api.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itmayiedu.api.entity.Order;
import com.itmayiedu.api.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {


    Integer insertOrder(int id, String orderName);
}
