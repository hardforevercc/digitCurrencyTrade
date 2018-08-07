package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class OkexTradeInfo {
    /**
     * id
     * 表 : okex_trand_info
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 交易对名称
     * 表 : okex_trand_info
     * 对应字段 : traddes_name
     */
    private String traddesName;

    /**
     * 
     * 时间戳
     * 表 : okex_trand_info
     * 对应字段 : timestamp
     */
    private String timestamp;

    /**
     * 买一价
     * 表 : okex_trand_info
     * 对应字段 : ticker_buy
     */
    private BigDecimal tickerBuy;

    /**
     * 最高成交价
     * 表 : okex_trand_info
     * 对应字段 : ticker_high
     */
    private BigDecimal tickerHigh;

    /**
     * 最近成交价
     * 表 : okex_trand_info
     * 对应字段 : ticker_last
     */
    private BigDecimal tickerLast;

    /**
     * 最低成交价
     * 表 : okex_trand_info
     * 对应字段 : ticker_low
     */
    private BigDecimal tickerLow;

    /**
     * 卖一价
     * 表 : okex_trand_info
     * 对应字段 : ticker_sell
     */
    private BigDecimal tickerSell;

    /**
     * 成交量(最近24小时)
     * 表 : okex_trand_info
     * 对应字段 : ticker_vol
     */
    private BigDecimal tickerVol;

    /**
     * 最大卖出交易价格(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_max_price
     */
    private BigDecimal tradeSellMaxPrice;

    /**
     * 最大卖出交易量(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_max_amount
     */
    private BigDecimal tradeSellMaxAmount;

    /**
     * 最小卖出交易价格(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_min_price
     */
    private BigDecimal tradeSellMinPrice;

    /**
     * 最小卖出交易量(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_min_amount
     */
    private BigDecimal tradeSellMinAmount;

    /**
     * 平均卖出价格(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_avg_price
     */
    private BigDecimal tradeSellAvgPrice;

    /**
     * 平均卖出交易量(近200笔）
     * 表 : okex_trand_info
     * 对应字段 : trade_sell_avg_amount
     */
    private BigDecimal tradeSellAvgAmount;

    /**
     * 最大买入价格
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_max_price
     */
    private BigDecimal tradeBuyMaxPrice;

    /**
     * 最大买入交易量
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_max_amount
     */
    private BigDecimal tradeBuyMaxAmount;

    /**
     * 最小买入价格
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_min_price
     */
    private BigDecimal tradeBuyMinPrice;

    /**
     * 最小买入量
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_min_amount
     */
    private BigDecimal tradeBuyMinAmount;

    /**
     * 平均买入价格
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_avg_price
     */
    private BigDecimal tradeBuyAvgPrice;

    /**
     * 平均买入量
     * 表 : okex_trand_info
     * 对应字段 : trade_buy_avg_amount
     */
    private BigDecimal tradeBuyAvgAmount;

    /**
     * 获取卖出数量
     * 表 : okex_trand_info
     * 对应字段 : trad_sell_nums
     */
    private Integer tradSellNums;

    /**
     * 获取买入数量
     * 表 : okex_trand_info
     * 对应字段 : trad_buy_nums
     */
    private Integer tradBuyNums;

    /**
     * 最大卖方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_ask_max
     */
    private BigDecimal depAskMax;

    /**
     * 最小卖方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_ask_min
     */
    private BigDecimal depAskMin;

    /**
     * 平均卖方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_ask_avg
     */
    private BigDecimal depAskAvg;

    /**
     * 最大买方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_bid_max
     */
    private BigDecimal depBidMax;

    /**
     * 最小买方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_bid_min
     */
    private BigDecimal depBidMin;

    /**
     * 平均买方深度
     * 表 : okex_trand_info
     * 对应字段 : dep_bid_avg
     */
    private BigDecimal depBidAvg;

    /**
     * 获取卖方深度条数
     * 表 : okex_trand_info
     * 对应字段 : dep_ask_nums
     */
    private Integer depAskNums;

    /**
     * 获取卖方深度条数
     * 表 : okex_trand_info
     * 对应字段 : dep_bid_nums
     */
    private Integer depBidNums;

    /**
     * BTC价格
     * 表 : okex_trand_info
     * 对应字段 : usdt_btc
     */
    private BigDecimal usdtBtc;

    /**
     * 创建时间
     * 表 : okex_trand_info
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * get method 
     *
     * @return okex_trand_info.id：id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set method 
     *
     * @param id  id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.traddes_name：交易对名称
     */
    public String getTraddesName() {
        return traddesName;
    }

    /**
     * set method 
     *
     * @param traddesName  交易对名称
     */
    public void setTraddesName(String traddesName) {
        this.traddesName = traddesName == null ? null : traddesName.trim();
    }

    /**
     * get method 
     *
     * @return okex_trand_info.timestamp：时间戳
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * set method 
     *
     * @param timestamp  时间戳
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp == null ? null : timestamp.trim();
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_buy：买一价
     */
    public BigDecimal getTickerBuy() {
        return tickerBuy;
    }

    /**
     * set method 
     *
     * @param tickerBuy  买一价
     */
    public void setTickerBuy(BigDecimal tickerBuy) {
        this.tickerBuy = tickerBuy;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_high：最高成交价
     */
    public BigDecimal getTickerHigh() {
        return tickerHigh;
    }

    /**
     * set method 
     *
     * @param tickerHigh  最高成交价
     */
    public void setTickerHigh(BigDecimal tickerHigh) {
        this.tickerHigh = tickerHigh;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_last：最近成交价
     */
    public BigDecimal getTickerLast() {
        return tickerLast;
    }

    /**
     * set method 
     *
     * @param tickerLast  最近成交价
     */
    public void setTickerLast(BigDecimal tickerLast) {
        this.tickerLast = tickerLast;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_low：最低成交价
     */
    public BigDecimal getTickerLow() {
        return tickerLow;
    }

    /**
     * set method 
     *
     * @param tickerLow  最低成交价
     */
    public void setTickerLow(BigDecimal tickerLow) {
        this.tickerLow = tickerLow;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_sell：卖一价
     */
    public BigDecimal getTickerSell() {
        return tickerSell;
    }

    /**
     * set method 
     *
     * @param tickerSell  卖一价
     */
    public void setTickerSell(BigDecimal tickerSell) {
        this.tickerSell = tickerSell;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.ticker_vol：成交量(最近24小时)
     */
    public BigDecimal getTickerVol() {
        return tickerVol;
    }

    /**
     * set method 
     *
     * @param tickerVol  成交量(最近24小时)
     */
    public void setTickerVol(BigDecimal tickerVol) {
        this.tickerVol = tickerVol;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_max_price：最大卖出交易价格(近200笔）
     */
    public BigDecimal getTradeSellMaxPrice() {
        return tradeSellMaxPrice;
    }

    /**
     * set method 
     *
     * @param tradeSellMaxPrice  最大卖出交易价格(近200笔）
     */
    public void setTradeSellMaxPrice(BigDecimal tradeSellMaxPrice) {
        this.tradeSellMaxPrice = tradeSellMaxPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_max_amount：最大卖出交易量(近200笔）
     */
    public BigDecimal getTradeSellMaxAmount() {
        return tradeSellMaxAmount;
    }

    /**
     * set method 
     *
     * @param tradeSellMaxAmount  最大卖出交易量(近200笔）
     */
    public void setTradeSellMaxAmount(BigDecimal tradeSellMaxAmount) {
        this.tradeSellMaxAmount = tradeSellMaxAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_min_price：最小卖出交易价格(近200笔）
     */
    public BigDecimal getTradeSellMinPrice() {
        return tradeSellMinPrice;
    }

    /**
     * set method 
     *
     * @param tradeSellMinPrice  最小卖出交易价格(近200笔）
     */
    public void setTradeSellMinPrice(BigDecimal tradeSellMinPrice) {
        this.tradeSellMinPrice = tradeSellMinPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_min_amount：最小卖出交易量(近200笔）
     */
    public BigDecimal getTradeSellMinAmount() {
        return tradeSellMinAmount;
    }

    /**
     * set method 
     *
     * @param tradeSellMinAmount  最小卖出交易量(近200笔）
     */
    public void setTradeSellMinAmount(BigDecimal tradeSellMinAmount) {
        this.tradeSellMinAmount = tradeSellMinAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_avg_price：平均卖出价格(近200笔）
     */
    public BigDecimal getTradeSellAvgPrice() {
        return tradeSellAvgPrice;
    }

    /**
     * set method 
     *
     * @param tradeSellAvgPrice  平均卖出价格(近200笔）
     */
    public void setTradeSellAvgPrice(BigDecimal tradeSellAvgPrice) {
        this.tradeSellAvgPrice = tradeSellAvgPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_sell_avg_amount：平均卖出交易量(近200笔）
     */
    public BigDecimal getTradeSellAvgAmount() {
        return tradeSellAvgAmount;
    }

    /**
     * set method 
     *
     * @param tradeSellAvgAmount  平均卖出交易量(近200笔）
     */
    public void setTradeSellAvgAmount(BigDecimal tradeSellAvgAmount) {
        this.tradeSellAvgAmount = tradeSellAvgAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_max_price：最大买入价格
     */
    public BigDecimal getTradeBuyMaxPrice() {
        return tradeBuyMaxPrice;
    }

    /**
     * set method 
     *
     * @param tradeBuyMaxPrice  最大买入价格
     */
    public void setTradeBuyMaxPrice(BigDecimal tradeBuyMaxPrice) {
        this.tradeBuyMaxPrice = tradeBuyMaxPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_max_amount：最大买入交易量
     */
    public BigDecimal getTradeBuyMaxAmount() {
        return tradeBuyMaxAmount;
    }

    /**
     * set method 
     *
     * @param tradeBuyMaxAmount  最大买入交易量
     */
    public void setTradeBuyMaxAmount(BigDecimal tradeBuyMaxAmount) {
        this.tradeBuyMaxAmount = tradeBuyMaxAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_min_price：最小买入价格
     */
    public BigDecimal getTradeBuyMinPrice() {
        return tradeBuyMinPrice;
    }

    /**
     * set method 
     *
     * @param tradeBuyMinPrice  最小买入价格
     */
    public void setTradeBuyMinPrice(BigDecimal tradeBuyMinPrice) {
        this.tradeBuyMinPrice = tradeBuyMinPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_min_amount：最小买入量
     */
    public BigDecimal getTradeBuyMinAmount() {
        return tradeBuyMinAmount;
    }

    /**
     * set method 
     *
     * @param tradeBuyMinAmount  最小买入量
     */
    public void setTradeBuyMinAmount(BigDecimal tradeBuyMinAmount) {
        this.tradeBuyMinAmount = tradeBuyMinAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_avg_price：平均买入价格
     */
    public BigDecimal getTradeBuyAvgPrice() {
        return tradeBuyAvgPrice;
    }

    /**
     * set method 
     *
     * @param tradeBuyAvgPrice  平均买入价格
     */
    public void setTradeBuyAvgPrice(BigDecimal tradeBuyAvgPrice) {
        this.tradeBuyAvgPrice = tradeBuyAvgPrice;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trade_buy_avg_amount：平均买入量
     */
    public BigDecimal getTradeBuyAvgAmount() {
        return tradeBuyAvgAmount;
    }

    /**
     * set method 
     *
     * @param tradeBuyAvgAmount  平均买入量
     */
    public void setTradeBuyAvgAmount(BigDecimal tradeBuyAvgAmount) {
        this.tradeBuyAvgAmount = tradeBuyAvgAmount;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trad_sell_nums：获取卖出数量
     */
    public Integer getTradSellNums() {
        return tradSellNums;
    }

    /**
     * set method 
     *
     * @param tradSellNums  获取卖出数量
     */
    public void setTradSellNums(Integer tradSellNums) {
        this.tradSellNums = tradSellNums;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.trad_buy_nums：获取买入数量
     */
    public Integer getTradBuyNums() {
        return tradBuyNums;
    }

    /**
     * set method 
     *
     * @param tradBuyNums  获取买入数量
     */
    public void setTradBuyNums(Integer tradBuyNums) {
        this.tradBuyNums = tradBuyNums;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_ask_max：最大卖方深度
     */
    public BigDecimal getDepAskMax() {
        return depAskMax;
    }

    /**
     * set method 
     *
     * @param depAskMax  最大卖方深度
     */
    public void setDepAskMax(BigDecimal depAskMax) {
        this.depAskMax = depAskMax;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_ask_min：最小卖方深度
     */
    public BigDecimal getDepAskMin() {
        return depAskMin;
    }

    /**
     * set method 
     *
     * @param depAskMin  最小卖方深度
     */
    public void setDepAskMin(BigDecimal depAskMin) {
        this.depAskMin = depAskMin;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_ask_avg：平均卖方深度
     */
    public BigDecimal getDepAskAvg() {
        return depAskAvg;
    }

    /**
     * set method 
     *
     * @param depAskAvg  平均卖方深度
     */
    public void setDepAskAvg(BigDecimal depAskAvg) {
        this.depAskAvg = depAskAvg;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_bid_max：最大买方深度
     */
    public BigDecimal getDepBidMax() {
        return depBidMax;
    }

    /**
     * set method 
     *
     * @param depBidMax  最大买方深度
     */
    public void setDepBidMax(BigDecimal depBidMax) {
        this.depBidMax = depBidMax;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_bid_min：最小买方深度
     */
    public BigDecimal getDepBidMin() {
        return depBidMin;
    }

    /**
     * set method 
     *
     * @param depBidMin  最小买方深度
     */
    public void setDepBidMin(BigDecimal depBidMin) {
        this.depBidMin = depBidMin;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_bid_avg：平均买方深度
     */
    public BigDecimal getDepBidAvg() {
        return depBidAvg;
    }

    /**
     * set method 
     *
     * @param depBidAvg  平均买方深度
     */
    public void setDepBidAvg(BigDecimal depBidAvg) {
        this.depBidAvg = depBidAvg;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_ask_nums：获取卖方深度条数
     */
    public Integer getDepAskNums() {
        return depAskNums;
    }

    /**
     * set method 
     *
     * @param depAskNums  获取卖方深度条数
     */
    public void setDepAskNums(Integer depAskNums) {
        this.depAskNums = depAskNums;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.dep_bid_nums：获取卖方深度条数
     */
    public Integer getDepBidNums() {
        return depBidNums;
    }

    /**
     * set method 
     *
     * @param depBidNums  获取卖方深度条数
     */
    public void setDepBidNums(Integer depBidNums) {
        this.depBidNums = depBidNums;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.usdt_btc：BTC价格
     */
    public BigDecimal getUsdtBtc() {
        return usdtBtc;
    }

    /**
     * set method 
     *
     * @param usdtBtc  BTC价格
     */
    public void setUsdtBtc(BigDecimal usdtBtc) {
        this.usdtBtc = usdtBtc;
    }

    /**
     * get method 
     *
     * @return okex_trand_info.create_date：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}