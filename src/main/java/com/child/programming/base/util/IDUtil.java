package com.child.programming.base.util;

import java.util.Random;

public class IDUtil {
    /**
     * 生成随机图片名,小程序头像名称
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * 生成随机分享码后缀
     */
    public static String getLastShareCode() {
        //加上四位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足四位前面补0
        String str = String.format("%04d", end4);
        return str;
    }
}
