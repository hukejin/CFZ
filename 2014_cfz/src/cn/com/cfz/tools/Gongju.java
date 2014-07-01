package cn.com.cfz.tools;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @类描述：
 * @author liujunlin
 * @time  2013-8-2
 * @备注：
 */
public class Gongju {

	/**
	 * 获取系统时间
	 * @return 年、月、日、时、分、秒
	 */
	public static String GetSystime()
	{
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * long型时间转换为String型
	 */
	public static String longtoString(String longtime){
		Date date= new Date(Long.parseLong(longtime));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");     
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**
	 * String型转换为long型时间
	 */
	public static String stringtoLong(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		long millionSeconds = 0;
		try {
			date = sdf.parse(time);
			
			millionSeconds = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒

		String dateString = String.valueOf(millionSeconds);
		return dateString;
	}
	
	/**
	 * 获取系统时间
	 * @return年、月、日
	 */
	public static String getSystemtime(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("yyyy-MM-dd"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return 日
	 */
	public static String getSystemDay(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("dd"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return 月
	 */
	public static String getSystemMoth(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("MM"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return 年月日时分秒
	 */
	public static String getSystemMoth_ch(){
		String systime = "";
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat year = new   SimpleDateFormat("yyyy"); 
		Date date = c.getTime();
		systime += year.format(date) +"年" ;
		
		SimpleDateFormat month = new   SimpleDateFormat("MM"); 
		Date date1 = c.getTime();
		systime += month.format(date1) +"月" ;
		
		SimpleDateFormat day = new   SimpleDateFormat("dd"); 
		Date date2 = c.getTime();
		systime += day.format(date2) +"日  " ;
		
		SimpleDateFormat hour = new   SimpleDateFormat("HH"); 
		Date date3 = c.getTime();
		systime += hour.format(date3) +"时" ;
		
		SimpleDateFormat min = new   SimpleDateFormat("HH"); 
		Date date4 = c.getTime();
		systime += min.format(date4) +"分" ;
		
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return 年
	 */
	public static String getSystemYear(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("yyyy"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return分、秒
	 */
	public static String getSystemcurtime(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("mm:ss"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 获取系统时间
	 * @return时、分、秒
	 */
	public static String getSystemtime2HMS(){
		String systime;
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat df = new   SimpleDateFormat("HH:mm:ss"); 
		Date date = c.getTime();
		systime = df.format(date);
	    return systime;  
	}
	
	/**
	 * 命名文件名
	 * @author liujunlin
	 * @return
	 * @time 2013-8-2
	 */
	public static String file_name()
	{
		String systime;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new   SimpleDateFormat("yyyyMMddHHmmss");
		Date date = c.getTime();
		systime = df.format(date);
	    return systime; 
	}
	
	/**
	 * 检测是否有网络连接
	 * @author liujunlin
	 * @param mActivity
	 * @return
	 * @time 2013-8-2
	 */
	public static boolean isConnect(Activity mActivity) {
		Context context = mActivity.getApplicationContext(); 
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();

				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("error", e.toString());
		}
		return false;
	}
	/**
	 * 获取目前使用的网络类型
	 * @author liujunlin
	 * @param context
	 * @return
	 * @time 2013-8-22
	 */
	public static String netType(Context context) {
		String type = "";
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();

				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						if(info.getType()==ConnectivityManager.TYPE_MOBILE){
							type = getCurApnName(context);
						}else if(info.getType()==ConnectivityManager.TYPE_WIFI){
							type = "wifi";
						}
							
					}
				}
			}
		} catch (Exception e) {
			Log.v("error", e.toString());
		}
		return type;
	}
	
	private static final Uri PREFERRED_APN_URI = Uri
			.parse("content://telephony/carriers/preferapn");// 当前可用的APN列表
	
	private static String getCurApnName(Context context){
		String name = "";
		Cursor mCursor = context.getContentResolver().query(PREFERRED_APN_URI,
				null, null, null, null);
		if (mCursor == null) {
			// throw new Exception("Non prefer apn exist");
			return null;

		}
		while (mCursor != null && mCursor.moveToNext()) {
			name = mCursor.getString(mCursor.getColumnIndex("apn")).toLowerCase();
		}
		return name;
	}
	
	/**
	 * 调用系统打电话
	 * @param context
	 * @param num
	 */
	public static void callTel(Context context, String num, String toastStr){
		TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String simNum = phoneMgr.getSimSerialNumber();
		System.out.println("simNum=================>"+simNum);
		if(simNum==null || simNum.equals("")){
			Toast.makeText(context, "当前无Sim卡，不能拨打电话!", Toast.LENGTH_LONG).show();
			return;
		}
		Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
		context.startActivity(phoneIntent);
		Toast.makeText(context, toastStr, Toast.LENGTH_LONG).show();
	}
	
	@SuppressLint("DefaultLocale")
	public static <T> T JsonToBean(String json, Class<T> classOfT) {
		boolean isValid = false;
		try {
			new JsonParser().parse(json);
		} catch (JsonParseException e) {
			isValid = true;
		}
		if (isValid) {
			return null;
		}
		T t = null;
		try {
			Gson gs = new Gson();
			t = gs.fromJson(json, classOfT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 从服务器端获取图片
	 * @param imagePath
	 * @return
	 */
	public static Bitmap getBitmapFromServer(String imagePath) {
		HttpGet get = new HttpGet(imagePath);
		HttpClient client = new DefaultHttpClient();
		Bitmap pic = null;
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			pic = BitmapFactory.decodeStream(is);   // 关键是这句代码
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	/**
	 * 根据毫秒数返回时间
	 * @param time
	 * @return
	 */
	public static String getDateByLong(long time){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
	}
	
	/**
	 * 根据毫秒数返回时间
	 * @param time
	 * @return
	 */
	public static String getDateTimeByLong(long time){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
	}
}
