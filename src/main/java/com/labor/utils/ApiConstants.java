package com.labor.utils;

/**
 * 系统常量类
 * @author liuxq
 * @date
 */
public class ApiConstants {
	// 接口返回-成功
	public final static int SUCCESS_200 = 200;
	public final static String SUCCESS_200_TEXT = "成功";

	//删除接口返回
	public final static int ERROR_205 = 205;
	public final static String ERROR_205_TEXT = "删除失败";

	// 接口返回-系统错误
	public final static int ERROR_500 = 500;
	public final static String ERROR_500_TEXT = "系统错误";
	// 接口返回-第三方系统调用失败
	public final static int ERROR_501 = 501;
	public final static String ERROR_501_TEXT = "第三方系统调用失败";
	// 接口返回-第三方接口返回结果解析失败
	public final static int ERROR_502 = 502;
	public final static String ERROR_502_TEXT = "第三方接口返回结果解析失败";
	// 接口返回-参数错误
	public final static int ERROR_503 = 503;
	public final static String ERROR_503_TEXT = "参数错误";

	/* 数据有效状态，Y有效，N无效 **/
	public final static String VALID_STATUS_Y = "Y";
	public final static String VALID_STATUS_N = "N";

	// 状态字段-有效
	public final static int STATE_VALID = 1;
	// 状态字段-无效
	public final static int STATE_INVALID = 0;

	// 获取accessToken失败
	public final static int ERROR_409 = 409;
	public final static String ERROR_409_TEXT = "获取accessToken失败，clientId或clientSecret传参错误";

	// accessToken认证失败
	public final static int ERROR_401 = 401;
	public final static String ERROR_401_TEXT = "accessToken认证失败";

	//缺少必填参数
	public final static int ERROR_403 = 403;
	public final static String ERROR_403_TEXT = "必传参数未填写!";

	//重复用户
	public final static String ERROR_REPEAT_TEXT = "当前用户已存在!";

	//默认的用户名
	public final static String DEFAULT_USERNAME = "admin";

	//重复用户
	public final static String ERROR_REPEAT_Company_TEXT = "当前公司名称已存在!";

	//重复考勤组名
	public final static String ERROR_REPEAT_ATT_TEXT = "当前考勤组已存在!";

}
