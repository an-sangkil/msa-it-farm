package com.skan.farm.domain.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 공통코드 모델 클래스.
 *
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@ToString(exclude = {"commonCodes"})
@Entity
public class CommonCode implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 코드.
     */
    @Id
    @Column(length = 32)
    private String code;

    /**
     * 그룹별 코드 순번.
     */
    private Integer codeSeq;

    /**
     * 코드이름.
     */
    private String codeName;

    /**
     * 코드 설명.
     */
    private String codeComment;

    /**
     * 생성일시.
     */
    private Date createdTime;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "upperCode", referencedColumnName = "code", insertable = true, updatable = true)
    private CommonCode parentCode;

    /**
     * 공통코드 목록.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCode")
    private List<CommonCode> commonCodes;


}
