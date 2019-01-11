package com.example.switchtabdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class OrderProtectFragment extends Fragment implements OnClickListener {
	private static final String TAG = "OrderProtectFragment";
	private ArrayList<Fragment> list;
	private TextView barText;
	private int currIndex;
	private ViewPager viewpager_protect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order_protect, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initData();
		initTextBar();
		initViewPager();
	}

	private void initViewPager() {
		viewpager_protect = (ViewPager) getActivity().findViewById(R.id.viewpager_protect);
		list = new ArrayList<Fragment>();
		Fragment fragment1 = new BaseFragment();
		Fragment fragment2 = new BaseFragment();
		Fragment fragment3 = new BaseFragment();
		Fragment fragment4 = new BaseFragment();
		Fragment fragment5 = new BaseFragment();
		list.add(fragment1);
		list.add(fragment2);
		list.add(fragment3);
		list.add(fragment4);
		list.add(fragment5);
		viewpager_protect.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), list));
		viewpager_protect.setCurrentItem(0);
		viewpager_protect.setOnPageChangeListener(new MyOnPageChangeListener());
		// viewpager_protect.setOffscreenPageLimit(5);
		if (getActivity().getIntent().getBooleanExtra("isNeedSend", false)) {
			viewpager_protect.setCurrentItem(2);
		}
		if (getActivity().getIntent().getBooleanExtra("isHadSend", false)) {
			viewpager_protect.setCurrentItem(3);
		}
		if (getActivity().getIntent().getBooleanExtra("isHadFinish", false)) {
			viewpager_protect.setCurrentItem(4);
		}
		if (getActivity().getIntent().getBooleanExtra("isHadClosed", false)) {
			viewpager_protect.setCurrentItem(5);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void initTextBar() {
		barText = (TextView) super.getActivity().findViewById(R.id.cursor_protect);
		Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		int tabLineLength = metrics.widthPixels / 5;
		LayoutParams lp = (LayoutParams) barText.getLayoutParams();
		lp.width = tabLineLength;
		barText.setLayoutParams(lp);
	}

	private void initData() {
		TextView tv_1 = (TextView) getActivity().findViewById(R.id.tv_1);
		TextView tv_2 = (TextView) getActivity().findViewById(R.id.tv_2);
		TextView tv_3 = (TextView) getActivity().findViewById(R.id.tv_3);
		TextView tv_4 = (TextView) getActivity().findViewById(R.id.tv_4);
		TextView tv_5 = (TextView) getActivity().findViewById(R.id.tv_5);
		tv_1.setOnClickListener(new TxListener(0));
		tv_2.setOnClickListener(new TxListener(1));
		tv_3.setOnClickListener(new TxListener(2));
		tv_4.setOnClickListener(new TxListener(3));
		tv_5.setOnClickListener(new TxListener(4));
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) barText.getLayoutParams();

			if (currIndex == arg0) {
				ll.leftMargin = (int) (currIndex * barText.getWidth() + arg1 * barText.getWidth());
			} else if (currIndex > arg0) {
				ll.leftMargin = (int) (currIndex * barText.getWidth() - (1 - arg1) * barText.getWidth());
			}
			barText.setLayoutParams(ll);
		}

		@Override
		public void onPageSelected(int arg0) {
			currIndex = arg0;
			int i = currIndex + 1;
		}

	}

	public class TxListener implements View.OnClickListener {
		private int index = 0;

		public TxListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			viewpager_protect.setCurrentItem(index);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_left:
			getActivity().finish();
			break;

		default:
			break;
		}
	}

}
