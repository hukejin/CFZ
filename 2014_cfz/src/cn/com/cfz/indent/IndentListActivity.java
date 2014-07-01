package cn.com.cfz.indent;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.cfz.appcontext.CfzApplication;
import cn.com.cfz.main.DeleteIndent;
import cn.com.cfz.main.IndentInfoActivity;
import cn.com.cfz.main.MainAdapter;
import cn.com.cfz.pojo.TOrders;
import cn.com.cfz.pojo.TUser;
import cn.com.cfz.pojo.UserOrders;
import cn.com.cfz.webservices.webservice;

import com.cn.cfz.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IndentListActivity extends Activity implements DeleteIndent{

	private TextView txtTitle;
	private LinearLayout llBack;
	private ImageButton imbMenu;
	private ListView infolistListView;
	private TUser uBean;
	private UserOrders deluserOrder;
	private static int clickindex = -1;
	private ProgressBar progressBar;

	private static int DATA_ERROR = 6;
	private static int DATA_SUCCESS = 7;
	private static int DATA_NOHAD = 8;
	private static int DATA_DELSUCCESS = 9;
	private static int DATA_DELERROR = 10;
	ArrayList<UserOrders> userorders = new ArrayList<UserOrders>();
	private MainAdapter maAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.indentlist);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		uBean = CfzApplication.getInstance().getLoginUser();

		initUI();

		Thread tdThread = new Thread(getDate);
		tdThread.start();
	}

	private void initUI() {
		/** 初始化头部界面 **/
		this.txtTitle = (TextView) findViewById(R.id.set_titleTextView);
		this.llBack = (LinearLayout) findViewById(R.id.lefttitlebar);
		this.imbMenu = (ImageButton) findViewById(R.id.set_title_saveButton);
		this.txtTitle.setText("订单列表");
		this.llBack.setOnClickListener(btnBackClickListener);
		this.imbMenu.setVisibility(View.GONE);
		this.progressBar = (ProgressBar) findViewById(R.id.refresh_title_progressbar);
		this.progressBar.setVisibility(View.VISIBLE);
		this.infolistListView = (ListView) findViewById(R.id.infolist);
		infolistListView.setOnItemClickListener(listitemClickListener);
	}

	private Runnable getDate = new Runnable() {

		@Override
		public void run() {
			int state = DATA_ERROR;
			webservice service = new webservice(IndentListActivity.this);

			try {
				boolean flag = service.callFromweb("getOrders",
						new String[] { "markcode" },
						new String[] { uBean.getCMarkcode() });
				if (flag) {
					String resultXml = service.getXmlString();
					if (resultXml == null || "".equals(resultXml)
							|| "error".equals(resultXml)) {
						state = DATA_ERROR;
					} else if ("noindent".equals(resultXml)) {
						state = DATA_NOHAD;
					} else {
						state = DATA_SUCCESS;
						userorders = new Gson().fromJson(resultXml,
								new TypeToken<List<UserOrders>>() {
								}.getType());
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
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			progressBar.setVisibility(View.GONE);

			if (msg.getData().getInt("state") == DATA_ERROR) {
				Toast.makeText(IndentListActivity.this, "数据获取失败", Toast.LENGTH_LONG)
						.show();
			} else if (msg.getData().getInt("state") == DATA_NOHAD) {
				Toast.makeText(IndentListActivity.this, "没有订单", Toast.LENGTH_LONG)
						.show();
			} else if (msg.getData().getInt("state") == DATA_SUCCESS) {
				maAdapter = new MainAdapter(IndentListActivity.this, userorders);
				infolistListView.setAdapter(maAdapter);
			} else if (msg.getData().getInt("state") == DATA_DELERROR) {
				Toast.makeText(IndentListActivity.this, "数据删除失败", Toast.LENGTH_LONG)
						.show();
			} else if (msg.getData().getInt("state") == DATA_DELSUCCESS) {
				Toast.makeText(IndentListActivity.this, "数据删除成功", Toast.LENGTH_LONG)
						.show();
				userorders.remove(deluserOrder);
				maAdapter.notifyDataSetChanged();
			}
		}
	};
	
	// 点击回退按钮
	private OnClickListener btnBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

	private OnItemClickListener listitemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			clickindex = position;

			UserOrders userOrder = userorders.get(position);
			Intent itIntent = new Intent(IndentListActivity.this,
					IndentInfoActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("userorders", userOrder);
			itIntent.putExtras(bundle);
			startActivityForResult(itIntent, 0);
		}
	};

	public void delData(final int position) {

		new AlertDialog.Builder(IndentListActivity.this)
				.setTitle("删除警示")
				.setMessage("是否要删除订单？")
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						deluserOrder = userorders.get(position);
						TOrders tos = deluserOrder.gettOrders();
						if (tos != null) {
							DelThread tdThread = new DelThread(tos.getId());
							tdThread.start();
						}
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).show();

	}

	class DelThread extends Thread implements Runnable {

		private String orderid;

		public DelThread(String id) {
			orderid = id;
		}

		public void run() {
			webservice service = new webservice(IndentListActivity.this);
			int state = DATA_DELERROR;
			try {
				boolean flag = service.callFromweb("delOrders",
						new String[] { "id" }, new String[] { orderid });
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				if (clickindex != -1) {
					userorders.remove(clickindex);
					maAdapter.notifyDataSetChanged();
					clickindex = -1;
				}
			}
			break;
		default:
			break;
		}
	}

}
