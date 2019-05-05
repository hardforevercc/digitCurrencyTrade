package com.okex.mybatis.dao;

import com.okex.mybatis.model.OkexGridConfig;
import com.okex.mybatis.model.OkexGridConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OkexGridConfigMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(OkexGridConfigExample example);

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
    int insert(OkexGridConfig record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(OkexGridConfig record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<OkexGridConfig> selectByExample(OkexGridConfigExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OkexGridConfig selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OkexGridConfig record, @Param("example") OkexGridConfigExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OkexGridConfig record, @Param("example") OkexGridConfigExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OkexGridConfig record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OkexGridConfig record);
}