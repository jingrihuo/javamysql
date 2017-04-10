package cn.zucc.edu.summerWork.control.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.zucc.edu.summerWork.itf.ICarcategoryManager;
import cn.zucc.edu.summerWork.itf.ICartypeManager;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;

public class ExampleCarcategoryManager implements ICarcategoryManager{

	@Override
	public String[] loadCategoryname(String Carbrandname) throws BaseException {
		List list = new ArrayList();
		String[] result = null;
		String brandnum;
		Connection conn = null; 
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT brandnum FROM carbrand WHERE brandname = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Carbrandname);
			ResultSet rs = pst.executeQuery();
			rs.next();
			brandnum = rs.getString(1);
			pst.close();
			sql = "SELECT categoryname FROM carcategory WHERE brandnum = ? AND categorytype = '通过'";
			pst = conn.prepareStatement(sql);
			pst.setString(1, brandnum);
			rs = pst.executeQuery();
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
	public void addCategory(Beancarcategory carcategory) throws BaseException {
		if (carcategory.getCategorynum().equals("")) {
			throw new BusinessException("车系编号不可为空");
		}
		
		if (carcategory.getCategoryname().equals("")) {
			throw new BusinessException("车系名称不可为空");
		}
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carcategory WHERE categorynum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, carcategory.getCategorynum());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("车辆品牌编号已经被占用");
			}
			pst.close();
			rs.close();
			sql = "INSERT INTO carcategory(categorynum,brandnum,categoryname,categorytype) VALUES (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, carcategory.getCategorynum());
			pst.setString(2, carcategory.getBrandnum());
			pst.setString(3, carcategory.getCategoryname());
			pst.setString(4, carcategory.getCategorytype());
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
	public void modifyCategory(String Type,Beancarcategory Carcategory) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE carcategory SET categorytype = ? WHERE categorynum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Type);
			pst.setString(2, Carcategory.getCategorynum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Beancarcategory> loadApplyCategoryname() throws BaseException {
		List<Beancarcategory> result = new ArrayList<Beancarcategory>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carcategory WHERE categorytype = '申请中'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancarcategory b = new Beancarcategory();
				b.setCategorynum(rs.getString(1));
				b.setBrandnum(rs.getString(2));
				b.setCategoryname(rs.getString(3));
				b.setCategorytype(rs.getString(4));
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
	public List<Beancarcategory> loadCategoryname() throws BaseException {
		List<Beancarcategory> result = new ArrayList<Beancarcategory>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM carcategory WHERE categorytype = ? or categorytype = ? ORDER BY categoryname ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "通过");
			pst.setString(2, "禁用");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beancarcategory b = new Beancarcategory();
				b.setCategorynum(rs.getString(1));
				b.setBrandnum(rs.getString(2));
				b.setCategoryname(rs.getString(3));
				b.setCategorytype(rs.getString(4));
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
	public void modifyCategory(Beancarcategory Carcategory) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE carcategory SET categoryname = ? WHERE categorynum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Carcategory.getCategoryname());
			pst.setString(2, Carcategory.getCategorynum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
