package com.noushad.kidsapp.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.noushad.kidsapp.R;

public class MainActivity extends BaseActivity {

    private CardView calculateCard;
    private CardView rearrangeCard;
    private CardView drawCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

    }

    private void initializeViews() {
        calculateCard = findViewById(R.id.calc_card);
        rearrangeCard = findViewById(R.id.re_arrange_card);
        drawCard = findViewById(R.id.draw_card);

        calculateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(v);
                startActivity(new Intent(MainActivity.this, CalculateActivity.class));
            }
        });


        rearrangeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(v);
                startActivity(new Intent(MainActivity.this, ReArrangeActivity.class));
            }
        });


        drawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(v);

            }
        });


    }


    private void animate(View v) {

        Animator scaleFab = AnimatorInflater.loadAnimator(this, R.animator.scale);
        scaleFab.setTarget(v);

        scaleFab.start();

    }
}
