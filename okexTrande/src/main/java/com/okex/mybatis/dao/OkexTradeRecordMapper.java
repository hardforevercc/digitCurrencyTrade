package com.okex.mybatis.dao;

import com.okex.mybatis.model.OkexTradeRecord;
import com.okex.mybatis.model.OkexTradeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OkexTradeRecordMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(OkexTradeRecordExample example);

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
    int insert(OkexTradeRecord record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(OkexTradeRecord record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<OkexTradeRecord> selectByExample(OkexTradeRecordExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    OkexTradeRecord selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OkexTradeRecord record, @Param("example") OkexTradeRecordExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OkexTradeRecord record, @Param("example") OkexTradeRecordExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OkexTradeRecord record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(OkexTradeRecord record);
}