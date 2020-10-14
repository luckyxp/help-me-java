package com.xp.order.vo;

import com.xp.common.vo.DemandEntity;
import com.xp.order.entity.OrderEntity;
import com.xp.order.entity.OrderResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Climb.Xu
 * @date 2020/10/12 14:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVo {
    private DemandEntity demandEntity;
    private OrderEntity orderEntity;
    private List<OrderResultEntity> results;
}
