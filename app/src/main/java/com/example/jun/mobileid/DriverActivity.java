package com.example.jun.mobileid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidquery.AQuery;
import com.example.jun.mobileid.databinding.ActivityDriverBinding;
import com.example.jun.mobileid.databinding.ActivityIdBinding;

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

public class DriverActivity extends AppCompatActivity {

    ActivityDriverBinding binding;
    private AQuery aq = new AQuery( this );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver);

        new JSONTask().execute("http://35.190.228.126:8081/api/query");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(DriverActivity.this,MainActivity.class);
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

//                    String Name=object.getString("name");
//                    String Cc=object.getString("cc");
//                    String SSN=object.getString("ssn");
//                    String Address=object.getString("address");
//                    String Image=object.getString("image");
//                    String Date=object.getString("date");
//                    String Master=object.getString("master");
//                    String Stamp=object.getString("stamp");

//
//                    binding.idName.setText(Name+"("+Cc+")");
//                    binding.idSsn.setText(SSN);
//                    binding.idAddress.setText(Address);
//                    aq.id(binding.idFace).image(Image);
//                    binding.idDate.setText(Date);
//                    binding.idMaster.setText(Master);
//                    aq.id(binding.idStamp).image(Stamp);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
