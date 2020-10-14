package com.xp.demand.controller;

import com.xp.common.utils.PageUtils;
import com.xp.common.utils.R;
import com.xp.common.vo.ChangeDemandStatusVo;
import com.xp.demand.entity.DemandEntity;
import com.xp.demand.service.DemandService;
import com.xp.demand.vo.PubDemandVo;
import com.xp.demand.vo.PubInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:06
 */
@RestController
@RequestMapping("demand/demand")
public class DemandController {
    @Autowired
    private DemandService demandService;


    //主页需求列表 分页,除自己发布的需求
    @GetMapping("/home_list")
    public Object homeList(@RequestParam Map<String, Object> params) {
        PageUtils page = demandService.homeList(params);
        return R.ok().put("data", page);
    }

    //发布需求
    @PostMapping("/pub_demand")
    public Object pubDemand(@RequestBody PubDemandVo vo) {
        DemandEntity demandEntity = demandService.pubDemand(vo);
        return demandEntity == null ? R.error(444, "余额不足") : R.ok().put("data", demandEntity);
    }

    //取消需求
    @PostMapping("/cancel_demand")
    public Object cancelDemand(@RequestParam Integer demandId) {
        DemandEntity demandEntity = demandService.cancelDemand(demandId);
        return demandEntity == null ? R.error().put("msg", "取消失败") : R.ok().put("msg", "取消成功");
    }

    //我的发布
    @GetMapping("/my_pub")
    public Object myPub() {
        List<PubInfoVo> vos = demandService.myPub();
        return vos;
    }

    //根据id获取demand
    @GetMapping("/demand_id")
    public Object demandById(@RequestParam("demandId") Integer demandId) {
        return demandService.getById(demandId);
    }

    //修改demand状态
    @PostMapping("/change_status")
    public Object changeStatus(@RequestBody ChangeDemandStatusVo vo) {
        Integer result = demandService.changeStatus(vo);
        return result;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = demandService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        DemandEntity demand = demandService.getById(id);

        return R.ok().put("demand", demand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DemandEntity demand) {
        demandService.save(demand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DemandEntity demand) {
        demandService.updateById(demand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        demandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
