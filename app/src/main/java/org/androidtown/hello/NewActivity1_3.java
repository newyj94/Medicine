package org.androidtown.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import static org.jsoup.examples.HtmlToPlainText.main;


public final class NewActivity1_3 extends AppCompatActivity {

    SpinnerList list;
    TextView textView1;
    Elements link0, link1, link2, link3, link4, link5;
    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText input;
    String keycode = "";
    TextView textview1;
    //
    static String aa0="";
    static String aa1="";
    static String aa2="";
    static String aa3="";
    static String aa4="";
    static String k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1_3);

        String content = "ghghdghhfh";
        LinearLayout layout = new LinearLayout(this);
        textview1 = (TextView) findViewById(R.id.text1_view);

       /* new Thread() {
            public void run() {
                try {
                    main(a);
                } catch (IOException e) {
                }
            }
        }.start();*/
        // while(cursor.moveToNext()) {
        //   tv_list.append(content);
        textview1.setTextSize(20);
       // textview1.setText(content);
       // textview1.append(content);
        Log.w("content뜨나????", String.valueOf(content));
        //  layout.addView(textview1);

        input = (EditText) findViewById(R.id.inputSearch);
}

    public void onclick(View v) {

       // Intent intent = new Intent(getApplicationContext(),NewActivity1_3.class);
      //  startActivity(intent);
        final String[] b={};

        new Thread() {
            public void run() {
        try{main(b);}
        catch (IOException e){}
            }
        }.start();

        input = (EditText) findViewById(R.id.inputSearch);
        Log.w("인풋의값은????", String.valueOf(input.getText().toString()));
        String key = input.getText().toString();
        keycode = key;
        Log.w("이거제ㅂㄹ제발", String.valueOf(k));
        Log.w("이거제발??", String.valueOf(aa0));
        textview1.setText(aa0);
        textview1.append("\n");
        textview1.append(aa1);
        textview1.append("\n");
        textview1.append(aa2);
        textview1.append("\n");
        textview1.append(aa3);
        textview1.append("\n");
        textview1.append(aa4);

    }

    public void main(String[] a) throws IOException {
        String item_name = "아스피린";
        String name1 = "성공";
        String name2 = "실패";
        Log.w("keycode의값은????", String.valueOf(keycode));

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList");


        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=XtA4Wl%2FHnYeTUg902C0cFjbJBbE8W6s%2BnTzWX4J5ylYxDWLWmsTvfVss%2FQggtnMh2dfnP8Lmbc%2FN7RTj4mMRUQ%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("item_name", "UTF-8") + "=" + URLEncoder.encode(keycode, "UTF-8")); //품목명
        urlBuilder.append("&" + URLEncoder.encode("entp_name", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //업체명
        //urlBuilder.append("&" + URLEncoder.encode("DRUG_SHAPE", "UTF-8") + "=" + URLEncoder.encode("원형", "UTF-8")); //품목명
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); //한 페이지 결과 수
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            Log.w("line값은???", String.valueOf(line));
            sb.append(line);
        }

        Document doc = Jsoup.connect(urlBuilder.toString()).get();
        String title = doc.title();

        Elements link0 = doc.select("ITEM_NAME");
        String virtuallink0 = new String(String.valueOf(link0));
        String actuallink0[]  = virtuallink0.split("\n");
        Elements link1 = doc.select("CHART");
        String virtuallink1 = new String(String.valueOf(link1));
        String actuallink1[]  = virtuallink1.split("\n");
        Elements link2 = doc.select("CLASS_NAME");
        String virtuallink2 = new String(String.valueOf(link2));
        String actuallink2[]  = virtuallink2.split("\n");
        Elements link3 = doc.select("ETC_OTC_NAME");
        String virtuallink3 = new String(String.valueOf(link3));
        String actuallink3[]  = virtuallink3.split("\n");
        Elements link4 = doc.select("FORM_CODE_NAME");
        String virtuallink4 = new String(String.valueOf(link4));
        String actuallink4[]  = virtuallink4.split("\n");

        aa0 = actuallink0[1];
        aa1 = actuallink1[1];
        aa2 = actuallink2[1];
        aa3 = actuallink3[1];
        aa4 = actuallink4[1];

        Log.w("hh링크item_name값은???", String.valueOf(actuallink0[1]));
        Log.w("hh링크chart값은???", String.valueOf(actuallink1[1]));
        Log.w("hh링크class_name값은???", String.valueOf(actuallink2[1]));
        Log.w("//링크etc_otc_name값은???", String.valueOf(actuallink3[1]));
        Log.w("//링크form_code_name값은???", String.valueOf(actuallink4[1]));

        rd.close();
        conn.disconnect();
    }
}