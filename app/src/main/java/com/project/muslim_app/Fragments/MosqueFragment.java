package com.project.muslim_app.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.muslim_app.Adapters.MosqueAdapters;
import com.project.muslim_app.Json.getMosque;
import com.project.muslim_app.R;
import com.project.muslim_app.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class MosqueFragment extends Fragment {
    private static final String newurl = Server.URL + "Muslim/getMasjid.php";
    String[] nama_masjid;
    String[] alamat_masjid;
    String[] link_masjid;
    String[] gambar_masjid;

    BufferedInputStream is;
    String line=null;
    String result=null;
    ListView listView;


    public MosqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mosque, container, false);
        listView = (ListView)v.findViewById(R.id.lv_mosque);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        MosqueAdapters mosqueAdapters = new MosqueAdapters(getActivity(),nama_masjid,alamat_masjid,link_masjid,gambar_masjid);
        listView.setAdapter(mosqueAdapters);

        return v;
    }
    private void collectData()
    {
//Connection
        try{

            URL url=new URL(newurl);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            nama_masjid=new String[ja.length()];
            alamat_masjid=new String[ja.length()];
            link_masjid=new String[ja.length()];
            gambar_masjid=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                nama_masjid[i]=jo.getString("nama_masjid");
                alamat_masjid[i]=jo.getString("alamat");
                link_masjid[i]=jo.getString("link");
                gambar_masjid[i]=jo.getString("gambar");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }

}
