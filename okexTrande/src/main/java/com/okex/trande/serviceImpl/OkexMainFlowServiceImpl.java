package com.okex.trande.serviceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.okex.bean.TickerBean;
import com.okex.mybatis.model.OkexTradeInfo;
import com.okex.trande.serviceI.OkexMainFlowServiceI;
import com.okex.trande.serviceI.OkexPrivateServiceI;
import com.okex.trande.serviceI.OkexPublicServiceI;
import com.okex.trande.utils.OkexTradeUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("okexMainFlowService")
public class OkexMainFlowServiceImpl implements OkexMainFlowServiceI{
	@Autowired
	OkexPublicServiceI okexPublicService;
	@Autowired
	OkexPrivateServiceI okexPrivateService;
	private static final String BTC_USDT = "btc_usdt";
	private static final String ADA_BTC = "ada_usdt";
	/**
	 * 基于ADA稳定的价格 制定ADA就交易策略
	 * @throws Exception 
	 */
	public String Execute() throws Exception {		
		log.info("[***************OKEX TRADING START******************]");
		log.info("step 1: get the price of ada&btc statrt...");
		String btcTickerResp = okexPublicService.getTicker(BTC_USDT);
		TickerBean btcTicker = OkexTradeUtils.dealTicker(btcTickerResp);
		if(null == btcTicker) {
			throw new Exception("query the price of btc is empty!");
		}
		log.info("the last price of btc_usdt = "+btcTicker.getTicker().getLast());
		String adaTickerResp = okexPublicService.getTicker(ADA_BTC);
		TickerBean adaTicker = OkexTradeUtils.dealTicker(adaTickerResp);
		log.info("the last price of ada_usdt = "+adaTicker.getTicker().getLast());
		log.info("step 1: get the price of ada&btc end...");
		/**
		 * 等待数据完善根据数据信息修改交易规则
		 * 获取最近时间段的交易信息：
		 * 平均成交量
		 * 最大买入 卖出价格
		 * 
		 */
		
		/**
		 * 制定规则：
		 * 强规则:
		 * 买入: $ada <= 0.11 购入当前资金50%  在此价格基础上:每下跌1% 买入当前资金2%
		 * 卖出: $ada >= 0.15 卖出当前资金50%  在此价格基础上:每上涨1% 卖出当前资金2%
		 * 弱规则:
		 * $ada >= 0.11 && $ada <= 0.15
		 * 在此区间内随机买入，买入量>=(当前闲置资金)(0-50%)区间内
		 * 若价格大于或小于此区间内买入价格5% 按波动率买入或卖出
		 *  
		 */
		return btcTickerResp;
	}
}
