package com.xp.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xp.common.constant.DemandConstant;
import com.xp.common.entity.UserEntity;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.common.vo.DemandEntity;
import com.xp.common.vo.OrderDetailVo;
import com.xp.common.vo.OrderVo;
import com.xp.order.entity.OrderResultEntity;
import com.xp.order.feign.DemandFeignService;
import com.xp.order.feign.UserFeignService;
import com.xp.order.service.OrderResultService;
import com.xp.order.vo.OrderListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.common.utils.PageUtils;
import com.xp.common.utils.Query;

import com.xp.order.dao.OrderDao;
import com.xp.order.entity.OrderEntity;
import com.xp.order.service.OrderService;

import javax.annotation.Resource;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    private OrderResultService orderResultService;
    @Autowired
    private DemandFeignService demandFeignService;
    @Autowired
    private UserFeignService userFeignService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<OrderVo> listByDemandId(Integer demandId) {
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("demand_id", demandId));
        return orders.stream().map(item -> {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(item, orderVo);
            List<OrderResultEntity> results = orderResultService.listByOrderId(item.getId());
            List<OrderDetailVo> collect = results.stream().map(i -> {
                OrderDetailVo orderDetailVo = new OrderDetailVo();
                BeanUtils.copyProperties(i, orderDetailVo);
                return orderDetailVo;
            }).collect(Collectors.toList());
            orderVo.setDetailVos(collect);
            return orderVo;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderEntity subDemand(Integer demandId) {
        DemandEntity demandEntity = demandFeignService.demandById(demandId);
        if (demandEntity.getDemandStatus()!=0) {
            return null;
        }
        UserEntity info = userFeignService.info();
        if (demandEntity.getPubId().equals(info.getId())) {
            return null;
        }
        //修改demand状态
        demandEntity.setDemandStatus(DemandConstant.DemandStatus.ASSIGNED.getCode());
        demandFeignService.changeStatus(new ChangeDemandStatusVo(demandId, DemandConstant.DemandStatus.ASSIGNED.getCode()));
        //生成订单
        OrderEntity orderEntity = new OrderEntity(null, new Date(), demandId, info.getId(), DemandConstant.OrderStatus.CREATED.getCode());
        this.baseMapper.insert(orderEntity);
        return orderEntity;
    }

    @Override
    public void changeStatusById(Integer orderId, int code) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setOrderStatus(code);
        this.baseMapper.updateById(orderEntity);
    }

    @Override
    public void cancelOrderByDemandId(Integer demandId) {
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("demand_id", demandId));
        if (orders==null||orders.size()==0) {
            return;
        }
        orders.forEach(order->{
            //修改订单状态
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(order.getId());
            orderEntity.setOrderStatus(DemandConstant.OrderStatus.CANCEL.getCode());
            this.baseMapper.updateById(orderEntity);
            //修改订单下的订单详情状态
            List<OrderResultEntity> orderResults = orderResultService.listByOrderId(order.getId());
            orderResults.forEach(orderResult->{
                OrderResultEntity entity = new OrderResultEntity();
                entity.setId(orderResult.getId());
                entity.setStatus(DemandConstant.OrderStatus.CANCEL.getCode());
                orderResultService.updateById(entity);
            });
        });
    }

    @Override
    public List<OrderListVo> orderList() {
        UserEntity info = userFeignService.info();
        List<OrderEntity> mySub = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("sub_id", info.getId()));
        return mySub.stream().map(s -> {
            OrderListVo orderListVo = new OrderListVo();
            orderListVo.setOrderEntity(s);
            DemandEntity demandEntity = demandFeignService.demandById(s.getDemandId());
            orderListVo.setDemandEntity(demandEntity);
            List<OrderResultEntity> orderResultEntities = orderResultService.listByOrderId(s.getId());
            orderListVo.setResults(orderResultEntities);
            return orderListVo;
        }).collect(Collectors.toList());
    }
}