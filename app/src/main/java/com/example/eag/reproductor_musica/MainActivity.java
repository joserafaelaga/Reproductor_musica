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

        //Colamos las canciones en nuestro vertormp
        colocarCanciones();

    }

    public void playPause(View view){
        if(vectormp[posicion].isPlaying()){ //Si está sonando la pausamos
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausada", Toast.LENGTH_SHORT).show();
        }else{ //Si no está reproduciendo comenzamos a reproducir
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

    public void stop(View view){

        if(vectormp[posicion] != null){

            //Detenemos la reproducción
            vectormp[posicion].stop();

            //Volvemos a colocar las canciones
            colocarCanciones();

            //Iniciamos desde la posición cero
            posicion = 0;

            //Volvemos a color el botón de "play"
            play_pause.setBackgroundResource(R.drawable.reproducir);

            //Ponemos la primera carátula
            img_cancion.setImageResource(R.drawable.portada1);

            Toast.makeText(this,"Está detenida", Toast.LENGTH_SHORT).show();

        }
    }

    private void cambioPortadas(){
        if(posicion == 0){
            img_cancion.setImageResource(R.drawable.portada1);
        }else if(posicion == 1){
            img_cancion.setImageResource(R.drawable.portada2);
        }else if(posicion == 2){
            img_cancion.setImageResource(R.drawable.portada3);
        }
    }

    public void siguiente(View view){
        if(posicion < vectormp.length -1){ //Hay canciones para pasar a la siguiente

            if(vectormp[posicion].isPlaying()){ //Si está reproduciendo la detenemos

                vectormp[posicion].stop();

                //Aumentamos posición para pasar de canción
                posicion ++;

                vectormp[posicion].start();

            }else{
                posicion ++;
            }

            //Cambiamos la portada
            cambioPortadas();

        }else{
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void anterior(View view){
        if(posicion >= 1){ //Hay canciones para pasar a la siguiente

            if(vectormp[posicion].isPlaying()){ //Si está reproduciendo la detenemos

                vectormp[posicion].stop();

                //Aumentamos posición para pasar de canción
                posicion --;

                vectormp[posicion].start();

            }else{
                posicion --;
            }

            //Cambiamos la portada
            cambioPortadas();

        }else{
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }




}
