package com.skan.farm.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * <pre>
 * Description : 관찰 일지 (질병 및 치료 관리 테이블 정보)
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@Getter @Setter
@ToString
public class ObservationDiary {

    /**
     * 치료_날짜.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cureDate;


    /**
     * 개체관리번호.
     */
    @Column(length = 32)
    private String entityManagementNumber;

    /**
     * 개체식별번호.
     */
    @Column(length = 32)
    private String entityIdentificationNumber;

    //월령(현재 개월수)
    private Short cureNumberOfMonth;

    //월령(치료 당시의 개월수 )
    private Short numberOfMonth;

    /**
     * 질병 명(증상).
     */
    private String diseaseName;

    /**
     * 약재명.
     */
    private String medicationName;

    /**
     * 투여방법(근육주사, 피하 주사).
     */
    private String injectionMethod;

    /**
     * 치료내역(방법).
     */
    private String treatmentDetails;

    /**
     * 위치(서식방)
     */
    private String location;

    /**
     * 비고
     */
    private String remark;

    /**
     * 휴약 기간 만료일.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate withdrawalPeriodExpirationDate;

}
