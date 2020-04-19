package com.zeitgeist.employee.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zeitgeist.employee.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/20/2017.
 */

public class SearchTypeAdapter extends BaseAdapter {
    Context context;
    List<String> searchTypes;
    LayoutInflater inflater;
    Typeface typeface;

    public SearchTypeAdapter(Context context, List<String> searchTypes,Typeface typeface) {
        this.context = context;
        this.searchTypes = searchTypes;
        this.typeface = typeface;
        inflater = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return searchTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_search_list, null);
        TextView types = (TextView) convertView.findViewById(R.id.search_type_list);
        types.setTypeface(typeface);
        types.setText(searchTypes.get(position));
        return convertView;
    }
}
