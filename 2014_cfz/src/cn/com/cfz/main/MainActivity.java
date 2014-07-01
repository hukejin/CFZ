package cn.com.cfz.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.cfz.customer.CustomerActivity;
import cn.com.cfz.customer.CustomerPager;
import cn.com.cfz.customer.WebCustomer;
import cn.com.cfz.indent.IndentListActivity;
import cn.com.cfz.tools.Gongju;

import com.cn.cfz.R;

public class MainActivity extends Activity  implements OnPageChangeListener {

	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	
	private Button customerbtn;
	private Button indentlistbtn;
	
	private ViewPager mediaPager;
	private MediaPagerAdapter mediaAdapter;
	private FrameLayout mediaFrameLayout;
	private List<Bitmap> images = new ArrayList<Bitmap>();
	private ImageView[] dots;// 底部小点图片
	private Bitmap dowBitmap = null;
	protected List<View> medias;
	
	private MainActivity context = MainActivity.this;
	private final static int DOWNLOAD_PIC = 1;
	private final static int DOWNLOAD_NOPIC = 2;
	private boolean isexit = false;
	
	private ProgressBar progressBar;
	private long preclick = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.cfzmain);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		Thread mediaThread = new Thread(runnableMedia);
		mediaThread.start();

		initUI();

	}

	private void initUI() {
		/** 初始化头部界面 **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText(R.string.main_title);
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);
		
		this.progressBar = (ProgressBar) findViewById(R.id.refresh_title_progressbar);
		this.progressBar.setVisibility(View.VISIBLE);
		
		this.mediaFrameLayout = (FrameLayout) findViewById(R.id.main_detail_media_frame);
		
		customerbtn = (Button)this.findViewById(R.id.btn_customer);
		customerbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent itIntent = new Intent(MainActivity.this,/*CustomerActivity*/CustomerPager.class);
				startActivity(itIntent);
			}
		});
		
		indentlistbtn = (Button)this.findViewById(R.id.btn_indent);
		indentlistbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent itIntent = new Intent(MainActivity.this,IndentListActivity.class);
				startActivity(itIntent);
			}
		});
	}
		
	// 点击回退按钮
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			exitSystem();
		}
	};
	
	private void exitSystem(){
			
		if(isexit){
			long t = System.currentTimeMillis();
			if((t-preclick)/1000 > 3)
				finish();
		}
		else{
			Toast.makeText(MainActivity.this, "再点击一次退出程序", Toast.LENGTH_LONG).show();
			isexit = true;
			preclick = System.currentTimeMillis();
		}
	}
	
	private Runnable runnableMedia = new Runnable() {
		@Override
		public void run() {
			
			String url = getString(R.string.pic_url);
			dowBitmap = Gongju.getBitmapFromServer(url+"p0.png");
			if(dowBitmap!=null){
				Message msg = handler.obtainMessage();
				Bundle bundle = new Bundle();
				bundle.putInt("download", DOWNLOAD_PIC);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}else{
				Message msg = handler.obtainMessage();
				Bundle bundle = new Bundle();
				bundle.putInt("download", DOWNLOAD_NOPIC);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
			
		}
	};

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			int state = msg.getData().getInt("download");
			progressBar.setVisibility(View.GONE);
			switch (state) {
			case DOWNLOAD_PIC:
				images.add(dowBitmap);
				break;
			case DOWNLOAD_NOPIC:
				Bitmap bp = BitmapFactory.decodeResource(getResources(),
						R.drawable.p0);
				images.add(bp);
				images.add(bp);
				images.add(bp);
				break;
			default:
				break;
			}
			setMedia();
		}

	};
	
	@SuppressWarnings("deprecation")
	private void setMedia() {
		
//		ImageView imageView = (ImageView)MainActivity.this.findViewById(R.id.pic);
//		imageView.setImageBitmap(images.get(0));
		
		/** 模拟图片 **/
		medias = new ArrayList<View>();
		for (Bitmap pic : images) {
			View v = LayoutInflater.from(this).inflate(
					R.layout.news_detail_media_item_view, null);
			ImageView img1 = (ImageView) v.findViewById(R.id.media_imageview);
			Drawable d = new BitmapDrawable(pic);
			d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
			img1.setBackgroundDrawable(d);
			medias.add(v);
		}
		/** 初始化viewpager **/
		mediaFrameLayout.setVisibility(View.VISIBLE);
		this.mediaPager = (ViewPager) findViewById(R.id.main_detail_viewpagerLayout);
		mediaAdapter = new MediaPagerAdapter(this);
		this.mediaPager.setAdapter(mediaAdapter);
		this.mediaPager.setOnPageChangeListener(context);
		// 初始化当前显示第一个view
		this.mediaPager.setCurrentItem(0);
		/** 初始化小点 **/
		LinearLayout ll = (LinearLayout) findViewById(R.id.dot_image_group);
		if (medias.size() == 1) {
			ll.setVisibility(View.GONE);// 只有一张图片，则隐藏小点
		}
		dots = new ImageView[medias.size()];
		for (int i = 0; i < medias.size(); i++) {
			dots[i] = new ImageView(MainActivity.this);
			if (i == 0) {
				dots[i].setImageResource(R.drawable.dot_image_selected);
			} else {
				dots[i].setImageResource(R.drawable.dot_image_normal);
			}
			// 设置小圆点的宽高
			LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
					12, 12);
			// 设置每个小圆点距离左边的间距
			margin.setMargins(10, 0, 0, 0);
			ll.addView(dots[i], margin);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		for(int i=0; i<medias.size(); i++){
			dots[arg0].setImageResource(R.drawable.dot_image_selected);
			if(arg0 != i)
				dots[i].setImageResource(R.drawable.dot_image_normal);
		}
		
	}
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isexit = false;
	}

	@Override
	public void onBackPressed() {
		exitSystem();
	}
	
	public void showUrl(int index){
		
//		Intent itIntent = new Intent(MainActivity.this,WebCustomer.class);
//		Bundle blBundle = new Bundle();
//		blBundle.putString("makecode", "http://www.10010.com/goodsdetail/361203213263.html");
//		blBundle.putString("title", "套餐活动");
//		itIntent.putExtra("bundle", blBundle);
//		startActivity(itIntent);
	}
}
