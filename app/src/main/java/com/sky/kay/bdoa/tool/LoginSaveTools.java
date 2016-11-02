package com.sky.kay.bdoa.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.sky.kay.bdoa.model.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginSaveTools {
	Context context;// 上下文参数

	public LoginSaveTools(Context context) {
		this.context = context;
	}

	// 检查是否是第一次登录
	public boolean checkIsFirst() {
		SharedPreferences sp = context.getSharedPreferences("first", 0);
		if (sp.contains("type")) {
			if (sp.getInt("type", 0) == 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	// 保存登录信息
	public void saveFlag(int type) {
		SharedPreferences sp = context.getSharedPreferences("flag", 0);
		Editor ed = sp.edit();
		ed.putInt("type", type);
		ed.commit();
	}

	// 检查是否有保存帐号
	public boolean checkSavePwd(int type) {
		List<String> list = getPwd(type);
		if (list != null) {
			if (list.size() == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	// 保存帐号密码
	public void savePwd(int type, String username, String password) {
		String name = "saveuserpwd";
		if (type == 1) {
			name = "saveuserpwd";
		} else {
			name = "saveuserpwdByvehLogin";
		}
		saveFlag(type);
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		Editor ed = sp.edit();
		ed.putString("username", username);
		ed.putString("password", password);
		ed.commit();
	}

	// 取得帐号
	public List<String> getPwd(int type) {
		String name = "saveuserpwd";
		if (type == 1) {
			name = "saveuserpwd";
		} else {
			name = "saveuserpwdByvehLogin";
		}
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		List<String> list = new ArrayList<String>();
		if (sp.contains("username") && sp.contains("password")) {
			list.add(sp.getString("username", null));
			list.add(sp.getString("password", null));
			return list;
		} else {
			return null;
		}
	}
	
	// 取得用户类型
	public int getFlag(){
		SharedPreferences share = context.getSharedPreferences("flag", 0);
		int type=share.getInt("type", 1);
		return type;
	}

	// 删除帐号
	public void removepwd(int type) {
		String name = "saveuserpwd";
		if (type == 0) {
			name = "saveuserpwd";
		} else {
			name = "saveuserpwdByvehLogin";
		}
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		Editor ed = sp.edit();
		ed.remove("username");
		ed.remove("password");
		ed.commit();
	}

	// 保存登录服务器信息
	public boolean saveIpPort(int type, String ip, int port) {
		try {
			String name = null;
			if (type == 0) { // 数据库
				name = "saveIpPort";
			} else if (type == 1) { // 中心
				name = "saveCenterIpPort";
			}
			SharedPreferences sp = context.getSharedPreferences(name, 0);
			Editor ed = sp.edit();
			ed.putString("ip", ip);
			ed.putInt("port", port);
			ed.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 获取登录服务器信息
	public List<String> getIpPort(int type) {
		String name = null;
		if (type == 0) { // 数据库
			name = "saveIpPort";
		} else if (type == 1) { // 中心
			name = "saveCenterIpPort";
		}
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		List<String> list = new ArrayList<String>();
		if (sp.contains("ip") && sp.contains("port")) {
			String ip = sp.getString("ip", null);
			int port = sp.getInt("port", 0);
			if (ip == null || ip.equals("") || port == 0 && type != 3) {
				return null;
			}
			list.add(ip);
			list.add(port + "");
			return list;
		} else {
			return null;
		}
	}

	// 保存背景图片ID
	public void setBgResId(int resId) {
		SharedPreferences sp = context.getSharedPreferences("bgResId", 0);
		Editor ed = sp.edit();
		ed.putInt("id", resId);
		ed.commit();
	}

	// 获取背景图片ID
	public int getBgResId() {
		SharedPreferences sp = context.getSharedPreferences("bgResId", 0);
		if (sp.contains("id") && sp.getInt("id", 0) != 0) {
			return sp.getInt("id", 0);
		}
		return 0;
	}

	// 保存油耗油价
	public void saveMileage(double oiluse, double oilprice, double corr) {
		SharedPreferences sp = context.getSharedPreferences("mileage", 0);
		Editor ed = sp.edit();
		ed.putString("oiluse", oiluse + "");
		ed.putString("oilprice", oilprice + "");
		ed.putString("corr", corr + "");
		ed.commit();
	}

	// 获取油耗油价
	public List<String> getMileage() {
		SharedPreferences sp = context.getSharedPreferences("mileage", 0);
		List<String> list = new ArrayList<String>();
		list.add(sp.getString("oiluse", "0"));
		list.add(sp.getString("oilprice", "0"));
		list.add(sp.getString("corr", "0"));
		return list;
	}

	/**
	 * 
	 * @Title: saveException
	 * @Description: TODO(保存程序异常信息)
	 * @param exmsg
	 *            设定文件
	 * @return void 返回类型
	 */
	public void saveException(String exmsg) {
		Log.e("saveException", "<>" + exmsg);
		SharedPreferences sp = context.getSharedPreferences("exception", 0);
		Editor ed = sp.edit();
		ed.putString("ex", exmsg);
		ed.commit();
	}

	/**
	 * 
	 * @Title: getException
	 * @Description: TODO(获取程序异常信息)
	 * @param 设定文件
	 * @return String 返回类型
	 */
	public String getException() {
		SharedPreferences sp = context.getSharedPreferences("exception", 0);
		String msg = sp.getString("ex", "null");
		Log.e("getException", "<>" + msg);
		return msg;
	}

	/**
	 * 
	 * @Title: savePowerTime
	 * @Description: TODO(保存省电模式时间)
	 * @param time
	 */
	public void savePowerTime(long time) {
		SharedPreferences sp = context.getSharedPreferences("PowerTime", 0);
		Editor ed = sp.edit();
		ed.putLong("PowerTime", time);
		ed.commit();
		App.POWERTIME = time;
	}

	/**
	 * 
	 * @Title: getPowerTime
	 * @Description: TODO(获取省电模式配置时间)
	 * @return
	 */
	public long getPowerTime() {
		SharedPreferences sp = context.getSharedPreferences("PowerTime", 0);
		return sp.getLong("PowerTime", 1000 * 60 * 15);
	}

	/**
	 * 
	 * @Title: saveSpeed
	 * @Description: TODO(保存超速阀值)
	 * @param time
	 */
	public void saveSpeed(String speed) {
		SharedPreferences sp = context.getSharedPreferences("Speed", 0);
		Editor ed = sp.edit();
		ed.putString("Speed", speed);
		ed.commit();
	}

	/**
	 * 
	 * @Title: getSpeed
	 * @Description: TODO(获取超速阀值)
	 * @return
	 */
	public String getSpeed() {
		SharedPreferences sp = context.getSharedPreferences("Speed", 0);
		return sp.getString("Speed", "0");
	}

	// 保存报警开关信息
	public void saveAlertOption(String sOption) {
		SharedPreferences sp = context.getSharedPreferences("alertoption", 0);
		Editor ed = sp.edit();
		ed.putString("alertoption", sOption);
		ed.commit();
		App.sAlertOption = sOption;
	}

	// 取得报警开关信息
	public String getAlertOption() {
		String name = "alertoption";
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		if (sp.contains("alertoption")) {
			return sp.getString("alertoption", "1110");
		} else {
			return "1110";
		}
	}

	// 保存是否已配置报警开关
	public void saveIsAlertOption() {
		SharedPreferences sp = context.getSharedPreferences("isalertoption", 0);
		Editor ed = sp.edit();
		ed.putBoolean("isalertoption", true);
		ed.commit();
		App.isAlertOption = true;
	}

	// 取得是否已配置报警开关
	public Boolean getIsAlertOption() {
		String name = "isalertoption";
		SharedPreferences sp = context.getSharedPreferences(name, 0);
		if (sp.contains("isalertoption")) {
			return sp.getBoolean("isalertoption", false);
		} else {
			return false;
		}
	}

	// 保存用户类型
	public void saveUserType(int type) {
		SharedPreferences sp = context.getSharedPreferences("userType", 0);
		Editor ed = sp.edit();
		ed.putInt("type", type);
		ed.commit();
	}

	// 获取用户类型
	public int getUserType() {
		SharedPreferences sp = context.getSharedPreferences("userType", 0);
		return sp.getInt("type", 3);
	}

	// 保存手机号码
	public void savePhoneNumber(String number) {
		SharedPreferences sp = context.getSharedPreferences("phoneNumber", 0);
		Editor ed = sp.edit();
		ed.putString("number", number);
		ed.commit();
	}

	// 获取手机号码
	public String getPhoneNumber() {

		SharedPreferences sp = context.getSharedPreferences("phoneNumber", 0);
		return sp.getString("number", "");
	}

	// 保存推送标志
	public void savePushFlag(Boolean flag) {
		SharedPreferences sp = context.getSharedPreferences("phoneNumber", 0);
		Editor ed = sp.edit();
		ed.putBoolean("flag", flag);
		ed.commit();
	}

	// 获取推送标志
	public Boolean getPushFlag() {

		SharedPreferences sp = context.getSharedPreferences("phoneNumber", 0);
		return sp.getBoolean("flag", false);
	}

	// 保存透传信息
	public void savePayload(String payload) {
		SharedPreferences sp = context.getSharedPreferences("Lpayload", 0);
		Editor ed = sp.edit();
		ed.putString("payload", payload);
		ed.commit();
	}

	// 获取透传信息
	public String getPayload() {
		SharedPreferences sp = context.getSharedPreferences("Lpayload", 0);
		return sp.getString("payload", "");
	}

	// 保存围栏数据 GeoPoint,circle,id [{id:1,''},{}]
	public void saveCircle(List<Map<String, Integer>> dataList) {
		SharedPreferences sp = context.getSharedPreferences("circleData", 0);
		Editor ed = sp.edit();
		JSONArray arr = new JSONArray();
		if (dataList == null || dataList.size() < 1) {
			return;
		}
		try {
			for (Map map : dataList) {
				JSONObject obj = new JSONObject();
				obj.put("id", map.get("id"));
				obj.put("lat", map.get("lat"));
				obj.put("lon", map.get("lon"));
				obj.put("circle", map.get("circle"));
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ed.putString("json", arr.toString());
		ed.commit();
	}

	// 保存围栏数据 GeoPoint,circle,id
	public List<Map<String, Integer>> getCircle() {
		SharedPreferences sp = context.getSharedPreferences("circleData", 0);
		String jsonArr = sp.getString("json", "");

		List<Map<String, Integer>> dataList = new ArrayList<Map<String, Integer>>();
		try {
			if (jsonArr.length() < 2) {
				return null;
			}
			JSONArray json = new JSONArray(jsonArr);
			for (int i = 0; i < json.length(); i++) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				JSONObject obj = (JSONObject) json.get(i);
				map.put("id", obj.getInt("id"));
				map.put("lat", obj.getInt("lat"));
				map.put("lon", obj.getInt("lon"));
				map.put("circle", obj.getInt("circle"));
				dataList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return dataList;
	}
}