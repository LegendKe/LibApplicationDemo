package com.ptja.android.mms.commons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TabHost;
import android.widget.Toast;

import com.ptja.android.mms.bean.UserBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobeVariable {
	public static UserBean UserInfos = null;


	public static final int SCAN_TYPE_ADD = 1;//录入
	public static final int SCAN_TYPE_IN = 2;//入库
	public static final int SCAN_TYPE_OUT = 3;//出库
	public static final int SCAN_TYPE_FIX = 4;//维保
	public static final int SCAN_TYPE_DISCARD = 5;//报废
	public static final int SCAN_TYPE_DEAL_REPAIR_TASK = 6;//处理维保任务
	public static final int SCAN_TYPE_DEAL_DISCARD_TASK = 7;//处理报废任务
	public static final int SCAN_TYPE_DEAL_CHECK_TASK = 8;//处理检查任务

	public static final int SCAN_TYPE_ADD_REPAIR_TASK = 9;//添加维保任务
	public static final int SCAN_TYPE_ADD_DISCARD_TASK = 10;//添加报废任务
	public static final int SCAN_TYPE_ADD_CHECK_TASK = 11;//添加检查任务

	public static final int USER_TYPE_STORE_ADMIN = 1;//仓库管理员
	public static final int USER_TYPE_TRUCK_ADMIN = 2;//车辆管理员
	public static final int USER_TYPE_CAPTAIN = 3;//大队长
	public static final int USER_TYPE_BOSS = 4;//政委
	public static final int USER_TYPE_SOLDIER = 5;//战士


	public static Map<String,String> REPAIR_TASK_RESULT = new HashMap<>();
	public static Map<String,String> DISCARD_TASK_RESULT = new HashMap<>();
	public static Map<String,Integer> TASK_TYPE_OPERATE_TYPE = new HashMap<>();
	public static Map<String,Integer> TASK_TYPE_ADD_TYPE = new HashMap<>();
	public static Map<String,Integer> EQUIP_STATUS = new HashMap<>();
	public static Map<String,String> TASK_TYPE = new HashMap<>();
	public static List<String> USE_TYPE = new ArrayList<>();
	public static List<String> UNITS = new ArrayList<>();
	static{
		EQUIP_STATUS.put("报废",0);
//		EQUIP_STATUS.put("删除",-1);
		EQUIP_STATUS.put("在库",1);
		EQUIP_STATUS.put("领用",2);
		EQUIP_STATUS.put("借用",3);
		EQUIP_STATUS.put("维保",4);

		TASK_TYPE.put("装备检查","1");
		TASK_TYPE.put("装备维保","2");
		TASK_TYPE.put("装备报废","3");
		TASK_TYPE.put("车辆检查","4");
		TASK_TYPE.put("车辆维保","5");
		TASK_TYPE.put("随车物资检查","6");
		TASK_TYPE.put("随车物资维保","7");

		TASK_TYPE_OPERATE_TYPE.put(1+"",SCAN_TYPE_DEAL_CHECK_TASK);
		TASK_TYPE_OPERATE_TYPE.put(2+"",SCAN_TYPE_DEAL_REPAIR_TASK);
		TASK_TYPE_OPERATE_TYPE.put(3+"",SCAN_TYPE_DEAL_DISCARD_TASK);

		TASK_TYPE_ADD_TYPE.put(1+"",SCAN_TYPE_ADD_CHECK_TASK);
		TASK_TYPE_ADD_TYPE.put(2+"",SCAN_TYPE_ADD_REPAIR_TASK);
		TASK_TYPE_ADD_TYPE.put(3+"",SCAN_TYPE_ADD_DISCARD_TASK);

		REPAIR_TASK_RESULT.put("维保完成","1");
		REPAIR_TASK_RESULT.put("报废","2");
		DISCARD_TASK_RESULT.put("报废","2");
		DISCARD_TASK_RESULT.put("继续服役","3");
		UNITS.add("瓶");
		UNITS.add("双");
		UNITS.add("套");
		UNITS.add("只");
		UNITS.add("付");
		UNITS.add("个");
		UNITS.add("件");
		UNITS.add("把");
		UNITS.add("罐");
		UNITS.add("包");
		UNITS.add("盒");
		UNITS.add("辆");
		UNITS.add("升");
		UNITS.add("方");
		UNITS.add("块");
		UNITS.add("斤");
		UNITS.add("吨");
		USE_TYPE.add("使用类型1");
		USE_TYPE.add("使用类型2");
		USE_TYPE.add("使用类型3");
		USE_TYPE.add("使用类型4");
	}
}
