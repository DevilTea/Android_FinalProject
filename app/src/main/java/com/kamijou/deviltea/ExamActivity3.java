package com.kamijou.deviltea;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class ExamActivity3 extends AppCompatActivity {

    private int kanaType;
    private int examType;
    private int numOfAnswered = -1;
    private ArrayList<Question> questions;
    private int numOfCorrect = 0;
    private int numOfIncorrect = 0;

    private TextView tvQuestion;
    private TextView tvProgress;
    private TextView tvCorrect;
    private TextView tvIncorrect;
    private ArrayList<Button> btSelections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_3);
        Intent intent = getIntent();
        kanaType = intent.getIntExtra("kanaType", -1);
        examType = intent.getIntExtra("examType", -1);
        questions = (ArrayList<Question>) intent.getSerializableExtra("questions");

        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        tvCorrect = (TextView) findViewById(R.id.tv_correct);
        tvIncorrect = (TextView) findViewById(R.id.tv_incorrect);
        btSelections.add((Button) findViewById(R.id.bt_selection_1));
        btSelections.add((Button) findViewById(R.id.bt_selection_2));
        btSelections.add((Button) findViewById(R.id.bt_selection_3));
        btSelections.add((Button) findViewById(R.id.bt_selection_4));

        tvCorrect.setText("O: 0");
        tvIncorrect.setText("X: 0");
        tvProgress.setText("0 / " + questions.size());
        setButtonsOnClickListener();
        nextQuestion();
    }

    public void setButtonsOnClickListener() {
        btSelections.forEach(new Consumer<Button>() {
            @Override
            public void accept(final Button button) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = btSelections.indexOf(button);
                        Question question = questions.get(numOfAnswered);
                        if(question.getAnswer() == question.getSelections().get(index)) {
                            numOfCorrect++;
                            if(numOfAnswered == questions.size() - 1) {
                                numOfAnswered++;
                                showExamResultDialog();
                            } else {
                                nextQuestion();
                            }
                        } else {
                            numOfIncorrect++;
                            question.increaseIncorrectCount();
                        }
                        tvCorrect.setText("O: " + numOfCorrect);
                        tvIncorrect.setText("X: " + numOfIncorrect);
                        tvProgress.setText(numOfAnswered + " / " + questions.size());
                    }
                });
            }
        });
    }

    public void nextQuestion() {
        numOfAnswered++;
        Question question = questions.get(numOfAnswered);
        if(examType == Constant.TYPE_KANA_TO_PINYIN) {
            tvQuestion.setText(question.getAnswer().getKana(question.getKanaType()));
            for(int i = 0; i < 4; i++) {
                btSelections.get(i).setText(question.getSelections().get(i).getPinyin());
            }
        } else if(examType == Constant.TYPE_PINYIN_TO_KANA) {
            tvQuestion.setText(question.getAnswer().getPinyin());
            for(int i = 0; i < 4; i++) {
                btSelections.get(i).setText(question.getSelections().get(i).getKana(question.getKanaType()));
            }
        }
    }

    public void showExamResultDialog() {
        View v = getLayoutInflater().inflate(R.layout.dialog_exam_result, null);
        TextView tvCorrectPercentage = (TextView) v.findViewById(R.id.tv_correct_percentage);
        TextView tvCorrect = (TextView) v.findViewById(R.id.tv_correct);
        TextView tvIncorrect = (TextView) v.findViewById(R.id.tv_incorrect);
        tvCorrectPercentage.setText(((int) Math.floor(numOfCorrect * 100 / (numOfCorrect + numOfIncorrect))) + "%");
        tvCorrect.setText("O: " + numOfCorrect);
        tvIncorrect.setText("X: " + numOfIncorrect);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(v)
                .setCancelable(false)
                .setPositiveButton("結束", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("重來", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        numOfAnswered = -1;
                        numOfCorrect = 0;
                        numOfIncorrect = 0;
                        questions.forEach(new Consumer<Question>() {
                            @Override
                            public void accept(Question question) {
                                Collections.shuffle(question.getSelections());
                            }
                        });
                        Collections.shuffle(questions);
                        nextQuestion();
                        ExamActivity3.this.tvCorrect.setText("O: " + numOfCorrect);
                        ExamActivity3.this.tvIncorrect.setText("X: " + numOfIncorrect);
                        tvProgress.setText(numOfAnswered + " / " + questions.size());
                    }
                })
                .show();
    }
}
