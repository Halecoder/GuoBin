package com.hl.travel.controller;

import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.pojo.TravelGroup;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.TravelGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 跟团游管理
 */
@RequestMapping("/travelGroup")
@RestController
@CrossOrigin
@Tag(name = "跟团游相关接口")
public class TravelGroupCotroller {

    @Autowired
    private   TravelGroupService travelGroupService;


    /**
     * 分页查询跟团游
     * @param queryPageBean 分页查询条件
     * @return
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelGroupService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    /**
     * 新增跟团游
     * @param travelGroup 旅游项目
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        System.out.println(travelGroup);
        try {
            travelGroupService.add(travelGroup,travelItemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }


    /**
     * 根据跟团游id查询跟团游
     * @param id 跟团游id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(Integer id){
        TravelGroup travelGroup =  travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelGroup);
    }

    /**
     * 根据跟团游id查询旅游项目id
     * @param id 跟团游id
     * @return
     */
    @GetMapping("/findTravelItemIdByTravelGroupId")
    public List<Integer> findTravelItemIdByTravelGroupId(Integer id){
        List<Integer> travelItemIds = travelGroupService.findTravelItemIdByTravelGroupId(id);
        return travelItemIds;
    }

    /**
     * 编辑跟团游
     * @param travelItemIds 旅游项目id
     * @param travelGroup  跟团游
     * @return
     */
    @PostMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup ){
        travelGroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }


    /**
     * 删除跟团游
     * @param id 跟团游id
     * @return
     */
    @GetMapping("/delete")
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
     * 查询所有跟团游
     * @return 查询所有跟团游
     */
    @GetMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> travelGroups = travelGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroups);
    }



}
