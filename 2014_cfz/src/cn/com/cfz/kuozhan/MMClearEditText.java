package cn.com.cfz.kuozhan;

import com.cn.cfz.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * @ClassName: MMClearEditText
 * @Description: 可清除输入信息的编辑框
 * @author: songqinyang
 * 
 */
public class MMClearEditText extends EditText {
	final Drawable gyP;
	public String gzj;

	public MMClearEditText(Context context) {
		super(context);
		gzj = "";
		gyP = getResources().getDrawable(R.drawable.search_clear);
		cG();
	}

	public MMClearEditText(Context context, AttributeSet attributeset) {
		super(context, attributeset);
		gzj = "";
		gyP = getResources().getDrawable(R.drawable.search_clear);
		cG();
	}

	public MMClearEditText(Context context, AttributeSet attributeset, int i) {
		super(context, attributeset, i);
		gzj = "";
		gyP = getResources().getDrawable(R.drawable.search_clear);
		cG();
	}

	static void a(MMClearEditText mmclearedittext) {
		mmclearedittext.aGK();
	}

	private void aGJ() {
		if (getText().toString().equals("") || !isFocused())
			aGK();
		else
			setCompoundDrawables(getCompoundDrawables()[0],
					getCompoundDrawables()[1], gyP, getCompoundDrawables()[3]);
	}

	private void aGK() {
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
	}

	static void b(MMClearEditText mmclearedittext) {
		mmclearedittext.aGJ();
	}

	private void cG() {
		gyP.setBounds(0, 0, gyP.getIntrinsicWidth()/2, gyP.getIntrinsicHeight()/2);
		aGJ();
		setMinHeight(gyP.getIntrinsicHeight() + 5
				* getResources().getDimensionPixelSize(R.dimen.OneDPPadding));
		setOnTouchListener(onTouchListener);
		addTextChangedListener(textWatcher);
		setOnFocusChangeListener(onFocusChangeListener);
	}

	private OnTouchListener onTouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
	        if(getCompoundDrawables()[2] != null && event.getAction() == 1 && event.getX() > (float)(getWidth() - getPaddingRight() - gyP.getIntrinsicWidth()/2))
	        {
	            setText("");
	            a(MMClearEditText.this);
	        }
	        
			return false;
		}
	};
	
	private TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			b(MMClearEditText.this);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			
		}
	};
	
	private OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			b(MMClearEditText.this);
		}
	};
}
