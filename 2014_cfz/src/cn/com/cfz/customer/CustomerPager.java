package cn.com.cfz.customer;

import java.util.ArrayList;
import java.util.List;

import cn.com.cfz.appcontext.CfzApplication;
import cn.com.cfz.pojo.TUser;
import cn.com.cfz.register.RegisterActivity;
import cn.com.cfz.tools.Gongju;

import com.cn.cfz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerPager extends Activity {

	private ViewPager pager;
	private ImageView curDot;
	private LinearLayout dotContain; // �洢�������
	private int offset; // λ����
	private int curPos = 0; // ��¼��ǰ��λ��
	private List<View> views = new ArrayList<View>();
	// �������һ��
	private static final int TO_THE_END = 0;
	// �뿪���һ��
	private static final int LEAVE_FROM_END = 1;

	private TUser uBean;

	private TextView usernametv, emailtv, ownmoneytv, areatv, bankcardtv,
			lastlogintv, makecodetv,zongcaifutv,zhanghaoleixingtv,createtimetv,mobiletv,zstv,tgtv;
	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	private View page1;
	private View page2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.customerpager);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		uBean = CfzApplication.getInstance().getLoginUser();

		init();
	}

	// ������˰�ť
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

	private void init() {
		
		addView();
		

		/** ��ʼ��ͷ������ **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText("������Ϣ");
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);
		/** ��ʼ������ **/
		this.usernametv = (TextView) page1.findViewById(R.id.usernametv);
		usernametv.setText(uBean.getCUsername());
		this.emailtv = (TextView) page1.findViewById(R.id.emailtv);
		emailtv.setText(uBean.getCEmail());
		this.ownmoneytv = (TextView) page1.findViewById(R.id.ownmoneytv);
		float money = uBean.getCMoney()==null?0:uBean.getCMoney();
		ownmoneytv.setText(money + "");
		this.areatv = (TextView) page1.findViewById(R.id.areatv);
		areatv.setText(uBean.getCRegiondesc());
		this.bankcardtv = (TextView)page1.findViewById(R.id.bankcardtv);
		bankcardtv.setText(uBean.getCDefaultcardcode());
		this.lastlogintv = (TextView) page1.findViewById(R.id.lastlogintv);
		lastlogintv.setText(Gongju.longtoString(String.valueOf(uBean
				.getCLogintime())));
		this.makecodetv = (TextView) page1.findViewById(R.id.makecodetv);
		makecodetv.setText(uBean.getCMarkcode());
		this.zhanghaoleixingtv = (TextView)page1.findViewById(R.id.zhanghaotv);
		zhanghaoleixingtv.setText(uBean.getCType()==0?"����":"��ҵ");
		this.mobiletv = (TextView)page1.findViewById(R.id.mobiletv);
		mobiletv.setText(uBean.getCPhone());
		this.zongcaifutv = (TextView)page1.findViewById(R.id.zongcaifutv);
		zongcaifutv.setText(uBean.getCTotalmoney()==0?"0":uBean.getCTotalmoney()+"");
		this.createtimetv = (TextView)page1.findViewById(R.id.zhucetimetv);
		createtimetv.setText(Gongju.longtoString(String.valueOf(uBean
				.getCCreatetime())));
		
		this.zstv = (TextView)page2.findViewById(R.id.zstv);
		zstv.setText(
		            Html.fromHtml(
		                "<a href=\"http://www.10010.com/goodsdetail/361203213263.html\">ר����ά��</a>"));
		zstv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent itIntent = new Intent(CustomerPager.this,WebCustomer.class);
				Bundle blBundle = new Bundle();
				blBundle.putString("makecode", "http://211.91.228.204:9090/cfz/packages/list?markcode="+uBean.getCMarkcode());
				blBundle.putString("title", "ר���ײ�");
				itIntent.putExtra("bundle", blBundle);
				startActivity(itIntent);
			}
		});
//		zstv.setMovementMethod(LinkMovementMethod.getInstance());
		
		
		this.tgtv = (TextView)page2.findViewById(R.id.tgtv);
		tgtv.setText(
	            Html.fromHtml(
	                "<a href=\"http://www.10010.com/goodsdetail/361203213263.html\">�ƹ��ά��</a>"));
		tgtv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent itIntent = new Intent(CustomerPager.this,RegisterActivity.class);
				startActivity(itIntent);
				
			}
		});
//		tgtv.setMovementMethod(LinkMovementMethod.getInstance());

		dotContain = (LinearLayout) this.findViewById(R.id.dot_contain);
		pager = (ViewPager) findViewById(R.id.contentPager);
		curDot = (ImageView) findViewById(R.id.cur_dot);
		
		initDot();

		// ��curDot�����ڵ����β�ν�Ҫ�����ʱ�˷���������
		curDot.getViewTreeObserver().addOnPreDrawListener(
				new OnPreDrawListener() {
					public boolean onPreDraw() {
						// ��ȡImageView�Ŀ��Ҳ���ǵ�ͼƬ�Ŀ��
						offset = curDot.getWidth();
						return true;
					}
				});

		final GuidePagerAdapter adapter = new GuidePagerAdapter(views);
		// ViewPager�������������������������ʹ��ListViewʱ�õ�adapter
		pager.setAdapter(adapter);
		pager.clearAnimation();
		// ΪViewpager����¼������� OnPageChangeListener
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				int pos = position % views.size();

				moveCursorTo(pos);

				if (pos == views.size() - 1) {// �����һ����
					handler.sendEmptyMessageDelayed(TO_THE_END, 500);

				} else if (curPos == views.size() - 1) {
					handler.sendEmptyMessageDelayed(LEAVE_FROM_END, 100);
				}
				curPos = pos;
				super.onPageSelected(position);
			}
		});
	}

	/**
	 * �ƶ�ָ�뵽���ڵ�λ�� ����
	 * 
	 * @param position
	 *            ָ�������ֵ
	 * */
	private void moveCursorTo(int position) {
		AnimationSet animationSet = new AnimationSet(true);
		TranslateAnimation tAnim = new TranslateAnimation(offset * curPos,
				offset * position, 0, 0);
		animationSet.addAnimation(tAnim);
		animationSet.setDuration(300);
		animationSet.setFillAfter(true);
		curDot.startAnimation(animationSet);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == TO_THE_END) {

			} else if (msg.what == LEAVE_FROM_END) {

			}
		}
	};

	// ViewPager ������
	class GuidePagerAdapter extends PagerAdapter {

		private List<View> views;

		public GuidePagerAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1 % views.size()));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			// ע������һ��Ҫ����һ����΢���ֵ,��Ȼ�������ͻ�������
			return views.size() /** 20*/;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			Log.e("tag", "instantiateItem = " + arg1);
			((ViewPager) arg0).addView(views.get(arg1 % views.size()), 0);
			return views.get(arg1 % views.size());
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

	}

	private void addView() {
		page1 = LayoutInflater.from(this).inflate(R.layout.pager1, null);
		views.add(page1);
		page2 = LayoutInflater.from(this).inflate(R.layout.pager2, null);
		views.add(page2);

	}

	/**
	 * ��ʼ���� ImageVIew
	 * 
	 * @return ����true˵����ʼ����ɹ�������ʵ����ʧ��
	 */
	private boolean initDot() {

		if (views.size() > 0) {
			ImageView dotView;
			for (int i = 0; i < views.size(); i++) {
				dotView = new ImageView(this);
				dotView.setImageResource(R.drawable.dot1_w);
				dotView.setLayoutParams(new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

				dotContain.addView(dotView);
			}
			return true;
		} else {
			return false;
		}
	}
}
