package edu.mit.media.hellopds;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

@SuppressLint("ValidFragment")
public class WebViewFragment extends Fragment {

	private static final String TAG = "LivingLabActivity";
	private String mUrl;
	private FrameLayout mView;
	private WebView mWebView;
	private View mLoadingStatusView;
	private String mTitle;
	private Activity mActivity;
	private ViewPager mViewPager;
	private WebViewFragmentJavascriptInterface mJavascriptInterface;
	private WebViewFragmentClient mWebViewClient;
	
	public static WebViewFragment Create(String url, String title, Activity activity, ViewPager viewPager) {
		return Create(url, title, activity, viewPager, new WebViewFragmentJavascriptInterface(viewPager, activity));
	}
	
	public static WebViewFragment Create(String url, String title, Activity activity, ViewPager viewPager, WebViewFragmentJavascriptInterface jsInterface) {
		WebViewFragment fragment = new WebViewFragment(url, title, activity, viewPager);
		fragment.mJavascriptInterface = jsInterface;
		fragment.mWebViewClient = new WebViewFragmentClient(activity, fragment);
		
		return fragment;
	}
	
	public WebViewFragmentClient getWebViewClient() {
		return mWebViewClient;
	}

	public void setWebViewClient(WebViewFragmentClient webViewClient) {
		mWebViewClient = webViewClient;
	}

	public static WebViewFragment Create(String url, String title, Activity activity, ViewPager viewPager, WebViewFragmentJavascriptInterface jsInterface, WebViewFragmentClient webViewClient) {
		WebViewFragment fragment = new WebViewFragment(url, title, activity, viewPager);
		fragment.mJavascriptInterface = jsInterface;
		webViewClient.setContext(activity);
		webViewClient.setWebViewFragment(fragment);
		fragment.mWebViewClient = webViewClient;
		
		return fragment;
	}
	
	public WebViewFragment() {
		super();
		mUrl = mTitle = "";
		mWebViewClient = new WebViewFragmentClient();
		mWebViewClient.setWebViewFragment(this);
	}
	
	protected WebViewFragment(String url, String title, Activity activity, ViewPager viewPager) {
		mUrl = url;
		mTitle = title;
		mActivity = activity;
		mViewPager = viewPager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = (FrameLayout) inflater.inflate(R.layout.webview_fragment_layout, container, false); 
		mLoadingStatusView = mView.findViewById(R.id.loading_status);
	
		// NOTE: code below for programmatically adding the webview may be necessary to avoid a 
		// memory leak related to using the main activity as the context when constructing a webview
		// This is what occurs when the webview is specified declaratively in the layout xml
		
		mWebView = (WebView) mView.findViewById(R.id.fragment_webview);
		//mWebView.setVisibility(View.VISIBLE);
		//mWebView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		//mWebViewClient.setWebView(mWebView);
		//mWebViewClient.setErrorString("Problem contacting server");
		//mWebViewClient.setLoadingStatusView(mLoadingStatusView);
		mWebView.setWebViewClient(mWebViewClient);
					
		mWebView.addJavascriptInterface(mJavascriptInterface, "android");
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		    //WebView.setWebContentsDebuggingEnabled(true);
		}
	
		if (savedInstanceState != null) {
			mWebView.restoreState(savedInstanceState);
		} else {
			mWebView.loadUrl(mUrl);
		}		
		
		//mView.addView(mWebView);
		return mView;
	}	
	
	public String getTitle() {
		return mTitle;
	}
	
	public void showLoadingScreen() {
		mWebView.setVisibility(View.GONE);
		mLoadingStatusView.setVisibility(View.VISIBLE);
	}
	
	public void hideLoadingScreen() {
		mWebView.setVisibility(View.VISIBLE);
		mLoadingStatusView.setVisibility(View.GONE);
	}
	
	public void showErrorScreen() {
		mWebView.loadData(getString(R.string.problem_contacting_server), "text/html", "UTF-8");
	}
}
