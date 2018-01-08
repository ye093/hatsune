package com.hatsune.net;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyHttp {
    OkHttpClient client = new OkHttpClient();

    public interface MyHttpCallback {
        void result(String response);
    }
    public void doGet(final String url, final MyHttpCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseStr = null;
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    responseStr = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (callback != null) {
                    callback.result(responseStr);
                }
            }
        }).start();
    }
}
