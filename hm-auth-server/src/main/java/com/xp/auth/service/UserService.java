package com.xp.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xp.auth.entity.UserEntity;
import com.xp.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 18:47:36
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserEntity selectByName(String name);
}

