package com.atguigu.gulimall.oms.dao;

import com.atguigu.gulimall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:46:33
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
