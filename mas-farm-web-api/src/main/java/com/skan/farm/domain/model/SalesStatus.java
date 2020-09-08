package com.skan.farm.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.skan.farm.code.GenderCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * <pre>
 * Description : 판매 현황
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@Getter@Setter
@ToString
public class SalesStatus {

    // 식별 번호
    private String entityIdentificationNumber;

    /**
     * 개체관리번호. ( 일련번호 8자리)
     */
    private String entityManagementNumber;

    /**
     * 성별.
     */
    private GenderCode gender;

    /**
     * 출생일, 태어난날.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    // 모개체 식별번호
    /**
     * 모 번호.
     */
    private String parentMomNo;
    // 모개체 유무


    // 상태 (yyyyMMdd 판매)
    /**
     * 판매여부.
     */
    private String sellYn;

    /** 판매/도태일자. */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sellDate;


    /**
     * 위치 (사육장 위치)
     */
    private String location;

    /** 판매처. 도축장 명 */
    private String storeName;

    /**
     * 개월수(팔린 날짜 당일의 개월수)
     */
    private String ageOfMonth;

    /** 생체중(살아있는생물의무게). */
    private Short liveweight;

    /** 도체중( 도살한 가축의 가죽, 머리, 발목, 내장 따위를 떼어 낸 나머지 몸뚱이의 체중.). */
    private Short dressedWeight;

    /** 등급(1++A, 1++B). */
    private String beefGrade;

    /** 판매가격. */
    private Integer sellingPrice;







}
