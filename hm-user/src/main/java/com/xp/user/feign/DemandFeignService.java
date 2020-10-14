package com.xp.user.feign;

import com.xp.user.interceptor.OAuth2FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Climb.Xu
 * @date 2020/9/24 16:56
 */
@Service
@FeignClient(value = "hm-auth-server")
public interface DemandFeignService {
    @PostMapping(value = "/oauth/token",headers = {"Content-Type: multipart/form-data"})
    Object postAccessToken(@RequestBody MultiValueMap<String, String> map);
}
