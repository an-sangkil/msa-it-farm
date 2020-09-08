/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables;


import com.skan.farm.domain.JNaiveItFarm;
import com.skan.farm.domain.Keys;
import com.skan.farm.domain.tables.records.JLocalBeefManagementRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JLocalBeefManagement extends TableImpl<JLocalBeefManagementRecord> {

    private static final long serialVersionUID = 1100192504;

    /**
     * The reference instance of <code>naive-it-farm.local_beef_management</code>
     */
    public static final JLocalBeefManagement LOCAL_BEEF_MANAGEMENT = new JLocalBeefManagement();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JLocalBeefManagementRecord> getRecordType() {
        return JLocalBeefManagementRecord.class;
    }

    /**
     * The column <code>naive-it-farm.local_beef_management.entity_identification_number</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> ENTITY_IDENTIFICATION_NUMBER = createField(DSL.name("entity_identification_number"), org.jooq.impl.SQLDataType.VARCHAR(12).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.entity_management_number</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> ENTITY_MANAGEMENT_NUMBER = createField(DSL.name("entity_management_number"), org.jooq.impl.SQLDataType.VARCHAR(8).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.birth_day</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDate> BIRTH_DAY = createField(DSL.name("birth_day"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.castration_date</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDate> CASTRATION_DATE = createField(DSL.name("castration_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.created_time</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("created_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.delete_yn</code>.
     */
    public final TableField<JLocalBeefManagementRecord, Boolean> DELETE_YN = createField(DSL.name("delete_yn"), org.jooq.impl.SQLDataType.BIT, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.ear_tag_date</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDate> EAR_TAG_DATE = createField(DSL.name("ear_tag_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.enter_date</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDate> ENTER_DATE = createField(DSL.name("enter_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.gender</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> GENDER = createField(DSL.name("gender"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.location</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> LOCATION = createField(DSL.name("location"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.modified_time</code>.
     */
    public final TableField<JLocalBeefManagementRecord, LocalDateTime> MODIFIED_TIME = createField(DSL.name("modified_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.parent_mom_no</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> PARENT_MOM_NO = createField(DSL.name("parent_mom_no"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.parent_papa_no</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> PARENT_PAPA_NO = createField(DSL.name("parent_papa_no"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.sell_yn</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> SELL_YN = createField(DSL.name("sell_yn"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.local_beef_management.user_id</code>.
     */
    public final TableField<JLocalBeefManagementRecord, String> USER_ID = createField(DSL.name("user_id"), org.jooq.impl.SQLDataType.VARCHAR(32), this, "");

    /**
     * Create a <code>naive-it-farm.local_beef_management</code> table reference
     */
    public JLocalBeefManagement() {
        this(DSL.name("local_beef_management"), null);
    }

    /**
     * Create an aliased <code>naive-it-farm.local_beef_management</code> table reference
     */
    public JLocalBeefManagement(String alias) {
        this(DSL.name(alias), LOCAL_BEEF_MANAGEMENT);
    }

    /**
     * Create an aliased <code>naive-it-farm.local_beef_management</code> table reference
     */
    public JLocalBeefManagement(Name alias) {
        this(alias, LOCAL_BEEF_MANAGEMENT);
    }

    private JLocalBeefManagement(Name alias, Table<JLocalBeefManagementRecord> aliased) {
        this(alias, aliased, null);
    }

    private JLocalBeefManagement(Name alias, Table<JLocalBeefManagementRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> JLocalBeefManagement(Table<O> child, ForeignKey<O, JLocalBeefManagementRecord> key) {
        super(child, key, LOCAL_BEEF_MANAGEMENT);
    }

    @Override
    public Schema getSchema() {
        return JNaiveItFarm.NAIVE_IT_FARM;
    }

    @Override
    public UniqueKey<JLocalBeefManagementRecord> getPrimaryKey() {
        return Keys.KEY_LOCAL_BEEF_MANAGEMENT_PRIMARY;
    }

    @Override
    public List<UniqueKey<JLocalBeefManagementRecord>> getKeys() {
        return Arrays.<UniqueKey<JLocalBeefManagementRecord>>asList(Keys.KEY_LOCAL_BEEF_MANAGEMENT_PRIMARY, Keys.KEY_LOCAL_BEEF_MANAGEMENT_UK_3BXCO9DXGOLPQ8R9JPHAI7V9R);
    }

    @Override
    public JLocalBeefManagement as(String alias) {
        return new JLocalBeefManagement(DSL.name(alias), this);
    }

    @Override
    public JLocalBeefManagement as(Name alias) {
        return new JLocalBeefManagement(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JLocalBeefManagement rename(String name) {
        return new JLocalBeefManagement(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JLocalBeefManagement rename(Name name) {
        return new JLocalBeefManagement(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row15<String, String, LocalDate, LocalDate, LocalDateTime, Boolean, LocalDate, LocalDate, String, String, LocalDateTime, String, String, String, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
