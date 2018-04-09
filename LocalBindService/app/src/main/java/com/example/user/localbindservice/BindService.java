package com.example.user.localbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class BindService extends Service {

    private final IBinder mBinder = new BindServiceBinder();
    private ICallback mCallback;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // declare callback function
    public interface ICallback {
        public void remoteCall();
    }

    // for registration in activity
    public void registerCallback(ICallback cb){
        mCallback = cb;
    }

    // service contents
    public void myServiceFunc(){
        Log.d("BindService","called by Activity");

        // call callback in Activity
        mCallback.remoteCall();
    }

    // Declare inner class
    public class BindServiceBinder extends Binder {
        BindService getService(){
            return BindService.this; // return current service
        }
    }
}
