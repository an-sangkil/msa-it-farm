package com.skan.farm.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * <p>
     * ArrayJSON
     * {@link #convertJson(String, Class)}
     * <p>
     * readValue.(jsonInput, new TypeReference<List<MyClass>>(){})
     *
     *
     * </example>
     */
    public static synchronized <T> T convertJson(String strJson, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
//        objectMapper.registerModule(javaTimeModule);

        T objectClass = objectMapper.readValue(strJson, clazz);
        return objectClass;
    }

    public static synchronized <T> T convertJson(String strJson, TypeReference<T> type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        T objectClass = objectMapper.readValue(strJson, type);
        return objectClass;
    }

    /**
     * example )
     * JavaType javaType = mapper.getTypeFactory().constructParametricType(String.class, String.class)
     *
     * @param strJson
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    public static synchronized <T> T convertJson(String strJson, JavaType type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // json 데이터 와 객체의 property 가 맞지 않는경우
        // @JsonIgnoreProperties(ignoreUnknown = true) 처리와 같음
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        T objectClass = objectMapper.readValue(strJson, type);
        return objectClass;
    }


}


 class LocalDateSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        jsonGenerator.writeString(localDate.format(FORMATTER));
    }
}

 class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(jsonParser.getValueAsString(), FORMATTER);
    }
}


