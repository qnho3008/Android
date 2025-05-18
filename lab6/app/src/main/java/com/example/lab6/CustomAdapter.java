package com.example.lab6;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] student_name;
    private final String[] mssv;
    private final int[] images;

    public CustomAdapter(Activity context, String[] student_name, String[] mssv, int[] images) {
        super(context, R.layout.list_item, student_name);
        this.context = context;
        this.student_name = student_name;
        this.mssv = mssv;
        this.images = images;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);

        TextView titleText = rowView.findViewById(R.id.student_name);
        TextView versionText = rowView.findViewById(R.id.mssv);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        titleText.setText(student_name[position]);
        versionText.setText(mssv[position]);
        imageView.setImageResource(images[position]);

        return rowView;
    }
}