package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OkexTradeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *
     */
    public OkexTradeInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @param orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @param distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @param criteria
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTraddesNameIsNull() {
            addCriterion("traddes_name is null");
            return (Criteria) this;
        }

        public Criteria andTraddesNameIsNotNull() {
            addCriterion("traddes_name is not null");
            return (Criteria) this;
        }

        public Criteria andTraddesNameEqualTo(String value) {
            addCriterion("traddes_name =", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameNotEqualTo(String value) {
            addCriterion("traddes_name <>", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameGreaterThan(String value) {
            addCriterion("traddes_name >", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameGreaterThanOrEqualTo(String value) {
            addCriterion("traddes_name >=", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameLessThan(String value) {
            addCriterion("traddes_name <", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameLessThanOrEqualTo(String value) {
            addCriterion("traddes_name <=", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameLike(String value) {
            addCriterion("traddes_name like", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameNotLike(String value) {
            addCriterion("traddes_name not like", value, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameIn(List<String> values) {
            addCriterion("traddes_name in", values, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameNotIn(List<String> values) {
            addCriterion("traddes_name not in", values, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameBetween(String value1, String value2) {
            addCriterion("traddes_name between", value1, value2, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTraddesNameNotBetween(String value1, String value2) {
            addCriterion("traddes_name not between", value1, value2, "traddesName");
            return (Criteria) this;
        }

        public Criteria andTimestampIsNull() {
            addCriterion("timestamp is null");
            return (Criteria) this;
        }

        public Criteria andTimestampIsNotNull() {
            addCriterion("timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andTimestampEqualTo(String value) {
            addCriterion("timestamp =", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotEqualTo(String value) {
            addCriterion("timestamp <>", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThan(String value) {
            addCriterion("timestamp >", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThanOrEqualTo(String value) {
            addCriterion("timestamp >=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThan(String value) {
            addCriterion("timestamp <", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThanOrEqualTo(String value) {
            addCriterion("timestamp <=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLike(String value) {
            addCriterion("timestamp like", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotLike(String value) {
            addCriterion("timestamp not like", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampIn(List<String> values) {
            addCriterion("timestamp in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotIn(List<String> values) {
            addCriterion("timestamp not in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampBetween(String value1, String value2) {
            addCriterion("timestamp between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotBetween(String value1, String value2) {
            addCriterion("timestamp not between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTickerBuyIsNull() {
            addCriterion("ticker_buy is null");
            return (Criteria) this;
        }

        public Criteria andTickerBuyIsNotNull() {
            addCriterion("ticker_buy is not null");
            return (Criteria) this;
        }

        public Criteria andTickerBuyEqualTo(BigDecimal value) {
            addCriterion("ticker_buy =", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyNotEqualTo(BigDecimal value) {
            addCriterion("ticker_buy <>", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyGreaterThan(BigDecimal value) {
            addCriterion("ticker_buy >", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_buy >=", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyLessThan(BigDecimal value) {
            addCriterion("ticker_buy <", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_buy <=", value, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyIn(List<BigDecimal> values) {
            addCriterion("ticker_buy in", values, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyNotIn(List<BigDecimal> values) {
            addCriterion("ticker_buy not in", values, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_buy between", value1, value2, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerBuyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_buy not between", value1, value2, "tickerBuy");
            return (Criteria) this;
        }

        public Criteria andTickerHighIsNull() {
            addCriterion("ticker_high is null");
            return (Criteria) this;
        }

        public Criteria andTickerHighIsNotNull() {
            addCriterion("ticker_high is not null");
            return (Criteria) this;
        }

        public Criteria andTickerHighEqualTo(BigDecimal value) {
            addCriterion("ticker_high =", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighNotEqualTo(BigDecimal value) {
            addCriterion("ticker_high <>", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighGreaterThan(BigDecimal value) {
            addCriterion("ticker_high >", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_high >=", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighLessThan(BigDecimal value) {
            addCriterion("ticker_high <", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_high <=", value, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighIn(List<BigDecimal> values) {
            addCriterion("ticker_high in", values, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighNotIn(List<BigDecimal> values) {
            addCriterion("ticker_high not in", values, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_high between", value1, value2, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerHighNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_high not between", value1, value2, "tickerHigh");
            return (Criteria) this;
        }

        public Criteria andTickerLastIsNull() {
            addCriterion("ticker_last is null");
            return (Criteria) this;
        }

        public Criteria andTickerLastIsNotNull() {
            addCriterion("ticker_last is not null");
            return (Criteria) this;
        }

        public Criteria andTickerLastEqualTo(BigDecimal value) {
            addCriterion("ticker_last =", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastNotEqualTo(BigDecimal value) {
            addCriterion("ticker_last <>", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastGreaterThan(BigDecimal value) {
            addCriterion("ticker_last >", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_last >=", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastLessThan(BigDecimal value) {
            addCriterion("ticker_last <", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_last <=", value, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastIn(List<BigDecimal> values) {
            addCriterion("ticker_last in", values, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastNotIn(List<BigDecimal> values) {
            addCriterion("ticker_last not in", values, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_last between", value1, value2, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLastNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_last not between", value1, value2, "tickerLast");
            return (Criteria) this;
        }

        public Criteria andTickerLowIsNull() {
            addCriterion("ticker_low is null");
            return (Criteria) this;
        }

        public Criteria andTickerLowIsNotNull() {
            addCriterion("ticker_low is not null");
            return (Criteria) this;
        }

        public Criteria andTickerLowEqualTo(BigDecimal value) {
            addCriterion("ticker_low =", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowNotEqualTo(BigDecimal value) {
            addCriterion("ticker_low <>", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowGreaterThan(BigDecimal value) {
            addCriterion("ticker_low >", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_low >=", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowLessThan(BigDecimal value) {
            addCriterion("ticker_low <", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_low <=", value, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowIn(List<BigDecimal> values) {
            addCriterion("ticker_low in", values, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowNotIn(List<BigDecimal> values) {
            addCriterion("ticker_low not in", values, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_low between", value1, value2, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerLowNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_low not between", value1, value2, "tickerLow");
            return (Criteria) this;
        }

        public Criteria andTickerSellIsNull() {
            addCriterion("ticker_sell is null");
            return (Criteria) this;
        }

        public Criteria andTickerSellIsNotNull() {
            addCriterion("ticker_sell is not null");
            return (Criteria) this;
        }

        public Criteria andTickerSellEqualTo(BigDecimal value) {
            addCriterion("ticker_sell =", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellNotEqualTo(BigDecimal value) {
            addCriterion("ticker_sell <>", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellGreaterThan(BigDecimal value) {
            addCriterion("ticker_sell >", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_sell >=", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellLessThan(BigDecimal value) {
            addCriterion("ticker_sell <", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_sell <=", value, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellIn(List<BigDecimal> values) {
            addCriterion("ticker_sell in", values, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellNotIn(List<BigDecimal> values) {
            addCriterion("ticker_sell not in", values, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_sell between", value1, value2, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerSellNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_sell not between", value1, value2, "tickerSell");
            return (Criteria) this;
        }

        public Criteria andTickerVolIsNull() {
            addCriterion("ticker_vol is null");
            return (Criteria) this;
        }

        public Criteria andTickerVolIsNotNull() {
            addCriterion("ticker_vol is not null");
            return (Criteria) this;
        }

        public Criteria andTickerVolEqualTo(BigDecimal value) {
            addCriterion("ticker_vol =", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolNotEqualTo(BigDecimal value) {
            addCriterion("ticker_vol <>", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolGreaterThan(BigDecimal value) {
            addCriterion("ticker_vol >", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_vol >=", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolLessThan(BigDecimal value) {
            addCriterion("ticker_vol <", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticker_vol <=", value, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolIn(List<BigDecimal> values) {
            addCriterion("ticker_vol in", values, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolNotIn(List<BigDecimal> values) {
            addCriterion("ticker_vol not in", values, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_vol between", value1, value2, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTickerVolNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticker_vol not between", value1, value2, "tickerVol");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceIsNull() {
            addCriterion("trade_sell_max_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceIsNotNull() {
            addCriterion("trade_sell_max_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_price =", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_price <>", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_max_price >", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_price >=", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceLessThan(BigDecimal value) {
            addCriterion("trade_sell_max_price <", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_price <=", value, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceIn(List<BigDecimal> values) {
            addCriterion("trade_sell_max_price in", values, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_max_price not in", values, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_max_price between", value1, value2, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_max_price not between", value1, value2, "tradeSellMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountIsNull() {
            addCriterion("trade_sell_max_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountIsNotNull() {
            addCriterion("trade_sell_max_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_amount =", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_amount <>", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_max_amount >", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_amount >=", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountLessThan(BigDecimal value) {
            addCriterion("trade_sell_max_amount <", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_max_amount <=", value, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountIn(List<BigDecimal> values) {
            addCriterion("trade_sell_max_amount in", values, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_max_amount not in", values, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_max_amount between", value1, value2, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_max_amount not between", value1, value2, "tradeSellMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceIsNull() {
            addCriterion("trade_sell_min_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceIsNotNull() {
            addCriterion("trade_sell_min_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_price =", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_price <>", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_min_price >", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_price >=", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceLessThan(BigDecimal value) {
            addCriterion("trade_sell_min_price <", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_price <=", value, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceIn(List<BigDecimal> values) {
            addCriterion("trade_sell_min_price in", values, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_min_price not in", values, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_min_price between", value1, value2, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_min_price not between", value1, value2, "tradeSellMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountIsNull() {
            addCriterion("trade_sell_min_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountIsNotNull() {
            addCriterion("trade_sell_min_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_amount =", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_amount <>", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_min_amount >", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_amount >=", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountLessThan(BigDecimal value) {
            addCriterion("trade_sell_min_amount <", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_min_amount <=", value, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountIn(List<BigDecimal> values) {
            addCriterion("trade_sell_min_amount in", values, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_min_amount not in", values, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_min_amount between", value1, value2, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellMinAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_min_amount not between", value1, value2, "tradeSellMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceIsNull() {
            addCriterion("trade_sell_avg_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceIsNotNull() {
            addCriterion("trade_sell_avg_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_price =", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_price <>", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_avg_price >", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_price >=", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceLessThan(BigDecimal value) {
            addCriterion("trade_sell_avg_price <", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_price <=", value, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceIn(List<BigDecimal> values) {
            addCriterion("trade_sell_avg_price in", values, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_avg_price not in", values, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_avg_price between", value1, value2, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_avg_price not between", value1, value2, "tradeSellAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountIsNull() {
            addCriterion("trade_sell_avg_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountIsNotNull() {
            addCriterion("trade_sell_avg_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_amount =", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_amount <>", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_sell_avg_amount >", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_amount >=", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountLessThan(BigDecimal value) {
            addCriterion("trade_sell_avg_amount <", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_sell_avg_amount <=", value, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountIn(List<BigDecimal> values) {
            addCriterion("trade_sell_avg_amount in", values, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_sell_avg_amount not in", values, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_avg_amount between", value1, value2, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeSellAvgAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_sell_avg_amount not between", value1, value2, "tradeSellAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceIsNull() {
            addCriterion("trade_buy_max_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceIsNotNull() {
            addCriterion("trade_buy_max_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_price =", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_price <>", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_max_price >", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_price >=", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceLessThan(BigDecimal value) {
            addCriterion("trade_buy_max_price <", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_price <=", value, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceIn(List<BigDecimal> values) {
            addCriterion("trade_buy_max_price in", values, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_max_price not in", values, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_max_price between", value1, value2, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_max_price not between", value1, value2, "tradeBuyMaxPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountIsNull() {
            addCriterion("trade_buy_max_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountIsNotNull() {
            addCriterion("trade_buy_max_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_amount =", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_amount <>", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_max_amount >", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_amount >=", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountLessThan(BigDecimal value) {
            addCriterion("trade_buy_max_amount <", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_max_amount <=", value, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountIn(List<BigDecimal> values) {
            addCriterion("trade_buy_max_amount in", values, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_max_amount not in", values, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_max_amount between", value1, value2, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_max_amount not between", value1, value2, "tradeBuyMaxAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceIsNull() {
            addCriterion("trade_buy_min_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceIsNotNull() {
            addCriterion("trade_buy_min_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_price =", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_price <>", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_min_price >", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_price >=", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceLessThan(BigDecimal value) {
            addCriterion("trade_buy_min_price <", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_price <=", value, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceIn(List<BigDecimal> values) {
            addCriterion("trade_buy_min_price in", values, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_min_price not in", values, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_min_price between", value1, value2, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_min_price not between", value1, value2, "tradeBuyMinPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountIsNull() {
            addCriterion("trade_buy_min_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountIsNotNull() {
            addCriterion("trade_buy_min_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_amount =", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_amount <>", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_min_amount >", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_amount >=", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountLessThan(BigDecimal value) {
            addCriterion("trade_buy_min_amount <", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_min_amount <=", value, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountIn(List<BigDecimal> values) {
            addCriterion("trade_buy_min_amount in", values, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_min_amount not in", values, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_min_amount between", value1, value2, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyMinAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_min_amount not between", value1, value2, "tradeBuyMinAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceIsNull() {
            addCriterion("trade_buy_avg_price is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceIsNotNull() {
            addCriterion("trade_buy_avg_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_price =", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_price <>", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_avg_price >", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_price >=", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceLessThan(BigDecimal value) {
            addCriterion("trade_buy_avg_price <", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_price <=", value, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceIn(List<BigDecimal> values) {
            addCriterion("trade_buy_avg_price in", values, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_avg_price not in", values, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_avg_price between", value1, value2, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_avg_price not between", value1, value2, "tradeBuyAvgPrice");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountIsNull() {
            addCriterion("trade_buy_avg_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountIsNotNull() {
            addCriterion("trade_buy_avg_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_amount =", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_amount <>", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_buy_avg_amount >", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_amount >=", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountLessThan(BigDecimal value) {
            addCriterion("trade_buy_avg_amount <", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_buy_avg_amount <=", value, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountIn(List<BigDecimal> values) {
            addCriterion("trade_buy_avg_amount in", values, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_buy_avg_amount not in", values, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_avg_amount between", value1, value2, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradeBuyAvgAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_buy_avg_amount not between", value1, value2, "tradeBuyAvgAmount");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsIsNull() {
            addCriterion("trad_sell_nums is null");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsIsNotNull() {
            addCriterion("trad_sell_nums is not null");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsEqualTo(Integer value) {
            addCriterion("trad_sell_nums =", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsNotEqualTo(Integer value) {
            addCriterion("trad_sell_nums <>", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsGreaterThan(Integer value) {
            addCriterion("trad_sell_nums >", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("trad_sell_nums >=", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsLessThan(Integer value) {
            addCriterion("trad_sell_nums <", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsLessThanOrEqualTo(Integer value) {
            addCriterion("trad_sell_nums <=", value, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsIn(List<Integer> values) {
            addCriterion("trad_sell_nums in", values, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsNotIn(List<Integer> values) {
            addCriterion("trad_sell_nums not in", values, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsBetween(Integer value1, Integer value2) {
            addCriterion("trad_sell_nums between", value1, value2, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradSellNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("trad_sell_nums not between", value1, value2, "tradSellNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsIsNull() {
            addCriterion("trad_buy_nums is null");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsIsNotNull() {
            addCriterion("trad_buy_nums is not null");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsEqualTo(Integer value) {
            addCriterion("trad_buy_nums =", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsNotEqualTo(Integer value) {
            addCriterion("trad_buy_nums <>", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsGreaterThan(Integer value) {
            addCriterion("trad_buy_nums >", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("trad_buy_nums >=", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsLessThan(Integer value) {
            addCriterion("trad_buy_nums <", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsLessThanOrEqualTo(Integer value) {
            addCriterion("trad_buy_nums <=", value, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsIn(List<Integer> values) {
            addCriterion("trad_buy_nums in", values, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsNotIn(List<Integer> values) {
            addCriterion("trad_buy_nums not in", values, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsBetween(Integer value1, Integer value2) {
            addCriterion("trad_buy_nums between", value1, value2, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andTradBuyNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("trad_buy_nums not between", value1, value2, "tradBuyNums");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxIsNull() {
            addCriterion("dep_ask_max is null");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxIsNotNull() {
            addCriterion("dep_ask_max is not null");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxEqualTo(BigDecimal value) {
            addCriterion("dep_ask_max =", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxNotEqualTo(BigDecimal value) {
            addCriterion("dep_ask_max <>", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxGreaterThan(BigDecimal value) {
            addCriterion("dep_ask_max >", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_max >=", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxLessThan(BigDecimal value) {
            addCriterion("dep_ask_max <", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_max <=", value, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxIn(List<BigDecimal> values) {
            addCriterion("dep_ask_max in", values, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxNotIn(List<BigDecimal> values) {
            addCriterion("dep_ask_max not in", values, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_max between", value1, value2, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_max not between", value1, value2, "depAskMax");
            return (Criteria) this;
        }

        public Criteria andDepAskMinIsNull() {
            addCriterion("dep_ask_min is null");
            return (Criteria) this;
        }

        public Criteria andDepAskMinIsNotNull() {
            addCriterion("dep_ask_min is not null");
            return (Criteria) this;
        }

        public Criteria andDepAskMinEqualTo(BigDecimal value) {
            addCriterion("dep_ask_min =", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinNotEqualTo(BigDecimal value) {
            addCriterion("dep_ask_min <>", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinGreaterThan(BigDecimal value) {
            addCriterion("dep_ask_min >", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_min >=", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinLessThan(BigDecimal value) {
            addCriterion("dep_ask_min <", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_min <=", value, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinIn(List<BigDecimal> values) {
            addCriterion("dep_ask_min in", values, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinNotIn(List<BigDecimal> values) {
            addCriterion("dep_ask_min not in", values, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_min between", value1, value2, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_min not between", value1, value2, "depAskMin");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgIsNull() {
            addCriterion("dep_ask_avg is null");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgIsNotNull() {
            addCriterion("dep_ask_avg is not null");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgEqualTo(BigDecimal value) {
            addCriterion("dep_ask_avg =", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgNotEqualTo(BigDecimal value) {
            addCriterion("dep_ask_avg <>", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgGreaterThan(BigDecimal value) {
            addCriterion("dep_ask_avg >", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_avg >=", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgLessThan(BigDecimal value) {
            addCriterion("dep_ask_avg <", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_ask_avg <=", value, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgIn(List<BigDecimal> values) {
            addCriterion("dep_ask_avg in", values, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgNotIn(List<BigDecimal> values) {
            addCriterion("dep_ask_avg not in", values, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_avg between", value1, value2, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskAvgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_ask_avg not between", value1, value2, "depAskAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxIsNull() {
            addCriterion("dep_bid_max is null");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxIsNotNull() {
            addCriterion("dep_bid_max is not null");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxEqualTo(BigDecimal value) {
            addCriterion("dep_bid_max =", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxNotEqualTo(BigDecimal value) {
            addCriterion("dep_bid_max <>", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxGreaterThan(BigDecimal value) {
            addCriterion("dep_bid_max >", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_max >=", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxLessThan(BigDecimal value) {
            addCriterion("dep_bid_max <", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_max <=", value, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxIn(List<BigDecimal> values) {
            addCriterion("dep_bid_max in", values, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxNotIn(List<BigDecimal> values) {
            addCriterion("dep_bid_max not in", values, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_max between", value1, value2, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_max not between", value1, value2, "depBidMax");
            return (Criteria) this;
        }

        public Criteria andDepBidMinIsNull() {
            addCriterion("dep_bid_min is null");
            return (Criteria) this;
        }

        public Criteria andDepBidMinIsNotNull() {
            addCriterion("dep_bid_min is not null");
            return (Criteria) this;
        }

        public Criteria andDepBidMinEqualTo(BigDecimal value) {
            addCriterion("dep_bid_min =", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinNotEqualTo(BigDecimal value) {
            addCriterion("dep_bid_min <>", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinGreaterThan(BigDecimal value) {
            addCriterion("dep_bid_min >", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_min >=", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinLessThan(BigDecimal value) {
            addCriterion("dep_bid_min <", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_min <=", value, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinIn(List<BigDecimal> values) {
            addCriterion("dep_bid_min in", values, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinNotIn(List<BigDecimal> values) {
            addCriterion("dep_bid_min not in", values, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_min between", value1, value2, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_min not between", value1, value2, "depBidMin");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgIsNull() {
            addCriterion("dep_bid_avg is null");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgIsNotNull() {
            addCriterion("dep_bid_avg is not null");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgEqualTo(BigDecimal value) {
            addCriterion("dep_bid_avg =", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgNotEqualTo(BigDecimal value) {
            addCriterion("dep_bid_avg <>", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgGreaterThan(BigDecimal value) {
            addCriterion("dep_bid_avg >", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_avg >=", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgLessThan(BigDecimal value) {
            addCriterion("dep_bid_avg <", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dep_bid_avg <=", value, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgIn(List<BigDecimal> values) {
            addCriterion("dep_bid_avg in", values, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgNotIn(List<BigDecimal> values) {
            addCriterion("dep_bid_avg not in", values, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_avg between", value1, value2, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepBidAvgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dep_bid_avg not between", value1, value2, "depBidAvg");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsIsNull() {
            addCriterion("dep_ask_nums is null");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsIsNotNull() {
            addCriterion("dep_ask_nums is not null");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsEqualTo(Integer value) {
            addCriterion("dep_ask_nums =", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsNotEqualTo(Integer value) {
            addCriterion("dep_ask_nums <>", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsGreaterThan(Integer value) {
            addCriterion("dep_ask_nums >", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("dep_ask_nums >=", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsLessThan(Integer value) {
            addCriterion("dep_ask_nums <", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsLessThanOrEqualTo(Integer value) {
            addCriterion("dep_ask_nums <=", value, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsIn(List<Integer> values) {
            addCriterion("dep_ask_nums in", values, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsNotIn(List<Integer> values) {
            addCriterion("dep_ask_nums not in", values, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsBetween(Integer value1, Integer value2) {
            addCriterion("dep_ask_nums between", value1, value2, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepAskNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("dep_ask_nums not between", value1, value2, "depAskNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsIsNull() {
            addCriterion("dep_bid_nums is null");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsIsNotNull() {
            addCriterion("dep_bid_nums is not null");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsEqualTo(Integer value) {
            addCriterion("dep_bid_nums =", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsNotEqualTo(Integer value) {
            addCriterion("dep_bid_nums <>", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsGreaterThan(Integer value) {
            addCriterion("dep_bid_nums >", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("dep_bid_nums >=", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsLessThan(Integer value) {
            addCriterion("dep_bid_nums <", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsLessThanOrEqualTo(Integer value) {
            addCriterion("dep_bid_nums <=", value, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsIn(List<Integer> values) {
            addCriterion("dep_bid_nums in", values, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsNotIn(List<Integer> values) {
            addCriterion("dep_bid_nums not in", values, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsBetween(Integer value1, Integer value2) {
            addCriterion("dep_bid_nums between", value1, value2, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andDepBidNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("dep_bid_nums not between", value1, value2, "depBidNums");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcIsNull() {
            addCriterion("usdt_btc is null");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcIsNotNull() {
            addCriterion("usdt_btc is not null");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcEqualTo(BigDecimal value) {
            addCriterion("usdt_btc =", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcNotEqualTo(BigDecimal value) {
            addCriterion("usdt_btc <>", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcGreaterThan(BigDecimal value) {
            addCriterion("usdt_btc >", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usdt_btc >=", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcLessThan(BigDecimal value) {
            addCriterion("usdt_btc <", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usdt_btc <=", value, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcIn(List<BigDecimal> values) {
            addCriterion("usdt_btc in", values, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcNotIn(List<BigDecimal> values) {
            addCriterion("usdt_btc not in", values, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usdt_btc between", value1, value2, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andUsdtBtcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usdt_btc not between", value1, value2, "usdtBtc");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}