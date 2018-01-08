package com.hatsune.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hatsune.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewMessageAdapter extends BaseAdapter {
    private static final String CONTENT = "[{\"title\":\"大乐透第17152期推荐：前区关注和值的回落\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":20972},{\"title\":\"大乐透第17152期预测：前区三胆15 17 29\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":9383},{\"title\":\"双色球第17150期预测：关注龙头02凤尾32\",\"source\":\"2017年12月20日新浪彩票\",\"clickNum\":167358},{\"title\":\"双色球第17149期分析：新码出号活跃\",\"source\":\"2017年12月18日新浪彩票\",\"clickNum\":73985},{\"title\":\"第17149期双色球预测：蓝球看好小数\",\"source\":\"2017年12月18日新浪彩票\",\"clickNum\":31027},{\"title\":\"大乐透第17148期推荐：后区关注0 2组合\",\"source\":\"2017年12月18日新浪彩票\",\"clickNum\":12543},{\"title\":\"双色球第17147期预测：关注凤尾29 32\",\"source\":\"2017年12月13日新浪彩票\",\"clickNum\":159681},{\"title\":\"大乐透第17146期预测：前区三胆11 24 39\",\"source\":\"2017年12月13日新浪彩票\",\"clickNum\":43616},{\"title\":\"双色球第17146期推荐：红球胆码09 16 20\",\"source\":\"2017年12月11日新浪彩票\",\"clickNum\":92423},{\"title\":\"大乐透第17145期分析：后区单挑03 12\",\"source\":\"2017年12月11日新浪彩票\",\"clickNum\":27907},{\"title\":\"大乐透第17144期推荐：四区码断档\",\"source\":\"2017年12月07日新浪彩票\",\"clickNum\":71536},{\"title\":\"双色球第17144期预测：除4余2看02 22 30\",\"source\":\"2017年12月07日新浪彩票\",\"clickNum\":77629},{\"title\":\"大乐透第17143期预测：后区胆04 05\",\"source\":\"2017年12月06日新浪彩票\",\"clickNum\":35046},{\"title\":\"双色球第17144期预测：蓝球不看好02 11\",\"source\":\"2017年12月06日新浪彩票\",\"clickNum\":35046},{\"title\":\"大乐透第17152期推荐：前区关注和值的回落\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":20972},{\"title\":\"大乐透第17152期推荐：前区关注和值的回落\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":20972},{\"title\":\"大乐透第17152期推荐：前区关注和值的回落\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":20972},{\"title\":\"大乐透第17152期推荐：前区关注和值的回落\",\"source\":\"2017年12月26日新浪彩票\",\"clickNum\":20972}]\n";
    private JSONArray dataArray;
    public NewMessageAdapter() {
        try {
            dataArray = new JSONArray(CONTENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getCount() {
        int count = dataArray == null ? 0 : dataArray.length();
        return count;
    }

    @Override
    public JSONObject getItem(int position) {
        return dataArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_message,
                    parent, false);
            holder = new Holder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.sourceFrom = (TextView) convertView.findViewById(R.id.sourceFrom);
            holder.clickNum = (TextView) convertView.findViewById(R.id.clickNum);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        JSONObject object = getItem(position);
        holder.title.setText(object.optString("title"));
        holder.sourceFrom.setText(object.optString("source"));
        holder.clickNum.setText(object.optInt("clickNum") + "");
        return convertView;
    }

    static final class Holder {
        TextView title, sourceFrom, clickNum;
    }
}
