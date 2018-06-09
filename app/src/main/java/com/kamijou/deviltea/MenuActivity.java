package com.kamijou.deviltea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView tvEnterLearning;
    private TextView tvEnterExamination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvEnterLearning = (Button) findViewById(R.id.bt_enter_learning);
        tvEnterExamination = (Button) findViewById(R.id.bt_enter_examination);

        tvEnterLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LearningActivity.class);
                startActivity(intent);
            }
        });

        tvEnterExamination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MenuActivity.this, "還沒做好QQ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });
    }
}
