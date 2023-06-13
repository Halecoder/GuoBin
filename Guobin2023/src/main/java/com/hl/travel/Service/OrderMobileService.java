package com.hl.travel.Service;

import com.hl.travel.vo.Result;

import java.util.Map;

public interface OrderMobileService {

    Result order(Map map) throws Exception;

    Map findByIdForDetail(Integer id) throws Exception;
}
