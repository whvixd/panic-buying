package com.github.whvixd.panic.buying.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wangzhx on 2018/7/26.
 */
@UtilityClass
public class FastJsonUtil {
    public static String toJson(Object object) {
        return JSONObject.toJSONString(object);
    }

    public static String toJsonWithNull(Object object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return JSONObject.parseObject(json, type,
                Feature.SupportNonPublicField, Feature.AllowComment, Feature.InitStringFieldAsEmpty);
    }

    /**
     * 支持范型json转成bean
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T formJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<T>() {
                }.getType(),
                Feature.SupportNonPublicField, Feature.AllowComment, Feature.InitStringFieldAsEmpty);
    }

    /**
     * String json = "[\"a\",\"b\",\"c\"]";
     * List<String> list = FastjsonUtil.fromArrayJson(json,String.class);
     *
     * @param arrayJson
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> fromArrayJson(String arrayJson, Class<T> clazz) {
        return JSONArray.parseArray(arrayJson, clazz);
    }

    /**
     *
     * @param arrayJson
     * @param type
     * @return
     */
    public static List fromArrayJson(String arrayJson, Type... type) {
        return JSONArray.parseArray(arrayJson, type);
    }
}
