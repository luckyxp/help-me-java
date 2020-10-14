package com.xp.user.vo;

import com.xp.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/19 10:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegResultVo {
    private int code;
    private UserEntity user;
    private String msg;
}
