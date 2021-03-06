/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables;


import com.skan.farm.domain.JNaiveItFarm;
import com.skan.farm.domain.Keys;
import com.skan.farm.domain.tables.records.JDiseaseTreatmentRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row11;
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
public class JDiseaseTreatment extends TableImpl<JDiseaseTreatmentRecord> {

    private static final long serialVersionUID = 327251068;

    /**
     * The reference instance of <code>naive-it-farm.disease_treatment</code>
     */
    public static final JDiseaseTreatment DISEASE_TREATMENT = new JDiseaseTreatment();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JDiseaseTreatmentRecord> getRecordType() {
        return JDiseaseTreatmentRecord.class;
    }

    /**
     * The column <code>naive-it-farm.disease_treatment.day</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, LocalDate> DAY = createField(DSL.name("day"), org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.entity_identification_number</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> ENTITY_IDENTIFICATION_NUMBER = createField(DSL.name("entity_identification_number"), org.jooq.impl.SQLDataType.VARCHAR(12).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.entity_management_number</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> ENTITY_MANAGEMENT_NUMBER = createField(DSL.name("entity_management_number"), org.jooq.impl.SQLDataType.VARCHAR(8).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.created_time</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("created_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.cure_age_of_month</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, Short> CURE_AGE_OF_MONTH = createField(DSL.name("cure_age_of_month"), org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.cure_date</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, LocalDate> CURE_DATE = createField(DSL.name("cure_date"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.disease_name</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> DISEASE_NAME = createField(DSL.name("disease_name"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.modified_time</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, LocalDateTime> MODIFIED_TIME = createField(DSL.name("modified_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.remark</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> REMARK = createField(DSL.name("remark"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.symptom</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> SYMPTOM = createField(DSL.name("symptom"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>naive-it-farm.disease_treatment.treatment_details</code>.
     */
    public final TableField<JDiseaseTreatmentRecord, String> TREATMENT_DETAILS = createField(DSL.name("treatment_details"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>naive-it-farm.disease_treatment</code> table reference
     */
    public JDiseaseTreatment() {
        this(DSL.name("disease_treatment"), null);
    }

    /**
     * Create an aliased <code>naive-it-farm.disease_treatment</code> table reference
     */
    public JDiseaseTreatment(String alias) {
        this(DSL.name(alias), DISEASE_TREATMENT);
    }

    /**
     * Create an aliased <code>naive-it-farm.disease_treatment</code> table reference
     */
    public JDiseaseTreatment(Name alias) {
        this(alias, DISEASE_TREATMENT);
    }

    private JDiseaseTreatment(Name alias, Table<JDiseaseTreatmentRecord> aliased) {
        this(alias, aliased, null);
    }

    private JDiseaseTreatment(Name alias, Table<JDiseaseTreatmentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> JDiseaseTreatment(Table<O> child, ForeignKey<O, JDiseaseTreatmentRecord> key) {
        super(child, key, DISEASE_TREATMENT);
    }

    @Override
    public Schema getSchema() {
        return JNaiveItFarm.NAIVE_IT_FARM;
    }

    @Override
    public UniqueKey<JDiseaseTreatmentRecord> getPrimaryKey() {
        return Keys.KEY_DISEASE_TREATMENT_PRIMARY;
    }

    @Override
    public List<UniqueKey<JDiseaseTreatmentRecord>> getKeys() {
        return Arrays.<UniqueKey<JDiseaseTreatmentRecord>>asList(Keys.KEY_DISEASE_TREATMENT_PRIMARY);
    }

    @Override
    public List<ForeignKey<JDiseaseTreatmentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<JDiseaseTreatmentRecord, ?>>asList(Keys.FKQPKXO8RKFQVR65KXY88EEXRR1);
    }

    public JLocalBeefManagement localBeefManagement() {
        return new JLocalBeefManagement(this, Keys.FKQPKXO8RKFQVR65KXY88EEXRR1);
    }

    @Override
    public JDiseaseTreatment as(String alias) {
        return new JDiseaseTreatment(DSL.name(alias), this);
    }

    @Override
    public JDiseaseTreatment as(Name alias) {
        return new JDiseaseTreatment(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JDiseaseTreatment rename(String name) {
        return new JDiseaseTreatment(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JDiseaseTreatment rename(Name name) {
        return new JDiseaseTreatment(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<LocalDate, String, String, LocalDateTime, Short, LocalDate, String, LocalDateTime, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}
