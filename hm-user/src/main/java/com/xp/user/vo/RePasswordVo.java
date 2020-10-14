package com.xp.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/24 21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RePasswordVo {
    private String oldPassword;
    private String newPassword;
}
