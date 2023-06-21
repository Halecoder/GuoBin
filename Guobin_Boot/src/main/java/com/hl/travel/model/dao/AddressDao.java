package com.hl.travel.model.dao;

import com.github.pagehelper.Page;
import com.hl.travel.model.pojo.Address;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AddressDao {


    void deleteById(Integer id);

    void addAddress(Address address);

    List<Address> findAll();

    Page<Address> selectByCondition(String queryString);
}
