package com.hl.travel.utils;

import com.hl.travel.constant.RedisConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件工具类
 * 注意：因为Jedis需要Spring IOC容器管理(IOC管理的Jedis已经配置好了连接池，端口，URL等等，具体在XML文件)，
 * 所以不能使用new Jedis()的方式创建Jedis对象
 * 此类既不是Controller也不是Service，所以使用@Component注解，才能被IOC容器管理，才能注入JedisPool
 */

@Component
public  class FileUtils {


    // 获取文件Hash值
    public static String calculateFileHash(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] fileBytes = file.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(fileBytes);
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }


    //查看Redis是否存在该文件Hash值
    public static boolean checkRedisFileHash(MultipartFile imgFile,Integer id) throws IOException, NoSuchAlgorithmException {

        String hash = FileUtils.calculateFileHash(imgFile);


        //唯一的key
        String IdKey = RedisConstant.SETMEAL_PIC_UNIQUE_ID;
        String HashKey = RedisConstant.SETMEAL_PIC_HASH_ID;

        //如果Redis没有存该Key,进行存储
        if (!(RedisUtils.getJedisPool().getResource().exists(HashKey))) {
            // 创建一个String
            RedisUtils.getJedisPool().getResource().set(HashKey, hash);
            //创建唯一标识Id
            RedisUtils.getJedisPool().getResource().set(IdKey, String.valueOf(id));
            return false;

        } else {

            //检查Hash值是否相同
            if (RedisUtils.getJedisPool().getResource().get(HashKey).equals(hash)&&RedisUtils.getJedisPool().getResource().get(IdKey).equals(String.valueOf(id))) {
                return true;
            } else {
                //如果不相同,进行更新
                RedisUtils.getJedisPool().getResource().set(HashKey, hash);
                RedisUtils.getJedisPool().getResource().set(IdKey, String.valueOf(id));
                return false;
            }
        }


    }

}
