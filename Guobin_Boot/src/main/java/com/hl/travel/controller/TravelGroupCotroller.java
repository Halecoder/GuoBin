package com.hl.travel.controller;

import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.pojo.TravelGroup;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.TravelGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/travelGroup")
@RestController
@CrossOrigin
public class TravelGroupCotroller {

    @Autowired
    private   TravelGroupService travelGroupService;




    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelGroupService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    /**
     * 新增跟团游
     *
     * @param travelGroup 旅游项目
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        System.out.println(travelGroup);
        try {
            travelGroupService.add(travelGroup,travelItemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }


    @RequestMapping("/findById")
    public Result findById(Integer id){
        TravelGroup travelGroup =  travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelGroup);
    }

    @RequestMapping("/findTravelItemIdByTravelGroupId")
    public List<Integer> findTravelItemIdByTravelGroupId(Integer id){
        List<Integer> travelItemIds = travelGroupService.findTravelItemIdByTravelGroupId(id);
        return travelItemIds;
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup ){
        travelGroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }


    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            travelGroupService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (RuntimeException e) {
            // 运行时异常，表示自由行和跟团游的关联表中存在数据
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

    /**
     * @return 查询所有跟团游
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> travelGroups = travelGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroups);
    }



}
