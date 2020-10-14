package com.xp.demand.feign;

import com.xp.common.vo.OrderVo;
import com.xp.demand.interceptor.OAuth2FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Climb.Xu
 * @date 2020/9/25 11:31
 */
@Service
@FeignClient(value = "hm-order",configuration = OAuth2FeignRequestInterceptor.class)
public interface OrderFeignService {
    @GetMapping("/order/order/list_demandId")
    List<OrderVo> listByDemandId(@RequestParam("demandId") Integer demandId);

    @GetMapping("/order/order/cancel_order_demand")
    Object cancelOrderByDemandId(@RequestParam("demandId") Integer demandId);
}
