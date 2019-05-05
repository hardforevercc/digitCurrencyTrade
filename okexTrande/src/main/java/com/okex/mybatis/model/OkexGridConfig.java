package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class OkexGridConfig {
    /**
     * 
     * 表 : okex_grid_config
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 币种
     * 表 : okex_grid_config
     * 对应字段 : currency
     */
    private String currency;

    /**
     * 下跌百分比
     * 表 : okex_grid_config
     * 对应字段 : x
     */
    private Double x;

    /**
     * 下跌最大百分比
     * 表 : okex_grid_config
     * 对应字段 : y
     */
    private Double y;

    /**
     * 资金段数
     * 表 : okex_grid_config
     * 对应字段 : n
     */
    private Integer n;

    /**
     * 总金额
     * 表 : okex_grid_config
     * 对应字段 : totalAmt
     */
    private BigDecimal totalamt;

    /**
     * 创建日期
     * 表 : okex_grid_config
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 更新日期
     * 表 : okex_grid_config
     * 对应字段 : update_date
     */
    private Date updateDate;

    /**
     * get method 
     *
     * @return okex_grid_config.id：
     */
    public Integer getId() {
        return id;
    }

    /**
     * set method 
     *
     * @param id  
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.currency：币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * set method 
     *
     * @param currency  币种
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * get method 
     *
     * @return okex_grid_config.x：下跌百分比
     */
    public Double getX() {
        return x;
    }

    /**
     * set method 
     *
     * @param x  下跌百分比
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.y：下跌最大百分比
     */
    public Double getY() {
        return y;
    }

    /**
     * set method 
     *
     * @param y  下跌最大百分比
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.n：资金段数
     */
    public Integer getN() {
        return n;
    }

    /**
     * set method 
     *
     * @param n  资金段数
     */
    public void setN(Integer n) {
        this.n = n;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.totalAmt：总金额
     */
    public BigDecimal getTotalamt() {
        return totalamt;
    }

    /**
     * set method 
     *
     * @param totalamt  总金额
     */
    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.create_date：创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * get method 
     *
     * @return okex_grid_config.update_date：更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * set method 
     *
     * @param updateDate  更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}