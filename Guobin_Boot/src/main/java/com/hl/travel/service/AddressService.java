package com.hl.travel.service;


import com.hl.travel.model.pojo.Address;
import com.hl.travel.model.vo.PageResult;
import com.hl.travel.model.vo.QueryPageBean;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    PageResult findPage(QueryPageBean queryPageBean);

    void addAddress(Address address);

    void deleteById(Integer id);
}
