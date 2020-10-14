package com.xp.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/19 10:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegVo {
    private String name;
    private String password;
    private String phone;

}
