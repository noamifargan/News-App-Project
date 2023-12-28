package com.example.myapplication3;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class itemListAdapter extends ArrayAdapter<item>{

   private static String TAG = "itemListAdapter";
    private Context mContext;
    private int mResource;


    public itemListAdapter(Context context, int resource, ArrayList<item> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position , View convertView, ViewGroup parent){
        String title = getItem(position).getTitle();
        String pubDate = getItem(position).getPubDate();
        String description = Jsoup.parse(getItem(position).getDescription()).text();
        String thumbnail = getItem(position).getImage();
        String link = getItem(position).getLink();

        item item = new item(title,description,link,pubDate,thumbnail);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvPubDate = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textView3);
        //ImageView IvImage = (ImageView) convertView.findViewById(R.id.image1);

        tvTitle.setText(title);
        tvPubDate.setText(pubDate);
        tvDescription.setText(description);
        //Picasso.get().load(image).into(IvImage);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                mContext.startActivity(openLinksIntent);
            }
        });


        return convertView;

    }

}
