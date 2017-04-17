package cdac.audioplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class AudioService extends Service {
    MediaPlayer mediaPlayer;
    int length = 0;
    int[] audio_files = {R.raw.music_file,R.raw.audio_file_2};
    int curSongIndex = 0;

    public AudioService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //one time operation
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //whenever you call start service

        int code = intent.getIntExtra("code",-1);
        switch (code){
            case 1:
                //play
                play();
                break;
            case 2:
                //stop
                stop();
                break;
            case 3:
                //pasue
                pause();
                break;
            case 4:
                //resume
                resume();
                break;
            case 5:
                //next
                next();
                break;
            case 6:
                //prev
                previous();
                break;
            case -1:
                //stop the service
                stopSelf();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void next(){
        curSongIndex++;
        if(curSongIndex == audio_files.length)
            curSongIndex = 0;
        stop();
        play();
    }

    public void previous(){
        curSongIndex--;
        if(curSongIndex == -1)
            curSongIndex = audio_files.length - 1;
        stop();
        play();
    }

    public void play(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),audio_files[curSongIndex]);
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
        length  = mediaPlayer.getCurrentPosition();
    }

    public void resume(){
        mediaPlayer.seekTo(length);
        mediaPlayer.start();

    }

    public void stop(){
        mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
