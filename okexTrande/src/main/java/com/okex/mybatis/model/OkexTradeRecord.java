package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class OkexTradeRecord {
    /**
     * 自增ID
     * 表 : okex_trade_record
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 当前价格
     * 表 : okex_trade_record
     * 对应字段 : curprice
     */
    private BigDecimal curprice;

    /**
     * 当前数量
     * 表 : okex_trade_record
     * 对应字段 : curamount
     */
    private BigDecimal curamount;

    /**
     * 总金额
     * 表 : okex_trade_record
     * 对应字段 : amt
     */
    private BigDecimal amt;

    /**
     * 类型:sell 卖出 buy买入
     * 表 : okex_trade_record
     * 对应字段 : type
     */
    private String type;

    /**
     * 交易是否成功 Y成功 N失败
     * 表 : okex_trade_record
     * 对应字段 : isOk
     */
    private String isok;

    /**
     * 
     * 表 : okex_trade_record
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * get method 
     *
     * @return okex_trade_record.id：自增ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * set method 
     *
     * @param id  自增ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get method 
     *
     * @return okex_trade_record.curprice：当前价格
     */
    public BigDecimal getCurprice() {
        return curprice;
    }

    /**
     * set method 
     *
     * @param curprice  当前价格
     */
    public void setCurprice(BigDecimal curprice) {
        this.curprice = curprice;
    }

    /**
     * get method 
     *
     * @return okex_trade_record.curamount：当前数量
     */
    public BigDecimal getCuramount() {
        return curamount;
    }

    /**
     * set method 
     *
     * @param curamount  当前数量
     */
    public void setCuramount(BigDecimal curamount) {
        this.curamount = curamount;
    }

    /**
     * get method 
     *
     * @return okex_trade_record.amt：总金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * set method 
     *
     * @param amt  总金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * get method 
     *
     * @return okex_trade_record.type：类型:sell 卖出 buy买入
     */
    public String getType() {
        return type;
    }

    /**
     * set method 
     *
     * @param type  类型:sell 卖出 buy买入
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * get method 
     *
     * @return okex_trade_record.isOk：交易是否成功 Y成功 N失败
     */
    public String getIsok() {
        return isok;
    }

    /**
     * set method 
     *
     * @param isok  交易是否成功 Y成功 N失败
     */
    public void setIsok(String isok) {
        this.isok = isok == null ? null : isok.trim();
    }

    /**
     * get method 
     *
     * @return okex_trade_record.create_date：
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}