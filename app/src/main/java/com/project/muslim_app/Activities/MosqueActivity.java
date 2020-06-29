package com.project.muslim_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.muslim_app.Adapters.MosqueAdapters;
import com.project.muslim_app.R;
import com.project.muslim_app.Server.Server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MosqueActivity extends AppCompatActivity {
    ListView listView;
    private static final String newurl = Server.URL + "Muslim/displayMosque.php";
    String[] nama_masjid;
    String[] alamat_masjid;
    String[] link_masjid;
    String[] gambar_masjid;

    BufferedInputStream is;
    String line=null;
    String result=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque);
        listView = (ListView)findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        MosqueAdapters mosqueAdapters = new MosqueAdapters(this,nama_masjid,alamat_masjid,link_masjid,gambar_masjid);
        listView.setAdapter(mosqueAdapters);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent h = new Intent(MosqueActivity.this, MainActivity.class);
                        startActivity(h);
                        break;

                    case R.id.mosque:
//                        Intent m = new Intent(MosqueActivity.this, MosqueActivity.class);
//                        startActivity(m);
                        break;

                    case R.id.about:
                        Intent a = new Intent(MosqueActivity.this, AboutActivity.class);
                        startActivity(a);
                        break;
                }
                return false;
            }
        });

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
