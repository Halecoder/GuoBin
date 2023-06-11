package com.hl.travel.constant;

public class RedisConstant {
    //套餐图片的所有图片名称（七牛）
    public static final String SETMEAL_PIC_RESOURCES = "setmealPicResources";
    //套餐图片保存在数据库中的图片名称（数据库）
    public static final String SETMEAL_PIC_DB_RESOURCES = "setmealPicDbResources";

    //套餐图片的所有图片名称（实现幂等性，防止重复提交）
    public static final String SETMEAL_PIC_UNIQUE_ID = "setmealPicUniqueId";
}
