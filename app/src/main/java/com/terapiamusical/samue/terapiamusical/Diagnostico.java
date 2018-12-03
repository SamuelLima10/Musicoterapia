package com.terapiamusical.samue.terapiamusical;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.internal.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Diagnostico extends AppCompatActivity {

    Button btnStress, btnAnsiedade, btnAutismo, btnParkinson;
    ImageButton imgBtnNext;
    MediaPlayer mediaPlayer;
    private boolean isPlaying;
    ArrayList<String> listStress, listAnsiedade, listAutismo, listParkinson;

    //Seekbar
    Handler handler;
    Runnable runnable, runnablemusic;
    SeekBar seekBar;


    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);
        addListas();
        btnParkinson = (Button) findViewById(R.id.btnDiagnosticoParkinson);
        handler = new Handler();
        seekBar = (SeekBar) findViewById(R.id.loadMusic);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playCycle(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    public void addListas(){
        listParkinson = new ArrayList<String>();
        listAnsiedade = new ArrayList<String>();
        listStress = new ArrayList<String>();
        listAutismo = new ArrayList<String>();
        listAnsiedade.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Ansiedade%2FMellomaniac%20Chillout%20MIx.mp3?alt=media&token=603dede2-9531-45a9-be1b-032585944a09");
        listAnsiedade.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Ansiedade%2FPlease%20Don%20t%20Go%20with%20lyrics.mp3?alt=media&token=312d2050-7bea-4ab8-81ca-6851350c1e39");
        listAnsiedade.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Ansiedade%2FPure%20Shores%20Official%20Music%20Video.mp3?alt=media&token=bc73b5a1-e577-4973-8ecf-193b518393dc");
        listAnsiedade.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Ansiedade%2FSomeone%20Like%20You.mp3?alt=media&token=23cc11c1-ef6c-49e1-9701-1aaefd727168");
        listAnsiedade.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Ansiedade%2FWeightless%20Official%20Video.mp3?alt=media&token=44e0b43e-3564-4154-b425-a058c8cbc314");

        listStress.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Stress%2F1988%20Watermark%20-%2001%20Watermark.mp3?alt=media&token=bb340c3d-8659-486e-9acf-d051f9b8dcee");
        listStress.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Stress%2FAirstream%20-%20Electra.mp3?alt=media&token=535a3499-50e3-4fed-8e78-a8e33a30401e");
        listStress.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Stress%2FCafe%20Del%20Mar%20We%20Can%20Fly%20wmv.mp3?alt=media&token=681a973f-de7c-45ca-b55b-fe2945e66897");
        listStress.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Stress%2FCanzonetta%20Sull%20aria.mp3?alt=media&token=8b44fc13-cf1e-4323-b54b-fdfb44ab4899");
        listStress.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Stress%2Fcoldplay.mp3?alt=media&token=49c7136c-fef5-4b07-a8d5-213c29de3a22");

        listParkinson.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Parkinson%2FChopin%20Waltz%20op%2064%20no%201%20Minute%20Waltz.mp3?alt=media&token=d97fd756-c346-463f-86d8-37b662f81440");
        listParkinson.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Parkinson%2FConsolation%20Nr%203%20-%20Daniel%20Barenboim.mp3?alt=media&token=49e35e69-99c5-4f22-b1c3-76614a1b3f4a");
        listParkinson.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Parkinson%2FDebussy%20Arabesque%20No%202%20Ciccolini%20SD.mp3?alt=media&token=37de0a5e-92bf-4848-8fc8-e4a149d2d2d2");
        listParkinson.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Parkinson%2FJouni%20Somero%20plays%20Sibelius%20Romance%20D%20flat%20op%2024%209.mp3?alt=media&token=cc6b3a8c-190a-4278-bb46-23d063df3082");
        listParkinson.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Parkinson%2FMost%20beautiful%20Bach%20Prelude%20ever%20plus%20insane%20Fugue%20BWV%20873.mp3?alt=media&token=ba9a057a-cff2-49cd-b9df-8bc30e02a6f4");

        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FA%20Thousand%20Years%20Piano%20Cello%20Cover.mp3?alt=media&token=57f98258-884d-43c8-9706-85702d7d7696");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FAdele%20Piano%20Cover%20-%20Costantino%20Carrara.mp3?alt=media&token=9adc2b3f-313d-4e2b-8ac7-8f799987bddf");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FAlan%20Walker%20Faded%20on%20Fingerstyle%20by%20Fabio%20Lima.mp3?alt=media&token=2a5484c7-d471-4b22-8cb8-08d57e8f03e8");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FBohemian%20Rhapsody%20Full%20Instrumental%20Studio%20Version.mp3?alt=media&token=5794ea56-da0c-428e-ab4e-eb2d708c1693");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FMetallica%20Nothing%20Else%20Matters%20on%20Fingerstyle%20by%20Fabio%20Lima.mp3?alt=media&token=a1c40bea-77d4-4097-99db-a5f426d04c58");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FViva%20La%20Vida.mp3?alt=media&token=a6226ae8-1e33-4155-a7b8-03034a73ba2a");
//        listAutismo.add("https://firebasestorage.googleapis.com/v0/b/terapiamusical-4dcf5.appspot.com/o/Autismo%2FWhat%20Makes%20You%20Beautiful%205%20Piano%20Guys%201%20piano%20-%20The%20Piano%20Guys.mp3?alt=media&token=755e9a8c-b017-41fe-8862-0d3ebde9c0d1");
    }

    public void play_music_parkinson(View v){

        stopPlaying();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(index <= listParkinson.size()){
                        index++;
                        if(index != listParkinson.size())
                            btnParkinson.performClick();
                        else {
                            Intent i = new Intent(Diagnostico.this, CameraFim.class);
                            startActivity(i);
                            finish();
                        }
                    }

                }
            });
            mediaPlayer.setDataSource(listParkinson.get(index));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    mp.start();
                    playCycle();
                }
            });

            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public  void play_music_autismo(View v){
        stopPlaying();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(index <= listAutismo.size()){
                        index++;
                        if(index != listAutismo.size())
                            btnParkinson.performClick();
                        else {
                            Intent i = new Intent(Diagnostico.this, CameraFim.class);
                            startActivity(i);
                            finish();
                        }
                    }

                }
            });
            mediaPlayer.setDataSource(listAutismo.get(index));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    mp.start();
                    playCycle();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void play_music_stress(View v){
        stopPlaying();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(index <= listStress.size()){
                        index++;
                        if(index != listStress.size())
                            btnParkinson.performClick();
                        else {
                            Intent i = new Intent(Diagnostico.this, CameraFim.class);
                            startActivity(i);
                            finish();
                        }
                    }

                }
            });
            mediaPlayer.setDataSource(listStress.get(index));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    mp.start();
                    playCycle();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void play_music_ansiedade(View v){
        stopPlaying();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(index <= listAnsiedade.size()){
                        index++;
                        if(index != listAnsiedade.size())
                            btnParkinson.performClick();
                        else {
                            Intent i = new Intent(Diagnostico.this, CameraFim.class);
                            startActivity(i);
                            finish();
                        }
                    }

                }
            });
            mediaPlayer.setDataSource(listAnsiedade.get(index));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    mp.start();
                    playCycle();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mediaPlayer.start();
//        playCycle();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.back_page) {
            //stopPlaying();
            Intent i = new Intent(Diagnostico.this, Login.class);
            startActivity(i);
            finish();
        }
        return true;
    }

    private void displayMessage(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    private void alert(String s) {
        Toast.makeText(Diagnostico.this,s,Toast.LENGTH_SHORT).show();
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //SeekBar

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }


}


