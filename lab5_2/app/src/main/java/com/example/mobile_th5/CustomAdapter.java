package com.example.mobile_th5;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] titles;
    private final String[] calories;
    private final int[] images;

    public CustomAdapter(Activity context, String[] titles, String[] calories, int[] images) {
        super(context, R.layout.list_item, titles);
        this.context = context;
        this.titles = titles;
        this.calories = calories;
        this.images = images;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);

        TextView titleText = rowView.findViewById(R.id.textViewTitle);
        TextView versionText = rowView.findViewById(R.id.textViewCalo);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        titleText.setText(titles[position]);
        versionText.setText(calories[position]);
        imageView.setImageResource(images[position]);

        return rowView;
    }
}