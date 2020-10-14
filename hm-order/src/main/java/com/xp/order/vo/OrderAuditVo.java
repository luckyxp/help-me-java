package com.xp.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/28 11:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAuditVo {
    private Integer orderId;
    private Integer orderResultId;
    private Boolean isAudit; //是否审核通过
}
