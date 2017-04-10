package cn.zucc.edu.summerWork.itf;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public interface ICarbrandManager {
	//导出所有车辆品牌信息
	public List<Beancarbrand> loadCarbrands()throws BaseException;
	//导出所有待审核的车辆品牌
	public List<Beancarbrand> loadAllApplyCarbrands()throws BaseException;
	//根据车辆品牌编号导出车辆品牌名称
	public String loadCarbrand(String brandnum)throws BaseException;
	//导出所有车辆品牌名称
	public String[] loadCarbrandname()throws BaseException;
	//根据车辆品牌名称导出车辆品牌编号
	public String loadbrandnum(String Carbrand)throws BaseException;
	//添加车辆品牌
	public void addCarbrand(Beancarbrand Carbrand)throws BaseException;
	//修改车辆品牌状态
	public void modifyCarbrandtype(String CarType,Beancarbrand Carbrand)throws BaseException;
	//修改车辆品牌
	public void modifyCarbrand(Beancarbrand Carbrand)throws BaseException;
}
