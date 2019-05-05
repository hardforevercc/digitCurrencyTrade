package com.okex.mybatis.dao;

import com.okex.mybatis.model.OkexGridPlan;
import com.okex.mybatis.model.OkexGridPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OkexGridPlanMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(OkexGridPlanExample example);

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
    int insert(OkexGridPlan record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(OkexGridPlan record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<OkexGridPlan> selectByExample(OkexGridPlanExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OkexGridPlan selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OkexGridPlan record, @Param("example") OkexGridPlanExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OkexGridPlan record, @Param("example") OkexGridPlanExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OkexGridPlan record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OkexGridPlan record);
}