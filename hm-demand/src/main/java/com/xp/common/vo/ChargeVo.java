package com.xp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/24 22:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeVo {
    private Float money;
    private String validation;
    private Integer targetId;
}
