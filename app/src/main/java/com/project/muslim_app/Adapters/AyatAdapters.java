package com.project.muslim_app.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.muslim_app.R;

import java.io.InputStream;

public class AyatAdapters extends ArrayAdapter<String> {
    private String[] nama_ayat;
    private String[] latin;
    private String[] arti;
    private Activity context;

    private Fragment fragment;

    public AyatAdapters(Activity context, String[] nama_ayat , String[] latin , String[] arti) {
        super(context, R.layout.item_ayat, nama_ayat);
        this.context = context;
        // this.urls = urls;
        this.nama_ayat = nama_ayat;
        this.latin = latin;
        this.arti = arti;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        AyatAdapters.ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.item_ayat,null,true);
            viewHolder=new AyatAdapters.ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(AyatAdapters.ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(nama_ayat[position]);
        viewHolder.tvw2.setText(latin[position]);
        viewHolder.tvw3.setText(arti[position]);

        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.tvnama_ayat);
            tvw2=(TextView)v.findViewById(R.id.tvlatin2);
            tvw3=(TextView)v.findViewById(R.id.tvarti2);
        }

    }

}
