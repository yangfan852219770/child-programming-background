package com.child.programming.base.util;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Log4j2
public class ListUtil {

    /**
     * 转换list中的元素
     * @param sourceList 原list集合
     * @param targetClazz 目标clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertElement(List sourceList, Class<T> targetClazz){
       if (!EmptyUtils.listIsEmpty(sourceList)){
           List<T> targetList = new ArrayList<>();

           for (Object sourceObject:sourceList
                ) {
               try {
                   T t = targetClazz.newInstance();
                   BeanUtils.copyProperties(sourceObject, t);
                   targetList.add(t);
               } catch (Exception e) {
                   e.printStackTrace();
                   log.error("There are some errors in converting sourceOject to targetObject!");
                   return null;
               }
           }
           return targetList;
       }
       return null;

    }

    /**
     * 将string数组转化为Integer的list
     * @return
     */
    public static List<Integer> stringArrayToIntegerList(String[] strings){
        if (EmptyUtils.arrayIsEmpty(strings))
            return null;
        List<Integer> list = new ArrayList<>();
        for (String str:strings
             ) {
            try{
                list.add(Integer.parseInt(str));
            }catch (Exception e){
                e.printStackTrace();
                log.error("There are some errors in converting String to Integer");
                return null;
            }
        }
        return list;
    }
}
