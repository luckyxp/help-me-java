package com.xp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/28 11:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDemandStatusVo {
    private Integer demandId;
    private Integer status;
}
