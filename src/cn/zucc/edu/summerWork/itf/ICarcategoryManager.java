package cn.zucc.edu.summerWork.itf;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public interface ICarcategoryManager {
	//导出所有待申请的车系
	public List<Beancarcategory>  loadApplyCategoryname() throws BaseException;
	//根据品牌导出车系
	public String[] loadCategoryname(String Carbrandname) throws BaseException;
	//添加车辆车系
	public void addCategory(Beancarcategory carcategory)throws BaseException;
	//修改车辆车系状态
	public void modifyCategory(String Type, Beancarcategory Carcategory)throws BaseException;
	//修改车辆车系信息
	public void modifyCategory(Beancarcategory Carcategory)throws BaseException;
	//导出所有通过申请的车系
	public List<Beancarcategory>  loadCategoryname() throws BaseException;
}
