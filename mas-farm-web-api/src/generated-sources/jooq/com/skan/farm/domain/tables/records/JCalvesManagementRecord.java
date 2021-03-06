/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables.records;


import com.skan.farm.domain.tables.JCalvesManagement;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record14;
import org.jooq.Record3;
import org.jooq.Row14;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JCalvesManagementRecord extends UpdatableRecordImpl<JCalvesManagementRecord> implements Record14<String, String, Short, LocalDateTime, LocalDateTime, String, String, LocalDateTime, LocalDateTime, Short, LocalDateTime, String, String, String> {

    private static final long serialVersionUID = 931776163;

    /**
     * Setter for <code>naive-it-farm.calves_management.entity_identification_number</code>.
     */
    public void setEntityIdentificationNumber(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.entity_identification_number</code>.
     */
    public String getEntityIdentificationNumber() {
        return (String) get(0);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.entity_management_number</code>.
     */
    public void setEntityManagementNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.entity_management_number</code>.
     */
    public String getEntityManagementNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.seq</code>.
     */
    public void setSeq(Short value) {
        set(2, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.seq</code>.
     */
    public Short getSeq() {
        return (Short) get(2);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.calving_date</code>.
     */
    public void setCalvingDate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.calving_date</code>.
     */
    public LocalDateTime getCalvingDate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.entity_identification_number_child</code>.
     */
    public void setEntityIdentificationNumberChild(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.entity_identification_number_child</code>.
     */
    public String getEntityIdentificationNumberChild() {
        return (String) get(5);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.entity_management_number_child</code>.
     */
    public void setEntityManagementNumberChild(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.entity_management_number_child</code>.
     */
    public String getEntityManagementNumberChild() {
        return (String) get(6);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.expected_date_confinement</code>.
     */
    public void setExpectedDateConfinement(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.expected_date_confinement</code>.
     */
    public LocalDateTime getExpectedDateConfinement() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.fertilization_date</code>.
     */
    public void setFertilizationDate(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.fertilization_date</code>.
     */
    public LocalDateTime getFertilizationDate() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.fertilization_index</code>.
     */
    public void setFertilizationIndex(Short value) {
        set(9, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.fertilization_index</code>.
     */
    public Short getFertilizationIndex() {
        return (Short) get(9);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.modified_time</code>.
     */
    public void setModifiedTime(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.modified_time</code>.
     */
    public LocalDateTime getModifiedTime() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.nothing_special</code>.
     */
    public void setNothingSpecial(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.nothing_special</code>.
     */
    public String getNothingSpecial() {
        return (String) get(11);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.sperm_no</code>.
     */
    public void setSpermNo(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.sperm_no</code>.
     */
    public String getSpermNo() {
        return (String) get(12);
    }

    /**
     * Setter for <code>naive-it-farm.calves_management.years</code>.
     */
    public void setYears(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>naive-it-farm.calves_management.years</code>.
     */
    public String getYears() {
        return (String) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, Short> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row14<String, String, Short, LocalDateTime, LocalDateTime, String, String, LocalDateTime, LocalDateTime, Short, LocalDateTime, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    @Override
    public Row14<String, String, Short, LocalDateTime, LocalDateTime, String, String, LocalDateTime, LocalDateTime, Short, LocalDateTime, String, String, String> valuesRow() {
        return (Row14) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JCalvesManagement.CALVES_MANAGEMENT.ENTITY_IDENTIFICATION_NUMBER;
    }

    @Override
    public Field<String> field2() {
        return JCalvesManagement.CALVES_MANAGEMENT.ENTITY_MANAGEMENT_NUMBER;
    }

    @Override
    public Field<Short> field3() {
        return JCalvesManagement.CALVES_MANAGEMENT.SEQ;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return JCalvesManagement.CALVES_MANAGEMENT.CALVING_DATE;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return JCalvesManagement.CALVES_MANAGEMENT.CREATED_TIME;
    }

    @Override
    public Field<String> field6() {
        return JCalvesManagement.CALVES_MANAGEMENT.ENTITY_IDENTIFICATION_NUMBER_CHILD;
    }

    @Override
    public Field<String> field7() {
        return JCalvesManagement.CALVES_MANAGEMENT.ENTITY_MANAGEMENT_NUMBER_CHILD;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return JCalvesManagement.CALVES_MANAGEMENT.EXPECTED_DATE_CONFINEMENT;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return JCalvesManagement.CALVES_MANAGEMENT.FERTILIZATION_DATE;
    }

    @Override
    public Field<Short> field10() {
        return JCalvesManagement.CALVES_MANAGEMENT.FERTILIZATION_INDEX;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return JCalvesManagement.CALVES_MANAGEMENT.MODIFIED_TIME;
    }

    @Override
    public Field<String> field12() {
        return JCalvesManagement.CALVES_MANAGEMENT.NOTHING_SPECIAL;
    }

    @Override
    public Field<String> field13() {
        return JCalvesManagement.CALVES_MANAGEMENT.SPERM_NO;
    }

    @Override
    public Field<String> field14() {
        return JCalvesManagement.CALVES_MANAGEMENT.YEARS;
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
    public LocalDateTime component4() {
        return getCalvingDate();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedTime();
    }

    @Override
    public String component6() {
        return getEntityIdentificationNumberChild();
    }

    @Override
    public String component7() {
        return getEntityManagementNumberChild();
    }

    @Override
    public LocalDateTime component8() {
        return getExpectedDateConfinement();
    }

    @Override
    public LocalDateTime component9() {
        return getFertilizationDate();
    }

    @Override
    public Short component10() {
        return getFertilizationIndex();
    }

    @Override
    public LocalDateTime component11() {
        return getModifiedTime();
    }

    @Override
    public String component12() {
        return getNothingSpecial();
    }

    @Override
    public String component13() {
        return getSpermNo();
    }

    @Override
    public String component14() {
        return getYears();
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
    public LocalDateTime value4() {
        return getCalvingDate();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedTime();
    }

    @Override
    public String value6() {
        return getEntityIdentificationNumberChild();
    }

    @Override
    public String value7() {
        return getEntityManagementNumberChild();
    }

    @Override
    public LocalDateTime value8() {
        return getExpectedDateConfinement();
    }

    @Override
    public LocalDateTime value9() {
        return getFertilizationDate();
    }

    @Override
    public Short value10() {
        return getFertilizationIndex();
    }

    @Override
    public LocalDateTime value11() {
        return getModifiedTime();
    }

    @Override
    public String value12() {
        return getNothingSpecial();
    }

    @Override
    public String value13() {
        return getSpermNo();
    }

    @Override
    public String value14() {
        return getYears();
    }

    @Override
    public JCalvesManagementRecord value1(String value) {
        setEntityIdentificationNumber(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value2(String value) {
        setEntityManagementNumber(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value3(Short value) {
        setSeq(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value4(LocalDateTime value) {
        setCalvingDate(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value5(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value6(String value) {
        setEntityIdentificationNumberChild(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value7(String value) {
        setEntityManagementNumberChild(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value8(LocalDateTime value) {
        setExpectedDateConfinement(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value9(LocalDateTime value) {
        setFertilizationDate(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value10(Short value) {
        setFertilizationIndex(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value11(LocalDateTime value) {
        setModifiedTime(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value12(String value) {
        setNothingSpecial(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value13(String value) {
        setSpermNo(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord value14(String value) {
        setYears(value);
        return this;
    }

    @Override
    public JCalvesManagementRecord values(String value1, String value2, Short value3, LocalDateTime value4, LocalDateTime value5, String value6, String value7, LocalDateTime value8, LocalDateTime value9, Short value10, LocalDateTime value11, String value12, String value13, String value14) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JCalvesManagementRecord
     */
    public JCalvesManagementRecord() {
        super(JCalvesManagement.CALVES_MANAGEMENT);
    }

    /**
     * Create a detached, initialised JCalvesManagementRecord
     */
    public JCalvesManagementRecord(String entityIdentificationNumber, String entityManagementNumber, Short seq, LocalDateTime calvingDate, LocalDateTime createdTime, String entityIdentificationNumberChild, String entityManagementNumberChild, LocalDateTime expectedDateConfinement, LocalDateTime fertilizationDate, Short fertilizationIndex, LocalDateTime modifiedTime, String nothingSpecial, String spermNo, String years) {
        super(JCalvesManagement.CALVES_MANAGEMENT);

        set(0, entityIdentificationNumber);
        set(1, entityManagementNumber);
        set(2, seq);
        set(3, calvingDate);
        set(4, createdTime);
        set(5, entityIdentificationNumberChild);
        set(6, entityManagementNumberChild);
        set(7, expectedDateConfinement);
        set(8, fertilizationDate);
        set(9, fertilizationIndex);
        set(10, modifiedTime);
        set(11, nothingSpecial);
        set(12, spermNo);
        set(13, years);
    }
}
