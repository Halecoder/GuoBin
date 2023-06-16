package com.hl.travel.Service;

import com.hl.travel.entity.Address;
import com.hl.travel.vo.PageResult;
import com.hl.travel.vo.QueryPageBean;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    PageResult findPage(QueryPageBean queryPageBean);

    void addAddress(Address address);

    void deleteById(Integer id);
}
