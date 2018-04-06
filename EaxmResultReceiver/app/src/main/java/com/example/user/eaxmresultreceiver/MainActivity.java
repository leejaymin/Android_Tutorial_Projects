package com.example.user.eaxmresultreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{


    private TextView syncMessage;
    private ProgressBar progressBar;
    private Button syncButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        syncMessage = (TextView)findViewById(R.id.textview);
        syncButton = (Button)findViewById(R.id.button);
        syncButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        syncMessage.setText("sync start");

        Intent intent = new Intent(this, SyncService.class);
        intent.putExtra("RECEIVER", resultReceiver);
        startService(intent);
    }

    private Handler handler = new Handler();

    private ResultReceiver resultReceiver = new ResultReceiver(handler){

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            // SYNC_COMPLETED CODE = 1
            if (resultCode == 1){
                String msg = resultData.getString("msg");
                progressBar.setVisibility(View.GONE);
                syncMessage.setText("Done "+msg);
            }

        }
    };

}
