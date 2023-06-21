package com.hl.travel.service;



import com.hl.travel.model.vo.Result;

import java.util.Map;

public interface OrderMobileService {

    Result order(Map map) throws Exception;

    Map findByIdForDetail(Integer id) throws Exception;
}
