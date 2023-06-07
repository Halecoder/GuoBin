package com.hl.travel.controller;

import com.hl.travel.Service.TravelItemService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.entity.TravelItem;
import com.hl.travel.vo.PageResult;
import com.hl.travel.vo.QueryPageBean;
import com.hl.travel.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //跨域
@RestController
@RequestMapping(value = "/travelItem")
public class TravelItemController {

    @Autowired
    private TravelItemService travelItemService;

    /**
     * 新增自由行
     *
     * @param travelItem 旅游项目
     * @return
     */
    @RequestMapping("/add")
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
     *
     * @param queryPageBean 查询条件
     * @return 分页结果
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelItemService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    /**
     * 删除自由行
     *
     * @param id 自由行id
     * @return
     */
    @RequestMapping("/delete")
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
    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    /**
     * 根据id查询自由行 数据回显
     * @param id 自由行id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        TravelItem travelItem =  travelItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
    }


}


