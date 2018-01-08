package com.hatsune.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import com.hatsune.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AboutUsFragment extends Fragment {
    TextView topTitle;
    private static final String TITLE_KEY = "title_key";
    private static final String NEWS_URL = "http://m.159cai.com/gong/news.html";
    private static final String BASE_URL = "http://m.159cai.com";
    private WebView webView;

    private String historyUrl = null;

    public static AboutUsFragment newInstance(String title) {
        AboutUsFragment homeFragment = new AboutUsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.frag_home, null);
        topTitle = (TextView) contentView.findViewById(R.id.topTitle);
        webView = (WebView) contentView.findViewById(R.id.webView);
        contentView.findViewById(R.id.backIV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView != null && webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString(TITLE_KEY, "");
        topTitle.setText(title);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadDataFromUrl(url, historyUrl);
                return true;
            }
        });

        loadDataFromUrl(NEWS_URL, historyUrl);
    }

    private void loadDataFromUrl(final String url, final String history) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Document document = Jsoup.connect(url).get();
                    Elements elements = document.getElementsByTag("header");
                    if (elements.size() > 0) elements.get(0).remove();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadDataWithBaseURL(BASE_URL, document.html(), "text/html; charset=UTF-8", null, history);//这种写法可以正确解码
                            historyUrl = url;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
