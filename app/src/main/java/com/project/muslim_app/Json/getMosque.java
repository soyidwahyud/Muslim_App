package com.project.muslim_app.Json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class getMosque {
    public static String[] Image_Url;
    public static Bitmap[] bitmaps;
    public static String[] Mosque_name;
    public static String[] Mosque_address;
    public static String[] Mosque_link;
    public static final String JSON_ARRAY="result";
    public static final String gambar = "gambar";
    public static final String nama_masjid = "nama_masjid";
    public static final String alamat = "alamat";
    public static final String link = "link";

    //JSON Declaration
    private String json;
    private JSONArray urls;

    public getMosque(String json)
    {
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject();
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(gambar));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void getAllImages() throws JSONException {
        Mosque_name = new String[urls.length()];
        Mosque_address = new String[urls.length()];
        Mosque_link = new String[urls.length()];
        Image_Url = new String[urls.length()];
        bitmaps = new Bitmap[urls.length()];
        for(int i=0;i<urls.length();i++)
        {
            Mosque_name[i]= urls.getJSONObject(i).getString(nama_masjid);
            Mosque_address[i]= urls.getJSONObject(i).getString(alamat);
            Mosque_link[i]= urls.getJSONObject(i).getString(link);
            Image_Url[i] = urls.getJSONObject(i).getString(gambar);
            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);
        }
    }

}
