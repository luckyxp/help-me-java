package com.xp.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.common.constant.DemandConstant;
import com.xp.common.utils.PageUtils;
import com.xp.common.utils.Query;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.common.vo.ChargeVo;
import com.xp.common.vo.DemandEntity;
import com.xp.order.dao.OrderResultDao;
import com.xp.order.entity.OrderEntity;
import com.xp.order.entity.OrderResultEntity;
import com.xp.order.feign.DemandFeignService;
import com.xp.order.feign.UserFeignService;
import com.xp.order.service.OrderResultService;
import com.xp.order.service.OrderService;
import com.xp.order.vo.OrderAuditVo;
import com.xp.order.vo.OrderResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("orderResultService")
public class OrderResultServiceImpl extends ServiceImpl<OrderResultDao, OrderResultEntity> implements OrderResultService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DemandFeignService demandFeignService;
    @Autowired
    private UserFeignService userFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderResultEntity> page = this.page(
                new Query<OrderResultEntity>().getPage(params),
                new QueryWrapper<OrderResultEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderResultEntity> listByOrderId(Integer id) {
        return this.baseMapper.selectList(new QueryWrapper<OrderResultEntity>().eq("order_id", id));
    }

    @Override
    public OrderResultEntity createResult(OrderResultVo vo, int code) {
        OrderEntity order = orderService.getById(vo.getOrderId());
        if (order.getOrderStatus() == DemandConstant.OrderStatus.UNFINISHED.getCode() ||
                order.getOrderStatus() == DemandConstant.OrderStatus.FINISH.getCode()) {
            return null;
        }
        OrderResultEntity entity = new OrderResultEntity();
        BeanUtils.copyProperties(vo, entity);
        entity.setTime(new Date());
        entity.setStatus(code);
        this.baseMapper.insert(entity);
        orderService.changeStatusById(vo.getOrderId(), code);
        if (code== DemandConstant.OrderStatus.UNFINISHED.getCode()){
            demandFeignService.changeStatus(new ChangeDemandStatusVo(order.getDemandId(), DemandConstant.DemandStatus.CREATED.getCode()));
        }
        return entity;
    }

    @Override
    public void audit(OrderAuditVo vo) {
        OrderEntity order = orderService.getById(vo.getOrderId());

        DemandEntity demandEntity = demandFeignService.demandById(order.getDemandId());
        if (!demandEntity.getPubId().equals(userFeignService.info().getId())) {
            return;
        }
        OrderResultEntity entity = new OrderResultEntity();
        entity.setId(vo.getOrderResultId());
        if (vo.getIsAudit()) {
            //订单结果审核通过
            entity.setStatus(DemandConstant.OrderStatus.FINISH.getCode());
            //订单已完成
            orderService.changeStatusById(vo.getOrderId(), DemandConstant.OrderStatus.FINISH.getCode());
            //需求已完成
            demandFeignService.changeStatus(new ChangeDemandStatusVo(order.getDemandId(), DemandConstant.DemandStatus.FINISH.getCode()));
            //用户赏金到账 todo
            userFeignService.charge(new ChargeVo(demandEntity.getBounty(), "", order.getSubId()));
        } else {
            orderService.changeStatusById(vo.getOrderId(), DemandConstant.OrderStatus.CREATED.getCode());
            entity.setStatus(DemandConstant.OrderStatus.UNFINISHED.getCode());
            this.baseMapper.updateById(entity);
        }
        this.baseMapper.updateById(entity);
    }

}