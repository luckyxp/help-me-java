package com.xp.user.controller;

import com.xp.common.utils.PageUtils;
import com.xp.common.utils.R;
import com.xp.common.vo.ChargeVo;
import com.xp.user.config.ResourceServerConfig;
import com.xp.user.entity.UserEntity;
import com.xp.user.service.UserService;
import com.xp.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;


/**
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 18:47:36
 */
@RestController
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    //退出登录 todo
    @PostMapping("/logout")
    public Object logout() {
        return R.ok();
    }
    //用户注册
    @PostMapping("/reg")
    public Object reg(@RequestBody RegVo vo) {
        RegResultVo result = userService.reg(vo);
        return result;
    }

    //用户登录
    @PostMapping("/login")
    public Object login(@RequestBody LoginVo vo) {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("username", vo.getName());
        postData.add("password", vo.getPassword());
        postData.add("client_id", ResourceServerConfig.RESOURCE_ID);
        postData.add("client_secret", "secret");
        postData.add("grant_type", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postData, headers);
        System.out.println("ssss");
        String result = restTemplate.postForObject("http://localhost:9000/oauth/token", r, String.class);
        return result;
    }

    //获取个人信息
    @GetMapping("/info")
    public Object info() {
        UserEntity user = userService.info();
        return user;
    }

    //修改用户信息 phone fullName headImage
    @PostMapping("/change_info")
    public Object changeInfo(@RequestBody UserEntity userEntity) {
        RegResultVo vo = userService.changeInfo(userEntity);
        return vo;
    }

    //修改密码 todo 失效token
    @PostMapping("/re_password")
    public Object rePassword(@RequestBody RePasswordVo vo) {
        int result = userService.rePassword(vo);
        return result > 0 ? R.ok() : R.error();
    }

    //用户充值
    @PostMapping("/charge")
    public Object charge(@RequestBody ChargeVo vo) {
        UserEntity userEntity = userService.charge(vo);
        return R.ok().put("data", userEntity);
    }

    //用户发布需求(扣钱)
    @PostMapping("/deduct")
    public Object deduct(@RequestBody ChargeVo vo) {
        UserEntity userEntity = userService.deduct(vo);
        return userEntity;
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
