/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables.records;


import com.skan.farm.domain.tables.JPersonalCode;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JPersonalCodeRecord extends UpdatableRecordImpl<JPersonalCodeRecord> implements Record7<String, String, String, Integer, LocalDateTime, String, String> {

    private static final long serialVersionUID = 1892528685;

    /**
     * Setter for <code>naive-it-farm.personal_code.code</code>.
     */
    public void setCode(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.code</code>.
     */
    public String getCode() {
        return (String) get(0);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.code_comments</code>.
     */
    public void setCodeComments(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.code_comments</code>.
     */
    public String getCodeComments() {
        return (String) get(1);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.code_name</code>.
     */
    public void setCodeName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.code_name</code>.
     */
    public String getCodeName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.code_seq</code>.
     */
    public void setCodeSeq(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.code_seq</code>.
     */
    public Integer getCodeSeq() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.user_id</code>.
     */
    public void setUserId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.user_id</code>.
     */
    public String getUserId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>naive-it-farm.personal_code.upper_code</code>.
     */
    public void setUpperCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>naive-it-farm.personal_code.upper_code</code>.
     */
    public String getUpperCode() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, Integer, LocalDateTime, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, String, String, Integer, LocalDateTime, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JPersonalCode.PERSONAL_CODE.CODE;
    }

    @Override
    public Field<String> field2() {
        return JPersonalCode.PERSONAL_CODE.CODE_COMMENTS;
    }

    @Override
    public Field<String> field3() {
        return JPersonalCode.PERSONAL_CODE.CODE_NAME;
    }

    @Override
    public Field<Integer> field4() {
        return JPersonalCode.PERSONAL_CODE.CODE_SEQ;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return JPersonalCode.PERSONAL_CODE.CREATED_TIME;
    }

    @Override
    public Field<String> field6() {
        return JPersonalCode.PERSONAL_CODE.USER_ID;
    }

    @Override
    public Field<String> field7() {
        return JPersonalCode.PERSONAL_CODE.UPPER_CODE;
    }

    @Override
    public String component1() {
        return getCode();
    }

    @Override
    public String component2() {
        return getCodeComments();
    }

    @Override
    public String component3() {
        return getCodeName();
    }

    @Override
    public Integer component4() {
        return getCodeSeq();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedTime();
    }

    @Override
    public String component6() {
        return getUserId();
    }

    @Override
    public String component7() {
        return getUpperCode();
    }

    @Override
    public String value1() {
        return getCode();
    }

    @Override
    public String value2() {
        return getCodeComments();
    }

    @Override
    public String value3() {
        return getCodeName();
    }

    @Override
    public Integer value4() {
        return getCodeSeq();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedTime();
    }

    @Override
    public String value6() {
        return getUserId();
    }

    @Override
    public String value7() {
        return getUpperCode();
    }

    @Override
    public JPersonalCodeRecord value1(String value) {
        setCode(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value2(String value) {
        setCodeComments(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value3(String value) {
        setCodeName(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value4(Integer value) {
        setCodeSeq(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value5(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value6(String value) {
        setUserId(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord value7(String value) {
        setUpperCode(value);
        return this;
    }

    @Override
    public JPersonalCodeRecord values(String value1, String value2, String value3, Integer value4, LocalDateTime value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JPersonalCodeRecord
     */
    public JPersonalCodeRecord() {
        super(JPersonalCode.PERSONAL_CODE);
    }

    /**
     * Create a detached, initialised JPersonalCodeRecord
     */
    public JPersonalCodeRecord(String code, String codeComments, String codeName, Integer codeSeq, LocalDateTime createdTime, String userId, String upperCode) {
        super(JPersonalCode.PERSONAL_CODE);

        set(0, code);
        set(1, codeComments);
        set(2, codeName);
        set(3, codeSeq);
        set(4, createdTime);
        set(5, userId);
        set(6, upperCode);
    }
}
