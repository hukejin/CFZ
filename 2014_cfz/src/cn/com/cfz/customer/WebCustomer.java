package cn.com.cfz.customer;

import com.cn.cfz.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WebCustomer extends Activity {

	private WebView webView;
	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	private String urlString;
	private String titleString;
	private LinearLayout loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.webcustomer);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		Intent it = getIntent();
		Bundle blBundle = it.getBundleExtra("bundle");
		urlString = blBundle.getString("makecode");
		titleString = blBundle.getString("title");
		initUI();
	}

	private void initUI() {
		/** 初始化头部界面 **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText(titleString);
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);

		loading = (LinearLayout) this.findViewById(R.id.loadingline);

		webView = (WebView) this.findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.setWebViewClient(wClient);
		
		webView.loadUrl(urlString);
		


	}
	
	private WebViewClient wClient = new WebViewClient(){

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			Log.e("@@@@@@@@", ""+2);
			loading.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			Log.e("@@@@@@@@", ""+3);
			loading.setVisibility(View.GONE);
		}
		
		@Override
		public void onReceivedError(WebView view, int errorCode,
	            String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			Log.e("@@@@@@@@", ""+4);
			loading.setVisibility(View.GONE);
			Toast.makeText(WebCustomer.this, "页面加载失败", Toast.LENGTH_SHORT).show();
	    }
	};

	// 点击回退按钮
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
}
