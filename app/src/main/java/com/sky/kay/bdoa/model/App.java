package com.sky.kay.bdoa.model;

import android.app.Application;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;
import com.sky.kay.bdoa.tool.LoginSaveTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;


/**
 * @ClassName: App
 * @Description: TODO(App全局共享类)
 * @author 科技 
 * @date 2013-6-10 下午6:34:34
 */
public class App extends Application implements Serializable,
		Thread.UncaughtExceptionHandler {


	private static App mInstance = null;
	public boolean m_bKeyRight = true;

	// 断油断电
	public boolean isCloseFule = false;
	public boolean isLoginByVeh; // 车牌号登录
	public boolean isLoginByVehGetUserSuc; // 车牌号登录获取帐号密码是否成功

	public ArrayList<Mail> mails=new ArrayList<Mail>();
	public Message[] messages;



	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 548784515151L;

	@Override
	public void onCreate() {
		super.onCreate();

		mInstance = this;
		POWERTIME = new LoginSaveTools(this).getPowerTime();
		isAlertOption = new LoginSaveTools(this).getIsAlertOption();
		sAlertOption = new LoginSaveTools(this).getAlertOption();
		SDKInitializer.initialize(getApplicationContext());
//		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
//		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
//		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//		mc.addMailcap("image/gif;;  x-java-content-handler=com.sun.mail.handlers.image_gif");
//		mc.addMailcap("image/jpeg;; x-java-content-handler=com.sun.mail.handlers.image_jpeg");
//		mc.addMailcap("image/png;;  x-java-content-handler=com.sun.mail.handlers.image_png");
//		CommandMap.setDefaultCommandMap(mc);

		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public static App getInstance() {
		return mInstance;
	}




	/**
	 * 省电模式轮询时间
	 */
	public static long POWERTIME = 1000 * 60 * 15;

	// 是否设置了报警开关
	public static boolean isAlertOption = false;
	public static String sAlertOption = "1110";
	/**
	 * 中心重连时间
	 */
	public static final long CENTERRECONNTIME = 1000 * 60 * 5;
	/**
	 * App Handler消息接收处理
	 */
	public Handler handler = new Handler();

	public Handler mapHandler;

	// 3. 字段1为integer类型，内容=1 为登录成功,登录=0为登录失败
	// 4. 字段2为integer类型，内容为返回的用户ID
	// 5. 字段3为String类型，内容为中心GPRS地址
	// 6. 字段4为String类型，内容为中心GPRS端口
	// 7. 字段5为integer类型，内容为用户类型
	public String sUserName;
	public String sUserPassWord;


	public boolean isReconn = false;



	/** 添加选中的车辆数据ip */
	public String selectIp;

	/** 是否在线 */
	public boolean isOnline;



	Map<String, Long> dianMingVeh = new HashMap<String, Long>();
	/**
	 * 点名回传有效时间段
	 */
	final long MAXTIME = 15 * 60 * 1000;

	/**
	 * 
	 * @Title: addDiaMingVeh
	 * @Description: TODO(增加点名车辆Ip 操作时间)
	 * @param ip
	 *            点名车辆Ip
	 * @return void 返回类型
	 */
	public void addDianMingVeh(String ip) {
		synchronized (dianMingVeh) {
			dianMingVeh.put(ip, System.currentTimeMillis());
		}
	}

	/**
	 * 判断点名是否成功
	 * 
	 * @Title: isDianMingSuc
	 * @Description: TODO(判断点名是否成功)
	 * @param ip
	 *            返回数据伪IP
	 * @param 设定文件
	 * @return boolean true　点名回传成功
	 */
	public boolean isDianMingSuc(String ip) {
		synchronized (dianMingVeh) {
			if (dianMingVeh.containsKey(ip)) {
				long time = dianMingVeh.get(ip);
				dianMingVeh.remove(ip);
				if (System.currentTimeMillis() - time <= MAXTIME) {
					return true;
				}
			}
			return false;
		}
	}



	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println("uncaughtException");
	    System.exit(0);

	}

}
