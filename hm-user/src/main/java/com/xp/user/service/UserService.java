package com.xp.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xp.common.utils.PageUtils;
import com.xp.user.entity.UserEntity;
import com.xp.common.vo.ChargeVo;
import com.xp.user.vo.RePasswordVo;
import com.xp.user.vo.RegResultVo;
import com.xp.user.vo.RegVo;

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

    RegResultVo reg(RegVo vo);

    UserEntity info();

    RegResultVo changeInfo(UserEntity userEntity);

    int rePassword(RePasswordVo vo);

    UserEntity charge(ChargeVo vo);

    UserEntity deduct(ChargeVo vo);
}

