package com.skan.farm.model;


import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 구입기록 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Data
public class CattleBuyInformation implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Embeddable
	@Data
	public static class CattleBuyInformationPK implements Serializable {
		/**
		 * 개체관리번호.
		 */
		private String entityManagementNumber;

		/**
		 * 개체식별번호.
		 */
		private String entityIdentificationNumber;
	}

	@EmbeddedId
	CattleBuyInformationPK cattleBuyInformationPK;

	/** 구입처. */
	private String buyStoreName;

	/** 구입일자. */
	private LocalDate buyDate;

	/** 비고. */
	private String buyNote;

	/** 구입 연락처. */
	private String buyPhoneNumber;

	/** 구입 연락처 전화번호 2. */
	private String buySellPhoneNumber;

	/** 한우(암소/수소) 개체관리기록부. */
	@Transient
	private LocalBeefManagement localBeefManagement;

}
