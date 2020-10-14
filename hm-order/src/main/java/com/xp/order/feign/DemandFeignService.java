package com.xp.order.feign;

import com.xp.common.utils.R;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.common.vo.DemandEntity;
import com.xp.order.interceptor.OAuth2FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author Climb.Xu
 * @date 2020/9/25 18:09
 */

@Service
@FeignClient(value = "hm-demand", configuration = OAuth2FeignRequestInterceptor.class)
public interface DemandFeignService {
    @GetMapping("/demand/demand/demand_id")
    DemandEntity demandById(@RequestParam("demandId") Integer demandId);

    @PostMapping("/demand/demand/change_status")
    Integer changeStatus(@RequestBody ChangeDemandStatusVo vo);
}
