package cn.com.cfz.appcontext;

import cn.com.cfz.pojo.TUser;
import cn.com.cfz.tools.SdcardInfo;
import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

public class CfzApplication extends Application {

	private static CfzApplication mInstance = null;
	public static boolean hadstoragecard = false;
	public static boolean storageisfull = true;
	public String File_Pic = "";// 照片全部放在这个目录下
	public String File_Down = "";// 下载的图片全部放在这个目录下
	private TUser loginUser;
	//用户信息
	public static final String LOGIN_USER_INFO = "login_user_info";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	private SharedPreferences sp;
	private String LOGINNAME = "LOGINNAME";
	private String LOGINPASSWORD = "LOGINPASSWORD";
	private String loginname;
	private String loginpassword;
	
	public static CfzApplication getInstance() {
		return mInstance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		sp = mInstance.getSharedPreferences(LOGIN_USER_INFO, 0);
		
		
		checkSdcardInfo();
	}
	
	
	
	public String getLoginname() {
		loginname =sp.getString(LOGINNAME, "");
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
		sp.edit().putString(LOGINNAME, loginname).commit();
	}

	public String getLoginpassword() {
		loginpassword = sp.getString(LOGINPASSWORD, "");
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
		sp.edit().putString(LOGINPASSWORD, loginpassword).commit();
	}

	private void checkSdcardInfo() {
		// TODO Auto-generated method stub
		SdcardInfo sd = new SdcardInfo();
		File_Pic = SdcardInfo.File_Pic;
		File_Down = SdcardInfo.File_Download;
		hadstoragecard = sd.isExistSDcard();
		if (hadstoragecard) {
			storageisfull = sd.Sdcardisfull();
		}
		
		if(!hadstoragecard){
			Toast.makeText(
					CfzApplication.getInstance().getApplicationContext(),
					"没有存储卡是无法随手拍的！", Toast.LENGTH_LONG).show();
			return;
		}
		if(storageisfull){
			Toast.makeText(
					CfzApplication.getInstance().getApplicationContext(),
					"存储卡所剩空间不足，请及时清理！", Toast.LENGTH_LONG).show();
			return;
		}
	}
	
	public TUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(TUser loginUser) {
		this.loginUser = loginUser;
	}
}
