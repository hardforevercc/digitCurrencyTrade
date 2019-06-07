package com.okex.mybatis.dao;

import com.okex.mybatis.model.OkexDealChangePlan;
import com.okex.mybatis.model.OkexDealChangePlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OkexDealChangePlanMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(OkexDealChangePlanExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(OkexDealChangePlan record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(OkexDealChangePlan record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<OkexDealChangePlan> selectByExample(OkexDealChangePlanExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OkexDealChangePlan selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OkexDealChangePlan record, @Param("example") OkexDealChangePlanExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OkexDealChangePlan record, @Param("example") OkexDealChangePlanExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OkexDealChangePlan record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OkexDealChangePlan record);
}