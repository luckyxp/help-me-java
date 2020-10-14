package com.xp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Climb.Xu
 * @date 2020/9/25 11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
    private Integer id;
    private Integer demandId;
    private Integer orderStatus;
    private Integer subId;
    private Date time;
    private List<OrderDetailVo> detailVos;
}
