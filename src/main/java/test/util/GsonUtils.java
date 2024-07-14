package test.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


/**
 * @author mapengfei <mapengfei@kuaishou.com>
 * Created on 2019-10-25
 */
public class GsonUtils {

    private static final Gson GSON;

    static {
        GSON = new Gson();
    }

    private GsonUtils() {
        // do nothing
    }

    /**
     * 序列化为json
     */
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    /**
     * collection序列化为json
     */
    public static String toJson(Collections collection){
        return GSON.toJson(collection);
    }

    /**
     * 反序列化为对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return GSON.fromJson(jsonStr, clazz);
    }

    public static <T> Map<String, T> fromJsonToMap(String jsonStr) {
        Map<String, T> map = null;
        if (jsonStr != null) {
            map = GSON.fromJson(jsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 反序列化为list对象
     */
    public static <T> List<T> fromJsonToList(String jsonStr, Class<T> clazz) {
        List<JsonObject> jsonList = GSON.fromJson(jsonStr, new TypeToken<List<JsonObject>>() {}.getType());
        if (!CollectionUtils.isEmpty(jsonList)) {
            return jsonList.stream().map(str -> GSON.fromJson(str, clazz)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

