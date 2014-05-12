package edu.mit.media.hellopds;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.webkit.JavascriptInterface;

public class WebViewFragmentJavascriptInterface {
	
	protected ViewPager mViewPager;
	protected Activity mActivity;
	
	public WebViewFragmentJavascriptInterface(ViewPager viewPager, Activity activity) {
		mViewPager = viewPager;
		mActivity = activity;
	}
	
	@JavascriptInterface
	public boolean hideWebNavBar() {
		return true;
	}
	
	@JavascriptInterface
	public boolean handleTabChange(final String dimension, final int tabNumber) {
		if (mActivity != null && mViewPager != null) {
			mActivity.runOnUiThread(new Runnable() { 
				@Override
				public void run() {
					mViewPager.setCurrentItem(tabNumber + 1);
				}
			});
		}
		return true;
	}
}