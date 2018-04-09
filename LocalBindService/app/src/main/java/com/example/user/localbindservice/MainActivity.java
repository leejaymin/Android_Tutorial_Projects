package com.example.user.localbindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mButton;
    private Button mButtonServiceCall;
    private BindService mBindService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mButtonServiceCall = (Button)findViewById(R.id.button2);
        mButtonServiceCall.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent Service = new Intent(this, BindService.class);
                bindService(Service, mConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.button2:
                mBindService.myServiceFunc();
        }
    }

    // service connection definition
    private ServiceConnection mConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.BindServiceBinder binder = (BindService.BindServiceBinder) service;
            mBindService = binder.getService(); // get service.
            mBindService.registerCallback(mCallback); // callback registration
        }
        // Called when the connection with the service disconnects unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBindService = null;
        }
    };

    // call below callback in service. it is running in Activity.
    private BindService.ICallback mCallback = new BindService.ICallback() {
        @Override
        public void remoteCall() {
            Log.d("MainActivity","called by service");
        }
    };
}
