/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables.records;


import com.skan.farm.domain.tables.JCattleBuyInformation;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JCattleBuyInformationRecord extends UpdatableRecordImpl<JCattleBuyInformationRecord> implements Record7<String, String, LocalDate, String, String, String, String> {

    private static final long serialVersionUID = 1731007266;

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.entity_identification_number</code>.
     */
    public void setEntityIdentificationNumber(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.entity_identification_number</code>.
     */
    public String getEntityIdentificationNumber() {
        return (String) get(0);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.entity_management_number</code>.
     */
    public void setEntityManagementNumber(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.entity_management_number</code>.
     */
    public String getEntityManagementNumber() {
        return (String) get(1);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.buy_date</code>.
     */
    public void setBuyDate(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.buy_date</code>.
     */
    public LocalDate getBuyDate() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.buy_note</code>.
     */
    public void setBuyNote(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.buy_note</code>.
     */
    public String getBuyNote() {
        return (String) get(3);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.buy_phone_number</code>.
     */
    public void setBuyPhoneNumber(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.buy_phone_number</code>.
     */
    public String getBuyPhoneNumber() {
        return (String) get(4);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.buy_sell_phone_number</code>.
     */
    public void setBuySellPhoneNumber(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.buy_sell_phone_number</code>.
     */
    public String getBuySellPhoneNumber() {
        return (String) get(5);
    }

    /**
     * Setter for <code>naive-it-farm.cattle_buy_information.buy_store_name</code>.
     */
    public void setBuyStoreName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>naive-it-farm.cattle_buy_information.buy_store_name</code>.
     */
    public String getBuyStoreName() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, LocalDate, String, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, String, LocalDate, String, String, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.ENTITY_IDENTIFICATION_NUMBER;
    }

    @Override
    public Field<String> field2() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.ENTITY_MANAGEMENT_NUMBER;
    }

    @Override
    public Field<LocalDate> field3() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.BUY_DATE;
    }

    @Override
    public Field<String> field4() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.BUY_NOTE;
    }

    @Override
    public Field<String> field5() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.BUY_PHONE_NUMBER;
    }

    @Override
    public Field<String> field6() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.BUY_SELL_PHONE_NUMBER;
    }

    @Override
    public Field<String> field7() {
        return JCattleBuyInformation.CATTLE_BUY_INFORMATION.BUY_STORE_NAME;
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
    public LocalDate component3() {
        return getBuyDate();
    }

    @Override
    public String component4() {
        return getBuyNote();
    }

    @Override
    public String component5() {
        return getBuyPhoneNumber();
    }

    @Override
    public String component6() {
        return getBuySellPhoneNumber();
    }

    @Override
    public String component7() {
        return getBuyStoreName();
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
    public LocalDate value3() {
        return getBuyDate();
    }

    @Override
    public String value4() {
        return getBuyNote();
    }

    @Override
    public String value5() {
        return getBuyPhoneNumber();
    }

    @Override
    public String value6() {
        return getBuySellPhoneNumber();
    }

    @Override
    public String value7() {
        return getBuyStoreName();
    }

    @Override
    public JCattleBuyInformationRecord value1(String value) {
        setEntityIdentificationNumber(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value2(String value) {
        setEntityManagementNumber(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value3(LocalDate value) {
        setBuyDate(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value4(String value) {
        setBuyNote(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value5(String value) {
        setBuyPhoneNumber(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value6(String value) {
        setBuySellPhoneNumber(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord value7(String value) {
        setBuyStoreName(value);
        return this;
    }

    @Override
    public JCattleBuyInformationRecord values(String value1, String value2, LocalDate value3, String value4, String value5, String value6, String value7) {
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
     * Create a detached JCattleBuyInformationRecord
     */
    public JCattleBuyInformationRecord() {
        super(JCattleBuyInformation.CATTLE_BUY_INFORMATION);
    }

    /**
     * Create a detached, initialised JCattleBuyInformationRecord
     */
    public JCattleBuyInformationRecord(String entityIdentificationNumber, String entityManagementNumber, LocalDate buyDate, String buyNote, String buyPhoneNumber, String buySellPhoneNumber, String buyStoreName) {
        super(JCattleBuyInformation.CATTLE_BUY_INFORMATION);

        set(0, entityIdentificationNumber);
        set(1, entityManagementNumber);
        set(2, buyDate);
        set(3, buyNote);
        set(4, buyPhoneNumber);
        set(5, buySellPhoneNumber);
        set(6, buyStoreName);
    }
}