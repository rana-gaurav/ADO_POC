package com.faceopen.ado_poc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mikhaellopez.circleview.CircleView;

public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior sheetBehavior;
    LinearLayout layoutBottomSheet;
    private Context mContext;
    public CircleView circleView1;
    public CircleView circleView2;
    public CircleView circleView3;
    public ImageView ado1, ado2;
    public FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_bottom_sheet = findViewById(R.id.btn_bottom_sheet);
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        circleView1 = findViewById(R.id.circleView1);
        circleView2 = findViewById(R.id.circleView2);
        circleView3 = findViewById(R.id.circleView3);
        frameLayout = findViewById(R.id.frameLayout);
        ado1 = findViewById(R.id.ado1);
        ado2 = findViewById(R.id.ado2);
        btn_bottom_sheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
//                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
//                        bottomSheet.show(getSupportFragmentManager(),
//                                "ModalBottomSheet");

                        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            //btnBottomSheet.setText("Close sheet");
                        } else {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            //btnBottomSheet.setText("Expand sheet");
                        }
                    }
                });


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        final Handler handler=new Handler();
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        PulseAnimation.newInstance(MainActivity.this).startRippleAnimation(circleView1, circleView2, circleView3);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //PulseAnimation.newInstance(MainActivity.this).foundDevice(ado1);
                                PulseAnimation.newInstance(MainActivity.this).createDevices(MainActivity.this,frameLayout);
                            }
                        },1000);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}