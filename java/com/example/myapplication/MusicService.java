package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String activity = intent.getStringExtra("activity");
        int level = intent.getIntExtra("level", 1);
        int stage = intent.getIntExtra("stage",1);
        if (activity != null) {
            switch (activity) {
                case "mainActivity":
                    mediaPlayer = MediaPlayer.create(this, R.raw.main_menu);
                    break;
                case "locate":
                    mediaPlayer = MediaPlayer.create(this, R.raw.locate);
                    break;
                case "fight":
                    if (level == 10 || level == 18 || level == 30) {
                        mediaPlayer = MediaPlayer.create(this, R.raw.boss_fight);
                    } else {
                        mediaPlayer = MediaPlayer.create(this, R.raw.fight);
                    }
                    break;
            }
        } else {
            stopSelf();
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
