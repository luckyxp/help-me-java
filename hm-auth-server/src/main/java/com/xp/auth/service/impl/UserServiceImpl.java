package com.xp.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.auth.dao.UserDao;
import com.xp.auth.entity.UserEntity;
import com.xp.auth.service.UserService;
import com.xp.common.utils.PageUtils;

import com.xp.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity selectByName(String name) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().eq("name", name);
        List<UserEntity> userEntities = this.baseMapper.selectList(wrapper);
        if (userEntities==null||userEntities.size()==0){
            return null;
        }
        return userEntities.get(0);
    }
}