package com.heliang.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson 工具类
 */
public class MapperUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 转换为 JSON 字符串
     *
     * @param obj Object
     * @return Json
     * @throws JsonProcessingException 转换异常
     */
    public static String obj2json(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 转换为 JSON 字符串，忽略空值
     *
     * @param obj Object
     * @return Json
     * @throws JsonProcessingException 转换异常
     */
    public static String obj2jsonIgnoreNull(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * Json 转换为 JavaBean
     *
     * @param jsonString Json
     * @param clazz Class
     * @return JavaBean
     * @throws JsonProcessingException 转换异常
     */
    public static <T> T json2pojo(String jsonString, Class<T> clazz) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * 字符串转换为 Map
     *
     * @param jsonString Json
     * @return Map
     * @throws JsonProcessingException 转换异常
     */
    public static Map<String, Object> json2map(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * 字符串转换为 Map
     *
     * @param jsonString Json
     * @param clazz Class
     * @param <T> 泛型
     * @return Map
     * @throws JsonProcessingException 转换异常
     */
    public static<T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws JsonProcessingException {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Map<String, Object>>>() {});
        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * 深度转换 JSON 成 Map
     *
     * @param json Json
     * @return Map
     * @throws JsonProcessingException 转换异常
     */
    public static Map<String, Object> json2mapDeeply(String json) throws JsonProcessingException {
        return json2MapRecursion(json);
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     *
     * @param json Json
     * @return List
     * @throws JsonProcessingException 转换异常
     */
    private static List<Object> json2ListRecursion(String json) throws JsonProcessingException {
        if (json == null) {
            return null;
        }

        List<Object> list = objectMapper.readValue(json, new TypeReference<List<Object>>() {});

        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    list.set(i, json2ListRecursion(str));
                } else if (obj.toString().startsWith("{")) {
                    list.set(i, json2MapRecursion(str));
                }
            }
        }

        return list;
    }

    /**
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     *
     * @param json Json
     * @return Map
     * @throws JsonProcessingException 转换异常
     */
    private static Map<String, Object> json2MapRecursion(String json) throws JsonProcessingException {
        if (json == null) {
            return null;
        }

        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }

    /**
     * 将 JSON 转换为 List
     *
     * @param jsonString Json
     * @param clazz Class
     * @return List
     * @throws JsonProcessingException 转换异常
     */
    public static<T> List<T> json2list(String jsonString, Class<T> clazz) throws JsonProcessingException {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        return objectMapper.readValue(jsonString, javaType);
    }


    /**
     * 获取泛型的 Collection Type
     *
     * @param collectionClass 泛型的 Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将 Map 转换为 JavaBean
     *
     * @param map Map
     * @param clazz Class
     * @return JavaBean
     */
    public static <T> T map2pojo(Map<String, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 将 Map 转换为 JSON
     *
     * @param map Map
     * @return Json
     */
    public static String mapToJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将 Object 对象转换为 JavaBean
     *
     * @param obj Object
     * @param clazz Class
     * @return JavaBean
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    /**
     * 将指定节点的 JSON 数据转换为 JavaBean
     *
     * @param jsonString Json
     * @param clazz Class
     * @return JavaBean
     * @throws JsonProcessingException 转换异常
     */
    public static<T> T json2pojoByTree(String jsonString, String treeNode, Class<T> clazz) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode data = jsonNode.findPath(treeNode);
        return json2pojo(data.toString(), clazz);
    }

    /**
     * 将指定节点的 JSON 数组转换为集合
     *
     * @param jsonStr  JSON 字符串
     * @param treeNode 查找 JSON 中的节点
     * @return JavaBean
     * @throws JsonProcessingException 转换异常
     */
    public static<T> List<T> json2listByTree(String jsonStr, String treeNode, Class<T> clazz) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        JsonNode data = jsonNode.findPath(treeNode);
        return json2list(data.toString(), clazz);
    }
}
