package com.hl.travel.job;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.utils.B2Utils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class ClearImgJob extends QuartzJobBean {

    @Autowired
    private JedisPool jedisPool;

    ExecutorService service = Executors.newFixedThreadPool(5);


    //清理图片
    public void clearImg(){
        service.execute(() -> {

        });
    }


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
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