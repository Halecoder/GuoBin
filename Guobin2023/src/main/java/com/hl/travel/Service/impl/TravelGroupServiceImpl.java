package com.hl.travel.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hl.travel.Service.TravelGroupService;
import com.hl.travel.dao.TravelGroupDao;
import com.hl.travel.entity.TravelGroup;
import com.hl.travel.entity.TravelItem;
import com.hl.travel.vo.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TravelGroupServiceImpl implements TravelGroupService {

    private final TravelGroupDao travelGroupDao;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 使用分页插件PageHelper（简化上面的操作）
        // 1：初始化分页操作
        PageHelper.startPage(currentPage,pageSize);
        // 2：使用sql语句进行查询（不必在使用mysql的limit了）
        Page<TravelGroup> page = travelGroupDao.findPage(queryString);
        // 3：封装
        return new PageResult(page.getTotal(),page.getResult());
    }


    @Override
    public void add(TravelGroup travelGroup,Integer[] travelItemIds) {
        // 1 新增跟团游，向t_travelgroup中添加数据，新增后返回新增的id
        travelGroupDao.add(travelGroup);
        // 2 新增跟团游和自由行中间表t_travelgroup_travelitem新增数据(新增几条，由travelItemIds决定)
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupDao.findById(id);
    }

    @Override
    public List<Integer> findTravelItemIdByTravelGroupId(Integer id) {
        return travelGroupDao.findTravelItemIdByTravelGroupId(id);

    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);

        // 1：删除中间表数据
        travelGroupDao.deleteTravelGroupAndTravelItem(travelGroup.getId());
        // 2：重新添加中间表数据
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public void deleteById(Integer id) {

        travelGroupDao.deleteTravelGroupAndTravelItem(id);
        travelGroupDao.deleteById(id);

    }

    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        // 新增几条数据，由travelItemIds的长度决定
        if (travelItemIds!=null && travelItemIds.length>0){
            for (Integer travelItemId : travelItemIds) {
                // 如果有多个参数使用map
                Map<String, Integer> map = new HashMap<>();
                map.put("travelGroup",travelGroupId);
                map.put("travelItem",travelItemId);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }
}
