package cn.zucc.edu.summerWork.itf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.edu.summerWork.model.Beansellcar;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.util.DBUtil;

public interface ISellcarManager {
	//出售二手车
	public void AddSellCar(Beansellcar beansellcar)throws BaseException;
	//导出出售信息
	public List<Beansellcar> loadCars(String username) throws BaseException;	
	//修改上架信息
	public void ModifyCar(Beansellcar Modifycar)throws BaseException;
	//二手车下架
	public void ShelvesCar(Beansellcar Deletecar)throws BaseException;
	//查询二手车
	public List<Beanusercar> QuerySellCar(String brandnum,String carcategory,String Username)throws BaseException;
	//根据车标号导出该车的销售信息
	public Beansellcar loadSellcar(String carnum)throws BaseException;
	//
	public void DeleteSellCar(Beansellcar Deletecar)throws BaseException;
}
