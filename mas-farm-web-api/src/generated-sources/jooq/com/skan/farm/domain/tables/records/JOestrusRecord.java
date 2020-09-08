/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables.records;


import com.skan.farm.domain.tables.JOestrus;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JOestrusRecord extends UpdatableRecordImpl<JOestrusRecord> implements Record8<String, String, Short, Short, LocalDateTime, LocalDateTime, LocalDateTime, String> {

    private static final long serialVersionUID = -315531190;

    /**
     * Setter for <code>naive-it-farm.oestrus.entity_identification_number</code>.
     */
    public void setEntityIdentificationNumber(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.entity_identification_number</code>.
     */
    public String getEntityIdentificationNumber() {
        return (String) get(0);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.entity_management_number</code>.
     */
    public void setEntityManagementNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.entity_management_number</code>.
     */
    public String getEntityManagementNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.seq</code>.
     */
    public void setSeq(Short value) {
        set(2, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.seq</code>.
     */
    public Short getSeq() {
        return (Short) get(2);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.age_of_month</code>.
     */
    public void setAgeOfMonth(Short value) {
        set(3, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.age_of_month</code>.
     */
    public Short getAgeOfMonth() {
        return (Short) get(3);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.modified_time</code>.
     */
    public void setModifiedTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.modified_time</code>.
     */
    public LocalDateTime getModifiedTime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.oestrus_datetime</code>.
     */
    public void setOestrusDatetime(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.oestrus_datetime</code>.
     */
    public LocalDateTime getOestrusDatetime() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>naive-it-farm.oestrus.symptom</code>.
     */
    public void setSymptom(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>naive-it-farm.oestrus.symptom</code>.
     */
    public String getSymptom() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, Short> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, Short, Short, LocalDateTime, LocalDateTime, LocalDateTime, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, Short, Short, LocalDateTime, LocalDateTime, LocalDateTime, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JOestrus.OESTRUS.ENTITY_IDENTIFICATION_NUMBER;
    }

    @Override
    public Field<String> field2() {
        return JOestrus.OESTRUS.ENTITY_MANAGEMENT_NUMBER;
    }

    @Override
    public Field<Short> field3() {
        return JOestrus.OESTRUS.SEQ;
    }

    @Override
    public Field<Short> field4() {
        return JOestrus.OESTRUS.AGE_OF_MONTH;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return JOestrus.OESTRUS.CREATED_TIME;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return JOestrus.OESTRUS.MODIFIED_TIME;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return JOestrus.OESTRUS.OESTRUS_DATETIME;
    }

    @Override
    public Field<String> field8() {
        return JOestrus.OESTRUS.SYMPTOM;
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
        return getSeq();
    }

    @Override
    public Short component4() {
        return getAgeOfMonth();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime component6() {
        return getModifiedTime();
    }

    @Override
    public LocalDateTime component7() {
        return getOestrusDatetime();
    }

    @Override
    public String component8() {
        return getSymptom();
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
        return getSeq();
    }

    @Override
    public Short value4() {
        return getAgeOfMonth();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime value6() {
        return getModifiedTime();
    }

    @Override
    public LocalDateTime value7() {
        return getOestrusDatetime();
    }

    @Override
    public String value8() {
        return getSymptom();
    }

    @Override
    public JOestrusRecord value1(String value) {
        setEntityIdentificationNumber(value);
        return this;
    }

    @Override
    public JOestrusRecord value2(String value) {
        setEntityManagementNumber(value);
        return this;
    }

    @Override
    public JOestrusRecord value3(Short value) {
        setSeq(value);
        return this;
    }

    @Override
    public JOestrusRecord value4(Short value) {
        setAgeOfMonth(value);
        return this;
    }

    @Override
    public JOestrusRecord value5(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public JOestrusRecord value6(LocalDateTime value) {
        setModifiedTime(value);
        return this;
    }

    @Override
    public JOestrusRecord value7(LocalDateTime value) {
        setOestrusDatetime(value);
        return this;
    }

    @Override
    public JOestrusRecord value8(String value) {
        setSymptom(value);
        return this;
    }

    @Override
    public JOestrusRecord values(String value1, String value2, Short value3, Short value4, LocalDateTime value5, LocalDateTime value6, LocalDateTime value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JOestrusRecord
     */
    public JOestrusRecord() {
        super(JOestrus.OESTRUS);
    }

    /**
     * Create a detached, initialised JOestrusRecord
     */
    public JOestrusRecord(String entityIdentificationNumber, String entityManagementNumber, Short seq, Short ageOfMonth, LocalDateTime createdTime, LocalDateTime modifiedTime, LocalDateTime oestrusDatetime, String symptom) {
        super(JOestrus.OESTRUS);

        set(0, entityIdentificationNumber);
        set(1, entityManagementNumber);
        set(2, seq);
        set(3, ageOfMonth);
        set(4, createdTime);
        set(5, modifiedTime);
        set(6, oestrusDatetime);
        set(7, symptom);
    }
}
