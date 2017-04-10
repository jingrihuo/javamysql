package cn.zucc.edu.summerWork.itf;

import java.util.List;

import cn.zucc.edu.summerWork.model.Beantradingcords;
import cn.zucc.edu.summerWork.util.BaseException;

public interface ITradingcordsManager {
	//添加交易记录
	public void AddTradingcords(Beantradingcords Tradingcords)throws BaseException;
	//导出买卖车数据
	public List<Beantradingcords> LoadTradingcords()throws BaseException;
}
