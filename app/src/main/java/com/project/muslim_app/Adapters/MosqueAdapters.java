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

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.muslim_app.Models.Masjid;
import com.project.muslim_app.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class MosqueAdapters extends ArrayAdapter<String> {
    private String[] nama_masjid;
    private String[] alamat;
    private String[] link;
    private String[] gambar;

    private Bitmap bitmaps;
    private Activity context;

    private Fragment fragment;

    public MosqueAdapters(Activity context, String[] nama_masjid , String[] alamat , String[] link, String[]gambar  ) {
        super(context, R.layout.item_mosque, nama_masjid);
        this.context = context;
        // this.urls = urls;
        this.nama_masjid = nama_masjid;
        this.alamat = alamat;
        this.link = link;
        this.gambar = gambar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.item_mosque,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(nama_masjid[position]);
        viewHolder.tvw2.setText(alamat[position]);
        viewHolder.tvw3.setText(link[position]);
        new GetImageFromURL(viewHolder.ivw).execute(gambar[position]);

        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ImageView ivw;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.tvnama_masjid);
            tvw2=(TextView)v.findViewById(R.id.tvalamat);
            tvw3=(TextView)v.findViewById(R.id.tvlink);
            tvw3.setAutoLinkMask(Linkify.ALL);
            ivw=(ImageView)v.findViewById(R.id.ivgambar);
        }

    }

    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
    {

        ImageView imgView;
        public GetImageFromURL(ImageView imgv)
        {
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmaps=null;

            try{

                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmaps= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return bitmaps;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){

            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }
}
