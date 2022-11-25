package com.example.myqandaapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//عملية لنشاء قارئ لملف الاسئلة
/*
 * عن طريق عمل اوبجكت فى الاكتفتى واستدعاؤه هنا للاستفادة من خصاءصه ( وراثة خصاءصه )
 *ومن ثم انشاء سكانر لقراءة الملف الذى تم فتحه سطر سطر
 *
 *
 * */
public class QustionReder {
    CompitionActivity activity;

    public QustionReder(CompitionActivity activity) {
        this.activity = activity;
    }

    public List<Qstion> getQstion(String fileName) throws IOException {
        InputStream is = activity.getAssets().open(fileName);
        Scanner s = new Scanner(is);
        List<Qstion> qustion = new ArrayList<>();
        while (s.hasNextLine()) {                                             //طالما يوجد سطر تالى
            String qtext = s.nextLine();                                     // اقرا السطر الاول واحفظه ك نص للسؤال
            List<String> choises = new ArrayList<>();                       // انشاء مصفوفة اختيارات
            for (int i = 0; i < 4; i++) {                                  // قراءة 4 سطور معا ( الثانى والثالث والرابعوالخامس  )
                choises.add(s.nextLine());                                // تخزينهم ك خيارات
            }
            String correctAnswer = s.nextLine();                        //قراءة السطر الخاص بالاجابة ( السطر السادس )
            Qstion q = new Qstion();                                   //انشاء اوبجكت من نوع سؤال لتخزين  القيم التى تم حفظها

            q.setQustionText(qtext);
            q.setChoises(choises);
            q.setCorrectAnswer(correctAnswer);
            qustion.add(q);

        }
        return qustion;
    }

}
