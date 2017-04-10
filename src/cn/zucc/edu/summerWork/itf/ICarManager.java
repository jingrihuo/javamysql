package cn.zucc.edu.summerWork.itf;

import cn.zucc.edu.summerWork.util.BaseException;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;

public interface ICarManager {
	//导出所有二手车
	public List<Beanusercar> loadAllCars() throws BaseException;
	//用户车
	public List<Beanusercar> loadCars(Beanuser user) throws BaseException;
	//导出申请的车
	public List<Beanusercar> loadApplyCars() throws BaseException;
	//导出出售的车
	public List<Beanusercar> loadSellCars(String username) throws BaseException;
	//
	public List<Beanusercar> loadShelvesCars() throws BaseException;
	//申请二手车
	public boolean addCar(Beanusercar usercar)throws BaseException;
	//修改车状态
	public void ModifyCartype(String cartype,Beanusercar usecar)throws BaseException;
	//给二手车设置价格
	public void AddCarPrice(int carPrice,Beanusercar usecar)throws BaseException;
	//修改二手车信息
	public void Modifycar(Beanusercar Modifycar)throws BaseException;
	//删除二手车
	public void deleteCar(Beanusercar usercar)throws BaseException;
	//根据车编号查询车
	public Beanusercar loadCar(String carnum)throws BaseException;
}
