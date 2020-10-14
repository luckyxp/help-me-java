package com.xp.demand.feign;

import com.xp.common.entity.UserEntity;
import com.xp.common.utils.R;
import com.xp.common.vo.ChargeVo;
import com.xp.demand.interceptor.OAuth2FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Climb.Xu
 * @date 2020/9/24 21:48
 */
@Service
@FeignClient(value = "hm-user",configuration = OAuth2FeignRequestInterceptor.class)
public interface UserFeignService {
    @GetMapping("/user/user/info")
    UserEntity info();

    @PostMapping("/user/user/deduct")
    UserEntity deduct(@RequestBody ChargeVo vo);

    @PostMapping("/user/user/charge")
    Object charge(@RequestBody ChargeVo vo);
}
