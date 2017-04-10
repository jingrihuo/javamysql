package cn.zucc.edu.summerWork.control.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.edu.summerWork.itf.ICartypeManager;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beancartype;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;

public class ExampleCartypeManager implements ICartypeManager{

	@Override
	public List<Beancartype> loadAllApplyCartypes() throws BaseException {
		List<Beancartype> result = new ArrayList<Beancartype>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM cartype WHERE cartypetype = '申请中'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancartype b = new Beancartype();
				b.setCartypenum(rs.getString(1));
				b.setCartypename(rs.getString(2));
				b.setCartypeintroduce(rs.getString(3));
				b.setCartypetype(rs.getString(4));
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
	public String loadCartype(String cartypenum) throws BaseException {
		String result = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT cartypename FROM cartype WHERE cartypenum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cartypenum);
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
	public String[] loadAllCartypename() throws BaseException {
		List list = new ArrayList();
		String[] result = null;
		Connection conn = null; 
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT cartypename FROM cartype";
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
	public String loadCartypenum(String Cartype) throws BaseException {
		String result = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT cartypenum FROM cartype WHERE cartypename = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Cartype);
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
	public void addCartype(Beancartype type) throws BaseException {
		if (type.getCartypenum().equals("")) {
			throw new BusinessException("车辆车型编号不可为空");
		}
		if (type.getCartypename().equals("")) {
			throw new BusinessException("车辆车型名字不可为空");
		}
		String brandtype = "申请中";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carbrand WHERE brandnum = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, type.getCartypenum());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("车辆车型编号已经被占用");
			}
			pst.close();
			rs.close();
			sql = "SELECT * FROM cartype WHERE cartypename = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, type.getCartypename());
			rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("车辆车型名字已经被占用");
			}
			pst.close();
			rs.close();
			sql = "INSERT INTO cartype (cartypenum,cartypename,cartypeintroduce,cartypetype) VALUES (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, type.getCartypenum());
			pst.setString(2, type.getCartypename());
			pst.setString(3, type.getCartypeintroduce());
			pst.setString(4, brandtype);
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
	public void ModifyCartype( String CarType,Beancartype type) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE cartype SET cartypetype =? WHERE cartypenum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, CarType);
			pst.setString(2, type.getCartypenum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Beancartype> loadCartypes() throws BaseException {
		List<Beancartype> result = new ArrayList<Beancartype>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM cartype WHERE cartypetype = ? or cartypetype = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "通过");
			pst.setString(2, "禁用");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancartype b = new Beancartype();
				b.setCartypenum(rs.getString(1));
				b.setCartypename(rs.getString(2));
				b.setCartypeintroduce(rs.getString(3));
				b.setCartypetype(rs.getString(4));
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
	public void ModifyCartype(Beancartype type) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE cartype SET cartypeintroduce = ? WHERE cartypenum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, type.getCartypeintroduce());
			pst.setString(2, type.getCartypenum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
