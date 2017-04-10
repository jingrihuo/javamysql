package cn.zucc.edu.summerWork.control.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.edu.summerWork.itf.ITradingcordsManager;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beantradingcords;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.DBUtil;

public class ExamTradingcordsManager implements ITradingcordsManager {

	@Override
	public void AddTradingcords(Beantradingcords Tradingcords) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO tradingrecords(username,tradingsell,tradingbuy,tradingcarnum,tradingprice,tradingdata)VALUES(?,?,?,?,?,?) ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Tradingcords.getUsername());
			pst.setString(2, Tradingcords.getTradingsell());
			pst.setString(3, Tradingcords.getTradingbuy());
			pst.setString(4, Tradingcords.getTradingcarnum());
			pst.setInt(5, Tradingcords.getTradingprice());
			pst.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.execute();
			pst.close();
			sql = "UPDATE usercar SET username = ?,cartype = ? WHERE carnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, Tradingcords.getUsername());
			pst.setString(2, "通过申请");
			pst.setString(3, Tradingcords.getTradingcarnum());
			pst.execute();
			pst.close();
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
	}

	@Override
	public List<Beantradingcords> LoadTradingcords() throws BaseException {
		List<Beantradingcords> result = new ArrayList<Beantradingcords>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM tradingrecords WHERE tradingbuy = ? or tradingsell = ? ORDER BY tradingbuy";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Beanuser.LoginUser.getUsername());
			pst.setString(2, Beanuser.LoginUser.getUsername());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beantradingcords b = new Beantradingcords();
				b.setTradingsell(rs.getString(3));
				b.setTradingbuy(rs.getString(4));
				b.setTradingprice(rs.getInt(6));
				b.setTradingdata(rs.getTimestamp(7));
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

}
