package com.skan.farm.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 질병 및 치료 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Getter@Setter
@Entity
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true,value = {"hibernateLazyInitializer","$$_hibernate_interceptor", "handler"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DiseaseTreatment implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Builder
	public DiseaseTreatment(LocalBeefManagement localBeefManagement, DiseaseTreatmentPK diseaseTreatmentPK,  LocalDate cureDate, String diseaseName, String medicationName, String treatmentDetails, String injectionMethod, LocalDate withdrawalPeriodExpirationDate, String needleLoseYn) {
		this.localBeefManagement = localBeefManagement;
		this.diseaseTreatmentPK = diseaseTreatmentPK;
		this.cureDate = cureDate;
		this.diseaseName = diseaseName;
		this.medicationName = medicationName;
		this.treatmentDetails = treatmentDetails;
		this.injectionMethod = injectionMethod;
		this.withdrawalPeriodExpirationDate = withdrawalPeriodExpirationDate;
		this.needleLoseYn = needleLoseYn;
	}


	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DiseaseTreatmentPK implements Serializable {
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

		/**
		 * 순번.
		 */
		private Short seq;
	}

	@EmbeddedId
	DiseaseTreatmentPK diseaseTreatmentPK;


	/** 치료_날짜. */
	private LocalDate cureDate;

	/** 질병 명. */
	private String diseaseName;

	/** 약재명. */
	private String medicationName;

	/** 치료내역. */
	private String treatmentDetails;

	/** 투여방법(근육주사, 피하 주사). */
	private String injectionMethod;

	/** 휴약 기간 만료일. */
	private LocalDate withdrawalPeriodExpirationDate;

	/** 주사침분실여부. */
	private String needleLoseYn;

	/**
	 * 생성일시.
	 */
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime createdTime;

	/**
	 * 수정시간.
	 */
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime modifiedTime;

	/** 한우(암소/수소) 개체관리기록부. */
	@MapsId("localBeefManagementPK")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "entityIdentificationNumber", referencedColumnName = "entityIdentificationNumber"),
			@JoinColumn(name = "entityManagementNumber", referencedColumnName = "entityManagementNumber"),
	})
	@JsonBackReference("diseaseTreatmentSet")
	private LocalBeefManagement localBeefManagement;

}
