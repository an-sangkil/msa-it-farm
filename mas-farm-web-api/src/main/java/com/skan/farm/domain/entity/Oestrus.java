package com.skan.farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <pre>
 * Description : 발정관리
 *  발정주기. (21일뒤)
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN. All right reserved.
 * @since 2020-08-27
 */
@Entity
@Getter@Setter
@ToString
public class Oestrus {


    @Embeddable
    @EqualsAndHashCode
    @Getter@Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OestrusPK implements Serializable {
        /**
         * 개체식별번호.
         */
        @Column(length = 12)
        private String entityIdentificationNumber;


        /**
         * 개체관리번호. ( 일련번호 8자리)
         */
        @Column(length = 8)
        private String entityManagementNumber;

        private Short seq;

    }

    @EmbeddedId OestrusPK oestrusPK;

    /**
     * 날짜 (발정 일시)
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime oestrusDatetime;

    /**
     * 발정 당시의 개월 수
     */
    private Short ageOfMonth;


    // 증상 (증후)
    @Column(length = 512)
    private String symptom;

    /**
     * 생성일시.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    /**
     * 수정시간.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime modifiedTime;


}
