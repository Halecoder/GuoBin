package com.hl.travel.util;

import org.apache.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 深源恒际（DeepFinch）第三方短信发送
 */
public class SMSUtils {

    /**
     * 发送短信
     * @param phoneNumber 手机号码
     * @param code 4位或6位数字的验证码
     * @throws Exception
     */
    public static void sendShortMessage(String phoneNumber,String code) throws Exception {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        // 修改为自己购买的appcode
        String appcode = "9d4d61a023e5459d97dddeb6227360d8";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("phone_number", phoneNumber);
        bodys.put("template_id", "TPL_0000");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}