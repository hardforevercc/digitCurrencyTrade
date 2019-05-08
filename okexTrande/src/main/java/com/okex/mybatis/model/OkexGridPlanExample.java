package com.okex.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OkexGridPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *
     */
    public OkexGridPlanExample() {
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

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andBuyidIsNull() {
            addCriterion("buyId is null");
            return (Criteria) this;
        }

        public Criteria andBuyidIsNotNull() {
            addCriterion("buyId is not null");
            return (Criteria) this;
        }

        public Criteria andBuyidEqualTo(String value) {
            addCriterion("buyId =", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidNotEqualTo(String value) {
            addCriterion("buyId <>", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidGreaterThan(String value) {
            addCriterion("buyId >", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidGreaterThanOrEqualTo(String value) {
            addCriterion("buyId >=", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidLessThan(String value) {
            addCriterion("buyId <", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidLessThanOrEqualTo(String value) {
            addCriterion("buyId <=", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidLike(String value) {
            addCriterion("buyId like", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidNotLike(String value) {
            addCriterion("buyId not like", value, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidIn(List<String> values) {
            addCriterion("buyId in", values, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidNotIn(List<String> values) {
            addCriterion("buyId not in", values, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidBetween(String value1, String value2) {
            addCriterion("buyId between", value1, value2, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyidNotBetween(String value1, String value2) {
            addCriterion("buyId not between", value1, value2, "buyid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidIsNull() {
            addCriterion("buyOrderId is null");
            return (Criteria) this;
        }

        public Criteria andBuyorderidIsNotNull() {
            addCriterion("buyOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andBuyorderidEqualTo(String value) {
            addCriterion("buyOrderId =", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidNotEqualTo(String value) {
            addCriterion("buyOrderId <>", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidGreaterThan(String value) {
            addCriterion("buyOrderId >", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidGreaterThanOrEqualTo(String value) {
            addCriterion("buyOrderId >=", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidLessThan(String value) {
            addCriterion("buyOrderId <", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidLessThanOrEqualTo(String value) {
            addCriterion("buyOrderId <=", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidLike(String value) {
            addCriterion("buyOrderId like", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidNotLike(String value) {
            addCriterion("buyOrderId not like", value, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidIn(List<String> values) {
            addCriterion("buyOrderId in", values, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidNotIn(List<String> values) {
            addCriterion("buyOrderId not in", values, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidBetween(String value1, String value2) {
            addCriterion("buyOrderId between", value1, value2, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andBuyorderidNotBetween(String value1, String value2) {
            addCriterion("buyOrderId not between", value1, value2, "buyorderid");
            return (Criteria) this;
        }

        public Criteria andSellidIsNull() {
            addCriterion("sellId is null");
            return (Criteria) this;
        }

        public Criteria andSellidIsNotNull() {
            addCriterion("sellId is not null");
            return (Criteria) this;
        }

        public Criteria andSellidEqualTo(String value) {
            addCriterion("sellId =", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidNotEqualTo(String value) {
            addCriterion("sellId <>", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidGreaterThan(String value) {
            addCriterion("sellId >", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidGreaterThanOrEqualTo(String value) {
            addCriterion("sellId >=", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidLessThan(String value) {
            addCriterion("sellId <", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidLessThanOrEqualTo(String value) {
            addCriterion("sellId <=", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidLike(String value) {
            addCriterion("sellId like", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidNotLike(String value) {
            addCriterion("sellId not like", value, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidIn(List<String> values) {
            addCriterion("sellId in", values, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidNotIn(List<String> values) {
            addCriterion("sellId not in", values, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidBetween(String value1, String value2) {
            addCriterion("sellId between", value1, value2, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellidNotBetween(String value1, String value2) {
            addCriterion("sellId not between", value1, value2, "sellid");
            return (Criteria) this;
        }

        public Criteria andSellorderidIsNull() {
            addCriterion("sellOrderId is null");
            return (Criteria) this;
        }

        public Criteria andSellorderidIsNotNull() {
            addCriterion("sellOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andSellorderidEqualTo(String value) {
            addCriterion("sellOrderId =", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidNotEqualTo(String value) {
            addCriterion("sellOrderId <>", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidGreaterThan(String value) {
            addCriterion("sellOrderId >", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidGreaterThanOrEqualTo(String value) {
            addCriterion("sellOrderId >=", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidLessThan(String value) {
            addCriterion("sellOrderId <", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidLessThanOrEqualTo(String value) {
            addCriterion("sellOrderId <=", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidLike(String value) {
            addCriterion("sellOrderId like", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidNotLike(String value) {
            addCriterion("sellOrderId not like", value, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidIn(List<String> values) {
            addCriterion("sellOrderId in", values, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidNotIn(List<String> values) {
            addCriterion("sellOrderId not in", values, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidBetween(String value1, String value2) {
            addCriterion("sellOrderId between", value1, value2, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andSellorderidNotBetween(String value1, String value2) {
            addCriterion("sellOrderId not between", value1, value2, "sellorderid");
            return (Criteria) this;
        }

        public Criteria andBuypriceIsNull() {
            addCriterion("buyPrice is null");
            return (Criteria) this;
        }

        public Criteria andBuypriceIsNotNull() {
            addCriterion("buyPrice is not null");
            return (Criteria) this;
        }

        public Criteria andBuypriceEqualTo(BigDecimal value) {
            addCriterion("buyPrice =", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotEqualTo(BigDecimal value) {
            addCriterion("buyPrice <>", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThan(BigDecimal value) {
            addCriterion("buyPrice >", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyPrice >=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThan(BigDecimal value) {
            addCriterion("buyPrice <", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyPrice <=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceIn(List<BigDecimal> values) {
            addCriterion("buyPrice in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotIn(List<BigDecimal> values) {
            addCriterion("buyPrice not in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyPrice between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyPrice not between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNull() {
            addCriterion("sellPrice is null");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNotNull() {
            addCriterion("sellPrice is not null");
            return (Criteria) this;
        }

        public Criteria andSellpriceEqualTo(BigDecimal value) {
            addCriterion("sellPrice =", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotEqualTo(BigDecimal value) {
            addCriterion("sellPrice <>", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThan(BigDecimal value) {
            addCriterion("sellPrice >", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellPrice >=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThan(BigDecimal value) {
            addCriterion("sellPrice <", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellPrice <=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIn(List<BigDecimal> values) {
            addCriterion("sellPrice in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotIn(List<BigDecimal> values) {
            addCriterion("sellPrice not in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellPrice between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellPrice not between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andBuyamtIsNull() {
            addCriterion("buyAmt is null");
            return (Criteria) this;
        }

        public Criteria andBuyamtIsNotNull() {
            addCriterion("buyAmt is not null");
            return (Criteria) this;
        }

        public Criteria andBuyamtEqualTo(BigDecimal value) {
            addCriterion("buyAmt =", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtNotEqualTo(BigDecimal value) {
            addCriterion("buyAmt <>", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtGreaterThan(BigDecimal value) {
            addCriterion("buyAmt >", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyAmt >=", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtLessThan(BigDecimal value) {
            addCriterion("buyAmt <", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyAmt <=", value, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtIn(List<BigDecimal> values) {
            addCriterion("buyAmt in", values, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtNotIn(List<BigDecimal> values) {
            addCriterion("buyAmt not in", values, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyAmt between", value1, value2, "buyamt");
            return (Criteria) this;
        }

        public Criteria andBuyamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyAmt not between", value1, value2, "buyamt");
            return (Criteria) this;
        }

        public Criteria andActbuypriceIsNull() {
            addCriterion("actBuyPrice is null");
            return (Criteria) this;
        }

        public Criteria andActbuypriceIsNotNull() {
            addCriterion("actBuyPrice is not null");
            return (Criteria) this;
        }

        public Criteria andActbuypriceEqualTo(BigDecimal value) {
            addCriterion("actBuyPrice =", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceNotEqualTo(BigDecimal value) {
            addCriterion("actBuyPrice <>", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceGreaterThan(BigDecimal value) {
            addCriterion("actBuyPrice >", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actBuyPrice >=", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceLessThan(BigDecimal value) {
            addCriterion("actBuyPrice <", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actBuyPrice <=", value, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceIn(List<BigDecimal> values) {
            addCriterion("actBuyPrice in", values, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceNotIn(List<BigDecimal> values) {
            addCriterion("actBuyPrice not in", values, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actBuyPrice between", value1, value2, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actBuyPrice not between", value1, value2, "actbuyprice");
            return (Criteria) this;
        }

        public Criteria andActbuyamountIsNull() {
            addCriterion("actbuyAmount is null");
            return (Criteria) this;
        }

        public Criteria andActbuyamountIsNotNull() {
            addCriterion("actbuyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andActbuyamountEqualTo(BigDecimal value) {
            addCriterion("actbuyAmount =", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountNotEqualTo(BigDecimal value) {
            addCriterion("actbuyAmount <>", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountGreaterThan(BigDecimal value) {
            addCriterion("actbuyAmount >", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actbuyAmount >=", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountLessThan(BigDecimal value) {
            addCriterion("actbuyAmount <", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actbuyAmount <=", value, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountIn(List<BigDecimal> values) {
            addCriterion("actbuyAmount in", values, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountNotIn(List<BigDecimal> values) {
            addCriterion("actbuyAmount not in", values, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actbuyAmount between", value1, value2, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActbuyamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actbuyAmount not between", value1, value2, "actbuyamount");
            return (Criteria) this;
        }

        public Criteria andActsellpriceIsNull() {
            addCriterion("actSellPrice is null");
            return (Criteria) this;
        }

        public Criteria andActsellpriceIsNotNull() {
            addCriterion("actSellPrice is not null");
            return (Criteria) this;
        }

        public Criteria andActsellpriceEqualTo(BigDecimal value) {
            addCriterion("actSellPrice =", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceNotEqualTo(BigDecimal value) {
            addCriterion("actSellPrice <>", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceGreaterThan(BigDecimal value) {
            addCriterion("actSellPrice >", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actSellPrice >=", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceLessThan(BigDecimal value) {
            addCriterion("actSellPrice <", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actSellPrice <=", value, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceIn(List<BigDecimal> values) {
            addCriterion("actSellPrice in", values, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceNotIn(List<BigDecimal> values) {
            addCriterion("actSellPrice not in", values, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actSellPrice between", value1, value2, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actSellPrice not between", value1, value2, "actsellprice");
            return (Criteria) this;
        }

        public Criteria andActsellamountIsNull() {
            addCriterion("actSellAmount is null");
            return (Criteria) this;
        }

        public Criteria andActsellamountIsNotNull() {
            addCriterion("actSellAmount is not null");
            return (Criteria) this;
        }

        public Criteria andActsellamountEqualTo(BigDecimal value) {
            addCriterion("actSellAmount =", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountNotEqualTo(BigDecimal value) {
            addCriterion("actSellAmount <>", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountGreaterThan(BigDecimal value) {
            addCriterion("actSellAmount >", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actSellAmount >=", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountLessThan(BigDecimal value) {
            addCriterion("actSellAmount <", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actSellAmount <=", value, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountIn(List<BigDecimal> values) {
            addCriterion("actSellAmount in", values, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountNotIn(List<BigDecimal> values) {
            addCriterion("actSellAmount not in", values, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actSellAmount between", value1, value2, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andActsellamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actSellAmount not between", value1, value2, "actsellamount");
            return (Criteria) this;
        }

        public Criteria andBuystsIsNull() {
            addCriterion("buySts is null");
            return (Criteria) this;
        }

        public Criteria andBuystsIsNotNull() {
            addCriterion("buySts is not null");
            return (Criteria) this;
        }

        public Criteria andBuystsEqualTo(String value) {
            addCriterion("buySts =", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsNotEqualTo(String value) {
            addCriterion("buySts <>", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsGreaterThan(String value) {
            addCriterion("buySts >", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsGreaterThanOrEqualTo(String value) {
            addCriterion("buySts >=", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsLessThan(String value) {
            addCriterion("buySts <", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsLessThanOrEqualTo(String value) {
            addCriterion("buySts <=", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsLike(String value) {
            addCriterion("buySts like", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsNotLike(String value) {
            addCriterion("buySts not like", value, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsIn(List<String> values) {
            addCriterion("buySts in", values, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsNotIn(List<String> values) {
            addCriterion("buySts not in", values, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsBetween(String value1, String value2) {
            addCriterion("buySts between", value1, value2, "buysts");
            return (Criteria) this;
        }

        public Criteria andBuystsNotBetween(String value1, String value2) {
            addCriterion("buySts not between", value1, value2, "buysts");
            return (Criteria) this;
        }

        public Criteria andSellstsIsNull() {
            addCriterion("sellSts is null");
            return (Criteria) this;
        }

        public Criteria andSellstsIsNotNull() {
            addCriterion("sellSts is not null");
            return (Criteria) this;
        }

        public Criteria andSellstsEqualTo(String value) {
            addCriterion("sellSts =", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsNotEqualTo(String value) {
            addCriterion("sellSts <>", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsGreaterThan(String value) {
            addCriterion("sellSts >", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsGreaterThanOrEqualTo(String value) {
            addCriterion("sellSts >=", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsLessThan(String value) {
            addCriterion("sellSts <", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsLessThanOrEqualTo(String value) {
            addCriterion("sellSts <=", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsLike(String value) {
            addCriterion("sellSts like", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsNotLike(String value) {
            addCriterion("sellSts not like", value, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsIn(List<String> values) {
            addCriterion("sellSts in", values, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsNotIn(List<String> values) {
            addCriterion("sellSts not in", values, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsBetween(String value1, String value2) {
            addCriterion("sellSts between", value1, value2, "sellsts");
            return (Criteria) this;
        }

        public Criteria andSellstsNotBetween(String value1, String value2) {
            addCriterion("sellSts not between", value1, value2, "sellsts");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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