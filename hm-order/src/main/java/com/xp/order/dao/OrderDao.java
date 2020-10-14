package com.xp.order.dao;

import com.xp.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
