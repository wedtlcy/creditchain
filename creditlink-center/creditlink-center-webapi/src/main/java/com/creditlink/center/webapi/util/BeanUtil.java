package com.creditlink.center.webapi.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Bean工具
 * 
 * @version 2017年12月8日下午1:09:39
 * @author wuliu
 */
public class BeanUtil {
    
    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);
    
    /**
     * 简单Bean属性拷贝
     * 
     * @version 2017年12月8日下午1:09:31
     * @author wuliu
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
    
    /**
     * 简单Bean属性拷贝，返回目标对象实例
     * 
     * @version 2017年12月8日下午1:09:48
     * @author wuliu
     * @param source
     * @param targetClass
     * @return
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * List对象拷贝
     * 
     * @version 2017年12月8日下午1:10:04
     * @author wuliu
     * @param sourceList
     * @param targetClass
     * @return
     */
    public static <T> List<T> copy(List<? extends Object> sourceList, Class<T> targetClass) {
        try {
            List<T> targetList = new ArrayList<T>();
            for (Object source : sourceList) {
                T target = targetClass.newInstance();
                BeanUtils.copyProperties(source, target);
                targetList.add(target);
            }
            return targetList;
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 使用Introspector进行转换
     * 
     * @version 2017年12月8日下午1:12:30
     * @author wuliu
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObjectByIntrospector(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        
        return obj;
    }
    
    /**
     * 使用Introspector进行转换
     * 
     * @version 2017年12月8日下午1:12:46
     * @author wuliu
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMapByIntrospector(Object obj) throws Exception {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }
    
    /**
     * 使用reflect进行转换
     * 
     * @version 2017年12月8日下午1:13:29
     * @author wuliu
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObjectByReflect(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;
        
        Object obj = beanClass.newInstance();
        
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        
        return obj;
    }
    
    /**
     * 使用reflect进行转换
     * 
     * @version 2017年12月8日下午1:13:37
     * @author wuliu
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMapByReflect(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        
        return map;
    }

    /**
     * map-reqVo
     * 
     * @version 2018年1月10日上午11:28:45
     * @author wuliu
     * @param map
     * @param class1
     * @return
     */
    public static Object mapToReqVo(Map<String, Object> map,
            Class<?> beanClass) {
        try {
            if(map == null){
                return null;
            }
            String jsonStr = JSONObject.toJSONString(map);
            if(StringUtils.isBlank(jsonStr)){
                return null;
            }
            Object obj = JSONObject.parseObject(jsonStr, beanClass);
            return obj;
        }
        catch (Throwable e) {
            log.error("转换异常",e);
            return null;
        }
    }
}
