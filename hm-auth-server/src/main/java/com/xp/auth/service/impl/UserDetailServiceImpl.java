package com.xp.auth.service.impl;

import com.xp.auth.dao.UserDao;
import com.xp.auth.entity.UserEntity;
import com.xp.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author Climb.Xu
 * @date 2020/9/19 11:16
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("进入认证"+name);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        UserEntity user = userService.selectByName(name);
        if (user == null) {
            return null;
        }


        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName()).password(user.getPassword())
                .authorities("p1").build();
    }
}
