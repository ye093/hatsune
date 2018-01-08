package com.hatsune.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hatsune.R;
import com.hatsune.adapter.OpenResultAdapter;
import com.hatsune.net.MyHttp;
import com.hatsune.ui.DividerItemDecoration;
import com.hatsune.ui.entity.OpenCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OpenResultFragment  extends Fragment {
    TextView topTitle;
    private static final String TITLE_KEY = "title_key";
    private RecyclerView listView;
    private OpenResultAdapter adapter;

    public static OpenResultFragment newInstance(String title) {
        OpenResultFragment fragment = new OpenResultFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);
        return fragment;
    }
    //http://m.159cai.com/cpdata/game/80/c.json


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.frag_open_result, null);
        topTitle = (TextView) contentView.findViewById(R.id.topTitle);
        listView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString(TITLE_KEY, "");
        topTitle.setText(title);
        adapter = new OpenResultAdapter();
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        listView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        MyHttp myHttp = new MyHttp();
        myHttp.doGet("http://m.159cai.com/cpdata/game/aopencode.json", new MyHttp.MyHttpCallback() {
            @Override
            public void result(String response) {
                if (TextUtils.isEmpty(response)) return;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonRows = jsonObject.getJSONObject("rows");
                    JSONArray rowArray = jsonRows.getJSONArray("row");
                    final ArrayList<OpenCode> openCodes = new ArrayList<>();
                    for (int i = 0; i < rowArray.length(); i++) {
                        JSONObject jsonRow = rowArray.getJSONObject(i);
                        String auditdate = jsonRow.optString("auditdate");
                        String moneys = jsonRow.optString("moneys");
                        String nums = jsonRow.optString("nums");
                        String sales = jsonRow.optString("sales");
                        String at = jsonRow.optString("at");
                        String gname = jsonRow.optString("gname");
                        if ("胜负彩".equals(gname)) continue;
                        String gid = jsonRow.optString("gid");
                        String codes = jsonRow.optString("codes");
                        String pid = jsonRow.optString("pid");
                        String et = jsonRow.optString("et");
                        String pools = jsonRow.optString("pools");
                        String bt = jsonRow.optString("bt");
                        OpenCode openCode = new OpenCode(auditdate, moneys, nums, sales, at, gname,
                                gid, codes, pid, et, pools, bt);
                        openCodes.add(openCode);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setDataSet(openCodes);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
