package com.mmall.util;

import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 18-8-19.
 */

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空BEAN转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        //所有的日期可是都同意成以下的样式即：yyyy-mm-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));


        //反序列化
        //忽略在json字符串中存在，但是在java对象中不存在的对应的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj to String error" + e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj to String error" + e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class)? (T)str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("String to Obj error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("String to Obj error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("String to Obj error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(100);
        user1.setPhone("12345678900");

        User user2 = new User();
        user2.setId(100);
        user2.setPhone("12345678900");

        String userJson = JsonUtil.obj2String(user1);
        String userJsonPretty = JsonUtil.obj2StringPretty(user1);
        log.info("json:{}", userJson);
        log.info("jsonPretty:{}", userJsonPretty);

        User u2 = JsonUtil.string2Obj(userJson, User.class);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        String userListJson = JsonUtil.obj2StringPretty(userList);
        log.info("userListJson :{}", userListJson);

        List<User> userList1 = JsonUtil.string2Obj(userListJson, new TypeReference<List<User>>() {
        });


        List<User> userList2 = JsonUtil.string2Obj(userListJson, List.class, User.class);
        System.out.printf("end");
    }
}
