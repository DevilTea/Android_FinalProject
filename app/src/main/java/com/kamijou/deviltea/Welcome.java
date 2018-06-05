package com.kamijou.deviltea;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        MyHandler myHandler = new MyHandler();
        myHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private class MyHandler extends Handler {
        public MyHandler() {}

        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
