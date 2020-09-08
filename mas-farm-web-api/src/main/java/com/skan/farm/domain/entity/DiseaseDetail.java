package com.skan.farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-09-07
 */
@Entity
@Getter@Setter
@ToString
public class DiseaseDetail {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Builder
    public DiseaseDetail(DiseaseDetailPK diseaseDetailPK,String medicationName, String injectionMethod, LocalDate withdrawalPeriodExpirationDate, String needleLoseYn, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.diseaseDetailPK = diseaseDetailPK;
        this.medicationName = medicationName;
        this.injectionMethod = injectionMethod;
        this.withdrawalPeriodExpirationDate = withdrawalPeriodExpirationDate;
        this.needleLoseYn = needleLoseYn;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    @Getter@Setter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiseaseDetailPK  implements Serializable {


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

        /**
         * 날짜
         */
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate day;


        /** 순번. */
        private Short seq;

    }

    @EmbeddedId DiseaseDetailPK diseaseDetailPK;


    /** 약재 명. */
    private String medicationName;

    /** 투여방법(근육주사, 피하 주사). */
    private String injectionMethod;

    /** 휴약 기간 만료일. */
    private LocalDate withdrawalPeriodExpirationDate;

    /** 주사침분실여부. */
    private String needleLoseYn;

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

    /**
     * 생성자.
     */
    public DiseaseDetail() {
    }

    @MapsId("DiseaseTreatmentPK")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="entityIdentificationNumber" , referencedColumnName = "entityIdentificationNumber")
    @JoinColumn(name="entityManagementNumber" , referencedColumnName = "entityManagementNumber")
    @JoinColumn(name="day" , referencedColumnName = "day")
    @JsonBackReference("diseaseDetails")
    private DiseaseTreatment diseaseTreatment;

}
