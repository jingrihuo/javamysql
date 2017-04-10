package cn.zucc.edu.summerWork.control.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.edu.summerWork.itf.ISellcarManager;
import cn.zucc.edu.summerWork.model.Beansellcar;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;

public class ExampleSellcarManager implements ISellcarManager {

	@Override
	public void AddSellCar(Beansellcar beansellcar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO sellcar (carnum,username,selldate,sellprice,sellway,selltype) VALUES(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, beansellcar.getCarnum());
			pst.setString(2, beansellcar.getUsername());
			pst.setDate(3, new java.sql.Date(beansellcar.getSelldate().getTime()));
			pst.setInt(4, beansellcar.getSellprice());
			pst.setString(5, beansellcar.getSellway());
			pst.setString(6, beansellcar.getSelltype());
			pst.execute();
			pst.close();
			sql = "UPDATE usercar SET cartype = ? WHERE carnum = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "上架中");
			pst.setString(2,beansellcar.getCarnum());
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
	public List<Beansellcar> loadCars(String username) throws BaseException {
		List<Beansellcar> result = new ArrayList<Beansellcar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from sellcar WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beansellcar b = new Beansellcar();
				b.setSellnum(rs.getInt(1));
				b.setCarnum(rs.getString(2));
				b.setUsername(rs.getString(3));
				b.setSelldate(rs.getDate(4));
				b.setSellprice(rs.getInt(5));
				b.setSellway(rs.getString(6));
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
	public void ModifyCar(Beansellcar Modifycar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE sellcar SET sellprice = ?,selldate = ?,sellway =? WHERE sellnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Modifycar.getSellprice());
			pst.setDate(2, new java.sql.Date(Modifycar.getSelldate().getTime()));
			pst.setString(3, Modifycar.getSellway());
			pst.setInt(4, Modifycar.getSellnum());
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
	public void ShelvesCar(Beansellcar Deletecar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sellcar WHERE sellnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Deletecar.getSellnum());
			pst.execute();
			pst.close();
			sql = "UPDATE usercar SET cartype = ? WHERE carnum = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "通过申请");
			pst.setString(2,Deletecar.getCarnum());
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
	public List<Beanusercar> QuerySellCar(String brandnum, String carcategory,String Username) throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE cartype = ? and username != ?";
			if (!brandnum.equals(" ")) {
				sql += " and brandnum = ?";
			}
			if (!carcategory.equals(" ")) {
				sql += " and carcategory = ?";
			}
			sql += " ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "上架中");
			pst.setString(2, Username);
			if (!brandnum.equals(" ")) {
				pst.setString(3, brandnum);
			}
			if (!carcategory.equals(" ")) {
				pst.setString(4, carcategory);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Beanusercar b = new Beanusercar();
				b.setCarnum(rs.getString(1));
				b.setCartypenum(rs.getString(3));
				b.setBrandnum(rs.getString(4));
				b.setCarcategory(rs.getString(5));
				b.setCartransmission(rs.getString(6));
				b.setCardisplacement(rs.getString(7));
				b.setCaryear(rs.getString(8));
				b.setCaruseyear(rs.getString(9));
				b.setCarplatedate(rs.getDate(10));
				b.setCarlength(rs.getInt(11));
				b.setCarcolor(rs.getString(12));
				b.setCarprice(rs.getInt(13));
				b.setCartypeintroduce(rs.getString(14));
				b.setCartype(rs.getString(16));
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
	public Beansellcar loadSellcar(String carnum) throws BaseException {
		Beansellcar result = new Beansellcar();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from sellcar where carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, carnum);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result.setSellnum(rs.getInt(1));
				result.setCarnum(rs.getString(2));
				result.setUsername(rs.getString(3));
				result.setSelldate(rs.getDate(4));
				result.setSellprice(rs.getInt(5));
				result.setSellway(rs.getString(6));
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
	public void DeleteSellCar(Beansellcar Deletecar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sellcar WHERE sellnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Deletecar.getSellnum());
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
}
