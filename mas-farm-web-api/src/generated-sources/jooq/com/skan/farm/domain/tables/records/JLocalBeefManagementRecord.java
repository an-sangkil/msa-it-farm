/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables.records;


import com.skan.farm.domain.tables.JLocalBeefManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record18;
import org.jooq.Record2;
import org.jooq.Row18;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JLocalBeefManagementRecord extends UpdatableRecordImpl<JLocalBeefManagementRecord> implements Record18<String, String, Short, LocalDate, Short, LocalDate, LocalDateTime, Boolean, LocalDate, LocalDate, String, String, LocalDateTime, String, String, String, String, String> {

    private static final long serialVersionUID = 813330905;

    /**
     * Setter for <code>naive-it-farm.local_beef_management.entity_identification_number</code>.
     */
    public void setEntityIdentificationNumber(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.entity_identification_number</code>.
     */
    public String getEntityIdentificationNumber() {
        return (String) get(0);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.entity_management_number</code>.
     */
    public void setEntityManagementNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.entity_management_number</code>.
     */
    public String getEntityManagementNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.age_of_month</code>.
     */
    public void setAgeOfMonth(Short value) {
        set(2, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.age_of_month</code>.
     */
    public Short getAgeOfMonth() {
        return (Short) get(2);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.birth_day</code>.
     */
    public void setBirthDay(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.birth_day</code>.
     */
    public LocalDate getBirthDay() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.calf_care_skill_level</code>.
     */
    public void setCalfCareSkillLevel(Short value) {
        set(4, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.calf_care_skill_level</code>.
     */
    public Short getCalfCareSkillLevel() {
        return (Short) get(4);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.castration_date</code>.
     */
    public void setCastrationDate(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.castration_date</code>.
     */
    public LocalDate getCastrationDate() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.delete_yn</code>.
     */
    public void setDeleteYn(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.delete_yn</code>.
     */
    public Boolean getDeleteYn() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.ear_tag_date</code>.
     */
    public void setEarTagDate(LocalDate value) {
        set(8, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.ear_tag_date</code>.
     */
    public LocalDate getEarTagDate() {
        return (LocalDate) get(8);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.enter_date</code>.
     */
    public void setEnterDate(LocalDate value) {
        set(9, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.enter_date</code>.
     */
    public LocalDate getEnterDate() {
        return (LocalDate) get(9);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.gender</code>.
     */
    public void setGender(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.gender</code>.
     */
    public String getGender() {
        return (String) get(10);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.location</code>.
     */
    public void setLocation(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.location</code>.
     */
    public String getLocation() {
        return (String) get(11);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.modified_time</code>.
     */
    public void setModifiedTime(LocalDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.modified_time</code>.
     */
    public LocalDateTime getModifiedTime() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.parent_mom_no</code>.
     */
    public void setParentMomNo(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.parent_mom_no</code>.
     */
    public String getParentMomNo() {
        return (String) get(13);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.parent_papa_no</code>.
     */
    public void setParentPapaNo(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.parent_papa_no</code>.
     */
    public String getParentPapaNo() {
        return (String) get(14);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.sell_yn</code>.
     */
    public void setSellYn(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.sell_yn</code>.
     */
    public String getSellYn() {
        return (String) get(15);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.udder_quality</code>.
     */
    public void setUdderQuality(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.udder_quality</code>.
     */
    public String getUdderQuality() {
        return (String) get(16);
    }

    /**
     * Setter for <code>naive-it-farm.local_beef_management.user_id</code>.
     */
    public void setUserId(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>naive-it-farm.local_beef_management.user_id</code>.
     */
    public String getUserId() {
        return (String) get(17);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record18 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row18<String, String, Short, LocalDate, Short, LocalDate, LocalDateTime, Boolean, LocalDate, LocalDate, String, String, LocalDateTime, String, String, String, String, String> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    @Override
    public Row18<String, String, Short, LocalDate, Short, LocalDate, LocalDateTime, Boolean, LocalDate, LocalDate, String, String, LocalDateTime, String, String, String, String, String> valuesRow() {
        return (Row18) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.ENTITY_IDENTIFICATION_NUMBER;
    }

    @Override
    public Field<String> field2() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.ENTITY_MANAGEMENT_NUMBER;
    }

    @Override
    public Field<Short> field3() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.AGE_OF_MONTH;
    }

    @Override
    public Field<LocalDate> field4() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.BIRTH_DAY;
    }

    @Override
    public Field<Short> field5() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.CALF_CARE_SKILL_LEVEL;
    }

    @Override
    public Field<LocalDate> field6() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.CASTRATION_DATE;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.CREATED_TIME;
    }

    @Override
    public Field<Boolean> field8() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.DELETE_YN;
    }

    @Override
    public Field<LocalDate> field9() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.EAR_TAG_DATE;
    }

    @Override
    public Field<LocalDate> field10() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.ENTER_DATE;
    }

    @Override
    public Field<String> field11() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.GENDER;
    }

    @Override
    public Field<String> field12() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.LOCATION;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.MODIFIED_TIME;
    }

    @Override
    public Field<String> field14() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.PARENT_MOM_NO;
    }

    @Override
    public Field<String> field15() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.PARENT_PAPA_NO;
    }

    @Override
    public Field<String> field16() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.SELL_YN;
    }

    @Override
    public Field<String> field17() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.UDDER_QUALITY;
    }

    @Override
    public Field<String> field18() {
        return JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT.USER_ID;
    }

    @Override
    public String component1() {
        return getEntityIdentificationNumber();
    }

    @Override
    public String component2() {
        return getEntityManagementNumber();
    }

    @Override
    public Short component3() {
        return getAgeOfMonth();
    }

    @Override
    public LocalDate component4() {
        return getBirthDay();
    }

    @Override
    public Short component5() {
        return getCalfCareSkillLevel();
    }

    @Override
    public LocalDate component6() {
        return getCastrationDate();
    }

    @Override
    public LocalDateTime component7() {
        return getCreatedTime();
    }

    @Override
    public Boolean component8() {
        return getDeleteYn();
    }

    @Override
    public LocalDate component9() {
        return getEarTagDate();
    }

    @Override
    public LocalDate component10() {
        return getEnterDate();
    }

    @Override
    public String component11() {
        return getGender();
    }

    @Override
    public String component12() {
        return getLocation();
    }

    @Override
    public LocalDateTime component13() {
        return getModifiedTime();
    }

    @Override
    public String component14() {
        return getParentMomNo();
    }

    @Override
    public String component15() {
        return getParentPapaNo();
    }

    @Override
    public String component16() {
        return getSellYn();
    }

    @Override
    public String component17() {
        return getUdderQuality();
    }

    @Override
    public String component18() {
        return getUserId();
    }

    @Override
    public String value1() {
        return getEntityIdentificationNumber();
    }

    @Override
    public String value2() {
        return getEntityManagementNumber();
    }

    @Override
    public Short value3() {
        return getAgeOfMonth();
    }

    @Override
    public LocalDate value4() {
        return getBirthDay();
    }

    @Override
    public Short value5() {
        return getCalfCareSkillLevel();
    }

    @Override
    public LocalDate value6() {
        return getCastrationDate();
    }

    @Override
    public LocalDateTime value7() {
        return getCreatedTime();
    }

    @Override
    public Boolean value8() {
        return getDeleteYn();
    }

    @Override
    public LocalDate value9() {
        return getEarTagDate();
    }

    @Override
    public LocalDate value10() {
        return getEnterDate();
    }

    @Override
    public String value11() {
        return getGender();
    }

    @Override
    public String value12() {
        return getLocation();
    }

    @Override
    public LocalDateTime value13() {
        return getModifiedTime();
    }

    @Override
    public String value14() {
        return getParentMomNo();
    }

    @Override
    public String value15() {
        return getParentPapaNo();
    }

    @Override
    public String value16() {
        return getSellYn();
    }

    @Override
    public String value17() {
        return getUdderQuality();
    }

    @Override
    public String value18() {
        return getUserId();
    }

    @Override
    public JLocalBeefManagementRecord value1(String value) {
        setEntityIdentificationNumber(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value2(String value) {
        setEntityManagementNumber(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value3(Short value) {
        setAgeOfMonth(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value4(LocalDate value) {
        setBirthDay(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value5(Short value) {
        setCalfCareSkillLevel(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value6(LocalDate value) {
        setCastrationDate(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value7(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value8(Boolean value) {
        setDeleteYn(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value9(LocalDate value) {
        setEarTagDate(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value10(LocalDate value) {
        setEnterDate(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value11(String value) {
        setGender(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value12(String value) {
        setLocation(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value13(LocalDateTime value) {
        setModifiedTime(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value14(String value) {
        setParentMomNo(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value15(String value) {
        setParentPapaNo(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value16(String value) {
        setSellYn(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value17(String value) {
        setUdderQuality(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord value18(String value) {
        setUserId(value);
        return this;
    }

    @Override
    public JLocalBeefManagementRecord values(String value1, String value2, Short value3, LocalDate value4, Short value5, LocalDate value6, LocalDateTime value7, Boolean value8, LocalDate value9, LocalDate value10, String value11, String value12, LocalDateTime value13, String value14, String value15, String value16, String value17, String value18) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JLocalBeefManagementRecord
     */
    public JLocalBeefManagementRecord() {
        super(JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT);
    }

    /**
     * Create a detached, initialised JLocalBeefManagementRecord
     */
    public JLocalBeefManagementRecord(String entityIdentificationNumber, String entityManagementNumber, Short ageOfMonth, LocalDate birthDay, Short calfCareSkillLevel, LocalDate castrationDate, LocalDateTime createdTime, Boolean deleteYn, LocalDate earTagDate, LocalDate enterDate, String gender, String location, LocalDateTime modifiedTime, String parentMomNo, String parentPapaNo, String sellYn, String udderQuality, String userId) {
        super(JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT);

        set(0, entityIdentificationNumber);
        set(1, entityManagementNumber);
        set(2, ageOfMonth);
        set(3, birthDay);
        set(4, calfCareSkillLevel);
        set(5, castrationDate);
        set(6, createdTime);
        set(7, deleteYn);
        set(8, earTagDate);
        set(9, enterDate);
        set(10, gender);
        set(11, location);
        set(12, modifiedTime);
        set(13, parentMomNo);
        set(14, parentPapaNo);
        set(15, sellYn);
        set(16, udderQuality);
        set(17, userId);
    }
}
