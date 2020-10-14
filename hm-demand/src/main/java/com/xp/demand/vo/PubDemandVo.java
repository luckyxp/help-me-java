package com.xp.demand.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/19 23:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubDemandVo {
    private String title;
    private String imges;
    private String content;
    private Float bounty;
}
