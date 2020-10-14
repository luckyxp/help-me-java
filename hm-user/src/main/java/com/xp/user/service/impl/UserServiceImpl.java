package com.xp.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.common.utils.PageUtils;
import com.xp.common.utils.Query;
import com.xp.user.dao.UserDao;
import com.xp.user.entity.UserEntity;
import com.xp.user.service.UserService;
import com.xp.common.vo.ChargeVo;
import com.xp.user.vo.RePasswordVo;
import com.xp.user.vo.RegResultVo;
import com.xp.user.vo.RegVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


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
    public RegResultVo reg(RegVo vo) {
        UserEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("name", vo.getName()));
        RegResultVo regResultVo = new RegResultVo();
        regResultVo.setCode(500);
        regResultVo.setMsg("注册失败!");
        if (entity != null) {
            return regResultVo;
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(vo, userEntity);
        String fullName = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
        userEntity.setFullName(fullName);
        userEntity.setHeadImage("default.png");
        userEntity.setMoeny(0f);
        userEntity.setAuthorities("");
        int result = this.baseMapper.insert(userEntity);
        if (result > 0) {
            userEntity.setPassword("***********");
            regResultVo.setCode(200);
            regResultVo.setUser(userEntity);
            regResultVo.setMsg("注册成功!");
        }
        return regResultVo;
    }

    @Override
    public UserEntity info() {
        return this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("name", getName()));
    }

    @Override
    public RegResultVo changeInfo(UserEntity userEntity) {
        RegResultVo regResultVo = new RegResultVo();
        regResultVo.setCode(500);
        regResultVo.setMsg("修改失败!");
        UserEntity entity = new UserEntity();
        entity.setFullName(userEntity.getFullName());
        entity.setHeadImage(userEntity.getHeadImage());
        entity.setPhone(userEntity.getPhone());
        int result = this.baseMapper.update(entity,new UpdateWrapper<UserEntity>().eq("name",getName()));
        if (result > 0) {
            regResultVo.setCode(200);
            regResultVo.setUser(userEntity);
            regResultVo.setMsg("用户信息修改成功!");
        }
        return regResultVo;
    }

    @Override
    public int rePassword(RePasswordVo vo) {
        UserEntity user = getUser();
        if (!user.getPassword().equals(vo.getOldPassword())){
            return 0;
        }
        UserEntity entity = new UserEntity();
        entity.setPassword(vo.getNewPassword());
        entity.setId(user.getId());
        return this.baseMapper.updateById(entity);
    }

    @Override
    public UserEntity charge(ChargeVo vo) {
        //todo 充值验证
        UserEntity userEntity = this.baseMapper.selectById(vo.getTargetId() == null ? getId() : vo.getTargetId());
        UserEntity entity = new UserEntity();
        entity.setId(userEntity.getId());
        entity.setMoeny(userEntity.getMoeny() + vo.getMoney());
        this.baseMapper.updateById(entity);
        return entity;
    }

    @Override
    public UserEntity deduct(ChargeVo vo) {
        //todo 扣钱验证
        UserEntity userEntity = this.baseMapper.selectById(vo.getTargetId() == null ? getId() : vo.getTargetId());
        if (userEntity.getMoeny()-vo.getMoney()<0){
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(userEntity.getId());
        entity.setMoeny(userEntity.getMoeny() - vo.getMoney());
        this.baseMapper.updateById(entity);
        return entity;
    }

    private UserEntity getUser() {
        return this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("name", getName()));
    }
    private Integer getId() {
        return this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("name", getName())).getId();
    }
    private String getName() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}