package com.xp.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/9/23 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthVo {
    private String username;
    private String password;
}
