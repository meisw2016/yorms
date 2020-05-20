package cn.com.yusys.file.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Administrator
 *
 */
public class Constants {
	
	public static Map<String,Integer> riskLevelMap = null;
	
	static {
		riskLevelMap = new ConcurrentHashMap<String, Integer>();
		riskLevelMap.put("1", 1);
		riskLevelMap.put("2", 2);
		riskLevelMap.put("3", 3);
		riskLevelMap.put("4", 4);
		riskLevelMap.put("5", 5);
	}
	
	/**
	 * 法规政策分类一级，编码，外规
	 */
	public static final String TYPETREECODE_W = "00001";
	/**
	 * 法规政策分类一级，名称，外规
	 */
	public static final String TYPETREECODE_W_CN = "外规";
	/**
	 * 法规政策分类一级，编码，内规
	 */
	public static final String TYPETREECODE_N = "00002";
	/**
	 * 法规政策分类一级，名称，内规
	 */
	public static final String TYPETREECODE_N_CN = "内规";
	
	

	/**
	 * 用户管理--默认用户组
	 */
	public static final Long DEFAULT_GROUP_ID = (long) 2;
	
	/**
	 * 用户管理--默认用户角色
	 */
	public static final Long DEFAULT_AUTHORITY_ID = (long) 2;
	
	
	

	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String result = uuid.toString().replace("-", "");
		return result;
	}
	
	public static void main(String[] args) {
		String result = getUUID();
	    System.out.println(result);
	    System.out.println(result.length());
    }
}
