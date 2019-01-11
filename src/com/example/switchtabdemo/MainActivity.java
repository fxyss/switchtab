package com.example.switchtabdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";
	private FragmentManager fm;
	private Button btn_message, btn_call;
	private OrderAllFragment messageFragment;
	private OrderProtectFragment callFragment;
	public static final int MESSAGE_FRAGMENT_TYPE = 1;
	public static final int CALL_FRAGMENT_TYPE = 2;
	public int currentFragmentType = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
	}

	private void initData() {
		fm = getSupportFragmentManager();
		ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
		TextView tv_right = (TextView) findViewById(R.id.tv_right);
		LinearLayout ll_search = (LinearLayout) findViewById(R.id.ll_search);
		btn_message = (Button) findViewById(R.id.btn_message);
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_message.setOnClickListener(onClicker);
		btn_call.setOnClickListener(onClicker);
		ll_search.setOnClickListener(onClicker);
		iv_left.setOnClickListener(onClicker);
		tv_right.setOnClickListener(onClicker);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment mainFragment = fragmentManager.findFragmentByTag("message");
		if (mainFragment != null) {
			transaction.replace(R.id.fl_content, mainFragment);
			transaction.commit();
		} else {
			loadFragment(MESSAGE_FRAGMENT_TYPE);
		}
	}

	private void switchFragment(int type) {
		switch (type) {
		case MESSAGE_FRAGMENT_TYPE:
			loadFragment(MESSAGE_FRAGMENT_TYPE);
			break;
		case CALL_FRAGMENT_TYPE:
			loadFragment(CALL_FRAGMENT_TYPE);
			break;
		}

	}

	private void loadFragment(int type) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (type == CALL_FRAGMENT_TYPE) {
			if (callFragment == null) {
				callFragment = new OrderProtectFragment();

				transaction.add(R.id.fl_content, callFragment, "zhishi");
			} else {
				transaction.show(callFragment);
			}
			if (messageFragment != null) {
				transaction.hide(messageFragment);
			}
			currentFragmentType = MESSAGE_FRAGMENT_TYPE;
		} else {
			if (messageFragment == null) {
				messageFragment = new OrderAllFragment();
				transaction.add(R.id.fl_content, messageFragment, "wenda");
			} else {
				transaction.show(messageFragment);
			}
			if (callFragment != null) {
				transaction.hide(callFragment);
			}
			currentFragmentType = CALL_FRAGMENT_TYPE;
		}
		transaction.commitAllowingStateLoss();
	}

	// private void setStatusBarColor() {
	// Window window = getWindow();
	// WindowManager.LayoutParams params = getWindow().getAttributes();
	// params.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
	// window.setAttributes(params);
	// SystemBarTintManager tintManager = new SystemBarTintManager(this);
	// tintManager.setStatusBarTintEnabled(true);
	// tintManager.setTintColor(getResources().getColor(R.color.tab));
	// }

	private OnClickListener onClicker = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_message:
				btn_message.setTextColor(getResources().getColor(R.color.tab));
				btn_call.setTextColor(Color.WHITE);
				btn_message.setBackgroundResource(R.drawable.left_bold);
				btn_call.setBackgroundResource(R.drawable.right_transparent);
				switchFragment(MESSAGE_FRAGMENT_TYPE);

				break;
			case R.id.btn_call:
				btn_message.setTextColor(Color.WHITE);
				btn_call.setTextColor(getResources().getColor(R.color.tab));
				btn_message.setBackgroundResource(R.drawable.left_transparent);
				btn_call.setBackgroundResource(R.drawable.right_bold);
				switchFragment(CALL_FRAGMENT_TYPE);
				break;
			case R.id.iv_left:
				finish();
				break;
			case R.id.ll_search:
				// startActivity(new Intent(getApplicationContext(),
				// SearchOrderActivity.class));
				break;
			}
		}
	};

}
