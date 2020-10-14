package com.xp.demand.vo;

import com.xp.common.vo.OrderVo;
import com.xp.demand.entity.DemandEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Climb.Xu
 * @date 2020/9/22 11:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubInfoVo {
    private DemandEntity demandEntity;
    private List<OrderVo> orders;
}
