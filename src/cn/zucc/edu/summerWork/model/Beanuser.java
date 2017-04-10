package cn.zucc.edu.summerWork.model;

import java.util.Date;


public class Beanuser {
	public static Beanuser LoginUser=null;
	private String username;//用户名
	private String usercode;//用户密码
	private String userphone;//用户手机号码
	private String useremail;//用户邮箱
	private String useraddress;//用户住址
	private String userlevel;//用户状态
	private Date usercreatedate;//用户创建时间
	private Date userbannedtime;//用户封禁时间
	private int  userwrongcode;//用户输错密码次数
	private String usertype;//用户类别
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}
	public Date getUsercreatedate() {
		return usercreatedate;
	}
	public void setUsercreatedate(Date usercreatedate) {
		this.usercreatedate = usercreatedate;
	}
	public Date getUserbannedtime() {
		return userbannedtime;
	}
	public void setUserbannedtime(Date userbannedtime) {
		this.userbannedtime = userbannedtime;
	}
	public int getUserwrongcode() {
		return userwrongcode;
	}
	public void setUserwrongcode(int userwrongcode) {
		this.userwrongcode = userwrongcode;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
}
