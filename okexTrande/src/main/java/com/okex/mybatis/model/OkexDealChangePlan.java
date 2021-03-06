package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class OkexDealChangePlan {
    /**
     * 
     * 表 : okex_deal_change_plan
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 配置ID
     * 表 : okex_deal_change_plan
     * 对应字段 : config_id
     */
    private Integer configId;

    /**
     * 策略类型
     * 表 : okex_deal_change_plan
     * 对应字段 : type
     */
    private String type;

    /**
     * 币种
     * 表 : okex_deal_change_plan
     * 对应字段 : currency
     */
    private String currency;

    /**
     * 买入ID
     * 表 : okex_deal_change_plan
     * 对应字段 : buyId
     */
    private String buyid;

    /**
     * OKEX订单号
     * 表 : okex_deal_change_plan
     * 对应字段 : buyOrderId
     */
    private String buyorderid;

    /**
     * 卖出ID
     * 表 : okex_deal_change_plan
     * 对应字段 : sellId
     */
    private String sellid;

    /**
     * OKEX订单号
     * 表 : okex_deal_change_plan
     * 对应字段 : sellOrderId
     */
    private String sellorderid;

    /**
     * 预期买入价格
     * 表 : okex_deal_change_plan
     * 对应字段 : buyPrice
     */
    private BigDecimal buyprice;

    /**
     * 预期卖出价格
     * 表 : okex_deal_change_plan
     * 对应字段 : sellPrice
     */
    private BigDecimal sellprice;

    /**
     * 预期买入数量
     * 表 : okex_deal_change_plan
     * 对应字段 : amount
     */
    private BigDecimal amount;

    /**
     * 买入总金额
     * 表 : okex_deal_change_plan
     * 对应字段 : buyAmt
     */
    private BigDecimal buyamt;

    /**
     * 实际买入价格
     * 表 : okex_deal_change_plan
     * 对应字段 : actBuyAmt
     */
    private BigDecimal actbuyamt;

    /**
     * 实际买入量
     * 表 : okex_deal_change_plan
     * 对应字段 : actbuyAmount
     */
    private BigDecimal actbuyamount;

    /**
     * 实际卖出价格
     * 表 : okex_deal_change_plan
     * 对应字段 : actSellAmt
     */
    private BigDecimal actsellamt;

    /**
     * 实际卖出数量
     * 表 : okex_deal_change_plan
     * 对应字段 : actSellAmount
     */
    private BigDecimal actsellamount;

    /**
     * 买入订单状态("-2":失败,"-1":撤单成功,"0":等待成交 ,"1":部分成交, "2":完全成交,"3":下单中,"4":撤单中 ）
     * 表 : okex_deal_change_plan
     * 对应字段 : buySts
     */
    private String buysts;

    /**
     * 卖出状态: 0 初始 
1 交易成功
2 交易失败，已撤单
3 挂单
     * 表 : okex_deal_change_plan
     * 对应字段 : sellSts
     */
    private String sellsts;

    /**
     * 
     * 表 : okex_deal_change_plan
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 
     * 表 : okex_deal_change_plan
     * 对应字段 : update_date
     */
    private Date updateDate;

    /**
     * get method 
     *
     * @return okex_deal_change_plan.id：
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
     * @return okex_deal_change_plan.config_id：配置ID
     */
    public Integer getConfigId() {
        return configId;
    }

    /**
     * set method 
     *
     * @param configId  配置ID
     */
    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.type：策略类型
     */
    public String getType() {
        return type;
    }

    /**
     * set method 
     *
     * @param type  策略类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.currency：币种
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
     * @return okex_deal_change_plan.buyId：买入ID
     */
    public String getBuyid() {
        return buyid;
    }

    /**
     * set method 
     *
     * @param buyid  买入ID
     */
    public void setBuyid(String buyid) {
        this.buyid = buyid == null ? null : buyid.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.buyOrderId：OKEX订单号
     */
    public String getBuyorderid() {
        return buyorderid;
    }

    /**
     * set method 
     *
     * @param buyorderid  OKEX订单号
     */
    public void setBuyorderid(String buyorderid) {
        this.buyorderid = buyorderid == null ? null : buyorderid.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.sellId：卖出ID
     */
    public String getSellid() {
        return sellid;
    }

    /**
     * set method 
     *
     * @param sellid  卖出ID
     */
    public void setSellid(String sellid) {
        this.sellid = sellid == null ? null : sellid.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.sellOrderId：OKEX订单号
     */
    public String getSellorderid() {
        return sellorderid;
    }

    /**
     * set method 
     *
     * @param sellorderid  OKEX订单号
     */
    public void setSellorderid(String sellorderid) {
        this.sellorderid = sellorderid == null ? null : sellorderid.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.buyPrice：预期买入价格
     */
    public BigDecimal getBuyprice() {
        return buyprice;
    }

    /**
     * set method 
     *
     * @param buyprice  预期买入价格
     */
    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.sellPrice：预期卖出价格
     */
    public BigDecimal getSellprice() {
        return sellprice;
    }

    /**
     * set method 
     *
     * @param sellprice  预期卖出价格
     */
    public void setSellprice(BigDecimal sellprice) {
        this.sellprice = sellprice;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.amount：预期买入数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * set method 
     *
     * @param amount  预期买入数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.buyAmt：买入总金额
     */
    public BigDecimal getBuyamt() {
        return buyamt;
    }

    /**
     * set method 
     *
     * @param buyamt  买入总金额
     */
    public void setBuyamt(BigDecimal buyamt) {
        this.buyamt = buyamt;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.actBuyAmt：实际买入价格
     */
    public BigDecimal getActbuyamt() {
        return actbuyamt;
    }

    /**
     * set method 
     *
     * @param actbuyamt  实际买入价格
     */
    public void setActbuyamt(BigDecimal actbuyamt) {
        this.actbuyamt = actbuyamt;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.actbuyAmount：实际买入量
     */
    public BigDecimal getActbuyamount() {
        return actbuyamount;
    }

    /**
     * set method 
     *
     * @param actbuyamount  实际买入量
     */
    public void setActbuyamount(BigDecimal actbuyamount) {
        this.actbuyamount = actbuyamount;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.actSellAmt：实际卖出价格
     */
    public BigDecimal getActsellamt() {
        return actsellamt;
    }

    /**
     * set method 
     *
     * @param actsellamt  实际卖出价格
     */
    public void setActsellamt(BigDecimal actsellamt) {
        this.actsellamt = actsellamt;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.actSellAmount：实际卖出数量
     */
    public BigDecimal getActsellamount() {
        return actsellamount;
    }

    /**
     * set method 
     *
     * @param actsellamount  实际卖出数量
     */
    public void setActsellamount(BigDecimal actsellamount) {
        this.actsellamount = actsellamount;
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.buySts：买入订单状态("-2":失败,"-1":撤单成功,"0":等待成交 ,"1":部分成交, "2":完全成交,"3":下单中,"4":撤单中 ）
     */
    public String getBuysts() {
        return buysts;
    }

    /**
     * set method 
     *
     * @param buysts  买入订单状态("-2":失败,"-1":撤单成功,"0":等待成交 ,"1":部分成交, "2":完全成交,"3":下单中,"4":撤单中 ）
     */
    public void setBuysts(String buysts) {
        this.buysts = buysts == null ? null : buysts.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.sellSts：卖出状态: 0 初始 
1 交易成功
2 交易失败，已撤单
3 挂单
     */
    public String getSellsts() {
        return sellsts;
    }

    /**
     * set method 
     *
     * @param sellsts  卖出状态: 0 初始 
1 交易成功
2 交易失败，已撤单
3 挂单
     */
    public void setSellsts(String sellsts) {
        this.sellsts = sellsts == null ? null : sellsts.trim();
    }

    /**
     * get method 
     *
     * @return okex_deal_change_plan.create_date：
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

    /**
     * get method 
     *
     * @return okex_deal_change_plan.update_date：
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * set method 
     *
     * @param updateDate  
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}