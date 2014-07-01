package cn.com.cfz.register;

import cn.com.cfz.appcontext.CfzApplication;
import cn.com.cfz.pojo.TUser;
import cn.com.cfz.webservices.webservice;

import com.cn.cfz.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private RegisterActivity context  = RegisterActivity.this;
	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	private ProgressBar progressBar;
	private EditText edtUsername, edtPassword, edtCard, edtCalltel/*, edtMakecode*/,edtRePassword,edtEmail;
	private Button btnSubmit;
	private Thread submitThread;
	private String username, password, card, calltel, makecode,repassword,email;
	private static final int REGISTER_HADREGISTER = 2003;
	private String userid = "";
	private final static int UPDATE_STATE_ERROR = 0;
	private final static int UPDATE_STATE_SUCCESS = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.register_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		initUI();
	}
	
	private void initUI() {
		/**初始化头部界面**/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText(R.string.setting_register_title);
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);
		this.progressBar = (ProgressBar) findViewById(R.id.refresh_title_progressbar);
		/**初始化界面**/
		this.edtUsername = (EditText) findViewById(R.id.register_username);
		this.edtPassword = (EditText) findViewById(R.id.register_password);
		this.edtCalltel = (EditText) findViewById(R.id.register_telmobile);
		this.edtCard = (EditText) findViewById(R.id.register_card);
//		this.edtMakecode = (EditText) findViewById(R.id.register_makecode);
		this.edtRePassword = (EditText) findViewById(R.id.register_repassword);
		this.edtEmail = (EditText) findViewById(R.id.register_email);
		this.btnSubmit = (Button) findViewById(R.id.btn_submit);
		this.btnSubmit.setOnClickListener(btnSubmitClickListener);
	}
	
	//点击回退按钮
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
	
	//点击登陆按钮
	private OnClickListener btnSubmitClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			username = edtUsername.getText().toString();
			password = edtPassword.getText().toString();
			card = edtCard.getText().toString();
			calltel = edtCalltel.getText().toString();
//			makecode = edtMakecode.getText().toString();
			repassword = edtRePassword.getText().toString();
			email = edtEmail.getText().toString();
			if(calltel==null || "".equals(calltel)){
				Toast.makeText(context, "手机号码不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if(password==null || "".equals(password)){
				Toast.makeText(context, "密码不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if(username==null || "".equals(username)){
				Toast.makeText(context, "用户名不能为空", Toast.LENGTH_LONG).show();
				return;
			}
//			if(makecode == null ||"".equals(makecode)){
//				Toast.makeText(context, "推广码不能为空", Toast.LENGTH_LONG).show();
//				return;
//			}
			if(!repassword.equals(password)){
				Toast.makeText(context, "两次输入的密码不一致", Toast.LENGTH_LONG).show();
				return;
			}
			btnSubmit.setClickable(false);
			beginSubmitData();
		}
	};
	
	private void beginSubmitData(){
		this.progressBar.setVisibility(View.VISIBLE);
		if(this.submitThread!=null){
			Thread thread = this.submitThread;
			this.submitThread = null;
			thread.interrupt();
		}
		this.submitThread = new Thread(runnable);
		this.submitThread.start();
	}
	
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			int state = UPDATE_STATE_ERROR;
			webservice service = new webservice(context);
			
			try {
				boolean flag = service.callFromweb("registerUser",
						new String[]{ "username", "password", "calltel", "card", "makecode", "email" },
						new String[]{ username, password, calltel, card, /*makecode*/"",email });
				if(flag){
					String resultXml = service.getXmlString();
					if(resultXml.contains("success")){
						state = UPDATE_STATE_SUCCESS;
						userid = resultXml.substring(resultXml.indexOf(":")+1, resultXml.length());
					}else if("hadregister".equals(resultXml)){
						state = REGISTER_HADREGISTER;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Message msg = handler.obtainMessage();
			Bundle data = new Bundle();
			data.putInt("update_state", state);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	};
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			progressBar.setVisibility(View.GONE);
			int state = msg.getData().getInt("update_state");
			switch (state) {
			case UPDATE_STATE_ERROR:
				createDialog("用户注册失败，请重试!", false);
				btnSubmit.setClickable(true);
				break;
			case UPDATE_STATE_SUCCESS:
				createDialog("恭喜您注册成功,请耐心等待审核!", true);
				break;
			case REGISTER_HADREGISTER:
				createDialog("该手机号已使用，请更换手机号!", false);
				btnSubmit.setClickable(true);
				break;
			default:
				break;
			}
		}
	};
	
	private void createDialog(String str, final boolean isSuccess){
		new AlertDialog.Builder(context)
				.setTitle("提示")
				.setMessage(str)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						if(isSuccess){
							//保存到application中
							TUser loginUser = new TUser();
							loginUser.setId(userid);
							loginUser.setCUsername(username);
							loginUser.setCPassword(password);
							loginUser.setCPhone(calltel);
							CfzApplication.getInstance().setLoginUser(loginUser);
							setResult(4444);
							context.finish();
						}
					}
				}).create().show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			context.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
