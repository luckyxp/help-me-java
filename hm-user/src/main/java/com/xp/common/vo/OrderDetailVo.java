package com.xp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Climb.Xu
 * @date 2020/9/25 11:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    private Integer id;
    private String images;
    private String note;
    private Integer orderId;
    private Integer status;
    private Date time;

}
