package cn.zucc.edu.summerWork.control.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.ParameterStyle;

import cn.zucc.edu.summerWork.itf.IUserManager;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;
import cn.zucc.edu.summerWork.util.DbException;

public class ExampleUserManager implements IUserManager {

	@Override
	public void reg(String username, String pwd, String pwd2, String userphone, String useremail, String useraddress)
			throws BaseException {
		if (username.equals(""))
			throw new BaseException("用户名不可为空，请输入用户名");
		if (pwd.equals(""))
			throw new BaseException("密码不可为空，请输入密码");
		if (pwd2.equals(""))
			throw new BaseException("请重复下自己设定的密码");
		if (!(pwd.equals(pwd2)))
			throw new BaseException("请确保两次密码输入一致");
		if (useremail.equals(""))
			throw new BaseException("邮箱地址不可为空");
		if (useraddress.equals(""))
			throw new BaseException("用户地址不可为空");
		if (userphone.equals(""))
			throw new BaseException("用户电话不可为空");
		String userlevel = "可用";
		int userwrongcode = 0;
		String usertype = "用户";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user where username = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				throw new BaseException("用户名已经被占用");
			rs.close();
			pst.close();
			sql = "INSERT INTO `user` (username,usercode,userphone,useremail,useraddress,userlevel,usercreatedate,userwrongcode,usertype) VALUES(?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			pst.setString(3, userphone);
			pst.setString(4, useremail);
			pst.setString(5, useraddress);
			pst.setString(6, userlevel);
			pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			pst.setInt(8, userwrongcode);
			pst.setString(9, usertype);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public Beanuser login(String userid, String pwd) throws BaseException {
		if (pwd.equals("")) {
			throw new BusinessException("请输入密码");
		}
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT username,usercode,userlevel,userwrongcode,usertype,userbannedtime FROM `user` WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BaseException("不存在该用户名");
			Beanuser result = new Beanuser();
			result.setUsername(rs.getString(1));
			result.setUsercode(rs.getString(2));
			result.setUserlevel(rs.getString(3));
			result.setUserwrongcode(rs.getInt(4));
			result.setUsertype(rs.getString(5));
			result.setUserbannedtime(rs.getTimestamp(6));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public int wrongPwd(String userid) throws BaseException {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE `user` SET userwrongcode=userwrongcode+1 WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.execute();
			pst.close();
			sql = "SELECT userwrongcode FROM `user` WHERE username = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getInt(1);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void resetWrongPwd(String userid) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "	UPDATE user SET userwrongcode = 0 WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userBannedtime(String userid) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE user SET userbannedtime = ? WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,
					new Timestamp(new Timestamp(System.currentTimeMillis()).getTime() + +3 * 24 * 60 * 60 * 1000));
			pst.setString(2, userid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resetUserBannedtime(String userid) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE user SET userbannedtime = null WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.execute();
			pst.close();
			sql = "	UPDATE user SET userwrongcode = 0 WHERE username = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Beanuser> loadAllUsers() throws BaseException {
		List<Beanuser> result = new ArrayList<Beanuser>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from `user` WHERE usertype = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "用户");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beanuser b = new Beanuser();
				b.setUsername(rs.getString(1));
				b.setUserphone(rs.getString(3));
				b.setUseremail(rs.getString(4));
				b.setUseraddress(rs.getString(5));
				b.setUsercreatedate(rs.getDate(7));
				b.setUserbannedtime(rs.getTimestamp(8));
				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	@Override
	public void superReg(String username, String pwd, String pwd2) throws BaseException {
		if (username.equals(""))
			throw new BaseException("用户名不可为空，请输入用户名");
		if (pwd.equals(""))
			throw new BaseException("密码不可为空，请输入密码");
		if (pwd2.equals(""))
			throw new BaseException("请重复下自己设定的密码");
		String userlevel = "可用";
		int userwrongcode = 0;
		String usertype = "管理员";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user where username = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				throw new BaseException("用户名已经被占用");
			rs.close();
			pst.close();
			sql = "INSERT INTO `user` (username,usercode,userlevel,usercreatedate,userwrongcode,usertype) VALUES(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			pst.setString(3, userlevel);
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.setInt(5, userwrongcode);
			pst.setString(6, usertype);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
