package cn.zucc.edu.summerWork.control.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.edu.summerWork.itf.ICarbrandManager;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;

public class ExampleCarbrandManager implements ICarbrandManager{

	@Override
	public List<Beancarbrand> loadCarbrands() throws BaseException {
		List<Beancarbrand> result = new ArrayList<Beancarbrand>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carbrand WHERE brandtype = ? or brandtype = ?  ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "通过");
			pst.setString(2, "禁用");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancarbrand b = new Beancarbrand();
				b.setBrandnum(rs.getString(1));
				b.setBrandname(rs.getString(2));
				b.setBrandcountry(rs.getString(3));
				b.setBrandintroduce(rs.getString(4));
				b.setBrandtype(rs.getString(5));
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
	public String loadCarbrand(String brandnum) throws BaseException {
		String result = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT brandname FROM carbrand WHERE brandnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, brandnum);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getString(1);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String[] loadCarbrandname() throws BaseException {
		List list = new ArrayList();
		String[] result = null;
		Connection conn = null; 
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT brandname FROM carbrand WHERE brandtype = '通过'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final int size = list.size();
		result = (String[])list.toArray(new String[size]);
		return result;
	}

	@Override
	public String loadbrandnum(String Carbrand) throws BaseException {
		String result = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT brandnum FROM carbrand WHERE  brandname= ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Carbrand);
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getString(1);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void addCarbrand(Beancarbrand Carbrand) throws BaseException {
		if (Carbrand.getBrandnum().equals("")) {
			throw new BusinessException("车辆品牌编号不可为空");
		}
		if (Carbrand.getBrandname().equals("")) {
			throw new BusinessException("车辆品牌名字不可为空");
		}
		if (Carbrand.getBrandcountry().equals("")) {
			throw new BusinessException("车辆品牌国家不可为空");
		}
		String brandtype = "申请中";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carbrand WHERE brandnum = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Carbrand.getBrandnum());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("车辆品牌编号已经被占用");
			}
			pst.close();
			rs.close();
			sql = "INSERT INTO carbrand (brandnum,brandname,brandcountry,brandintroduce,brandtype) VALUES (?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, Carbrand.getBrandnum());
			pst.setString(2, Carbrand.getBrandname());
			pst.setString(3, Carbrand.getBrandcountry());
			pst.setString(4, Carbrand.getBrandintroduce());
			pst.setString(5, brandtype);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void modifyCarbrandtype(String CarType,Beancarbrand Carbrand) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE carbrand SET brandtype = ? WHERE brandnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, CarType);
			pst.setString(2, Carbrand.getBrandnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Beancarbrand> loadAllApplyCarbrands() throws BaseException {
		List<Beancarbrand> result = new ArrayList<Beancarbrand>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carbrand WHERE brandtype = '申请中' ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancarbrand b = new Beancarbrand();
				b.setBrandnum(rs.getString(1));
				b.setBrandname(rs.getString(2));
				b.setBrandcountry(rs.getString(3));
				b.setBrandintroduce(rs.getString(4));
				b.setBrandtype(rs.getString(5));
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
	public void modifyCarbrand(Beancarbrand Carbrand) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE carbrand SET brandcountry=?,brandintroduce=? WHERE brandnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Carbrand.getBrandcountry());
			pst.setString(2, Carbrand.getBrandintroduce());
			pst.setString(3, Carbrand.getBrandnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
