package com.skan.farm.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-07
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocalBeefManagementPK implements Serializable {
    
    // 1,2 확장코드
    // 3 코드구분
    // 4,5,6,7 -  8,9,10,11 일련번호 8자리
    // 12 위변조 방지위한 체크번호
    /**
     * 개체식별번호.
     */
    @Column(length = 32)
    private String entityIdentificationNumber;


    /**
     * 개체관리번호. ( 일련번호 8자리)
     */
    @Column(length = 32)
    private String entityManagementNumber;


}