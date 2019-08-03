package com.example.jun.mobileid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.jun.mobileid.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {

    ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

        Animation anim1 = AnimationUtils.loadAnimation(
                getApplicationContext(), // 현재 화면의 제어권자
                R.anim.stop);    // 설정한 에니메이션 파일
        binding.fingerprint.startAnimation(anim1);

        Animation anim2 = AnimationUtils.loadAnimation(
                getApplicationContext(), // 현재 화면의 제어권자
                R.anim.rotate_anim);    // 설정한 에니메이션 파일
        binding.fingerprint.startAnimation(anim2);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },  1350);
    }
}
