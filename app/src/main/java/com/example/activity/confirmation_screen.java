package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.R;
import com.example.adapter.confirmation_Adapter;
import com.example.util.Constants;

public class confirmation_screen extends AppCompatActivity {
    TextView textView_hight, textView_weight;
    private confirmation_Adapter adapter;
    private String[] imagess = {

            // Constant.cro_pose[0], Constant.cro_pose[2], Constant.cro_pose[4], Constant.cro_pose[6]
            Constants.cro_pose[0], Constants.cro_pose[2],
    };

    private String[] imagess2 = {

//            Constant.cro_pose[1], Constant.cro_pose[3], Constant.cro_pose[5], Constant.cro_pose[7]
            Constants.cro_pose[1], Constants.cro_pose[3],
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirmation_screen);

        ViewPager viewPager = findViewById(R.id.viewpager);
        textView_hight = findViewById(R.id.textView_height);
        textView_weight = findViewById(R.id.textView_weight);
        textView_weight.setText("" + Constants.weight);
        textView_hight.setText("" + Constants.height);

        adapter = new confirmation_Adapter(confirmation_screen.this,
                imagess, imagess2, Constants.pose, Constants.pose2);
        viewPager.setAdapter(adapter);


    }


    public void okk(View view) {
        /*Intent myIntent = new Intent(confirmation_screen.this, measurement.class);
        myIntent.putExtra("childid",Constants.child_id);
        confirmation_screen.this.startActivity(myIntent);*/

    }

    public void back(View view) {
        Intent myIntent = new Intent(confirmation_screen.this, PosePreviewActivity.class);

        confirmation_screen.this.startActivity(myIntent);

    }
}
