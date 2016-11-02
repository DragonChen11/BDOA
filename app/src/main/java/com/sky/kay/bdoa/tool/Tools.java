package com.sky.kay.bdoa.tool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kay on 2016/7/29.
 */
public class Tools {
    /**
     * 调起系统发短信功能
     *
     */
    public static void doSendSMSTo(Activity activity,String phoneNumber){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            activity.startActivity(intent);
        }
    }

    /**
     * 调起系统打电话功能
     *
     */
    public static void doCallPhone(Activity activity,String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        activity.startActivity(intent);
    }

    public static SimpleDateFormat sdf1=new SimpleDateFormat("yy-MM-dd HH:mm");
    public static SimpleDateFormat sdf2=new SimpleDateFormat("MM-dd HH:mm");
    public static SimpleDateFormat sdf3=new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat sdf4=new SimpleDateFormat("yy-MM-dd");
    public static String showTime(Date date){
        Date d=new Date();
        if(date.getMonth()==d.getMonth() && date.getDay()==d.getDay()){
            return sdf3.format(date);
        }else {
           return sdf2.format(date);
        }
    }

    public static String getToday(){
        Date d=new Date();
        return sdf4.format(d);
    }

    public static String getToday2(){
        Date d=new Date();
        return sdf1.format(d);
    }

}
