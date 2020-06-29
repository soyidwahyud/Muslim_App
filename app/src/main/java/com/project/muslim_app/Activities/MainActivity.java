package com.project.muslim_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.muslim_app.Adapters.AyatAdapters;
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

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private static final String newurl = Server.URL + "Muslim/displayHadits.php";
    String[] nama_ayat;
    String[] nama_latin;
    String[] nama_arti;

    BufferedInputStream is;
    String line=null;
    String result=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lviewmain);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        AyatAdapters ayatAdapters = new AyatAdapters(this,nama_ayat,nama_latin,nama_arti);
        listView.setAdapter(ayatAdapters);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
//                        Intent h = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(h);
                        break;

                    case R.id.mosque:
                        Intent m = new Intent(MainActivity.this, MosqueActivity.class);
                        startActivity(m);
                        break;

                    case R.id.about:
                        Intent a = new Intent(MainActivity.this, AboutActivity.class);
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
            nama_ayat=new String[ja.length()];
            nama_latin=new String[ja.length()];
            nama_arti=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                nama_ayat[i]=jo.getString("nama_ayat");
                nama_latin[i]=jo.getString("latin");
                nama_arti[i]=jo.getString("arti");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }
}
