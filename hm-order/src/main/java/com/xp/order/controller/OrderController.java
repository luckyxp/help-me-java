package com.xp.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xp.common.constant.DemandConstant;
import com.xp.common.entity.UserEntity;
import com.xp.common.vo.OrderVo;
import com.xp.order.entity.OrderResultEntity;
import com.xp.order.service.OrderResultService;
import com.xp.order.vo.OrderAuditVo;
import com.xp.order.vo.OrderListVo;
import com.xp.order.vo.OrderResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.xp.order.entity.OrderEntity;
import com.xp.order.service.OrderService;
import com.xp.common.utils.PageUtils;
import com.xp.common.utils.R;



/**
 * 
 *
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderResultService orderResultService;

    //根据demandId取消订单
    @GetMapping("/cancel_order_demand")
    public Object cancelOrderByDemandId(@RequestParam("demandId") Integer demandId) {
        orderService.cancelOrderByDemandId(demandId);
        return R.ok();
    }

    //根据需求id获取需求的订单
    @GetMapping("/list_demandId")
    public List<OrderVo> listByDemandId(@RequestParam("demandId") Integer demandId) {
        List<OrderVo> orderList = orderService.listByDemandId(demandId);
        return orderList;
    }
    //接需求
    @GetMapping("/sub_demand")
    public Object subDemand(@RequestParam("demandId") Integer demandId) {
        OrderEntity order = orderService.subDemand(demandId);
        return order==null? "{\"msg\":\"生成订单失败\"}" : order;
    }

    //完成需求 接单者发布状态,待发布者审核通过  待审核
    @PostMapping("/submit_result")
    public Object submitResult(@RequestBody OrderResultVo vo) {
        OrderResultEntity entity = orderResultService.createResult(vo, DemandConstant.OrderStatus.AUDIT.getCode());
        return entity==null ? "发布状态失效" : entity;
    }
    //未完成需求 接单者取消订单
    @PostMapping("/un_finish_result")
    public Object unFinishResult(@RequestBody OrderResultVo vo) {
        OrderResultEntity entity = orderResultService.createResult(vo, DemandConstant.OrderStatus.UNFINISHED.getCode());
        return entity==null ? "发布状态失效" : entity;
    }

    //查看我执行的订单
    @GetMapping("/order_list")
    public Object orderList(){
        List<OrderListVo> vos = orderService.orderList();
        return vos;
    }
    //审核订单
    @PostMapping("/audit")
    public Object audit(@RequestBody OrderAuditVo vo) {
        orderResultService.audit(vo);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);
        String name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(name);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
