package com.ptja.android.mms.commons;

public class UrlConstants {

	//private static final String BASE_URL = "http://121.42.46.157:8081/api/";
	private static final String BASE_URL = "http://116.62.38.223:8086/api/";

	public static final String URL_LOGIN = BASE_URL+"auth";

	public static final String URL_QUERY_EQUIP_BY_CODE = BASE_URL+"equipment/query_by_code";

	public static final String URL_QUERY_EQUIP_TYPE = BASE_URL+"equipment_type/query";

	public static final String URL_QUERY_DEPT = BASE_URL+"dept/query";

	public static final String URL_COMMIT_NEW_EQUIP = BASE_URL+"equipment/add";

	public static final String URL_COMMIT_OUT_STOCK = BASE_URL+"equipment/outstock";

	public static final String URL_COMMIT_IN_STOCK = BASE_URL+"equipment/instock";

	public static final String URL_QUERY_DEPT_USER = BASE_URL+"user/query";

	public static final String URL_QUERY_TASK = BASE_URL+"task/query";

	public static final String URL_QUERY_EQUIP_TOTAL = BASE_URL+"statistics/amount";

	public static final String URL_QUERY_EQUIP_STATUS = BASE_URL+"statistics/status";

	public static final String URL_QUERY_EQUIP_DEPT = BASE_URL+"statistics/dept";

	public static final String URL_QUERY_EQUIP_TYPE_STATISIC = BASE_URL+"statistics/type";

	public static final String URL_QUERY_EQUIP_ARROVE = BASE_URL+"application/pending";

	public static final String URL_QUERY_EQUIP = BASE_URL+"equipment/query";

	public static final String URL_TASK_ADD = BASE_URL+"task/add";

	public static final String URL_SUBMIT_REPAIRE_TASK = BASE_URL+"maintenance_log/add";

	public static final String URL_SUBMIT_DISCARD_TASK = BASE_URL+"scrap_log/add";

	public static final String URL_COMPLETE_TASK = BASE_URL+"task/deal";

	public static final String URL_TASK_RECORD_ADD = BASE_URL+"task/record/add";

	public static final String URL_EQUIP_APPROVE = BASE_URL+"application/approval";

	public static final String URL_EQUIP_APPROVE_ADD = BASE_URL+"approval/add";

}
