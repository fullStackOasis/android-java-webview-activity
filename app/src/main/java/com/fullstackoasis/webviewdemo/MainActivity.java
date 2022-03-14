package com.fullstackoasis.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private String myurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This menu breaks because the text is not visible.

        myurl = "http://amazon.com/";
        setUpWebView();
    }

    private void setUpWebView() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // webSettings.setUserAgentString("Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail Firefox/firefoxversion");
        // THIS ONE WORKS. WHY?????
        // webSettings.setUserAgentString("Mozilla/5.0 (platform; rv:geckoversion) Gecko/geckotrail Firefox/firefoxversion");
        // This one DOES NOT WORK:
        // webSettings.setUserAgentString("AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75");
        // Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv)
        // webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv)");
        // This one works exactly as expected.
        String ua =  "Mozilla/5.0 (Linux; Android 10; SM-A205U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.48 Mobile Safari/537.36";
        webSettings.setUserAgentString(ua);

        if (2 == 1) {
            applyWebViewClient(myWebView);
        } else {
            applyWebViewController(myWebView);
            //applyWebChromeClient(myWebView);
        }
        myWebView.loadUrl(myurl);

    }
    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "loading url " + url);
            view.loadUrl(url);
            return true;
        }
    }

    private void applyWebViewController(WebView myWebView) {
        myWebView.setWebViewClient(new WebViewController());
    }
    private void applyWebViewClient(WebView myWebView) {
        // myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView  view, String  url) {
                if (1 != 2) {
                    onBackPressed();
                    return true;
                }
                return false;
            }

        });
    }

    private void applyWebChromeClient(WebView myWebView) {
        myWebView.setWebChromeClient(new WebChromeClient());/* {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d(TAG, consoleMessage.message() + " : line : "
                        + consoleMessage.lineNumber() + " : source : "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });*/
    }

}