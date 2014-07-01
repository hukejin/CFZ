package cn.com.cfz.main;

import java.util.ArrayList;
import java.util.List;

import cn.com.cfz.indent.IndentListActivity;
import cn.com.cfz.pojo.UserOrders;
import cn.com.cfz.tools.Gongju;

import com.cn.cfz.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private IndentListActivity mContext;
	private LayoutInflater inflater;
	private List<UserOrders> receiveInfoList = new ArrayList<UserOrders>();

	public MainAdapter(IndentListActivity context, ArrayList<UserOrders> orders) {
		mContext = context;
		receiveInfoList = orders;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return receiveInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return receiveInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int clickIndex = position;
		HandlerView mHandlerView = null;
		if (convertView == null) {
			mHandlerView = new HandlerView();
			convertView = inflater.inflate(R.layout.item_main, null);
			mHandlerView.staTextView = (TextView) convertView
					.findViewById(R.id.main_item_state);
			mHandlerView.contentTextView = (TextView) convertView
					.findViewById(R.id.main_item_title);
			mHandlerView.timeTextView = (TextView) convertView
					.findViewById(R.id.main_item_time);
			mHandlerView.delbtn = (Button) convertView
					.findViewById(R.id.delbtn);
			mHandlerView.customerTextView = (TextView) convertView
					.findViewById(R.id.main_item_customer);

			convertView.setTag(mHandlerView);
		} else {
			mHandlerView = (HandlerView) convertView.getTag();
		}
		mHandlerView.delbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mContext.delData(clickIndex);

			}
		});
		UserOrders os = receiveInfoList.get(position);
		mHandlerView.staTextView.setText(os.gettOrders().getCState() == 0 ? "未处理" : "已处理");
		if(os.gettOrders().getCState() == 0)
			mHandlerView.delbtn.setVisibility(View.VISIBLE);
		else{
			mHandlerView.delbtn.setVisibility(View.GONE);
		}
		mHandlerView.contentTextView.setText(os.gettOrders().getCOrdername());
		mHandlerView.timeTextView.setText(Gongju.longtoString(String.valueOf(os.gettOrders().getCCreatetime())));
		mHandlerView.customerTextView.setText("订购者  " +os.gettOrders().getCOwnname());

		return convertView;
	}

	class HandlerView {
		TextView staTextView;
		TextView contentTextView;
		TextView timeTextView;
		TextView customerTextView;
		Button delbtn;
	}

}
