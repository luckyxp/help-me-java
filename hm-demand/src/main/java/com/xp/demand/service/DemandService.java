package com.xp.demand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xp.common.utils.PageUtils;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.demand.entity.DemandEntity;
import com.xp.demand.vo.PubDemandVo;
import com.xp.demand.vo.PubInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:06
 */
public interface DemandService extends IService<DemandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils homeList(Map<String, Object> params);

    DemandEntity pubDemand(PubDemandVo vo);

    List<PubInfoVo> myPub();

    Integer changeStatus(ChangeDemandStatusVo vo);

    DemandEntity cancelDemand(Integer demandId);
}

