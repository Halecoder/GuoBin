package com.hl.travel.dao;

import com.github.pagehelper.Page;
import com.hl.travel.entity.Address;

import java.util.List;

public interface AddressDao {


    void deleteById(Integer id);

    void addAddress(Address address);

    List<Address> findAll();

    Page<Address> selectByCondition(String queryString);
}
