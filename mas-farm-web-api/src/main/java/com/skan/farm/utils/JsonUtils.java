package com.skan.farm.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

  /**
   * 객체 -> JSON String data로 변환
   *
   * @param t Class 객체 example : userDto
   */
  public static synchronized <T> String convertJson(T t) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    //mapper.setSerializationInclusion(Include.NON_NULL);
    // mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    String strJson = mapper.writeValueAsString(t);

    return strJson;
  }

  public static synchronized <T> String convertJson(T t, Include Type) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    mapper.setSerializationInclusion(Type);
    String strJson = mapper.writeValueAsString(t);

    return strJson;
  }

  /**
   * JSON String data - > 객체로 변환
   *
   * <example>
   *
   *   ArrayJSON
   *      {@link #convertJson(String, Class)}
   *
   *              readValue.(jsonInput, new TypeReference<List<MyClass>>(){})
   *
   *
   * </example>
   */
  public static synchronized  <T> T convertJson(String strJson, Class<T> clazz) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    T objectClass = objectMapper.readValue(strJson, clazz);
    return objectClass;
  }

  public static synchronized  <T> T  convertJson(String strJson, TypeReference<T> type) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    T objectClass = objectMapper.readValue(strJson, type);
    return objectClass;
  }

  /**
   * example )
   *    JavaType javaType = mapper.getTypeFactory().constructParametricType(String.class, String.class)
   *
   * @param strJson
   * @param type
   * @param <T>
   * @return
   * @throws IOException
   */
  public static synchronized  <T> T  convertJson(String strJson, JavaType type) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    // json 데이터 와 객체의 property 가 맞지 않는경우
    // @JsonIgnoreProperties(ignoreUnknown = true) 처리와 같음
    objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    T objectClass = objectMapper.readValue(strJson, type);
    return objectClass;
  }

}
