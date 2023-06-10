package com.hl.travel.controller;


import com.hl.travel.Service.SetmealService;
import com.hl.travel.Service.TravelGroupService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.entity.Setmeal;
import com.hl.travel.entity.TravelGroup;
import com.hl.travel.vo.PageResult;
import com.hl.travel.vo.QueryPageBean;
import com.hl.travel.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;

    @RequestMapping("/upload")
    public void upload() {


    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] travelgroupIds) {
        try {
            setmealService.add(setmeal, travelgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 根据id查询套餐信息
     * @param id 套餐id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);

    }

    /**
     * 根据套餐id查询跟团游的id
     * @param id 套餐id
     * @return
     */
    @RequestMapping("/findTravelGroupIdsBySetmealId")
    public List<Integer> findTravelGroupIdsBySetmealId(Integer id) {
        List<Integer> travelGroupIds = setmealService.findTravelGroupIdsBySetmealId(id);
        return travelGroupIds;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] travelgroupIds) {
        try {
            setmealService.edit(setmeal, travelgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
