package edu.mit.media.hellopds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragmentClient extends WebViewClient {
	
	private WebViewFragment mWebViewFragment;
	private Context mContext;
	
	public WebViewFragmentClient() {
		
	}
	
	public WebViewFragmentClient(Context context, WebViewFragment webViewFragment) {
		setContext(context);
		setWebViewFragment(webViewFragment);
	}
	
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		//Log.d("WebViewFragmentClient", String.format("url started: %s", url));
		getWebViewFragment().showLoadingScreen();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		//Log.d("WebViewFragmentClient", String.format("url finished: %s", url));
		getWebViewFragment().hideLoadingScreen();
	}
	
	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		//Log.d("WebViewFragmentClient", String.format("url error: %s", failingUrl));
		mWebViewFragment.showErrorScreen();
	}		
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (url != null && url.contains("maps.google.com")) {
            view.getContext().startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
		}
		return super.shouldOverrideUrlLoading(view, url);
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context context) {
		mContext = context;
	}
	
	public WebViewFragment getWebViewFragment() {
		return mWebViewFragment;
	}

	public void setWebViewFragment(WebViewFragment webViewFragment) {
		mWebViewFragment = webViewFragment;
	}
}
