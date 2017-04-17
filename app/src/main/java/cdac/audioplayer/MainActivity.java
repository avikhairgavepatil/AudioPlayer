package cdac.audioplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnPause, btnResume, btnStop, btnNext, btnPrev;
    MediaPlayer mediaPlayer = null;
    int length = 0;
    int[] audio_files = {R.raw.music_file,R.raw.audio_file_2};
    int curSongIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_layout);

        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnPause = (Button) findViewById(R.id.buttonPause);
        btnResume = (Button) findViewById(R.id.buttonResume);
        btnStop = (Button) findViewById(R.id.buttonStop);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnPrev = (Button) findViewById(R.id.buttonPrev);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play - 1, stop - 2, pause - 3, resume - 4, next - 5, prev - 6
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",1);
                startService(intent);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",2);
                startService(intent);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",3);
                startService(intent);
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",4);
                startService(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",5);
                startService(intent);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioService.class);
                intent.putExtra("code",6);
                startService(intent);
            }
        });






    }

    public void next(){
        curSongIndex++;
        if(curSongIndex == audio_files.length)
            curSongIndex = 0;
        play();
    }

    public void previous(){
        curSongIndex--;
        if(curSongIndex == -1)
            curSongIndex = audio_files.length - 1;
        play();
    }

    public void play(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),audio_files[curSongIndex]);
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
