package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusExist;

public interface IbusExistMapper {
	
	int deleteByTableName(String tableName);
	
	IbusExist findByTableName(String tableName);
	
	List<IbusExist> selectAll();
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    int insert(IbusExist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    int insertSelective(IbusExist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    IbusExist selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    int updateByPrimaryKeySelective(IbusExist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ibus_exist
     *
     * @mbggenerated Tue Nov 01 14:35:32 CST 2016
     */
    int updateByPrimaryKey(IbusExist record);
}