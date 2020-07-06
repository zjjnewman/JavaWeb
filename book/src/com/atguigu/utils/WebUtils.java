package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class WebUtils {

    public static <T> T copyParamToBean(Map map, T bean){

        try {
            BeanUtils.populate(bean, map);
            System.out.println("注入之后" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }



    public static void copyParamToBeanS(Map map, Object bean) {
        try {
            Iterator entryIte = map.entrySet().iterator();
            while (entryIte.hasNext()){
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entryIte.next();
                entry.getKey();
                try {
                    Field field = bean.getClass().getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(bean, ((String[])entry.getValue())[0]);
                } catch (NoSuchFieldException e){
                    continue;
                }
            }

            System.out.println("注入之后" + bean);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
