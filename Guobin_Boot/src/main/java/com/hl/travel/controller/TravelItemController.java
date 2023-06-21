package com.hl.travel.controller;

import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.pojo.TravelItem;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.TravelItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 自由行管理
 */
@CrossOrigin //跨域
@RestController
@RequestMapping(value = "/travelItem")
@Tag(name = "自由行相关接口")
public class TravelItemController {

    @Autowired
    private TravelItemService travelItemService;

    /**
     * 新增自由行
     * @param travelItem 旅游项目
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean 查询条件
     * @return 分页结果
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelItemService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    /**
     * 删除自由行
     * @param id 自由行id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Integer id) {
        try {
            travelItemService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (RuntimeException e) {
            // 运行时异常，表示自由行和跟团游的关联表中存在数据
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }


    /**
     * 编辑自由行
     * @param travelItem 旅游项目
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    /**
     * 根据id查询自由行 数据回显
     * @param id 自由行id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(Integer id){
        TravelItem travelItem =  travelItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
    }

    /**
     * 查询所有自由行
     * @return 查询所有自由行
     */
    @GetMapping("/findAllItem")
    public Result findAllItem(){
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItemService.findAllItem());
    }


}


