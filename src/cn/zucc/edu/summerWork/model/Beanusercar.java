package cn.zucc.edu.summerWork.model;

import java.util.Date;

public class Beanusercar {
	public static final String[] tableCarTitles={"品牌","车系","车型","变速箱","排量","生产年份","车龄","上牌时间","行驶里程","颜色","价格","介绍","状态"};
	private String carnum;//车辆编号
	private String username;//用户名
	private String cartypenum;//车型编号
	private String brandnum;//品牌编号
	private String carcategory;//车辆车系
	private String cartransmission;//车辆变速箱
	private String cardisplacement;//车辆排量
	private String caryear;//车辆生产年份
	private String caruseyear;//车龄
	private Date carplatedate;//车辆上牌时间
	private int carlength;//车辆行驶里程
	private String carcolor;//车辆颜色
	private int carprice;//车辆价格
	private String cartypeintroduce;//车型介绍
	private String carfeedback;//车辆申请反馈
	private String cartype;//车辆申请
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCartypenum() {
		return cartypenum;
	}
	public void setCartypenum(String cartypenum) {
		this.cartypenum = cartypenum;
	}
	public String getBrandnum() {
		return brandnum;
	}
	public void setBrandnum(String brandnum) {
		this.brandnum = brandnum;
	}
	public String getCarcategory() {
		return carcategory;
	}
	public void setCarcategory(String carcategory) {
		this.carcategory = carcategory;
	}
	public String getCartransmission() {
		return cartransmission;
	}
	public void setCartransmission(String cartransmission) {
		this.cartransmission = cartransmission;
	}
	public String getCardisplacement() {
		return cardisplacement;
	}
	public void setCardisplacement(String cardisplacement) {
		this.cardisplacement = cardisplacement;
	}
	public String getCaryear() {
		return caryear;
	}
	public void setCaryear(String caryear) {
		this.caryear = caryear;
	}
	public String getCaruseyear() {
		return caruseyear;
	}
	public void setCaruseyear(String caruseyear) {
		this.caruseyear = caruseyear;
	}
	public Date getCarplatedate() {
		return carplatedate;
	}
	public void setCarplatedate(Date carplatedate) {
		this.carplatedate = carplatedate;
	}
	public int getCarlength() {
		return carlength;
	}
	public void setCarlength(int carlength) {
		this.carlength = carlength;
	}
	public String getCarcolor() {
		return carcolor;
	}
	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}
	public int getCarprice() {
		return carprice;
	}
	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}
	public String getCartypeintroduce() {
		return cartypeintroduce;
	}
	public void setCartypeintroduce(String cartypeintroduce) {
		this.cartypeintroduce = cartypeintroduce;
	}
	public String getCarfeedback() {
		return carfeedback;
	}
	public void setCarfeedback(String carfeedback) {
		this.carfeedback = carfeedback;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
}
