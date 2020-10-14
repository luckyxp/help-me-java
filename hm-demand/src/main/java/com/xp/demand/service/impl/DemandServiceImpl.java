package com.xp.demand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.common.constant.DemandConstant;
import com.xp.common.entity.UserEntity;
import com.xp.common.utils.PageUtils;
import com.xp.common.utils.Query;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.common.vo.ChargeVo;
import com.xp.common.vo.OrderVo;
import com.xp.demand.dao.DemandDao;
import com.xp.demand.entity.DemandEntity;
import com.xp.demand.feign.OrderFeignService;
import com.xp.demand.feign.UserFeignService;
import com.xp.demand.service.DemandService;
import com.xp.demand.vo.PubDemandVo;
import com.xp.demand.vo.PubInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandDao, DemandEntity> implements DemandService {
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DemandEntity> page = this.page(
                new Query<DemandEntity>().getPage(params),
                new QueryWrapper<DemandEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public PageUtils homeList(Map<String, Object> params) {
        UserEntity info = userFeignService.info();
        IPage<DemandEntity> page = this.page(
                new Query<DemandEntity>().getPage(params),
                new QueryWrapper<DemandEntity>().ne("pub_id", info.getId())
                        .eq("demand_status", DemandConstant.DemandStatus.CREATED.getCode())
        );

        return new PageUtils(page);
    }

    @Override
    public DemandEntity pubDemand(PubDemandVo vo) {
        UserEntity info = userFeignService.info();
        UserEntity deduct = userFeignService.deduct(new ChargeVo(vo.getBounty(), "", info.getId()));
        if (deduct == null) {
            System.out.println("余额不足");
            return null;
        }
        DemandEntity demand = new DemandEntity();
        BeanUtils.copyProperties(vo, demand);
        demand.setDemandStatus(DemandConstant.DemandStatus.AUDIT.getCode());
        demand.setPubId(info.getId());
        demand.setTime(new Date());
        this.baseMapper.insert(demand);
        return demand;
    }

    @Override
    public List<PubInfoVo> myPub() {
        UserEntity info = userFeignService.info();
        List<DemandEntity> demandEntities = this.baseMapper.selectList(new QueryWrapper<DemandEntity>().eq("pub_id", info.getId()));
        return demandEntities.stream().map(item -> {
            PubInfoVo pubInfoVo = new PubInfoVo();
            List<OrderVo> orderVos = orderFeignService.listByDemandId(item.getId());
            pubInfoVo.setOrders(orderVos);
            pubInfoVo.setDemandEntity(item);
            return pubInfoVo;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer changeStatus(ChangeDemandStatusVo vo) {
        DemandEntity demandEntity = new DemandEntity();
        demandEntity.setId(vo.getDemandId());
        demandEntity.setDemandStatus(vo.getStatus());
        return this.baseMapper.updateById(demandEntity);
    }


    @Override
    public DemandEntity cancelDemand(Integer demandId) {
        DemandEntity demand = this.baseMapper.selectById(demandId);
        if (demand.getDemandStatus() != DemandConstant.DemandStatus.CREATED.getCode() &&
                demand.getDemandStatus() != DemandConstant.DemandStatus.AUDIT.getCode()) {
            return null;
        }
        //取消需求下的所有新建状态的订单
        orderFeignService.cancelOrderByDemandId(demandId);
        //需求修改状态
        demand.setDemandStatus(DemandConstant.DemandStatus.CANCEL.getCode());
        DemandEntity demandEntity = new DemandEntity();
        demandEntity.setId(demandId);
        demandEntity.setDemandStatus(DemandConstant.DemandStatus.CANCEL.getCode());
        this.baseMapper.updateById(demandEntity);
        //退钱
        userFeignService.charge(new ChargeVo(demand.getBounty(), "", userFeignService.info().getId()));
        return demand;
    }

    private String getName() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}