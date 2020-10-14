package com.xp.user.dao;

import com.xp.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 18:47:36
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
