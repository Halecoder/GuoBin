package com.hl.travel.controller;


import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.Service.SetmealService;
import com.hl.travel.Service.TravelGroupService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisConstant;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.entity.Setmeal;
import com.hl.travel.entity.TravelGroup;
import com.hl.travel.util.B2Utils;
import com.hl.travel.util.FileUtils;
import com.hl.travel.util.QiniuUtils;
import com.hl.travel.vo.PageResult;
import com.hl.travel.vo.QueryPageBean;
import com.hl.travel.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;

    private final JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {

        //获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        // 找到.最后出现的位置
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件后缀
        String suffix = originalFilename.substring(lastIndexOf);
        //使用UUID随机产生文件名称，防止同名文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        try {

            // 防止图片重复上传
            if (FileUtils.checkRedisFileHash(imgFile)) {
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


    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;

    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] travelgroupIds) {
        try {
            setmealService.add(setmeal, travelgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 根据id查询套餐信息
     *
     * @param id 套餐id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);

    }

    /**
     * 根据套餐id查询跟团游的id
     *
     * @param id 套餐id
     * @return
     */
    @RequestMapping("/findTravelGroupIdsBySetmealId")
    public List<Integer> findTravelGroupIdsBySetmealId(Integer id) {
        List<Integer> travelGroupIds = setmealService.findTravelGroupIdsBySetmealId(id);
        return travelGroupIds;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] travelgroupIds) {
        try {
            setmealService.edit(setmeal, travelgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
