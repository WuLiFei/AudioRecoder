package com.mariostudio.audiorecoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by MarioStudio on 2016/5/12.
 */
public class AudioListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public AudioListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mInflater.inflate(R.layout.layout_list_item, null);
    }
}
