package com.hl.travel.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hl.travel.Service.AddressService;
import com.hl.travel.dao.AddressDao;
import com.hl.travel.entity.Address;
import com.hl.travel.vo.PageResult;
import com.hl.travel.vo.QueryPageBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Override
    public List<Address> findAll() {

       return addressDao.findAll();
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Address> page = addressDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void addAddress(Address address) {
        addressDao.addAddress(address);

    }

    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);
    }
}
