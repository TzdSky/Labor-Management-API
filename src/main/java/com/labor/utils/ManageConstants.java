package com.labor.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量类
 *
 * @author liuxq
 * @date
 */
public class ManageConstants extends ApiConstants {
	// 缺少必填参数或参数为空
	public final static int ERROR_405 = 405;
	public final static String ERROR_405_TEXT = "传入参数有误";
	// 文件上传返回提示信息-文件过大
	public final static int ERROR_201 = 201;
	public final static String ERROR_201_TEXT = "文件过大";
	// 文件上传返回提示信息-系统错误
	public final static int ERROR_202 = 202;
	public final static String ERROR_202_TEXT = "系统错误";
	// 身份证识别失败
	public final static int ERROR_203 = 203;
	public final static String ERROR_203_TEXT = "人脸识别失败";
	// 没有查询到默认地址
	public final static int ERROR_204 = 204;
	public final static String ERROR_204_TEXT = "没有设置默认地址";
	// 字典项重复
	public final static int ERROR_205 = 205;
	public final static String ERROR_205_TEXT = "字典项已存在";
	// 未找到该表单材料附件
	public final static int ERROR_206 = 206;
	public final static String ERROR_206_TEXT = "未找到该表单材料附件";
	//查询数据失败
	public final static  int ERROR_207 = 207;
	public final static String ERROR_207_TEXT = "查询失败";

	// HTTP请求方法
	public final static String HTTP_METHOD_POST = "POST";
	public final static String HTTP_METHOD_GET = "GET";
	public final static String HTTP_METHOS_DELETE = "DELETE";
	public final static String HTTP_METHOD_PUT = "PUT";
	public final static String HTTP_METHOD_HEAD = "HEAD";
	public final static String HTTP_METHOD_PATCH = "PATCH";
	public final static String HTTP_METHOD_OPTIONS = "OPTIONS";
	public final static String HTTP_METHOD_TRACE = "TRACE";

	public final static Map<String, String> LEGALPERSONTHEME = new HashMap<String, String>() {
		{
			put("005", "设立变更");
			put("010", "准营准办");
			put("015", "资质认证");
			put("020", "年检年审");
			put("025", "税收财务");
			put("030", "人力资源");
			put("035", "社会保障");
			put("040", "投资审批");
			put("045", "融资信贷");
			put("050", "抵押质押");
			put("055", "商务贸易");
			put("060", "招标拍卖");
			put("065", "海关口岸");
			put("070", "涉外服务");
			put("075", "农林牧渔");
			put("085", "国土和规划建设");
			put("090", "交通运输");
			put("095", "环保绿化");
			put("100", "应对气候变化");
			put("105", "水务气象");
			put("110", "医疗卫生");
			put("115", "科技创新");
			put("120", "文体教育");
			put("125", "知识产权");
			put("130", "民族宗教");
			put("135", "质量技术");
			put("140", "检验检疫");
			put("145", "安全生产");
			put("150", "公安消防");
			put("155", "司法公证");
			put("160", "公用事业");
			put("165", "法人注销");
			put("170", "档案文物");
			put("998", "地方特色分类");
			put("999", "其他 ");
		}
	};

	public final static Map<String, String> NATUREPERSONTHEME = new HashMap<String, String>() {
		{
			put("005", "生育收养");
			put("010", "户籍办理");
			put("015", "民族宗教");
			put("020", "教育科研");
			put("025", "入伍服役");
			put("030", "就业创业");
			put("035", "设立变更");
			put("040", "准营准办");
			put("045", "抵押质押");
			put("050", "职业资格");
			put("055", "行政缴费");
			put("060", "婚姻登记");
			put("065", "优待抚恤");
			put("070", "规划建设");
			put("075", "住房保障");
			put("085", "社会保障（社会保险、社会救助）");
			put("090", "证件办理");
			put("095", "交通出行");
			put("100", "旅游观光");
			put("105", "出境入境");
			put("110", "消费维权");
			put("115", "公共安全");
			put("120", "司法公证");
			put("125", "知识产权");
			put("130", "环保绿化");
			put("135", "文化体育");
			put("140", "公用事业");
			put("145", "医疗卫生");
			put("150", "离职退休");
			put("155", "死亡殡葬");
			put("998", "地方特色分类");
			put("999", "其他");
		}
	};

}
