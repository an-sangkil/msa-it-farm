package com.skan.farm.model;

import com.fasterxml.jackson.annotation.*;
import com.skan.farm.code.GenderCode;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
@JsonIgnoreProperties(ignoreUnknown = false,value = {"hibernateLazyInitializer","$$_hibernate_interceptor", "handler"})
public class LocalBeefManagement implements Serializable {

    @Builder
    public LocalBeefManagement(LocalBeefManagementPK localBeefManagementPK, String parentPapaNo, String parentMomNo, LocalDate birthDay, LocalDate enterDate, LocalDate earTagDate, LocalDate castrationDate, GenderCode gender, String sellYn, Boolean deleteYn, Date createdTime, Date modifiedTime) {
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
     * 출생일, 태어난날.
     */
    private LocalDate birthDay;

    /**
     * 입식일.
     */
    private LocalDate enterDate;

    /**
     * 귀표장착일.
     */
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
     * 생성일시.
     */
    private Date createdTime;

    /**
     * 수정시간.
     */
    private Date modifiedTime;

    /**
     * 분만관리(송아지 관리) 목록.
     */
    @OneToMany(mappedBy = "localBeefManagement", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CalvesManagement> calvesManagementSet;

    /**
     * 구입기록 목록. {양방향 사용 하지 않음}
     */
    @OneToOne(mappedBy = "localBeefManagement"
            , fetch = FetchType.LAZY
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , optional = false)
    @JsonManagedReference
    private CattleBuyInformation cattleBuyInformation;

    /**
     * 판매지정보 목록.
     */
    @OneToOne(mappedBy = "localBeefManagement"
            , fetch = FetchType.LAZY
            , optional = false)
    @JsonManagedReference
    //@JsonIgnore
    private CattleSellStoreInformation cattleSellStoreInformation;

    /**
     * 질병 및 치료 목록.
     */
    @OneToMany(mappedBy = "localBeefManagement", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<DiseaseTreatment> diseaseTreatmentSet;

    /**
     * 생성자.
     */
    public LocalBeefManagement() {
    }


}
