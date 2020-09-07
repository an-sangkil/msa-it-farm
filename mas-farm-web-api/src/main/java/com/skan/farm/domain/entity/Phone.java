package com.skan.farm.domain.entity;


import java.io.Serializable;

/**
 * 주소록 전화번호부 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Phone implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 주소록 관리. */
	private AddressManagement addressManagement;

	/** 순번. */
	private Long hmPhoNo;

	/** 전화번호. */
	private String phoneNumber;

	/** 구분. */
	private String type;


	/**
	 * 생성자.
	 */
	public Phone() {
	}


	/**
	 * 순번을 설정합니다..
	 * 
	 * @param hmPhoNo
	 *            순번
	 */
	public void setHmPhoNo(Long hmPhoNo) {
		this.hmPhoNo = hmPhoNo;
	}

	/**
	 * 순번을 가져옵니다..
	 * 
	 * @return 순번
	 */
	public Long getHmPhoNo() {
		return this.hmPhoNo;
	}

	/**
	 * 전화번호을 설정합니다..
	 * 
	 * @param phoneNumber
	 *            전화번호
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 전화번호을 가져옵니다..
	 * 
	 * @return 전화번호
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 구분을 설정합니다..
	 * 
	 * @param type
	 *            구분
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 구분을 가져옵니다..
	 * 
	 * @return 구분
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 주소록 관리을 설정합니다..
	 * 
	 * @param addressManagement
	 *            주소록 관리
	 */
	public void setAddressManagement(AddressManagement addressManagement) {
		this.addressManagement = addressManagement;
	}

	/**
	 * 주소록 관리을 가져옵니다..
	 * 
	 * @return 주소록 관리
	 */
	public AddressManagement getAddressManagement() {
		return this.addressManagement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressManagement == null) ? 0 : addressManagement.hashCode());
		result = prime * result + ((hmPhoNo == null) ? 0 : hmPhoNo.hashCode());
		result = prime * result + ((addressManagement == null) ? 0 : addressManagement.hashCode());
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
		Phone other = (Phone) obj;
		if (addressManagement == null) {
			if (other.addressManagement != null) {
				return false;
			}
		} else if (!addressManagement.equals(other.addressManagement)) {
			return false;
		}
		if (hmPhoNo == null) {
			if (other.hmPhoNo != null) {
				return false;
			}
		} else if (!hmPhoNo.equals(other.hmPhoNo)) {
			return false;
		}
		if (addressManagement == null) {
			if (other.addressManagement != null) {
				return false;
			}
		} else if (!addressManagement.equals(other.addressManagement)) {
			return false;
		}
		return true;
	}

}