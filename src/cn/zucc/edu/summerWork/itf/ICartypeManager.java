package cn.zucc.edu.summerWork.itf;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancartype;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public interface ICartypeManager {
	//导出所有可用的车型
	public List<Beancartype> loadCartypes()throws BaseException;
	//导出所有待审核的车型
	public List<Beancartype> loadAllApplyCartypes()throws BaseException;
	//根据编号导出名字
	public String loadCartype(String cartypenum)throws BaseException;
	//导出所有车型名字
	public String[] loadAllCartypename()throws BaseException;
	//根据名字导出车辆车型编号
	public String loadCartypenum(String Cartype)throws BaseException;
	//添加车型
	public void addCartype(Beancartype type)throws BaseException;
	//处理申请
	public void ModifyCartype(String CarType,Beancartype type)throws BaseException;
	//修改车辆车型信息
	public void ModifyCartype(Beancartype type)throws BaseException;
}
