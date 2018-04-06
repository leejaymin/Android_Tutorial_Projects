package com.example.user.eaxmresultreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

public class SyncService extends Service {
    Intent mIntent;
    public SyncService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mIntent = intent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("SyncService","SyncService started");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                final ResultReceiver receiver = mIntent.getParcelableExtra("RECEIVER");
                bundle.putString("msg","Succeed !");
                receiver.send(1,bundle);
            }
        }).start();

        return START_NOT_STICKY;
    }
}
