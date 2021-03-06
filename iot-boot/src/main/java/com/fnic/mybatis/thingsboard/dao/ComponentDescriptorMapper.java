package com.fnic.mybatis.thingsboard.dao;

import com.fnic.mybatis.thingsboard.model.ComponentDescriptor;
import com.fnic.mybatis.thingsboard.model.ComponentDescriptorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComponentDescriptorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    long countByExample(ComponentDescriptorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int deleteByExample(ComponentDescriptorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int insert(ComponentDescriptor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int insertSelective(ComponentDescriptor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    List<ComponentDescriptor> selectByExample(ComponentDescriptorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    ComponentDescriptor selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComponentDescriptor record, @Param("example") ComponentDescriptorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComponentDescriptor record, @Param("example") ComponentDescriptorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComponentDescriptor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table component_descriptor
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComponentDescriptor record);
}