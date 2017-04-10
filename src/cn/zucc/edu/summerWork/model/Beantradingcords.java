package cn.zucc.edu.summerWork.model;

import java.util.*;

public class Beantradingcords {
	private int tradingnum;//交易编号
	private String username;//用户名
	private String tradingsell;//卖家
	private String tradingbuy;//买家
	private String tradingcarnum;//车辆编号
	private int tradingprice;//车辆价格
	private Date tradingdata;//成交时间
	public int getTradingnum() {
		return tradingnum;
	}
	public void setTradingnum(int tradingnum) {
		this.tradingnum = tradingnum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTradingsell() {
		return tradingsell;
	}
	public void setTradingsell(String tradingsell) {
		this.tradingsell = tradingsell;
	}
	public String getTradingbuy() {
		return tradingbuy;
	}
	public void setTradingbuy(String tradingbuy) {
		this.tradingbuy = tradingbuy;
	}
	public String getTradingcarnum() {
		return tradingcarnum;
	}
	public void setTradingcarnum(String tradingcarnum) {
		this.tradingcarnum = tradingcarnum;
	}
	public int getTradingprice() {
		return tradingprice;
	}
	public void setTradingprice(int tradingprice) {
		this.tradingprice = tradingprice;
	}
	public Date getTradingdata() {
		return tradingdata;
	}
	public void setTradingdata(Date tradingdata) {
		this.tradingdata = tradingdata;
	}

}
