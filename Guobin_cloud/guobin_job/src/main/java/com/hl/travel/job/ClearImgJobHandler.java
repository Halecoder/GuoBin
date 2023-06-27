package com.hl.travel.job;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.utils.B2Utils;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

@Component
@Slf4j
public class ClearImgJobHandler {

    @Autowired
    private JedisPool jedisPool;
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("ClearImgJobHandler")
    public void demoJobHandler() throws Exception {
        // 在此处编写您的任务逻辑
        System.out.println("执行定时任务");
        //计算redis中两个集合的差值，获取垃圾图片名称
        // 需要注意：在比较的时候，数据多的放到前面，如果pic多，那么pic放到前面，db多，db放到前面
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String pic = iterator.next();
            System.out.println("删除图片的名称是：" + pic);
            //删除图片服务器中的图片文件
            try {
                B2Utils.deleteFile(S3Constant.DELETE_FILE, S3Constant.BUCKET_NAME, pic);
            } catch (B2Exception e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (B2JsonException e) {
                throw new RuntimeException(e);
            }
            //删除redis中的数据
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, pic);
        }
    }

}
