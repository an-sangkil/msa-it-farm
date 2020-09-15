/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables;


import com.skan.farm.domain.JNaiveItFarm;
import com.skan.farm.domain.Keys;
import com.skan.farm.domain.tables.records.JVaccinationRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
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
public class JVaccination extends TableImpl<JVaccinationRecord> {

    private static final long serialVersionUID = -1804555706;

    /**
     * The reference instance of <code>naive-it-farm.vaccination</code>
     */
    public static final JVaccination VACCINATION = new JVaccination();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JVaccinationRecord> getRecordType() {
        return JVaccinationRecord.class;
    }

    /**
     * The column <code>naive-it-farm.vaccination.day</code>.
     */
    public final TableField<JVaccinationRecord, LocalDate> DAY = createField(DSL.name("day"), org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.entity_identification_number</code>.
     */
    public final TableField<JVaccinationRecord, String> ENTITY_IDENTIFICATION_NUMBER = createField(DSL.name("entity_identification_number"), org.jooq.impl.SQLDataType.VARCHAR(12).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.entity_management_number</code>.
     */
    public final TableField<JVaccinationRecord, String> ENTITY_MANAGEMENT_NUMBER = createField(DSL.name("entity_management_number"), org.jooq.impl.SQLDataType.VARCHAR(8).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.seq</code>.
     */
    public final TableField<JVaccinationRecord, Short> SEQ = createField(DSL.name("seq"), org.jooq.impl.SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.created_time</code>.
     */
    public final TableField<JVaccinationRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("created_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.vaccination.location</code>.
     */
    public final TableField<JVaccinationRecord, String> LOCATION = createField(DSL.name("location"), org.jooq.impl.SQLDataType.VARCHAR(32), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.modified_time</code>.
     */
    public final TableField<JVaccinationRecord, LocalDateTime> MODIFIED_TIME = createField(DSL.name("modified_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.vaccination.remark</code>.
     */
    public final TableField<JVaccinationRecord, String> REMARK = createField(DSL.name("remark"), org.jooq.impl.SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>naive-it-farm.vaccination.vaccination_type</code>.
     */
    public final TableField<JVaccinationRecord, String> VACCINATION_TYPE = createField(DSL.name("vaccination_type"), org.jooq.impl.SQLDataType.VARCHAR(32), this, "");

    /**
     * Create a <code>naive-it-farm.vaccination</code> table reference
     */
    public JVaccination() {
        this(DSL.name("vaccination"), null);
    }

    /**
     * Create an aliased <code>naive-it-farm.vaccination</code> table reference
     */
    public JVaccination(String alias) {
        this(DSL.name(alias), VACCINATION);
    }

    /**
     * Create an aliased <code>naive-it-farm.vaccination</code> table reference
     */
    public JVaccination(Name alias) {
        this(alias, VACCINATION);
    }

    private JVaccination(Name alias, Table<JVaccinationRecord> aliased) {
        this(alias, aliased, null);
    }

    private JVaccination(Name alias, Table<JVaccinationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> JVaccination(Table<O> child, ForeignKey<O, JVaccinationRecord> key) {
        super(child, key, VACCINATION);
    }

    @Override
    public Schema getSchema() {
        return JNaiveItFarm.NAIVE_IT_FARM;
    }

    @Override
    public UniqueKey<JVaccinationRecord> getPrimaryKey() {
        return Keys.KEY_VACCINATION_PRIMARY;
    }

    @Override
    public List<UniqueKey<JVaccinationRecord>> getKeys() {
        return Arrays.<UniqueKey<JVaccinationRecord>>asList(Keys.KEY_VACCINATION_PRIMARY);
    }

    @Override
    public JVaccination as(String alias) {
        return new JVaccination(DSL.name(alias), this);
    }

    @Override
    public JVaccination as(Name alias) {
        return new JVaccination(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JVaccination rename(String name) {
        return new JVaccination(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JVaccination rename(Name name) {
        return new JVaccination(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<LocalDate, String, String, Short, LocalDateTime, String, LocalDateTime, String, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}