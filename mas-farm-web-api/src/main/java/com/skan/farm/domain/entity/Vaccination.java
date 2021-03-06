package com.skan.farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 * Description : 예방 접종
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@Entity
@Getter@Setter
@NoArgsConstructor
@ToString
public class Vaccination {

    @Builder
    public Vaccination(VaccinationPK vaccinationPK, String vaccinationType, String location, String remark, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.vaccinationPK = vaccinationPK;
        this.vaccinationType = vaccinationType;
        this.location = location;
        this.remark = remark;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    @ToString
    @Embeddable
    @Getter@Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VaccinationPK implements Serializable {

        /**
         * 접종 날짜
         */
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate day;

        /**
         * 개체식별번호.
         */
        @Column(length = 12)
        private String entityIdentificationNumber;

        /**
         * 개체관리번호
         */
        @Column(length = 8)
        private String entityManagementNumber;
        /**
         * 순번
         */
        private Short seq;
    }

    @EmbeddedId
    private VaccinationPK vaccinationPK;

    // 예방 접종 종류
    @Column(length = 32)
    private String  vaccinationType;

    // 위치
    @Column(length = 32)
    private String location;

    // 비고
    @Column(length = 512)
    private String remark;

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
