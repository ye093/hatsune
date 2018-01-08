package com.hatsune.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hatsune.R;
import com.hatsune.ui.entity.HistoryOpenCode;

import java.util.ArrayList;

/**
 * HistoryOpenCodeAdapter
 */

public class HistoryOpenCodeAdapter extends RecyclerView.Adapter<HistoryOpenCodeAdapter.Holder> {
    private ArrayList<HistoryOpenCode> openCodes = new ArrayList<>();

    public void setDataSet(ArrayList<HistoryOpenCode> openCodes) {
        if (openCodes == null) return;
        this.openCodes.clear();
        this.openCodes.addAll(openCodes);
        notifyDataSetChanged();
    }

    public HistoryOpenCode getItem(int position) {
        if (position > this.openCodes.size() || position < 0)
            throw new IllegalArgumentException("position is wrong!");
        return this.openCodes.get(position);
    }

    public ArrayList<HistoryOpenCode> getDataSet() {
        return this.openCodes;
    }


    @Override
    public HistoryOpenCodeAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_open_result, parent, false);
        return new HistoryOpenCodeAdapter.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryOpenCodeAdapter.Holder holder, final int position) {
        HistoryOpenCode openCode = this.getItem(position);
        holder.itemNameTV.setText("第" + openCode.getPid() + "期");
        holder.itemNumTV.setText(openCode.getFet().split("\\s")[0]);
        String codeStr = openCode.getOpencode();
        boolean isCircleNum = codeStr != null && codeStr.contains("*");
        String[] blueCodes = null;
        String[] redCodes = null;
        if (codeStr != null) {
            String[] codeArea = codeStr.split("\\|");
            if (codeArea.length > 1) {
                blueCodes = codeArea[1].split(",");
            }
            redCodes = codeArea[0].split(",");
        }
        if (redCodes != null) {
            int i = 0;
            int j = blueCodes == null ? 0 : blueCodes.length;
            if (!isCircleNum) {
                isCircleNum = j == 0 && (redCodes.length > 7);
            }
            for (; i < redCodes.length + j; i++) {
                View contentView = holder.openCodeContainer.getChildAt(i);
                if (contentView == null) {
                    contentView = LayoutInflater.from(holder.itemView.getContext())
                            .inflate(R.layout.view_open_code, null);
                    holder.openCodeContainer.addView(contentView);
                }
                contentView.setVisibility(View.VISIBLE);
                if (contentView instanceof FrameLayout) {
                    ImageView iv = (ImageView) ((FrameLayout) contentView).getChildAt(0);
                    TextView tv = (TextView) ((FrameLayout) contentView).getChildAt(1);
                    if (isCircleNum) {
                        tv.setTextSize(14.0f);
                        tv.setTextColor(Color.parseColor("#7A7A7A"));
                        tv.setText(redCodes[i]);
                        iv.setImageResource(R.drawable.bg_rec_7afafa);
                    }else {
                        tv.setTextColor(Color.parseColor("#FFFFFF"));
                        tv.setTextSize(16.0f);
                        if (i < redCodes.length) {
                            iv.setImageResource(R.drawable.bg_circle_ff7f00);
                            tv.setText(redCodes[i]);
                        } else {
                            iv.setImageResource(R.drawable.bg_circle_4169e1);
                            tv.setText(blueCodes[i - redCodes.length]);
                        }
                    }
                }
            }
            while (i < holder.openCodeContainer.getChildCount()) {
                holder.openCodeContainer.getChildAt(i).setVisibility(View.GONE);
                i++;
            }
        }
    }

    @Override
    public int getItemCount() {
        return openCodes.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        View itemView;
        TextView itemNameTV;
        TextView itemNumTV;
        TextView dateTimeTV;
        LinearLayout openCodeContainer;
        ImageView rightNextIconIV;


        public Holder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemNameTV = (TextView) itemView.findViewById(R.id.itemNameTV);
            itemNumTV = (TextView) itemView.findViewById(R.id.itemNumTV);
            dateTimeTV = (TextView) itemView.findViewById(R.id.dateTimeTV);
            dateTimeTV.setVisibility(View.GONE);
            openCodeContainer = (LinearLayout) itemView.findViewById(R.id.openCodeContainer);
            rightNextIconIV = (ImageView) itemView.findViewById(R.id.rightNextIconIV);
            rightNextIconIV.setVisibility(View.GONE);
        }
    }
}
