package com.example.justwork.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.justwork.R;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<String> {
    public CountryAdapter(Context context, ArrayList<String> countryList){
        super(context,0,countryList);
    }
    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ad_country_item, parent, false);
        }

        TextView country = convertView.findViewById(R.id.countryName);
        ImageView imageView = convertView.findViewById(R.id.countryImage);
        String currentItem = getItem(position);
        if(!getItem(position).equals("Denmark")){
            imageView.setImageResource(R.drawable.white_roundcorners);
        }
        if (currentItem != null) {
            country.setText(currentItem);
        }
        return convertView;
    }
}
