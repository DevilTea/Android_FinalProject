package com.kamijou.deviltea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class ExamActivity2 extends AppCompatActivity {

    private int kanaType;
    private ArrayList<KanaData> examKanaDatas;
    private ArrayList<Question> questions;
    private Button btKanaToPinyin;
    private Button btPinyinToKana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);
        Intent intent = getIntent();
        kanaType = intent.getIntExtra("kanaType", -1);
        examKanaDatas = (ArrayList<KanaData>) intent.getSerializableExtra("examKanaDatas");
        generateQuestions();
        btKanaToPinyin = (Button) findViewById(R.id.bt_kana_to_pinyin);
        btPinyinToKana = (Button) findViewById(R.id.bt_pinyin_to_kana);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ExamActivity2.this, ExamActivity3.class);
                it.putExtra("kanaType", kanaType);
                if(v == btKanaToPinyin) {
                    it.putExtra("examType", Constant.TYPE_KANA_TO_PINYIN);
                } else if(v == btPinyinToKana) {
                    it.putExtra("examType", Constant.TYPE_PINYIN_TO_KANA);
                }
                it.putExtra("questions", questions);
                startActivity(it);
                finish();
            }
        };

        btKanaToPinyin.setOnClickListener(onClickListener);
        btPinyinToKana.setOnClickListener(onClickListener);
    }

    private void generateQuestions() {
        questions = new ArrayList<>();
        examKanaDatas.forEach(new Consumer<KanaData>() {
            @Override
            public void accept(KanaData kanaData) {
                if(kanaType == Constant.TYPE_ALLKANA) {
                    questions.add(new Question(Constant.TYPE_HIRAGANA, examKanaDatas, kanaData));
                    questions.add(new Question(Constant.TYPE_KATAKANA, examKanaDatas, kanaData));
                } else {
                    questions.add(new Question(kanaType, examKanaDatas, kanaData));
                }
            }
        });
        Collections.shuffle(questions);
    }
}
