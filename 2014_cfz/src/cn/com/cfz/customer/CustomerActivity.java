package cn.com.cfz.customer;

import com.cn.cfz.R;

import cn.com.cfz.appcontext.CfzApplication;
import cn.com.cfz.pojo.TUser;
import cn.com.cfz.tools.Gongju;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomerActivity extends Activity {

	private TUser uBean;
	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	private TextView usernametv, emailtv, ownmoneytv, areatv, bankcardtv,
			lastlogintv, makecodetv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.customerinfo);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		uBean = CfzApplication.getInstance().getLoginUser();

		initUI();
	}

	private void initUI() {
		/** 初始化头部界面 **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText("个人信息");
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);
		/** 初始化界面 **/
		this.usernametv = (TextView) findViewById(R.id.usernametv);
		usernametv.setText(uBean.getCUsername());
		this.emailtv = (TextView) findViewById(R.id.emailtv);
		emailtv.setText(uBean.getCEmail());
		this.ownmoneytv = (TextView) findViewById(R.id.ownmoneytv);
		ownmoneytv.setText(uBean.getCMoney() + "");
		this.areatv = (TextView) findViewById(R.id.areatv);
		areatv.setText(uBean.getCRegiondesc());
		this.bankcardtv = (TextView) findViewById(R.id.bankcardtv);
		bankcardtv.setText(uBean.getCDefaultcardcode());
		this.lastlogintv = (TextView) findViewById(R.id.lastlogintv);
		lastlogintv.setText(Gongju.longtoString(String.valueOf(uBean
				.getCLogintime())));
		this.makecodetv = (TextView) findViewById(R.id.makecodetv);
		makecodetv.setText(uBean.getCMarkcode());
	}

	// 点击回退按钮
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
}
