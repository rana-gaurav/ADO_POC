package com.faceopen.ado_poc;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mikhaellopez.circleview.CircleView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class PulseAnimation extends AppCompatActivity {
    public ImageView ado1;
    public ImageView ado2;
    public int state = 1;
    private boolean sizeChanged;
    private int savedWidth;
    Handler handler;
    private ViewGroup viewRoot;
    private static Context mContext;

    public static PulseAnimation newInstance(Context context) {
        Bundle args = new Bundle();
        PulseAnimation fragment = new PulseAnimation();
        mContext =context;
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse_animation);
        viewRoot = (ViewGroup) findViewById(R.id.main);
        ado1 = findViewById(R.id.ado1);
        ado2 = findViewById(R.id.ado2);

        //startRippleAnimation();

        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ado1.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        changeAdo1Layout();
                    }
                }, 200);
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ado2.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        changeAdo2Layout();
                    }
                }, 200);
            }
        }, 7000);

        ado1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                changeAdo1Layout();
            }
        });

        ado2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                changeAdo2Layout();
            }
        });
    }

    public void startRippleAnimation(CircleView cv1, CircleView cv2, CircleView cv3){
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                turn(state, cv1, cv2, cv3);
            }

        }, 0, 500);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void turn(int status, CircleView cv1, CircleView cv2, CircleView cv3){
        if(status == 1){
            cv1.setBorderColor(mContext.getColor(R.color.spiral_color_on));
            cv2.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            cv3.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            state = 2;
        }
        if(status == 2){
            cv1.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            cv2.setBorderColor(mContext.getColor(R.color.spiral_color_on));
            cv3.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            state = 3;
        }
        if(status == 3){
            cv1.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            cv2.setBorderColor(mContext.getColor(R.color.spiral_color_off));
            cv3.setBorderColor(mContext.getColor(R.color.spiral_color_on));
            state = 1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changeAdo1Layout() {
        ado1.setVisibility(View.VISIBLE);
        TransitionManager.beginDelayedTransition(viewRoot);
        ViewGroup.LayoutParams params = ado1.getLayoutParams();
        savedWidth = params.width;
        params.width = 250;
        ado1.setLayoutParams(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changeAdo2Layout() {
        ado2.setVisibility(View.VISIBLE);
        TransitionManager.beginDelayedTransition(viewRoot);
        ViewGroup.LayoutParams params = ado2.getLayoutParams();
        savedWidth = params.width;
        params.width = 250;
        ado2.setLayoutParams(params);
    }

    public void createDevices(Context mContext,FrameLayout frameLayout){
        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.bot);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList=new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        imageView.setVisibility(View.VISIBLE);
        animatorSet.start();
        imageView.setX(getRandomX());
        imageView.setY(getRandomY());
        frameLayout.addView(imageView);
    }


    private boolean isValidPoints(){
        if (getRandomX() > 100){

        }
    }


    private int getRandomX(){
        if(isValidPoints()) {
            Random r = new Random();
            int x = r.nextInt(500 - 300) + 300;
            return x;
        }
    }


    private int getRandomY(){
        if(isValidPoints()) {
            Random r = new Random();
            int y = r.nextInt(200 - 50) + 50;
            return y;
        }
        return 0;
    }
}