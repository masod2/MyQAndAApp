package com.example.myqandaapp;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText nameED = findViewById(R.id.name_ET);


        Button startbtn = findViewById(R.id.startBTN);//تعريف الزر
        startbtn.setOnClickListener(new View.OnClickListener() { //انشاء ليسنر
            @Override
            public void onClick(View v) {
                if (nameED.getText().toString() == null || nameED.getText().toString().equals("")) {

                    //عمل شاشة خطا للمستخدم بديل التوست
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.alert);
                    builder.setMessage(R.string.continiomsg);
                    builder.setIcon(android.R.drawable.ic_dialog_info);//ملاحظة يمكننا انشاء 3 ازرار بنفس الطريقة
                    builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });//DialogInterface.OnClickListener end
                    //عرض شاشة الديلوج بعد انشاءها بالاعلى
                    AlertDialog dialog = builder.create();
                    dialog.show();


                    Toast.makeText(MainActivity.this, "يرجى ادخال الاسم للاستمرار ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CompitionActivity.class);
                    startbtn.setBackgroundResource(R.drawable.choise_currect_shape);
                    intent.putExtra("name", nameED.getText().toString());
                    startActivity(intent);

                }
            }
        });// OnClickListener end
    }//onCreate end

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage(R.string.exsitconfirm)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.alert)
                .setPositiveButton("تأكيد ", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("الغاء ", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
// طريقة اخرى
/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
        //عمل شاشة خطا للمستخدم بديل التوست
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setTitle(R.string.alert);
        builder.setMessage(R.string.exsitconfirm);
        builder.setIcon(android.R.drawable.ic_dialog_alert);//ملاحظة يمكننا انشاء 3 ازرار بنفس الطريقة

        builder.setPositiveButton("تاكيد ", (dialog, which) -> {
            //   MainActivity.this.finish();
            //او باستخدام الدالة التالية
            MainActivity.super.onBackPressed();
        });//DialogInterface.OnClickListener end
        builder.setNegativeButton("الغاء   ", (dialog, which) -> dialog.dismiss());

        //عرض شاشة الديلوج بعد انشاءها بالاعلى
        AlertDialog dialg = builder.create();
        dialg.show();



    }//onBackPressed end
    */

}//MainActivity end