package com.skan.farm.domain.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.skan.farm.code.GenderCode;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 한우(암소/수소) 개체관리기록부 모델 클래스.
 *
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"cattleBuyInformation"}, callSuper = false)
@ToString(exclude = {
        "calvesManagementSet",
        "cattleBuyInformation",
        "cattleSellStoreInformation",
        "diseaseTreatmentSet"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true,value = {"hibernateLazyInitializer","$$_hibernate_interceptor", "handler"})
public class LocalBeefManagement implements Serializable {

    @Builder
    public LocalBeefManagement(LocalBeefManagementPK localBeefManagementPK, String parentPapaNo, String parentMomNo, LocalDate birthDay, LocalDate enterDate, LocalDate earTagDate, LocalDate castrationDate, GenderCode gender, String sellYn, Boolean deleteYn, LocalDateTime createdTime, LocalDateTime modifiedTime,String location) {
        this.localBeefManagementPK = localBeefManagementPK;
        this.parentPapaNo = parentPapaNo;
        this.parentMomNo = parentMomNo;
        this.birthDay = birthDay;
        this.enterDate = enterDate;
        this.earTagDate = earTagDate;
        this.castrationDate = castrationDate;
        this.gender = gender;
        this.sellYn = sellYn;
        this.deleteYn = deleteYn;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.location = location;
    }

    @EmbeddedId
    protected LocalBeefManagementPK localBeefManagementPK;

    /**
     * 부 번호.
     */
    private String parentPapaNo;

    /**
     * 모 번호.
     */
    private String parentMomNo;

    /**
     * 위치
     */
    private String location;

    @Transient
    private String betweenBirthDate;

    /**
     * 출생일, 태어난날.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    /**
     * 입식일.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enterDate;

    /**
     * 귀표장착일.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate earTagDate;

    /**
     * 거세일.
     */
    private LocalDate castrationDate;

    /**
     * 성별.
     */
    @Enumerated(EnumType.STRING)
    private GenderCode gender;

    /**
     * 판매여부.
     */
    private String sellYn;

    /**
     * 삭제여부
     */
    private Boolean deleteYn;

    /**
     * 분만 횟수
     */
    @Transient
    private String calvesCount;

    /**
     * 수정 횟수
     */
    @Transient
    private String inseminationCount;

    /** 개월수 */
    @Transient
    private String numberOfMonth;

    /** 분만예정일. */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedDateChildbirth;

    /**
     * 사용자 아이디.
     */
    @Column(unique = true,length = 32)
    private String userId;

    // 어미젖 상태
    // 송아지 케어 성숙도(단계)

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
     * 분만관리(송아지 관리) 목록.
     */
    @OneToMany(mappedBy = "localBeefManagement", fetch = FetchType.LAZY)
    @JsonManagedReference("calvesManagementSet")
    private Set<CalvesManagement> calvesManagementSet;

    /**
     * 구입기록 목록. {양방향 사용 하지 않음}
     */
    @OneToOne(mappedBy = "localBeefManagement"
            , fetch = FetchType.LAZY
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , optional = false)
    @JsonManagedReference("cattleBuyInformation")
    private CattleBuyInformation cattleBuyInformation;

    /**
     * 판매지정보 목록.
     */
    @OneToOne(mappedBy = "localBeefManagement"
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY
            , optional = false)
    @JsonManagedReference("cattleSellStoreInformation")
    private CattleSellStoreInformation cattleSellStoreInformation;

    /**
     * 질병 및 치료 목록.
     */
    @OneToMany(mappedBy = "localBeefManagement", fetch = FetchType.LAZY)
    @JsonManagedReference("diseaseTreatmentSet")
    private Set<DiseaseTreatment> diseaseTreatmentSet;

    /**
     * 생성자.
     */
    public LocalBeefManagement() {
    }

    public CattleSellStoreInformation getCattleSellStoreInformation(){
        return this.cattleSellStoreInformation != null ? this.cattleSellStoreInformation : new  CattleSellStoreInformation();
    }

}
