package cn.com.cfz.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.cfz.pojo.TOrders;
import cn.com.cfz.pojo.TPackage;
import cn.com.cfz.pojo.TPackageregion;
import cn.com.cfz.pojo.TRegion;
import cn.com.cfz.pojo.UserOrders;
import cn.com.cfz.tools.Gongju;
import cn.com.cfz.tools.SdcardInfo;
import cn.com.cfz.webservices.webservice;

import com.cn.cfz.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 
 * ���ƣ�IndentInfoActivity �������������� �����ˣ�Administrator ����ʱ�䣺2014-6-15 ����7:48:18 ��ע��
 */
public class IndentInfoActivity extends Activity  implements OnPageChangeListener {

	private IndentInfoActivity context = IndentInfoActivity.this;
	
	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;

	private Intent mIntent;
	private UserOrders mUserOrders;
	private TextView ownnametv,ownphonetv,ownemailtv,owntaocantv,taocandesctv;
	protected List<View> medias;
	private List<Bitmap> images = new ArrayList<Bitmap>();
	private ImageView[] dots;// �ײ�С��ͼƬ
	private static int DATA_DELSUCCESS = 9;
	private static int DATA_DELERROR = 10;
	private final static int DOWNLOAD_PIC = 1;
	private final static int DOWNLOAD_NOPIC = 2;
	private Bitmap dowBitmap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.indentinfo);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		mIntent = getIntent();
		mUserOrders = (UserOrders) mIntent.getExtras().getSerializable(
				"userorders");
		initUI();
		
		Thread mediaThread = new Thread(runnableMedia);
		mediaThread.start();
	}

	private Runnable runnableMedia = new Runnable() {
		@Override
		public void run() {
			
			String url = getString(R.string.pic_url);
			String picpath = url+getPicpath(mUserOrders.gettPackage());
//			picpath = "http://res.mall.10010.com/mall/res/uploader/gdesc/201405221327561572550704.jpg";
			
			File file = new File(SdcardInfo.File_Download+"/"+getPicpath(mUserOrders.gettPackage()));
			if(file.exists()){
				dowBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			}else{
				//���������ײ�ͼƬ
				dowBitmap = Gongju.getBitmapFromServer(picpath);
				try {
					if(file.createNewFile()){
						BufferedOutputStream bos = new BufferedOutputStream(
								new FileOutputStream(file));

						/* ����ѹ��ת������ */
						dowBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);

						/* ����flush()����������BufferStream */
						bos.flush();

						/* ����OutputStream */
						bos.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
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
			switch (state) {
			case DOWNLOAD_PIC:
				images.add(dowBitmap);
				break;
			case DOWNLOAD_NOPIC:
				Bitmap bp = BitmapFactory.decodeResource(getResources(),
						R.drawable.image1);
				images.add(bp);
				break;
			default:
				break;
			}
			setMedia();
		}

	};
	
	private String getPicpath(TPackage tPackage){
		String picString = "";
		if(tPackage!=null){
			picString = tPackage.getCPath();
		}
		
		return picString;
	}
	
	private void initUI() {

		

		/** ��ʼ��ͷ������ **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.imbMenu.setImageResource(R.drawable.delete_bg);
		this.imbMenu.setOnClickListener(delClickListener);
		if(mUserOrders.gettOrders().getCState() == 0)
			this.imbMenu.setVisibility(View.VISIBLE);
		
		this.txtTitle.setText(R.string.indent_title);
		this.llBack.setOnClickListener(btnBackClickListener);

		ownnametv = (TextView) this.findViewById(R.id.ownnametv);
		ownphonetv = (TextView) this.findViewById(R.id.ownphonetv);
		ownemailtv = (TextView) this.findViewById(R.id.ownemailtv);
		owntaocantv = (TextView) this.findViewById(R.id.owntaocantv);
		taocandesctv = (TextView)this.findViewById(R.id.taocandesctv);

		showInfo(mUserOrders);
	}

	private void showInfo(UserOrders uOrders) {
		String taocanstr = "";
		TPackageregion tpg = uOrders.gettPackageregion();
		if(tpg!=null)
			taocanstr = tpg.getCPackagename();
		ownnametv.setText(Null2space(uOrders.gettOrders().getCOwnname()));
		ownphonetv.setText(Null2space(uOrders.gettOrders().getCOwnphone()));
		ownemailtv.setText(Null2space(uOrders.gettOrders().getCEmail()));
		owntaocantv.setText(Null2space(taocanstr));
		
		TPackage package1 = uOrders.gettPackage();
		if(package1!=null)
			taocandesctv.setText(Null2space(package1.getCPackagedesc()));
		
	}

	@SuppressWarnings("deprecation")
	private void setMedia() {
		
		ImageView imageView = (ImageView)IndentInfoActivity.this.findViewById(R.id.taocanimg);
		imageView.setImageBitmap(images.get(0));
		
		/** ģ��ͼƬ **/
		/*medias = new ArrayList<View>();
		for (Bitmap pic : images) {
			View v = LayoutInflater.from(this).inflate(
					R.layout.news_detail_media_item_view, null);
			ImageView img1 = (ImageView) v.findViewById(R.id.media_imageview);
			Drawable d = new BitmapDrawable(pic);
			d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
			img1.setBackgroundDrawable(d);
			medias.add(v);
		}*/
		/** ��ʼ��viewpager **/
		/*mediaFrameLayout.setVisibility(View.VISIBLE);
		this.mediaPager = (ViewPager) findViewById(R.id.main_detail_viewpagerLayout);
		mediaAdapter = new MediaPagerAdapter(this);
		this.mediaPager.setAdapter(mediaAdapter);
		this.mediaPager.setOnPageChangeListener(context);
		// ��ʼ����ǰ��ʾ��һ��view
		this.mediaPager.setCurrentItem(0);
		*//** ��ʼ��С�� **//*
		LinearLayout ll = (LinearLayout) findViewById(R.id.dot_image_group);
		if (medias.size() == 1) {
			ll.setVisibility(View.GONE);// ֻ��һ��ͼƬ��������С��
		}
		dots = new ImageView[medias.size()];
		for (int i = 0; i < medias.size(); i++) {
			dots[i] = new ImageView(IndentInfoActivity.this);
			if (i == 0) {
				dots[i].setImageResource(R.drawable.dot_image_selected);
			} else {
				dots[i].setImageResource(R.drawable.dot_image_normal);
			}
			// ����СԲ��Ŀ��
			LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
					12, 12);
			// ����ÿ��СԲ�������ߵļ��
			margin.setMargins(10, 0, 0, 0);
			ll.addView(dots[i], margin);
		}*/
	}

	private String Null2space(Object ot) {
		return ot == null ? "" : ot.toString();
	}

	// ������˰�ť
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

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
	
	private OnClickListener delClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new AlertDialog.Builder(IndentInfoActivity.this)
			.setTitle("ɾ����ʾ")
			.setMessage("�Ƿ�Ҫɾ��������")
			.setPositiveButton("��",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface dialog,
								int whichButton) {
							TOrders tos = mUserOrders.gettOrders();
							if (tos != null) {
								DelThread tdThread = new DelThread(tos.getId());
								tdThread.start();
							}
						}
					})
			.setNegativeButton("��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							
						}
					}).show();
		}
	};
	
	class DelThread extends Thread implements Runnable {

		private String orderid;

		public DelThread(String id) {
			orderid = id;
		}

		public void run() {
			webservice service = new webservice(IndentInfoActivity.this);
			int state = DATA_DELERROR;
			try {
				boolean flag = service.callFromweb("delOrders",
						new String[] { "id" },
						new String[] {orderid});
				if (flag) {
					String resultXml = service.getXmlString();
					if (resultXml == null || "".equals(resultXml)
							|| "error".equals(resultXml)) {
						state = DATA_DELERROR;
					} else {
						state = DATA_DELSUCCESS;
						
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
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			if(msg.getData().getInt("state") == DATA_DELERROR){
				Toast.makeText(IndentInfoActivity.this, "����ɾ��ʧ��", Toast.LENGTH_LONG)
				.show();
			}else if(msg.getData().getInt("state") == DATA_DELSUCCESS){
				Toast.makeText(IndentInfoActivity.this, "����ɾ���ɹ�", Toast.LENGTH_LONG)
				.show();
				
				IndentInfoActivity.this.setResult(RESULT_OK, mIntent);
				IndentInfoActivity.this.finish();
			}
		}
	};
}
