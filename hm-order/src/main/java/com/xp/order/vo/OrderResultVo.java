package com.xp.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/28 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResultVo {
    private Integer orderId;
    private String note;
    private String images;
}
