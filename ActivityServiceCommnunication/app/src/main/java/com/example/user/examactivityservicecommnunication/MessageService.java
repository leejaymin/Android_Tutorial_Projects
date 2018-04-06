package com.example.user.examactivityservicecommnunication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendMessage();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage(){
        Log.d("messageService", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", "This is my first message!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
