package com.example.moveongridview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.moveongridview.widget.TagView;
import com.huxq17.moveongridview.scrollrunner.OnItemMovedListener;

import java.util.List;

public class GridViewAdapter extends BaseAdapter implements OnItemMovedListener {
    private Context context;
    private List<String> mDatas;

    public GridViewAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.mDatas = dataList;
    }

    private boolean inEditMode = false;

    public void setInEditMode(boolean inEditMode) {
        this.inEditMode = inEditMode;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TagView textView;
        if (convertView == null) {
            textView = new TagView(context);
            convertView = textView;
            textView.setLines(1);
            textView.setHeight(DensityUtil.dip2px(context, 40));
            int id = context.getResources().getIdentifier("s_grid_item", "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id);
            textView.setBackgroundDrawable(drawable);
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TagView) convertView;
        }
        if (!isFixed(position)) {
            textView.showDeleteIcon(inEditMode);
        } else {
            textView.showDeleteIcon(false);
        }
        textView.setText(getItem(position));
        return convertView;
    }

    @Override
    public void onItemMoved(int from, int to) {
        String s = mDatas.remove(from);
        mDatas.add(to, s);
    }

    @Override
    public boolean isFixed(int position) {
        if (position == 0) {
            return true;
        }
        return false;
    }
}