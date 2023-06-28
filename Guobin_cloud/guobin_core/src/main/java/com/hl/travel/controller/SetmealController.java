package com.hl.travel.controller;


import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.model.pojo.Setmeal;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.SetmealService;
import com.hl.travel.utils.B2Utils;
import com.hl.travel.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 套餐管理
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;



    /**
     * 分页查询套餐
     *
     * @param queryPageBean 分页查询条件
     * @return
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    /**
     * 新增套餐
     *
     * @param setmeal        套餐信息
     * @param travelgroupIds 跟团游id
     * @return
     */
    @PostMapping("/add")
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
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);

    }

    /**
     * 根据套餐id查询跟团游的id
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/findTravelGroupIdsBySetmealId")
    public List<Integer> findTravelGroupIdsBySetmealId(Integer id) {
        List<Integer> travelGroupIds = setmealService.findTravelGroupIdsBySetmealId(id);
        return travelGroupIds;
    }

    /**
     * 编辑套餐
     *
     * @param setmeal        套餐信息
     * @param travelgroupIds 跟团游id
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] travelgroupIds) {
        try {
            setmealService.edit(setmeal, travelgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    /**
     * 根据id删除套餐
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }


    /**
     * 移动端获取所有套餐
     *
     * @return 移动端获取数据
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal() {

        List<Setmeal> setmeals = setmealService.findAll();

        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, setmeals);

    }


    /**
     * 移动端根据id查询套餐详情
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/findDescById")
    public Result findDescById(Integer id) {
        //进行预约
        Setmeal setmeal = setmealService.findDescById(id);

        return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, setmeal);

    }


    /**
     *
     * @return 套餐统计
     */
    //Feign调用
    @RequestMapping("/api/findSetmealCount")
    public List<Map<String, Object>> findSetmealCount() {

        List<Map<String, Object>> list = setmealService.findSetmealCount();

        return list;

    }


}
