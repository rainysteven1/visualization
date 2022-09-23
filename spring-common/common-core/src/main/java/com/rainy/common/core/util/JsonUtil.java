package com.rainy.common.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {
    /**
     * 初始化ObjectMapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    static {

        // 对于空的对象转json的时候不抛出错误
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 禁用序列化日期为timestamps
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 如果为空则不输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //返回的JSON字符串中含有我们并不需要的字段，那么当对应的实体类中不含有该字段时，
        //会抛出一个异常，告诉你有些字段没有在实体类中找到。解决办法，在声明ObjectMapper之后，加上下述代码
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * json时区设置函数
     *
     * @param timeZone
     * @return ObjectMapper objectMapper
     */
    public static ObjectMapper setTimeZone(TimeZone timeZone) {
        return mapper.setTimeZone(timeZone);

    }

    /**
     * object转java类型
     *
     * @param fromValue 被转化对象object
     * @param clazz     Java对象
     * @return ObjectMapper ObjectMapper对象
     */
    public static Object objectToList(Object fromValue, Class clazz) {

        if (fromValue == null) {
            return null;
        }
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        return mapper.convertValue(fromValue, javaType);
    }

    /**
     * object转json串
     *
     * @param object object对象
     * @return String json串
     * @throws JsonProcessingException
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("ObjectToJson 出错", e);
        }
        return null;

    }

    /**
     * json串转object
     *
     * @param content   json串
     * @param valueType class类
     * @return Object object对象
     */
    @SuppressWarnings("unchecked")
    public static Object jsonToObject(String content, Class valueType) {

        if (content == null || content.trim().equals("")) {
            return null;
        }
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            log.error("jsonToObject 出错", e);

        }
        return null;


    }

    /**
     * json串转map
     *
     * @param content json串
     * @param clazz   java类
     * @return Map map对象
     * @throws IOException
     */
    public static Map jsonToMap(String content, Class clazz) {

        if (content == null || content.trim().equals("")) {
            return null;
        }
        TypeFactory t = TypeFactory.defaultInstance();
        try {
            return mapper.readValue(content, t.constructMapType(Map.class, String.class, clazz));
        } catch (IOException e) {
            log.error("jsonToMap 出错", e);

        }
        return null;
    }

    /**
     * json串转list
     *
     * @param content json串
     * @param clazz   java类
     * @return List list对象
     * @throws IOException
     */
    public static List jsonToList(String content, Class clazz) {
        if (content == null || content.trim().equals("")) {
            return null;
        }

        try {

            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            return mapper.readValue(content, javaType);
        } catch (IOException e) {
            log.error("jsonToList 出错", e);

        }
        return null;


    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
