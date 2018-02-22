package com.noushad.kidsapp.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.noushad.kidsapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CalculateActivity extends BaseActivity {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton10;
    private Button mButton11;
    private Button mButton12;
    private Button mButton13;
    private Button mButton14;
    private Button mButton15;
    private Button mChangeButton;
    private EditText wordInput;
    private TextView hintText;
    private TextView scoreText;
    private TextView lengthHintText;
    private TextView totalText;
    private StringBuilder mStringBuilder;
    private CallbackManager mCallbackManager;
    private ShareDialog mShareDialog;
    private Button facebookShareButton;

    private Map<Integer, String> questions;
    private int mIndex = 0;
    private String mAnswer;
    private Integer mHint;
    private int mLength;
    private int mCount = 0;
    private ArrayList<Button> mButtons;
    private char[] charArray;
    private int mScore = 0;
    private int mTotal = 0;
    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        init();
        setupQuestions();
        initializeViews();

    }

    private void init() {
        mButtons = new ArrayList<>();
        mStringBuilder = new StringBuilder();
        questions = new HashMap<>();
        mCallbackManager = CallbackManager.Factory.create();
        mShareDialog = new ShareDialog(this);
    }

    private void getQuestion() {

        reset();
        mIndex = new Random().nextInt(questions.size());

        if (mIndex < questions.size() + 1) { //because i am accessing key not index
            mAnswer = questions.get(mIndex);
            charArray = mAnswer.toCharArray();
            mLength = mAnswer.length();
            lengthHintText.setText(String.valueOf(mLength) + " WORDS");
            mHint = mIndex;
            hintText.setText(String.valueOf(mHint));

            setupButtons();
        }
    }

    private void setupButtons() {

        for (int i = 0; i < mLength; i++) {
            int num = getRandom();
            mButtons.get(num).setText(String.valueOf(charArray[i]));
        }
    }

    private void reset() {
        mStringBuilder = new StringBuilder();
        mCount = 0;
        wordInput.setText("");

        list = new ArrayList<Integer>();
        for (int i = 1; i < 15; i++) {
            list.add(i);
        }

        for (Button button : mButtons) {
            button.setBackgroundResource(R.color.colorAccent);
        }
    }

    private void setupQuestions() {
        questions.put(1, "ONE");
        questions.put(2, "TWO");
        questions.put(3, "THREE");
        questions.put(4, "FOUR");
        questions.put(5, "FIVE");
        questions.put(6, "SIX");
        questions.put(7, "SEVEN");
        questions.put(8, "EIGHT");
        questions.put(9, "NINE");
        questions.put(10, "TEN");
        questions.put(11, "ELEVEN");
        questions.put(12, "TWELVE");
        questions.put(13, "THIRTEEN");
        questions.put(14, "FOURTEEN");
        questions.put(15, "FIFTEEN");
        questions.put(16, "SIXTEEN");
        questions.put(17, "SEVENTEEN");
        questions.put(18, "EIGHTEEN");
        questions.put(19, "NINETEEN");
        questions.put(20, "TWENTY");
        questions.put(21, "TWENTYONE");

    }

    private void initializeViews() {

        hintText = findViewById(R.id.hint_text);
        totalText = findViewById(R.id.total_tries);
        scoreText = findViewById(R.id.score_text);
        lengthHintText = findViewById(R.id.length_hint);
        wordInput = findViewById(R.id.word_input);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton5 = findViewById(R.id.button5);
        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);
        mButton8 = findViewById(R.id.button8);
        mButton9 = findViewById(R.id.button9);
        mButton10 = findViewById(R.id.button10);
        mButton11 = findViewById(R.id.button11);
        mButton12 = findViewById(R.id.button12);
        mButton13 = findViewById(R.id.button13);
        mButton14 = findViewById(R.id.button14);
        mButton15 = findViewById(R.id.button15);
        facebookShareButton = findViewById(R.id.facebook_share_button);
        mButtons.add(mButton1);
        mButtons.add(mButton2);
        mButtons.add(mButton3);
        mButtons.add(mButton4);
        mButtons.add(mButton5);
        mButtons.add(mButton6);
        mButtons.add(mButton7);
        mButtons.add(mButton8);
        mButtons.add(mButton9);
        mButtons.add(mButton10);
        mButtons.add(mButton11);
        mButtons.add(mButton12);
        mButtons.add(mButton13);
        mButtons.add(mButton14);
        mButtons.add(mButton15);


        mChangeButton = findViewById(R.id.change_button);

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion();
            }
        });

        getQuestion();

        facebookShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShareDialog.registerCallback(mCallbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(CalculateActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(CalculateActivity.this, "CANCELLED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {

                        Toast.makeText(CalculateActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("I scored " + mScore + " Can You Beat Me ? ")
                        .setContentUrl(Uri.parse("https://youtube.com")).build();

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    mShareDialog.show(linkContent);
                }
            }
        });
    }


    private void animate(View v) {

        Animator scaleFab = AnimatorInflater.loadAnimator(this, R.animator.scale);
        scaleFab.setTarget(v);

        scaleFab.start();

    }

    public void buttonPressed(View view) {

        animate(view);
        mStringBuilder.append(((Button) view).getText() + " ");
        mCount++;
        wordInput.setText(mStringBuilder.toString());

        ColorDrawable buttonColor = (ColorDrawable) ((Button) view).getBackground();

        if (buttonColor.getColor() == getResources().getColor(R.color.colorYellow)) {
            ((Button) view).setBackgroundResource(R.color.colorAccent);
        } else {
            ((Button) view).setBackgroundResource(R.color.colorYellow);
        }

        if (mCount == mLength)
            checkAnswer();

    }

    private void checkAnswer() {
        mTotal++;
        totalText.setText(String.valueOf(mTotal));
        String str = wordInput.getText().toString().replace(" ", "");
        if (str.equalsIgnoreCase(mAnswer)) {
            mScore++;
            scoreText.setText(String.valueOf(mScore));
            Toast.makeText(this, "WELL DONE", Toast.LENGTH_LONG).show();
            getQuestion();
        } else {
            Toast.makeText(this, "TRY AGAIN", Toast.LENGTH_LONG).show();
        }
        reset();
    }

    private int getRandom() {

        Collections.shuffle(list);


        int random = new Random().nextInt(list.size());
        int n = list.get(random);
        list.remove(random);
        return n;
    }
}
