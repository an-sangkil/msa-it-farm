package com.skan.farm.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
//@Table(name="users")
@Getter@Setter
@ToString
public class Users implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** uuid. */
    @Id
    private String uuid;

    /** 사용자 아이디. */
    @Column(unique = true)
    private String userId;

    /** 패스워드. */
    private String password;

    /** 사용자이름. */
    private String userName;

    /** 이메일. */
    private String email;

    /** 전화번호. */
    private String phoneNumber;

    /** 휴대전화번호. */
    private String mobilePhoneNumber;

    /** 주민번호. */
    private String ssnNumber;

    /** 성별(M : 남성F : 여성). */
    private String gender;

    /** 마지막 접속 시간. */
    private Date lastLoginTime;

    /** 마지막 접속 실패 시간. */
    private Date failLoginTime;

    /** 로그인 실패 횟수. */
    private Long failLoginCount;

    /** 잠금여부 (Y : 잠김). */
    private String useLockState;

    /** 비고. */
    private String description;



}