package com.ochprince.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * JSON Common Tools
 *
 * <p>Copyright OchPrince 2019 Co.,Ltd.</p>
 *
 * @author shilong.jiang
 * @version 1.0.0.RELEASE
 * @since CreatedAt 2019-07-04 14:55:51
 */
public class JSONUtil {

    /**
     * use toJSONString to copy JSON object.<br><br>
     * <strong>Note: </strong>the copied objects and subObjects all become JSON objects.
     *
     * @param json class extends {@link JSON}
     * @return json
     * @author shilong.jiang
     */
    @SuppressWarnings(value = "unchecked")
    public static <T extends JSON> T copy(T json) {
        if (json == null) {
            return null;
        }
        if (json instanceof JSONObject) {
            return (T) JSON.parseObject(json.toJSONString());
        } else {
            return (T) JSON.parseArray(json.toJSONString());
        }
    }

    /**
     * use clone to copy JSON object.<br><br>
     * <strong>Note: </strong>it needs all subObjects implement {@link Cloneable}, otherwise it doesn't deep copy.
     *
     * @param json class extends {@link JSON}
     * @return json
     * @author shilong.jiang
     */
    @SuppressWarnings(value = "unchecked")
    public static <T extends JSON> T clone(T json) {
        if (json == null) {
            return null;
        }
        if (json instanceof JSONObject) {
            return (T) ((JSONObject) json).clone();
        } else {
            return (T) ((JSONArray) json).clone();
        }
    }

    /**
     * Returns true if the two objects are equal to each other and false otherwise. <br>
     * Consequently, if both arguments are null, true is returned and if exactly one argument is null, false is returned. <br>
     * Otherwise, equality is determined by comparing all subObjects.<br>
     *
     * @param jo  {@link JSONObject}
     * @param jo1 {@link JSONObject}
     * @return Returns true if the two objects are equal to each other and false otherwise.
     * @author shilong.jiang
     */
    public static boolean equals(JSONObject jo, JSONObject jo1) {
        if (Objects.equals(jo, jo1)) {
            return true;
        }
        return jo.keySet().stream().allMatch(key -> equals(jo.get(key), jo1.get(key)));
    }

    /**
     * Returns true if the two objects are equal to each other and false otherwise. <br>
     * Consequently, if both arguments are null, true is returned and if exactly one argument is null, false is returned. <br>
     * Otherwise, equality is determined by comparing all subObjects.<br>
     *
     * @param ja  {@link JSONArray}
     * @param ja1 {@link JSONArray}
     * @return Returns true if the two objects are equal to each other and false otherwise.
     * @author shilong.jiang
     */
    public static boolean equals(JSONArray ja, JSONArray ja1) {
        if (Objects.equals(ja, ja1)) {
            return true;
        }
        if (ja.size() != ja1.size()) {
            return false;
        }
        return ja.stream().allMatch(aja -> contains(ja1, aja))
                && ja1.stream().allMatch(aja -> contains(ja, aja));
    }

    /**
     * Returns true if this JSONArray contains the specified element.
     *
     * @param ja {@link JSONArray}
     * @param o  {@link Object}
     * @return Returns true if this JSONArray contains the specified element.
     * @author shilong.jiang
     */
    public static boolean contains(JSONArray ja, Object o) {
        if (ja == null) {
            return false;
        } else if (o == null) {
            return ja.stream().anyMatch(Objects::isNull);
        } else {
            return ja.stream().anyMatch(aja1 -> equals(o, aja1));
        }
    }

    /**
     * Returns true if the two objects are equal to each other and false otherwise. <br>
     * Consequently, if both arguments are null, true is returned and if exactly one argument is null, false is returned. <br>
     * Otherwise, equality is determined by comparing all subObjects.<br>
     *
     * @param o  {@link Object}
     * @param o1 {@link Object}
     * @return Returns true if the two objects are equal to each other and false otherwise.
     * @author shilong.jiang
     */
    public static boolean equals(Object o, Object o1) {
        if (o == null || o1 == null) {
            return o == o1;
        } else if (o.getClass() != o1.getClass()) {
            return false;
        } else if (o instanceof JSONObject) {
            return equals((JSONObject) o, (JSONObject) o1);
        } else if (o instanceof JSONArray) {
            return equals((JSONArray) o, (JSONArray) o1);
        } else {
            return Objects.equals(o, o1);
        }
    }

