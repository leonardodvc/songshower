package cn.com.lemont.songshower.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.*;

/**
 * @Project LiutaoProject
 * @Package cn.com.lemont.utils
 * @File PublicUtils.java
 * @Title 公共工具类
 * @Date 2020/10/12 10:35
 * @Description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @Author Liutao
 * @Version 1.0
 * @Copyright Copyright (c) 2020 六桃
 * @History 修订历史（历次修订内容、修订人、修订时间等）
 */
public class PublicUtils {

    /**
     * @Name getUUID
     * @Description 获取32位UUID
     * @Time 2020/11/6 17:24
     * @Param []
     * @Return java.lang.String
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * @Name isNotNull
     * @Description 判断对象是否不为空，如果不为空返回true，否则返回false
     * @Time 2020/10/12 15:08
     * @Param [obj]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static boolean isNotNull(Object obj) {
        boolean isNotNullFlag = true;
        if (isNull(obj)) {
            isNotNullFlag = false;
        }
        return isNotNullFlag;
    }

    /**
     * @Name isNull
     * @Description 判断对象是否为空，如果为空返回true，否则返回false
     * @Time 2020/10/12 10:58
     * @Param [obj]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static boolean isNull(Object obj) {
        boolean isNullFlag = true;
        if (obj != null) {
            if (obj instanceof List<?>) {
                isNullFlag = isNull((List<?>) obj);
            } else if (obj instanceof Set<?>) {
                isNullFlag = isNull((Set<?>) obj);
            } else if (obj instanceof Object[]) {
                isNullFlag = isNull((Object[]) obj);
            } else if (obj instanceof Map) {
                isNullFlag = isNull((Map) obj);
            } else if (obj instanceof String) {
                isNullFlag = isNull((String) obj);
            } else {
                isNullFlag = false;
            }
        }
        return isNullFlag;
    }

    /**
     * @Name isNull
     * @Description 判断列表是否为空
     * @Time 2020/10/12 10:59
     * @Param [list]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static boolean isNull(List<?> list) {
        return list == null || list.size() == 0;
    }

    /**
     * @Name isNull
     * @Description 判断列表是否为空
     * @Time 2020/10/12 10:59
     * @Param [set]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static boolean isNull(Set<?> set) {
        return set == null || set.size() == 0;
    }

    /**
     * @Name isNull
     * @Description 判断数组是否为空
     * @Time 2020/10/12 10:59
     * @Param [objects]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static boolean isNull(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * @Name isNull
     * @Description 判断Map是否为空
     * @Time 2020/10/12 10:59
     * @Param [map]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static boolean isNull(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * @Name isNull
     * @Description 判断字符串是否为空
     * @Time 2020/10/12 10:59
     * @Param [str]
     * @Return boolean
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim());
    }
}
