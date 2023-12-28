package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.XML;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // creating variables for our textview,
    // imageview,cardview and progressbar.
    //private TextView courseNameTV, courseTracksTV, courseBatchTV;
    //private ImageView courseIV;

    private ProgressBar loadingDB;
    private CardView courseCV;
    private TextView[] t = null;
    private String[] titles;
    LinearLayout layout;

    item myItem;


    // below line is the variable for url from
    // where we will be querying our data.
    String url = " https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Frcs.mako.co.il%2Frss%2F31750a2610f26110VgnVCM1000005201000aRCRD.xml";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.listView);
        ArrayList<item> itemArrayList = new ArrayList<>();
        //@SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout linearLayout = findViewById(R.id.layoutRight);


        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                //byte[] bytes = response.getBytes(StandardCharsets.ISO_8859_1);
                //response = new String(bytes, StandardCharsets.UTF_8);
                //System.out.println(response);

                try {
                    //JSONObject jsonObject = XML.toJSONObject(response);

                    //JSONArray items = res.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
                    JSONArray items = response.getJSONArray("items");
                    System.out.println(items);
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject stu = items.getJSONObject(i);
                        //System.out.println(items.getJSONObject(i));
                        itemArrayList.add(new item(stu.getString("title"), stu.getString("description"), stu.getString("link"),stu.getString("pubDate"), stu.getString("thumbnail")));
                    }
                    //System.out.println(itemArrayList.get(0).getLink());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }

    });

        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
       queue.add(jsonObjectRequest);
       System.out.println(jsonObjectRequest);
       itemListAdapter adapter = new itemListAdapter(this, R.layout.adapter_view_layout, itemArrayList);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
    }
}

