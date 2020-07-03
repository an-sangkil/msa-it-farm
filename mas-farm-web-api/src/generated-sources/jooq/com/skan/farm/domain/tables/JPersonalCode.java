/*
 * This file is generated by jOOQ.
 */
package com.skan.farm.domain.tables;


import com.skan.farm.domain.Indexes;
import com.skan.farm.domain.JNaiveItFarm;
import com.skan.farm.domain.Keys;
import com.skan.farm.domain.tables.records.JPersonalCodeRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
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
public class JPersonalCode extends TableImpl<JPersonalCodeRecord> {

    private static final long serialVersionUID = -1594306670;

    /**
     * The reference instance of <code>naive-it-farm.personal_code</code>
     */
    public static final JPersonalCode PERSONAL_CODE = new JPersonalCode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JPersonalCodeRecord> getRecordType() {
        return JPersonalCodeRecord.class;
    }

    /**
     * The column <code>naive-it-farm.personal_code.code</code>.
     */
    public final TableField<JPersonalCodeRecord, String> CODE = createField(DSL.name("code"), org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false), this, "");

    /**
     * The column <code>naive-it-farm.personal_code.upper_code</code>. 부모코드
     */
    public final TableField<JPersonalCodeRecord, String> UPPER_CODE = createField(DSL.name("upper_code"), org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false), this, "부모코드");

    /**
     * The column <code>naive-it-farm.personal_code.user_id</code>. 사용자 아이디
     */
    public final TableField<JPersonalCodeRecord, String> USER_ID = createField(DSL.name("user_id"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "사용자 아이디");

    /**
     * The column <code>naive-it-farm.personal_code.code_seq</code>. 순서
     */
    public final TableField<JPersonalCodeRecord, Integer> CODE_SEQ = createField(DSL.name("code_seq"), org.jooq.impl.SQLDataType.INTEGER, this, "순서");

    /**
     * The column <code>naive-it-farm.personal_code.code_name</code>. 코드명
     */
    public final TableField<JPersonalCodeRecord, String> CODE_NAME = createField(DSL.name("code_name"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "코드명");

    /**
     * The column <code>naive-it-farm.personal_code.code_comments</code>. 코드 상세
     */
    public final TableField<JPersonalCodeRecord, String> CODE_COMMENTS = createField(DSL.name("code_comments"), org.jooq.impl.SQLDataType.VARCHAR(256), this, "코드 상세");

    /**
     * The column <code>naive-it-farm.personal_code.created_time</code>. 생성일시
     */
    public final TableField<JPersonalCodeRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("created_time"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "생성일시");

    /**
     * Create a <code>naive-it-farm.personal_code</code> table reference
     */
    public JPersonalCode() {
        this(DSL.name("personal_code"), null);
    }

    /**
     * Create an aliased <code>naive-it-farm.personal_code</code> table reference
     */
    public JPersonalCode(String alias) {
        this(DSL.name(alias), PERSONAL_CODE);
    }

    /**
     * Create an aliased <code>naive-it-farm.personal_code</code> table reference
     */
    public JPersonalCode(Name alias) {
        this(alias, PERSONAL_CODE);
    }

    private JPersonalCode(Name alias, Table<JPersonalCodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private JPersonalCode(Name alias, Table<JPersonalCodeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> JPersonalCode(Table<O> child, ForeignKey<O, JPersonalCodeRecord> key) {
        super(child, key, PERSONAL_CODE);
    }

    @Override
    public Schema getSchema() {
        return JNaiveItFarm.NAIVE_IT_FARM;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PERSONAL_CODE_UPPER_CODE);
    }

    @Override
    public UniqueKey<JPersonalCodeRecord> getPrimaryKey() {
        return Keys.KEY_PERSONAL_CODE_PRIMARY;
    }

    @Override
    public List<UniqueKey<JPersonalCodeRecord>> getKeys() {
        return Arrays.<UniqueKey<JPersonalCodeRecord>>asList(Keys.KEY_PERSONAL_CODE_PRIMARY);
    }

    @Override
    public List<ForeignKey<JPersonalCodeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<JPersonalCodeRecord, ?>>asList(Keys.PERSONAL_CODE_IBFK_1);
    }

    public JPersonalCode personalCode() {
        return new JPersonalCode(this, Keys.PERSONAL_CODE_IBFK_1);
    }

    @Override
    public JPersonalCode as(String alias) {
        return new JPersonalCode(DSL.name(alias), this);
    }

    @Override
    public JPersonalCode as(Name alias) {
        return new JPersonalCode(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JPersonalCode rename(String name) {
        return new JPersonalCode(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JPersonalCode rename(Name name) {
        return new JPersonalCode(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, Integer, String, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}