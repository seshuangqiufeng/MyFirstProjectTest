package com.guoqiang.broadcasttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by wangguoqiang on 2016/8/23.
 */
public class BroadCastTestActivity extends Activity {

    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcasttest);
        mSend = (Button) findViewById(R.id.btn_send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadCastTestActivity.this,MyReceiver.class);
                sendBroadcast(intent);
            }
        });
    }
}
