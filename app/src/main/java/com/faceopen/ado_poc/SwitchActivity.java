package com.faceopen.ado_poc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwitchActivity extends AppCompatActivity {
    Switch labeledSwitch;
    TextView tvClose, tvOpen, circleText;
    CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        tvClose = findViewById(R.id.tvClose);
        tvOpen = findViewById(R.id.tvOpen);
        labeledSwitch = findViewById(R.id.labeledSwitch);
        circleText = findViewById(R.id.circleText);
        tvClose.setVisibility(View.INVISIBLE);
        tvOpen.setVisibility(View.VISIBLE);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.custom_progressBar);

        labeledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvClose.setVisibility(View.VISIBLE);
                    tvOpen.setVisibility(View.INVISIBLE);
                }else{
                    tvClose.setVisibility(View.INVISIBLE);
                    tvOpen.setVisibility(View.VISIBLE);
                }
            }
        });

        for(int i =0 ; i < 100; i++){
            circleProgressBar.setProgressWithAnimation(i);
            final Handler handler = new Handler(Looper.getMainLooper());
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(finalI == 99){
                        circleProgressBar.setProgress(99);
                    }
                    if(circleProgressBar.getProgress() == 99){
                        circleText.setVisibility(View.VISIBLE);
                        circleText.setText("2");
                    }
                }
            }, 1500);


        }

        RunAnimation();
    }

    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        a.reset();
        Animation b = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        b.reset();
        Animation c = AnimationUtils.loadAnimation(this, R.anim.move);
        c.reset();
        Animation d = AnimationUtils.loadAnimation(this, R.anim.blink);
        d.reset();

        TextView tv = (TextView) findViewById(R.id.animText);
        tv.clearAnimation();
        tv.startAnimation(a);

        TextView tv2 = (TextView) findViewById(R.id.animText2);
        tv2.clearAnimation();
        tv2.startAnimation(c);

//        TextView tv3 = (TextView) findViewById(R.id.animText3);
//        tv3.clearAnimation();
//        tv3.startAnimation(d);
    }
}