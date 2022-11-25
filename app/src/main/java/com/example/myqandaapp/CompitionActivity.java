package com.example.myqandaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompitionActivity extends AppCompatActivity {
    int score = 0;
    TextView qwstionTv;
    TextView scorevalTV;
    Button op1BTN, op2BTN, op3BTN, op4BTN;
    ImageView photpIM;
    Button[] choisesbtn = new Button[4];
    MediaPlayer winPlayer;
    MediaPlayer losePlayer;
    List<Qstion> qstion;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compition);
        TextView nameeTV = findViewById(R.id.nameeTV);

        scorevalTV = findViewById(R.id.scorevalTV);
        scorevalTV.setText("" + score);

        if (getIntent() != null && getIntent().hasExtra("name")) {
            nameeTV.setText(getIntent().getStringExtra("name"));
        }
        winPlayer = MediaPlayer.create(getApplicationContext(), R.raw.win);
        losePlayer = MediaPlayer.create(getApplicationContext(), R.raw.lose);
        losePlayer.setOnCompletionListener(mp -> loadMixedQustion());
        winPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                loadMixedQustion();

            }
        });


        qwstionTv = findViewById(R.id.qwstionTv);
        progressBar = findViewById(R.id.progressBar);
        photpIM = findViewById(R.id.photpIM);

        op1BTN = findViewById(R.id.op1BTN);
        op2BTN = findViewById(R.id.op2BTN);
        op3BTN = findViewById(R.id.op3BTN);
        op4BTN = findViewById(R.id.op4BTN);

        scorevalTV = findViewById(R.id.scorevalTV);
        //لتسهيل تعبئة الخيارات بالازرار
        choisesbtn[0] = op1BTN;
        choisesbtn[1] = op2BTN;
        choisesbtn[2] = op3BTN;
        choisesbtn[3] = op4BTN;


        try {
            QustionReder qreder = new QustionReder(this);
            qstion = qreder.getQstion("Qustionanswer.txt");
            Collections.shuffle(qstion);  // خلط الاسئلة
            loadMixedQustion();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }//onCreate end

    public void loadMixedQustion() {

        //اعادة تفعيل الازرار
        for (Button button : choisesbtn) {

            button.setEnabled(true);
            button.setBackgroundResource(R.drawable.choise_defult_shape);
            progressBar.setProgress(10000);
            MyTimer timer = new MyTimer (10000 , 1);
            timer.start();
        }

        final Qstion q = qstion.remove(0);//حذف السؤال الذى تم اختياره

        qwstionTv.setText(q.getQustionText());//عرض السؤال  بالتكست فيو
        for (int i = 0; i < choisesbtn.length; i++) {
            final int j = i;
            choisesbtn[i].setText(q.getChoises().get(i)); //تعبئة الخيارات بالازرار
            choisesbtn[i].setBackgroundResource(R.drawable.choise_defult_shape);// لتعيين الشكل الافتراضي للشكل
            choisesbtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choisesbtn[j].getText().toString().endsWith(q.getCorrectAnswer())) {
                        choisesbtn[j].setBackgroundResource(R.drawable.choise_currect_shape);
                        disabelButone();

                        winPlayer.start();
                        score += 10;
                        scorevalTV.setText("" + score);

                        Toast.makeText(CompitionActivity.this, "أحسنت + 10 نقاط ", Toast.LENGTH_SHORT).show();

                    } else {
                        choisesbtn[j].setBackgroundResource(R.drawable.choise_rong_shape);
                        disabelButone();
                        progressBar.setProgress(0);

                        losePlayer.start();

                        Toast.makeText(CompitionActivity.this,"الاجابة الصحيحة "+ q.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
                    }



                }

                private void disabelButone() {
                    for (Button button : choisesbtn) {
                        button.setEnabled(false);
                    }
                }
            });
        }
        //فى حال كان هناك صور للاسئلة
/*
            if (q.getPhotoPath().equalsIgnoreCase("no Image") || q.getPhotoPath() == null) {

                photpIM.setImageResource(0);

            } else {

                int dotIndex = q.getPhotoPath().lastIndexOf(".");
                String photoName = q.getCorrectAnswer().substring(0, dotIndex);
                int photoId = getResources().getIdentifier(photoName, "drawable", getPackageName());
                photpIM.setImageResource(photoId);


            }

*/
    }
    class MyTimer extends CountDownTimer{
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progressBar.setProgress((int)Math.round(millisUntilFinished/1d));

        }

        @Override
        public void onFinish() {
            for (Button button : choisesbtn) {
                button.setEnabled(false);
                progressBar.setProgress(0);
             }
            losePlayer.start();


        }
    }
}//AppCompatActivity end