package com.labor.utils;

import java.util.Random;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 时间加上随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        //生成唯一的主键
        Random random=new Random();
        //生成六位数
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
