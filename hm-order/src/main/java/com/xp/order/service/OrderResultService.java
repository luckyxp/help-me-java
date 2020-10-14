package com.xp.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xp.common.utils.PageUtils;
import com.xp.order.entity.OrderEntity;
import com.xp.order.entity.OrderResultEntity;
import com.xp.order.vo.OrderAuditVo;
import com.xp.order.vo.OrderResultVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
public interface OrderResultService extends IService<OrderResultEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderResultEntity> listByOrderId(Integer id);

    OrderResultEntity createResult(OrderResultVo vo,int code );

    void audit(OrderAuditVo vo);
}

