package com.xp.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xp.order.entity.OrderResultEntity;
import com.xp.order.service.OrderResultService;
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
@RequestMapping("order/orderresult")
public class OrderResultController {
    @Autowired
    private OrderResultService orderResultService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderResultService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		OrderResultEntity orderResult = orderResultService.getById(id);

        return R.ok().put("orderResult", orderResult);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderResultEntity orderResult){
		orderResultService.save(orderResult);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderResultEntity orderResult){
		orderResultService.updateById(orderResult);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		orderResultService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
