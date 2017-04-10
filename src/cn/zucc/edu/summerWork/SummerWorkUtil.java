package cn.zucc.edu.summerWork;

import cn.zucc.edu.summerWork.control.example.ExamTradingcordsManager;
import cn.zucc.edu.summerWork.control.example.ExampleCarManager;
import cn.zucc.edu.summerWork.control.example.ExampleCarbrandManager;
import cn.zucc.edu.summerWork.control.example.ExampleCarcategoryManager;
import cn.zucc.edu.summerWork.control.example.ExampleCartypeManager;
import cn.zucc.edu.summerWork.control.example.ExampleSellcarManager;
import cn.zucc.edu.summerWork.control.example.ExampleUserManager;

import cn.zucc.edu.summerWork.itf.ICarManager;
import cn.zucc.edu.summerWork.itf.ICarbrandManager;
import cn.zucc.edu.summerWork.itf.ICarcategoryManager;
import cn.zucc.edu.summerWork.itf.ICartypeManager;
import cn.zucc.edu.summerWork.itf.ISellcarManager;
import cn.zucc.edu.summerWork.itf.ITradingcordsManager;
import cn.zucc.edu.summerWork.itf.IUserManager;

public class SummerWorkUtil {
	public static IUserManager userManager = new ExampleUserManager();
	public static ICarManager carManager = new ExampleCarManager();
	public static ICarbrandManager carbrandManager = new ExampleCarbrandManager();
	public static ICartypeManager cartypeManager = new ExampleCartypeManager();
	public static ICarcategoryManager carcategoryManager = new ExampleCarcategoryManager(); 
	public static ISellcarManager sellcarManager = new ExampleSellcarManager();
	public static ITradingcordsManager tradingcordsManager = new ExamTradingcordsManager();
}
