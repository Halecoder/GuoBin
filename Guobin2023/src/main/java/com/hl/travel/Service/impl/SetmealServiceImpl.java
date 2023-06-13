package com.hl.travel.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hl.travel.Service.SetmealService;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.dao.SetmealDao;
import com.hl.travel.dao.TravelGroupDao;
import com.hl.travel.entity.Setmeal;
import com.hl.travel.entity.TravelGroup;
import com.hl.travel.vo.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SetmealServiceImpl implements SetmealService {

    private final SetmealDao setmealDao;


    private  final  JedisPool jedisPool;


    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 使用分页插件PageHelper（简化上面的操作）
        // 1：初始化分页操作
        PageHelper.startPage(currentPage, pageSize);
        // 2：使用sql语句进行查询（不必在使用mysql的limit了）
        Page<Setmeal> page = setmealDao.findPage(queryString);
        // 3：封装
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Setmeal setmeal, Integer[] travelgroupIds) {
        // 1：添加套餐
        setmealDao.add(setmeal);
        // 2：添加套餐和跟团游的关系

        addSetmealAndTravelGroup(setmeal.getId(), travelgroupIds);

        //将图片名称保存到Redis
        savePicToRedis(setmeal.getImg());


    }

    //将图片名称保存到Redis
    private void savePicToRedis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Integer> findTravelGroupIdsBySetmealId(Integer id) {

        return setmealDao.findTravelGroupIdsBySetmealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] travelgroupIds) {

        // 1：修改套餐
        setmealDao.edit(setmeal);
        // 2：删除旧的关系
        setmealDao.deleteSetmealAndTravelGroup(setmeal.getId());
        // 3：添加新的关系
        addSetmealAndTravelGroup(setmeal.getId(), travelgroupIds);

        //将图片名称保存到Redis
        savePicToRedis(setmeal.getImg());
    }

    @Override
    public void deleteById(Integer id) {
        // 1：删除套餐和跟团游的关系
        setmealDao.deleteSetmealAndTravelGroup(id);
        // 2：删除套餐
        setmealDao.deleteById(id);

    }

    @Override
    public List<Setmeal> findAll() {

       return setmealDao.findAll();

    }

    @Override
    public Setmeal findDescById(Integer id) {

//        自己封装很繁琐，需要对Setmeal一个个字段进行set，很麻烦，直接使用
//        mybatis进行封装


//        Setmeal setmeal = new Setmeal();
//
//        //中间表查出GroupIds
//      List<Integer> ids =  setmealDao.findTravelGroupIdsBySetmealId(id);
//
//      List<TravelGroup> travelGroups = new ArrayList<>();
//
//      for (Integer groupId : ids){
//          //根据Id查Group表详细信息
//          travelGroups.add(travelGroupDao.findById(groupId));
//      }
//      setmeal.setTravelGroups(travelGroups);

       return setmealDao.findDescById(id);
    }

    private void addSetmealAndTravelGroup(Integer id, Integer[] travelgroupIds) {
        if (travelgroupIds != null && travelgroupIds.length > 0) {
            for (Integer travelgroupId : travelgroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmealId", id);
                map.put("travelgroupId", travelgroupId);
                setmealDao.setSetmealAndTravelGroup(map);
            }
        }
    }
}
