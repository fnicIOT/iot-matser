package com.fnic.mybatis.thingsboard.dao;

import com.fnic.mybatis.thingsboard.model.TsKvLatest;
import com.fnic.mybatis.thingsboard.model.TsKvLatestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsKvLatestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    long countByExample(TsKvLatestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    int deleteByExample(TsKvLatestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    int insert(TsKvLatest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    int insertSelective(TsKvLatest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    List<TsKvLatest> selectByExample(TsKvLatestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TsKvLatest record, @Param("example") TsKvLatestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_kv_latest
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TsKvLatest record, @Param("example") TsKvLatestExample example);
}