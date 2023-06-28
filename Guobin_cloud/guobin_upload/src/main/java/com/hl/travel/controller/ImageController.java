package com.hl.travel.controller;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.model.vo.Result;
import com.hl.travel.utils.B2Utils;
import com.hl.travel.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/image")

public class ImageController {


    @Autowired
    private JedisPool jedisPool;


    /**
     * 上传图片
     *
     * @param imgFile 图片文件
     * @param id      套餐id
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile, Integer id) {

        //获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        // 找到.最后出现的位置
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件后缀
        String suffix = originalFilename.substring(lastIndexOf);
        //使用UUID随机产生文件名称，防止同名文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        try {
            // 防止图片重复上传 新建时id为0 编辑时id为当前行id
            //id相同且hash相同则为重复上传 id不同则可以上传
            if (FileUtils.checkRedisFileHash(imgFile, id)) {
                return new Result(false, MessageConstant.PIC_RAPID_UPLOAD);
            }

            File tempFile = File.createTempFile("upload-", ".tmp");
            imgFile.transferTo(tempFile);

            // 在这里可以使用临时文件路径进行后续操作
            String tempFilePath = tempFile.getAbsolutePath();
            System.out.println("临时文件路径：" + tempFilePath);


            try {
                B2Utils.uploadFile(S3Constant.UPLOAD_FILE, S3Constant.BUCKET_NAME, tempFilePath, fileName);

                // 处理完临时文件后，手动删除
                if (tempFile.exists()) {
                    tempFile.delete();
                    System.out.println("临时文件已删除");
                }

            } catch (B2Exception e) {
                throw new RuntimeException(e);
            } catch (B2JsonException e) {
                throw new RuntimeException(e);
            }


            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);

            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);

            return result;
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