    /**
     * Merge second {@link JSONObject} to first {@link JSONObject}.<br>
     * If there has some keys between two objects are equal to each other, use {@link BiFunction} to deal with.<br>
     * Unless the value type both is jsonObject or jsonArray
     *
     * @param jo1 {@link JSONObject}
     * @param jo2 {@link JSONObject}
     * @param bfn {@link BiFunction}
     * @author shilong.jiang
     */
    public static void merge(JSONObject jo1, JSONObject jo2, BiFunction<? super Object, ? super Object, ?> bfn) {
        if (jo2 == null || jo1 == null) {
            return;
        }
        for (String key : jo2.keySet()) {
            Object v2 = jo2.get(key);
            jo1.merge(key, v2, (newV, oldV) -> {
                if (newV instanceof JSONObject && oldV instanceof JSONObject) {
                    merge((JSONObject) newV, (JSONObject) oldV, bfn);
                    return newV;
                } else if (newV instanceof JSONArray && oldV instanceof JSONArray) {
                    return ((JSONArray) newV).addAll((JSONArray) oldV);
                } else {
                    return bfn.apply(newV, oldV);
                }
            });
        }
    }

    /**
     * Get the value from {@link JSONObject} by using a string of keys concatenated with dot.<br><br>
     * <p>
     * <strong>Note: </strong>if the value is an Array, it can indicate an index to get the element.
     *
     * @param jo        {@link JSONObject}
     * @param keys      a string of keys concatenated with dot. for example: "aggregations.infos[2].src"
     * @param elseValue the value to be returned if there is no value present, may be null.
     * @return Return the value if present, otherwise return elseValue.
     * @author shilong.jiang
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static <T> T getJSONValue(JSONObject jo, String keys, T elseValue) {
        Object value = getJSONValue(jo, keys);
        if (value == null) {
            return elseValue;
        } else {
            return (T) value;
        }
    }

    /**
     * Get the value from {@link JSONObject} by using a string of keys concatenated with dot.<br><br>
     * <p>
     * <strong>Note: </strong>if the value is an Array, it can indicate an index to get the element.
     *
     * @param jo   {@link JSONObject}
     * @param keys a string of keys concatenated with dot. for example: "aggregations.infos[2].src"
     * @return Return the value if present, otherwise return null.
     * @author shilong.jiang
     */
    public static Object getJSONValue(JSONObject jo, String keys) {
        String[] splits;
        if (jo == null
                || jo.isEmpty()
                || keys == null
                || (splits = keys.split("\\.")).length < 1) {
            return null;
        }
        String split = splits[0];
        int index = getIndexNum(split);

        try {
            String key = split.indexOf("[") > 0 ? split.substring(0, split.lastIndexOf("[")) : split;
            Object subObj = jo.get(key);

            if (splits.length == 1) {
                return subObj;
            }

            keys = keys.substring(keys.indexOf(".") + 1, keys.length());
            //get value from JSONObject by using recursion.
            if (subObj instanceof JSONObject) {
                return getJSONValue((JSONObject) subObj, keys);
            }
            //get value from JSONArray by using recursion.
            if (subObj instanceof JSONArray) {
                return getJSONValue(((JSONArray) subObj).getJSONObject(index), keys);
            }
        } catch (IndexOutOfBoundsException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Set value into {@link JSONObject} by using recursion.
     *
     * @param jo    {@link JSONObject}
     * @param keys  a string of keys concatenated with dot. for example: "aggregations.infos[2].src"
     * @param value the value {@link Object}
     * @return new {@link JSONObject}
     * @author shilong.jiang
     */
    public static JSONObject setJSONValue(JSONObject jo, String keys, Object value) {
        String[] fields = keys.split("\\.");
        List<String> fieldsArr = Arrays.asList(fields);
        String field = fieldsArr.get(fieldsArr.size() - 1);
        if (fieldsArr.size() == 1) {
            jo.put(field, value);
            return jo;
        } else {
            //get root elements.
            String root = keys.substring(0, keys.indexOf("."));
            JSONObject newJson = jo.getJSONObject(root);
            if (newJson == null) {
                newJson = new JSONObject();
            }
            //get keys of sub elements.
            keys = keys.substring(keys.indexOf(".") + 1, keys.length());
            //set value by using recursion.
            jo.put(root, setJSONValue(newJson, keys, value));
            return jo;
        }
    }

    /**
     * Convert a {@link JSONArray} to a {@link JSONObject} by combining the value in JSONArray.<br>
     * Example: [{"key":"event_list","doc_count":4852},{"key":"blocking_log","doc_count":29}].<br>
     * After converted: {"event_list":4852,"blocking_log":29}.<br><br>
     * <strong>Note: </strong>if the value for keyName is not unique throughout the JSONArray, the value for valueName will be overridden.
     *
     * @param ja        a {@link JSONArray} which contains elements of {@link JSONObject}.
     * @param keyName   indicate which field mapping to key of JSONObject. Just like 'event_list' in the example.
     * @param valueName indicate which field mapping to value of JSONObject. Just like 'doc_count' in the example.
     *                  It support a string of keys concatenated with dot to find the sub elements.<br>
     *                  <strong>Note: </strong>if the value for doc_count is not present, the element itself will be treated as value.
     * @return new {@link JSONObject}
     * @author shilong.jiang
     */
    public static JSONObject toJo(JSONArray ja, String keyName, String valueName) {
        return toJo(ja, keyName, valueName, o -> o);
    }

    /**
     * Convert a {@link JSONArray} to a {@link JSONObject} by combining the value in JSONArray.<br>
     * Example: [{"key":"event_list","doc_count":4852},{"key":"blocking_log","doc_count":29}].<br>
     * After converted: {"event_list":4852,"blocking_log":29}.<br><br>
     * <strong>Note: </strong>if the value for keyName is not unique throughout the JSONArray, the value for valueName will be overridden.
     *
     * @param ja          a {@link JSONArray} which contains elements of {@link JSONObject}.
     * @param keyName     indicate which field mapping to key of JSONObject. Just like 'event_list' in the example.
     * @param valueName   indicate which field mapping to value of JSONObject. Just like 'doc_count' in the example.
     *                    It support a string of keys concatenated with dot to find the sub elements.<br>
     *                    <strong>Note: </strong>if the value for doc_count is not present, the element itself will be treated as value.
     * @param reMappingFn the mapping function used to deal with the value. For example, convert to UpperCase and so on.
     * @return new {@link JSONObject}
     * @author shilong.jiang
     */
    public static <T> JSONObject toJo(JSONArray ja, String keyName, String valueName, Function<Object, T> reMappingFn) {
        return toJo(ja, jo -> jo.getString(keyName), jo -> {
            Object oldValue = StringUtils.isBlank(valueName) ? jo : getJSONValue(jo, valueName);
            return reMappingFn.apply(oldValue);
        });
    }

    /**
     * Convert a {@link JSONArray} to a {@link JSONObject} by combining the value in JSONArray.<br>
     * Example: [{"key":"event_list","doc_count":4852},{"key":"blocking_log","doc_count":29}].<br>
     * After converted: {"event_list":4852,"blocking_log":29}.<br><br>
     * <strong>Note: </strong>if the value for keyName is not unique throughout the JSONArray, the value for valueName will be overridden.
     *
     * @param ja             a {@link JSONArray} which contains elements of {@link JSONObject}.
     * @param keyMappingFn   the mapping function used to get the value as key of {@link JSONObject}.
     * @param valueMappingFn the mapping function used to get the value as value of {@link JSONObject}.
     * @return new {@link JSONObject}
     * @author shilong.jiang
     */
    public static <T, V extends List<?>> JSONObject toJo(V ja, Function<JSONObject, String> keyMappingFn, Function<JSONObject, T> valueMappingFn) {
        JSONObject result = new JSONObject();
        if (ja != null && !ja.isEmpty()) {
            ja.stream().filter(o -> o instanceof JSONObject)
                    .map(o -> (JSONObject) o)
                    .forEach(jo -> {
                        String key = keyMappingFn.apply(jo);
                        T newValue = valueMappingFn.apply(jo);
                        result.put(key, newValue);
                    });
        }
        return result;
    }

    /**
     * Convert a {@link JSONObject} to a {@link JSONArray} by getting the value in JSONObject.<br>
     *
     * @param jo             a {@link JSONObject} which contains elements of {@link JSONArray}.
     * @param itemMappingFn   the mapping function used to get the value of {@link JSONObject}.
     * @return new {@link JSONArray}
     * @author shilong.jiang
     */
    public static <T> JSONArray toJa(JSONObject jo, Function<String, T> itemMappingFn) {
        JSONArray result = new JSONArray();
        if (jo != null && !jo.isEmpty() && itemMappingFn != null) {
            jo.keySet().parallelStream()
                    .map(itemMappingFn)
                    .forEach(result::add);
        }
        return result;
    }

    /**
     * Put value into {@link JSONObject}
     *
     * @param key   {@link String}
     * @param value {@link Object}
     * @return {@link Json}
     * @author shilong.jiang
     */
    public static Json put(String key, Object value) {
        return new Json().put(key, value);
    }

    /**
     * Return the number in the [] if present, return 0 Otherwise.
     * For example. "abc[2]" will return 2, "abc[a]" will return 0, "abc" will return 0 too.
     *
     * @param str {@link String}
     * @return Index Number in the [].
     */
    private static int getIndexNum(String str) {
        int firstChar = str.lastIndexOf("[");
        int lastChar = str.lastIndexOf("]");
        if (firstChar == -1 || lastChar == -1 || firstChar > lastChar) {
            return 0;
        }
        String index = str.substring(firstChar + 1, lastChar);
        try {
            return Integer.valueOf(index);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Recursively put values into a {@link JSONObject}
     *
     * <p>Copyright Colasoft 2018 Co.,Ltd.</p>
     *
     * @author shilong.jiang
     * @version 1.0.0.RELEASE
     * @since CreatedAt 2019-05-25 13:55:45
     */
    public static class Json {
        private JSONObject jo = new JSONObject();

        private Json() {
        }

        public Json put(String key, Object value) {
            jo.put(key, value);
            return this;
        }

        public JSONObject get() {
            return jo;
        }

        @Override
        public String toString() {
            return jo.toJSONString();
        }
    }
}
