package cn.zucc.edu.summerWork.model;

import java.util.Date;

public class Beansellcar {
	private int sellnum;//挂牌编号
	private String username;//用户名
	private String carnum;
	private Date selldate;//挂牌有效时间
	private int sellprice;//挂牌价格
	private String sellway;//看车方式
	private String selltype;//挂牌申请
	public int getSellnum() {
		return sellnum;
	}
	public void setSellnum(int sellnum) {
		this.sellnum = sellnum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getSelldate() {
		return selldate;
	}
	public void setSelldate(Date selldate) {
		this.selldate = selldate;
	}
	public int getSellprice() {
		return sellprice;
	}
	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}
	public String getSellway() {
		return sellway;
	}
	public void setSellway(String sellway) {
		this.sellway = sellway;
	}
	public String getSelltype() {
		return selltype;
	}
	public void setSelltype(String selltype) {
		this.selltype = selltype;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	
}
