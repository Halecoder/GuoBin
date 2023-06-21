package com.hl.travel.service.impl;


import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.dao.MemberDao;
import com.hl.travel.model.dao.OrderMobileDao;
import com.hl.travel.model.dao.OrderSettingDao;
import com.hl.travel.model.pojo.Member;
import com.hl.travel.model.pojo.Order;
import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderMobileService;
import com.hl.travel.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service

public class OrderMobileServiceImpl implements OrderMobileService {

    @Autowired
    private OrderMobileDao orderMobileDao;

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;


    @Override
    public Result order(Map map) throws Exception {
        //1.检查当前日期是否进行了预约设置
        String orderDate = (String) map.get("orderDate");
        // 因为数据库预约设置表里面的时间是date类型，http协议传递的是字符串类型，所以需要转换
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(orderDate);
        if (orderSetting == null) {
            //当前日期没有进行预约设置，无法完成体检预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //2.检查当前日期是否预约已满
        int number = orderSetting.getNumber();//可预约人数
        int reservations = orderSetting.getReservations();//已预约人数
        if (reservations >= number) {
            //已经预约满了，不能预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        //3.检查用户是否重复预约（同一个用户在同一天预约了同一个套餐）
        String telephone = (String) map.get("telephone");//获取用户手机号
        Member member = memberDao.findByTelephone(telephone);//根据手机号查询会员信息
        if (member != null) {
            //判断是否在同一天预约了同一个套餐
            Integer memberId = member.getId();//会员id
            Integer setmealId = Integer.parseInt((String) map.get("setmealId"));//套餐id
            Order order = new Order(memberId, date, null, null, setmealId);
            List<Order> list = orderMobileDao.findByCondition(order);
            if (list != null && list.size() > 0) {
                //说明用户在重复预约，不能重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }else{
           // 如果不是会员：注册会员，向会员表中添加数据
            member = new Member();
            member.setName((String)map.get("name"));
            member.setSex((String)map.get("sex"));
            member.setPhoneNumber((String)map.get("telephone"));
            member.setIdCard((String)map.get("idCard"));
            member.setRegTime(new Date()); // 会员注册时间，当前时间
            memberDao.add(member);

        }

        //4.可以预约，设置预约人数加一
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        //5.保存预约信息到预约表
        Order order = new Order(member.getId(), date, (String)map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String)map.get("setmealId")));
        orderMobileDao.add(order);
        return new Result(true, MessageConstant.ORDER_SUCCESS,order);


    }

    @Override
    public Map findByIdForDetail(Integer id) throws Exception {
        Map map = orderMobileDao.findByIdForDetail(id);
        if(map != null){
                //处理日期格式
                Date orderDate = (Date) map.get("orderDate");
                map.put("orderDate", DateUtils.parseDate2String(orderDate));
                return map;
            }
        return map;
    }
}
