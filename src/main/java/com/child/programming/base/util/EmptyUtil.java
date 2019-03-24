package com.child.programming.base.util;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public class EmptyUtil {

    public static boolean arrayIsEmpty(Object[] array){
        return array == null || array.length == 0;
    }

    public static boolean listIsEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
