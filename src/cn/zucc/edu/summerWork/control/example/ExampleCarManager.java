package cn.zucc.edu.summerWork.control.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import cn.zucc.edu.summerWork.itf.ICarManager;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.BusinessException;
import cn.zucc.edu.summerWork.util.DBUtil;
import cn.zucc.edu.summerWork.util.DbException;

public class ExampleCarManager implements ICarManager{

	@Override
	public List<Beanusercar> loadCars(Beanuser user) throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE username = ? ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
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
	public boolean addCar(Beanusercar usercar) throws BaseException {
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, usercar.getCarnum());
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				throw new BusinessException("已经存在汽车编号请重新输入新的汽车编号");
			}
			pst.close();
			rs.close();
			sql = "INSERT INTO usercar(carnum,username,cartypenum,brandnum,carcategory,cartransmission,cardisplacement,caryear,caruseyear,carplatedate,carlength,carcolor,carprice,cartypeintroduce,cartype) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, usercar.getCarnum());
			pst.setString(2, usercar.getUsername());
			pst.setString(3, usercar.getCartypenum());
			pst.setString(4, usercar.getBrandnum());
			pst.setString(5, usercar.getCarcategory());
			pst.setString(6, usercar.getCartransmission());
			pst.setString(7, usercar.getCardisplacement());
			pst.setString(8, usercar.getCaryear());
			pst.setString(9, usercar.getCaruseyear());
			pst.setTimestamp(10,new Timestamp(usercar.getCarplatedate().getTime()));
			pst.setInt(11, usercar.getCarlength());
			pst.setString(12, usercar.getCarcolor());
			pst.setInt(13, usercar.getCarprice());
			pst.setString(14, usercar.getCartypeintroduce());
			pst.setString(15, usercar.getCartype());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}	
		return true;	
	}

	@Override
	public List<Beanusercar> loadApplyCars() throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE cartype= ? ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"待审核");
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
	public void ModifyCartype(String cartype,Beanusercar usecar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE usercar SET cartype = ? WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cartype);
			pst.setString(2, usecar.getCarnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Beanusercar> loadAllCars() throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
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
	public void AddCarPrice(int carPrice,Beanusercar usecar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE usercar SET carprice = ? WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, carPrice);
			pst.setString(2, usecar.getCarnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Modifycar(Beanusercar Modifycar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE usercar SET cardisplacement = ?,caryear = ?,caruseyear = ?,carplatedate = ?,carlength = ?,carcolor = ?,cartypeintroduce =? WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Modifycar.getCardisplacement());
			pst.setString(2, Modifycar.getCaryear());
			pst.setString(3, Modifycar.getCaruseyear());
			pst.setTimestamp(4, new Timestamp(Modifycar.getCarplatedate().getTime()));
			pst.setInt(5, Modifycar.getCarlength());
			pst.setString(6, Modifycar.getCarcolor());
			pst.setString(7, Modifycar.getCartypeintroduce());
			pst.setString(8, Modifycar.getCarnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCar(Beanusercar usercar) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM sellcar WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, usercar.getCarnum());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("该车处于挂牌状态请先下架");
			}
			pst.close();
			rs.close();
			sql = "DELETE FROM usercar WHERE carnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, usercar.getCarnum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Beanusercar> loadSellCars(String username) throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE cartype= ? and username = ? ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"通过申请");
			pst.setString(2, username);
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
	public List<Beanusercar> loadShelvesCars() throws BaseException {
		List<Beanusercar> result = new ArrayList<Beanusercar>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE cartype= ? ORDER BY brandnum";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"上架中");
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
	public Beanusercar loadCar(String carnum) throws BaseException {
		Beanusercar result = new Beanusercar();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * from usercar WHERE carnum = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,carnum);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result.setCarnum(rs.getString(1));
				result.setCartypenum(rs.getString(3));
				result.setBrandnum(rs.getString(4));
				result.setCarcategory(rs.getString(5));
				result.setCartransmission(rs.getString(6));
				result.setCardisplacement(rs.getString(7));
				result.setCaryear(rs.getString(8));
				result.setCaruseyear(rs.getString(9));
				result.setCarplatedate(rs.getDate(10));
				result.setCarlength(rs.getInt(11));
				result.setCarcolor(rs.getString(12));
				result.setCarprice(rs.getInt(13));
				result.setCartypeintroduce(rs.getString(14));
				result.setCartype(rs.getString(16));
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
