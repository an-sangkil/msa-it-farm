package com.skan.farm.domain.entity;

import com.fasterxml.jackson.annotation.*;
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
 * 구입기록 모델 클래스.
 *
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Getter@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"localBeefManagement"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true,value = {"hibernateLazyInitializer","$$_hibernate_interceptor", "handler"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CattleBuyInformation implements Serializable {

    @Builder
    public CattleBuyInformation(LocalBeefManagementPK cattleBuyInformationPK, String buyStoreName, LocalDate buyDate, String buyNote, String buyPhoneNumber, String buySellPhoneNumber) {
        this.localBeefManagementPK = cattleBuyInformationPK;
        this.buyStoreName = buyStoreName;
        this.buyDate = buyDate;
        this.buyNote = buyNote;
        this.buyPhoneNumber = buyPhoneNumber;
        this.buySellPhoneNumber = buySellPhoneNumber;
    }

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;


    @EmbeddedId
    protected LocalBeefManagementPK localBeefManagementPK;

    /**
     * 구입처.
     */
    private String buyStoreName;

    /**
     * 구입일자.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    /**
     * 비고.
     */
    private String buyNote;

    /**
     * 구입 연락처.
     */
    private String buyPhoneNumber;

    /**
     * 구입 연락처 전화번호 2.
     */
    private String buySellPhoneNumber;

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
     * 한우(암소/수소) 개체관리기록부.
     */
    @MapsId("localBeefManagementPK")
    @OneToOne(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , optional = false)
            @JoinColumn(name = "entityManagementNumber" ,referencedColumnName = "entityManagementNumber")
            @JoinColumn(name = "entityIdentificationNumber", referencedColumnName = "entityIdentificationNumber")
    @JsonBackReference("cattleBuyInformation")
    //@JsonIgnore
    private LocalBeefManagement localBeefManagement;


}
