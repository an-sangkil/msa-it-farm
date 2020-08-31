package com.skan.farm.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-16
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"uuid", "userId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 레벨 protected
public class Users implements Serializable {

    @Builder
    public Users(String uuid, String userId, String password, String userName, String email, String phoneNumber, String mobilePhoneNumber, String ssnNumber, String gender, Date lastLoginTime, Date failLoginTime, Long failLoginCount, String useLockState, String description) {
        this.uuid = uuid;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.ssnNumber = ssnNumber;
        this.gender = gender;
        this.lastLoginTime = lastLoginTime;
        this.failLoginTime = failLoginTime;
        this.failLoginCount = failLoginCount;
        this.useLockState = useLockState;
        this.description = description;
    }

    public Users(String uuid, String userId) {
        this.uuid = uuid;
        this.userId = userId;
    }

    /**
     * uuid.
     */
    @Id
    @Column(length = 128)
    private String uuid;

    /**
     * 사용자 아이디.
     */
    @Column(unique = true,length = 32)
    private String userId;

    /**
     * 패스워드.
     */
    private String password;

    /**
     * 사용자이름.
     */
    private String userName;

    /**
     * 이메일.
     */
    private String email;

    /**
     * 전화번호.
     */
    private String phoneNumber;

    /**
     * 휴대전화번호.
     */
    private String mobilePhoneNumber;

    /**
     * 주민번호.
     */
    private String ssnNumber;

    /**
     * 성별(M : 남성F : 여성).
     */
    private String gender;

    /**
     * 마지막 접속 시간.
     */
    private Date lastLoginTime;

    /**
     * 마지막 접속 실패 시간.
     */
    private Date failLoginTime;

    /**
     * 로그인 실패 횟수.
     */
    private Long failLoginCount;

    /**
     * 잠금여부 (Y : 잠김).
     */
    private String useLockState;

    /**
     * 비고.
     */
    private String description;


}