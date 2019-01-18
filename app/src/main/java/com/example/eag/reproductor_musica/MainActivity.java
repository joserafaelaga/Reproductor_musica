package com.example.eag.reproductor_musica;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img_cancion;
    Button play_pause, btn_repetir;
    MediaPlayer mp;
    boolean repetir = false;
    int posicion = 0;

    MediaPlayer vectormp [] = new MediaPlayer [3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_cancion = (ImageView) findViewById(R.id.caratula);
        play_pause = (Button) findViewById(R.id.play_pause);
        btn_repetir = (Button) findViewById(R.id.repetir);

        colocarCanciones();

    }

    public void playPause(View view){
        if(vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausada", Toast.LENGTH_SHORT).show();
        }else{
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();
        }
    }

    private void colocarCanciones(){
        vectormp[0] = MediaPlayer.create(this, R.raw.doctor);
        vectormp[1] = MediaPlayer.create(this, R.raw.juego);
        vectormp[2] = MediaPlayer.create(this, R.raw.vikingos);
    }
}
