package com.xp.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xp.auth.entity.UserEntity;
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
