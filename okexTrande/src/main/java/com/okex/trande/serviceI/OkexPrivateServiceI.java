package com.okex.trande.serviceI;

import java.util.Map;

/**
 * Okex交易接口
 * @author 10435
 *
 */
public interface OkexPrivateServiceI {
	/**
	 * 获取账户信息
	 * @return
	 */
	public String getBalance();
	/**
	 * 单笔下单
	 * @return
	 */
	public String exeOrder(Map<String,String> paramMap);
	/**
	 * 批量下单
	 * @return
	 */
	public String exeBatchOrders();
	/**
	 * 取消订单
	 * @return
	 */
	public String cancelOrder();
	/**
	 * 获取下单信息
	 * @return
	 */
	public String getOrderInfo();
	/**
	 * 获取批量下单信息
	 * @return
	 */
	public String getOrdersInfo();
	/**
	 * 获取历史订单信息（返回近两天信息）
	 * @return
	 */
	public String getHisOrders();
	
}
