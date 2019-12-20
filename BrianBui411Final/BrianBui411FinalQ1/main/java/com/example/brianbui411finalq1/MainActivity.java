package com.example.brianbui411finalq1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        root.setBackgroundColor(Color.rgb(255, 211, 0));
        root.setOrientation(LinearLayout.VERTICAL);

        lp.setMargins(5,5,5,5);

        TextView tv = new TextView(this);
        tv.setText("1");
        tv.setLayoutParams(lp);
        tv.setBackgroundColor(Color.GREEN);
        tv.setTextSize(12);
        tv.setGravity(Gravity.CENTER);
        tv.setHeight(outMetrics.heightPixels/8);
        root.addView(tv);

        TextView tv2 = new TextView(this);
        tv2.setText("2");
        tv2.setLayoutParams(lp);
        tv2.setBackgroundColor(Color.GREEN);
        tv2.setTextSize(24);
        tv2.setGravity(Gravity.CENTER);
        tv2.setHeight(outMetrics.heightPixels/4);
        root.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText("3");
        tv3.setLayoutParams(lp);
        tv3.setBackgroundColor(Color.GREEN);
        tv3.setTextSize(48);
        tv3.setGravity(Gravity.CENTER);
        tv3.setHeight(outMetrics.heightPixels/2);
        root.addView(tv3);

        setContentView(root);
    }
}