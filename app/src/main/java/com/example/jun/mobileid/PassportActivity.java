package com.example.jun.mobileid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.jun.mobileid.databinding.ActivityPassportBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pl.polidea.view.ZoomView;

public class PassportActivity extends AppCompatActivity {

    ImageView P_image;
    TextView P_type;
    TextView P_surname;
    TextView P_given_name;
    TextView P_nationality;
    TextView P_birth;
    TextView P_sex;
    TextView P_issue;
    TextView P_expiry;
    TextView P_contry_code;
    TextView P_passport_num;
    TextView P_record_num;
    TextView P_authority;

    ActivityPassportBinding binding;

    private AQuery aq = new AQuery( this );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passport);

        View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.zoom_item, null, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);



        ZoomView zoomView = new ZoomView(this);
        zoomView.addView(v);
        zoomView.setLayoutParams(layoutParams);
        zoomView.setMiniMapEnabled(false); // 좌측 상단 검은색 미니맵 설정
        zoomView.setMaxZoom(4f); // 줌 Max 배율 설정  1f 로 설정하면 줌 안됩니다.
        zoomView.setMiniMapCaption("Mini Map Test"); //미니 맵 내용
        zoomView.setMiniMapCaptionSize(20); // 미니 맵 내용 글씨 크기 설정

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.addView(zoomView);

        P_image=(ImageView) v.findViewById(R.id.p_face);
        P_type=(TextView) v.findViewById(R.id.type);
        P_surname=(TextView) v.findViewById(R.id.surname);
        P_given_name=(TextView) v.findViewById(R.id.given_n);
        P_nationality=(TextView) v.findViewById(R.id.nationality);
        P_birth=(TextView) v.findViewById(R.id.birth);
        P_sex=(TextView) v.findViewById(R.id.sex);
        P_issue=(TextView) v.findViewById(R.id.issue);
        P_expiry=(TextView) v.findViewById(R.id.expiry);
        P_contry_code=(TextView) v.findViewById(R.id.contry_code);
        P_passport_num=(TextView) v.findViewById(R.id.passport_n);
        P_record_num=(TextView) v.findViewById(R.id.record_n);
        P_authority=(TextView) v.findViewById(R.id.authority);
        new JSONTask().execute("http://35.190.228.126:8081/api/query");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(PassportActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public class JSONTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("user_id", "androidTest");
                jsonObject.accumulate("name", "yun");

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    //URL url = new URL("http://192.168.25.16:3000/users");
                    URL url = new URL(urls[0]);
                    con = (HttpURLConnection) url.openConnection();
                    con.connect();

                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //String str = result.replaceAll("\\\\", "");
            //Log.e("asda", str);
            Log.e("asda", result);
            try {
                JSONObject jsonObject=new JSONObject(result);
                String a=jsonObject.getString("response");
                JSONArray jsonArray=new JSONArray(a);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    Log.d("Name",explrObject.getString("Key"));
                    JSONObject object=new JSONObject(explrObject.getString("Record"));
                    Log.d("Record",object.getString("name"));

                    String S_image=object.getString("p_image");
                    String S_type=object.getString("p_type");
                    String S_surname=object.getString("p_surname");
                    String S_given_name=object.getString("p_given_name");
                    String S_nationality=object.getString("p_nationality");
                    String S_birth=object.getString("p_birth");
                    String S_sex=object.getString("p_sex");
                    String S_issue=object.getString("p_issue");
                    String S_expiry=object.getString("p_expiry");
                    String S_contry_code=object.getString("p_contry_code");
                    String S_passport_num=object.getString("p_passport_num");
                    String S_record_num=object.getString("p_record_num");
                    String S_authority=object.getString("p_authority");

                    aq.id(P_image).image(S_image);
                    P_type.setText(S_type);
                    P_surname.setText(S_surname);
                    P_given_name.setText(S_given_name);
                    P_nationality.setText(S_nationality);
                    P_birth.setText(S_birth);
                    P_sex.setText(S_sex);
                    P_issue.setText(S_issue);
                    P_expiry.setText(S_expiry);
                    P_contry_code.setText(S_contry_code);
                    P_passport_num.setText(S_passport_num);
                    P_record_num.setText(S_record_num);
                    P_authority.setText(S_authority);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
