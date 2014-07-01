package cn.com.cfz.denglu;

import cn.com.cfz.appcontext.CfzApplication;
import cn.com.cfz.main.MainActivity;
import cn.com.cfz.pojo.TUser;
import cn.com.cfz.register.RegisterActivity;
import cn.com.cfz.webservices.webservice;

import com.cn.cfz.R;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button btnLogin,btnRegister;
	private ProgressDialog pd;
	private EditText edtCalltel, edtPassword;
	private String calltel, password;
	private static int LOGIN_ERROR = 6;
	private static int LOGIN_SUCCESS = 7;
	private static int LOGIN_HAD = 8;
	private static int LOGIN_DONGJIE = 9;
	private static int LOGIN_ZHUXIAO = 10;
	private static int LOGIN_NOACTIVE =11;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		
		pd = new ProgressDialog(LoginActivity.this, 0);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setTitle("���Ժ�...");
		pd.setMessage("ϵͳ������...");
		
		initUI();
		
	}

	private void initUI(){
		
		this.edtCalltel = (EditText) findViewById(R.id.edt_calltel);
		this.edtCalltel.setText(((CfzApplication)getApplicationContext()).getLoginname());
		this.edtPassword = (EditText) findViewById(R.id.edt_password);
		this.edtPassword.setText(((CfzApplication)getApplicationContext()).getLoginpassword());
		this.btnLogin = (Button) findViewById(R.id.btn_login);
		this.btnLogin.setOnClickListener(btnLoginClickListener);
		btnRegister = (Button)findViewById(R.id.btn_fast_register);
		btnRegister.setOnClickListener(btnRegisterClickListener);
	}
	
	private OnClickListener btnRegisterClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
		}
	};
	
	//�����½��ť
		private OnClickListener btnLoginClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				calltel = edtCalltel.getText().toString();
				password = edtPassword.getText().toString();
				if(calltel==null || calltel.equals("")){
					Toast.makeText(LoginActivity.this, "�û�����Ϊ��", Toast.LENGTH_LONG).show();
					return;
				}
				if(password==null || password.equals("")){
					Toast.makeText(LoginActivity.this, "�û����벻Ϊ��", Toast.LENGTH_LONG).show();
					return;
				}
				pd.setMessage("��¼��...");
				pd.show();
				new Thread(){
					public void run() {
						int state = LOGIN_ERROR;
						try {
							webservice service = new webservice(LoginActivity.this);
							boolean flag = service.callFromweb("Login",
									new String[]{ "username", "passwd" },
									new String[]{ calltel, password });
							if(flag){
								String resultXml = service.getXmlString();
								if(resultXml==null || "".equals(resultXml) || "error".equals(resultXml)){
									state = LOGIN_ERROR;
								}else if("hadlogin".equals(resultXml)){
									state = LOGIN_HAD;
								}else if("noactive".equals(resultXml)){
									state = LOGIN_NOACTIVE;
								}else if("dongjie".equals(resultXml)){
									state = LOGIN_DONGJIE;
								}else if("zhuxiao".equals(resultXml)){
									state = LOGIN_ZHUXIAO;
								}
								else{
									TUser user = new Gson().fromJson(resultXml, TUser.class);
									if(user != null){
										state = LOGIN_SUCCESS;
										((CfzApplication)getApplicationContext()).setLoginUser(user);
										((CfzApplication)getApplicationContext()).setLoginname(calltel);
										((CfzApplication)getApplicationContext()).setLoginpassword(password);
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						Message msg = mHandler.obtainMessage();
						Bundle data = new Bundle();
						data.putInt("state", state);
						msg.setData(data);
						mHandler.sendMessage(msg);
					}
				}.start();
			}
		};
		
		private Handler mHandler = new Handler() {
			public void handleMessage(Message msg) {
				if(msg.getData().getInt("state") == LOGIN_ERROR){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "��¼ʧ�ܣ�������", Toast.LENGTH_LONG).show();
				}else if(msg.getData().getInt("state") == LOGIN_HAD){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "�û������ڣ�����������", Toast.LENGTH_LONG).show();
					edtCalltel.setText("");
					edtPassword.setText("");
				}else if(msg.getData().getInt("state") == LOGIN_SUCCESS){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "��¼�ɹ�����ӭ����ϵͳ", Toast.LENGTH_LONG).show();
					Intent it = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(it);
					LoginActivity.this.finish();
				}else if(msg.getData().getInt("state") == LOGIN_NOACTIVE){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "�˺�δ�����ȴ�����", Toast.LENGTH_LONG).show();
				}else if(msg.getData().getInt("state") == LOGIN_DONGJIE){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "�˺��Ѷ��ᣬ����ϵ����Ա", Toast.LENGTH_LONG).show();
				}else if(msg.getData().getInt("state") == LOGIN_ZHUXIAO){
					if (pd.isShowing()) {
						pd.cancel();
					}
					Toast.makeText(LoginActivity.this, "�˺���ע��������ϵ����Ա", Toast.LENGTH_LONG).show();
				}
			}
		};
}
