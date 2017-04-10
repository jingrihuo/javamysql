package cn.zucc.edu.summerWork.itf;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public interface IUserManager {
	//导出所有用户
	public List<Beanuser> loadAllUsers() throws BaseException;
	//注册用户
	public void reg(String username, String pwd,String pwd2,String userphone,String useremail,String useraddress) throws BaseException;
	//注册管理员
	public void superReg(String username, String pwd,String pwd2) throws BaseException;
	//登录
	public Beanuser login(String userid,String pwd)throws BaseException;
	//输错密码
	public int wrongPwd(String userid)throws BaseException;
	//重置输错密码
	public void resetWrongPwd(String userid)throws BaseException;
	//用户密码输错封禁
	public void userBannedtime(String userid)throws BaseException; 
	//用户解封
	public void resetUserBannedtime(String userid)throws BaseException;
}
