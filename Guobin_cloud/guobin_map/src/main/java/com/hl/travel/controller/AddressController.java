package com.hl.travel.controller;


import com.hl.travel.model.pojo.Address;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度地图地址信息
 * @Author: HL
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
//@Tag(name = "百度地图相关接口")
public class AddressController {

    @Autowired
    private  AddressService addressService;

    /**
     * 查询所有的地址信息
     * @return 返回所有的地址信息
     */
    @GetMapping("/findAllMaps")
    public Map findAll(){
        Map map=new HashMap();

        List<Address> addressList = addressService.findAll();

        //1、定义分店坐标集合
        List<Map> gridnMaps=new ArrayList<>();
        //2、定义分店名称集合
        List<Map> nameMaps=new ArrayList();
        for (Address address : addressList) {
            Map gridnMap=new HashMap();
            // 获取经度
            gridnMap.put("lng",address.getLng());
            // 获取纬度
            gridnMap.put("lat",address.getLat());
            gridnMaps.add(gridnMap);

            Map nameMap=new HashMap();
            // 获取地址的名字
            nameMap.put("addressName",address.getAddressName());
            nameMaps.add(nameMap);
        }
        // 存放经纬度
        map.put("gridnMaps",gridnMaps);
        // 存放名字
        map.put("nameMaps",nameMaps);
        return map;
    }

    /**
     * 地址分页查询
     * @param queryPageBean 地址分页查询
     * @return 返回分页结果
     *
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult=null;
        try{
            pageResult= addressService.findPage(queryPageBean);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResult;
    }

    /**
     * 添加地址
     * @param address 保存地址信息
     * @return
     */
    @PostMapping("/addAddress")
    public Result addAddress(@RequestBody Address address){
        //System.out.println(address.toString());
        addressService.addAddress(address);
        return new Result(true,"地址保存成功");
    }

    /**
     * 删除地址
     * @param id 根据id删除地址
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(Integer id){
        addressService.deleteById(id);
        return new Result(true,"已删除地址");
    }


}
