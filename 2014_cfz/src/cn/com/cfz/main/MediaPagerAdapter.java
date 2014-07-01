package cn.com.cfz.main;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

public class MediaPagerAdapter extends PagerAdapter {

	private MainActivity activity;
	
	public MediaPagerAdapter(MainActivity indeActivity){
		activity = indeActivity;
	}
	
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(this.activity.medias.get(arg1));
	}
	
	@Override
	public int getCount() {
		return this.activity.medias.size();
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(this.activity.medias.get(arg1),0);
		final int Index = arg1; 
		this.activity.medias.get(arg1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.showUrl(Index);
				
			}
		});
		return this.activity.medias.get(arg1);
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==(arg1);
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
