package com.skan.farm.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 가계부(house_keeping_book) 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class HouseKeepingBook implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 관리번호. */
	private String managementNo;

	/** 개인코드. */
	private PersonalCode categoryPersonalCode;

	/** 거래일. */
	private Date businessDay;

	/** 금액. */
	private Long amount;

	/** 내역. */
	private String breakdown;

	/** 메모. */
	private String detailContents;

	/**
	 * 생성자.
	 */
	public HouseKeepingBook() {
	}

	/**
	 * 관리번호을 설정합니다..
	 * 
	 * @param managementNo
	 *            관리번호
	 */
	public void setManagementNo(String managementNo) {
		this.managementNo = managementNo;
	}

	/**
	 * 관리번호을 가져옵니다..
	 * 
	 * @return 관리번호
	 */
	public String getManagementNo() {
		return this.managementNo;
	}

	/**
	 * 개인코드을 설정합니다..
	 * 
	 * @param categoryPersonalCode
	 *            개인코드
	 */
	public void setCategoryPersonalCode(PersonalCode categoryPersonalCode) {
		this.categoryPersonalCode = categoryPersonalCode;
	}

	/**
	 * 개인코드을 가져옵니다..
	 * 
	 * @return 개인코드
	 */
	public PersonalCode getCategoryPersonalCode() {
		return this.categoryPersonalCode;
	}

	/**
	 * 거래일을 설정합니다..
	 * 
	 * @param businessDay
	 *            거래일
	 */
	public void setBusinessDay(Date businessDay) {
		this.businessDay = businessDay;
	}

	/**
	 * 거래일을 가져옵니다..
	 * 
	 * @return 거래일
	 */
	public Date getBusinessDay() {
		return this.businessDay;
	}

	/**
	 * 금액을 설정합니다..
	 * 
	 * @param amount
	 *            금액
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * 금액을 가져옵니다..
	 * 
	 * @return 금액
	 */
	public Long getAmount() {
		return this.amount;
	}

	/**
	 * 내역을 설정합니다..
	 * 
	 * @param breakdown
	 *            내역
	 */
	public void setBreakdown(String breakdown) {
		this.breakdown = breakdown;
	}

	/**
	 * 내역을 가져옵니다..
	 * 
	 * @return 내역
	 */
	public String getBreakdown() {
		return this.breakdown;
	}

	/**
	 * 메모을 설정합니다..
	 * 
	 * @param detailContents
	 *            메모
	 */
	public void setDetailContents(String detailContents) {
		this.detailContents = detailContents;
	}

	/**
	 * 메모을 가져옵니다..
	 * 
	 * @return 메모
	 */
	public String getDetailContents() {
		return this.detailContents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((managementNo == null) ? 0 : managementNo.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HouseKeepingBook other = (HouseKeepingBook) obj;
		if (managementNo == null) {
			if (other.managementNo != null) {
				return false;
			}
		} else if (!managementNo.equals(other.managementNo)) {
			return false;
		}
		return true;
	}

}
