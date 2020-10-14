package com.xp.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xp.common.utils.PageUtils;
import com.xp.common.vo.OrderVo;
import com.xp.order.entity.OrderEntity;
import com.xp.order.vo.OrderListVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderVo>listByDemandId(Integer demandId);

    OrderEntity subDemand(Integer demandId);

    void changeStatusById(Integer orderId, int code);

    void cancelOrderByDemandId(Integer demandId);

    List<OrderListVo> orderList();
}

