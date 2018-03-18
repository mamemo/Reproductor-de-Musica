package tc2.mamendez.reproductordemusica;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    List<String> music_show;
    List<Integer> music;
    List<String> music_lyrics;
    ListView lv;
    boolean is_Paused = false;
    int cancion_Actual = -1;
    MediaPlayer mp = null;
    SeekBar progreso;
    RotateAnimation rotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        lv = (ListView) findViewById(R.id.list_music);

        music_show = new ArrayList<String>();
        music = new ArrayList<>();
        music_lyrics = new ArrayList<>();

        agregar_Canciones();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                music_show);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cambiar_cancion(position);
            }

        });

        final SeekBar volumen = findViewById(R.id.bar_volumen);
        final AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        volumen.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumen.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        progreso = findViewById(R.id.bar_duration);

        final Handler mHandler = new Handler();
        new Runnable() {

            @Override
            public void run() {
                if (mp != null && !is_Paused) {
                    int mCurrentPosition = mp.getCurrentPosition();
                    progreso.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 0);
            }
        }.run();

        progreso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(progreso.getProgress());
            }
        });

        TextView lyric = findViewById(R.id.txt_lyrics);
        lyric.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)||(keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            SeekBar volumen = findViewById(R.id.bar_volumen);
            AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            volumen.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
        return true;
    }

    public void agregar_Canciones() {
        music.add(R.raw.akasha__alunizar);
        music_show.add("Akasha - Alunizar");
        music_lyrics.add("akasha__alunizar.txt");
        music.add(R.raw.akasha__claridad);
        music_show.add("Akasha - Claridad");
        music_lyrics.add("akasha__claridad.txt");
        music.add(R.raw.akasha__profanar);
        music_show.add("Akasha - Profanar");
        music_lyrics.add("akasha__profanar.txt");
        music.add(R.raw.alphabetics__late_to_work);
        music_show.add("Alphabetics - Late to Work");
        music_lyrics.add("alphabetics__late_to_work.txt");
        music.add(R.raw.alphabetics__paint_paint_paint);
        music_show.add("Alphabetics - Paint Paint Paint");
        music_lyrics.add("alphabetics__paint_paint_paint.txt");
        music.add(R.raw.cafe_sura__quiero_ver);
        music_show.add("Café Surá - Quiero Ver");
        music_lyrics.add("cafe_sura__quiero_ver.txt");
        music.add(R.raw.cocofunka__carlitos_bad_boy);
        music_show.add("Cocofunka - Carlitos Bad Boy");
        music_lyrics.add("cocofunka__carlitos_bad_boy.txt");
        music.add(R.raw.cocofunka__mejor_sera);
        music_show.add("Cocofunka - Mejor Será");
        music_lyrics.add("cocofunka__mejor_sera.txt");
        music.add(R.raw.cocofunka__sin_jugar);
        music_show.add("Cocofunka - Sin Jugar");
        music_lyrics.add("cocofunka__sin_jugar.txt");
        music.add(R.raw.cocofunka__suele_suceder);
        music_show.add("Cocofunka - Suele Suceder");
        music_lyrics.add("cocofunka__suele_suceder.txt");
        music.add(R.raw.half_tangerine__high_on_you);
        music_show.add("Half Tangerine - High on You");
        music_lyrics.add("half_tangerine__high_on_you.txt");
        music.add(R.raw.jose_capmany__la_modelo);
        music_show.add("José Capmany - La Modelo");
        music_lyrics.add("jose_capmany__la_modelo.txt");
        music.add(R.raw.jose_capmany__los_pollitos);
        music_show.add("José Capmany - Los Pollitos");
        music_lyrics.add("jose_capmany__los_pollitos.txt");
        music.add(R.raw.jose_capmany__quiero_ser);
        music_show.add("José Capmany - Quiero Ser");
        music_lyrics.add("jose_capmany__quiero_ser.txt");
        music.add(R.raw.los_waldners__periodistas);
        music_show.add("Los Waldners - Periodistas");
        music_lyrics.add("los_waldners__periodistas.txt");
        music.add(R.raw.nakury__aunque_quieras);
        music_show.add("Nakury - Aunque Quieras");
        music_lyrics.add("nakury__aunque_quieras.txt");
        music.add(R.raw.percance__la_negra);
        music_show.add("Percance - La Negra");
        music_lyrics.add("percance__la_negra.txt");
        music.add(R.raw.raido_jesse_baez__meant_to_be);
        music_show.add("Raido ft. Jesse Baez - Meant to be");
        music_lyrics.add("raido_jesse_baez__meant_to_be.txt");
        music.add(R.raw.sonambulo_psicotropical__agua);
        music_show.add("Sonámbulo Psicotropical - Agua");
        music_lyrics.add("cocofunka__carlitos_bad_boy.txt");
        music.add(R.raw.sonambulo_psicotropical__chusma_funk);
        music_show.add("Sonámbulo Psicotropical - Chusma Funk");
        music_lyrics.add("sonambulo_psicotropical__chusma_funk.txt");
    }

    public void cambiar_cancion(int position) {
        if (cancion_Actual != -1) {
            mp.stop();
        }
        progreso.setProgress(0);
        ImageButton pause = findViewById(R.id.btn_pausar);
        pause.setImageResource(android.R.drawable.ic_media_pause);
        is_Paused = false;


        cancion_Actual = position;
        TextView txt = findViewById(R.id.txt_cancion);
        txt.setText("Canción: " + music_show.get(position));
        mp = MediaPlayer.create(getApplicationContext(), music.get(position));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                siguiente(null);
            }

        });
        enseñar_letras();
        mp.start();
        findViewById(R.id.img_vinyl).startAnimation(rotateAnimation);
        progreso.setMax(mp.getDuration());


    }

    public void pausar(View view) {
        if (cancion_Actual != -1) {
            ImageButton pause = findViewById(R.id.btn_pausar);
            if (!is_Paused) {
                pause.setImageResource(android.R.drawable.ic_media_play);
                is_Paused = true;
                mp.pause();
                findViewById(R.id.img_vinyl).clearAnimation();
            } else {
                pause.setImageResource(android.R.drawable.ic_media_pause);
                is_Paused = false;
                mp.start();
                findViewById(R.id.img_vinyl).startAnimation(rotateAnimation);
            }
        }
    }

    public void siguiente(View view) {
        if (cancion_Actual != -1) {
            cancion_Actual = cancion_Actual + 1 != music.size() ? cancion_Actual + 1 : 0;
            cambiar_cancion(cancion_Actual);
        }
    }

    public void anterior(View view) {
        if (cancion_Actual != -1) {
            cancion_Actual = cancion_Actual - 1 != -1 ? cancion_Actual - 1 : music.size() - 1;
            cambiar_cancion(cancion_Actual);
        }
    }

    public void enseñar_letras(){
        System.out.println("Entra");
        TextView lyrics = findViewById(R.id.txt_lyrics);
        lyrics.setTranslationY(500f);
        try{
            String texto = readFromAssets(getApplicationContext(), music_lyrics.get(cancion_Actual));
            System.out.println(texto);
            lyrics.setText(texto, TextView.BufferType.SPANNABLE);

        } catch (Exception e) {
            e.printStackTrace();
            lyrics.setText("Letras no disponibles", TextView.BufferType.SPANNABLE);
        }
        lyrics.animate().translationYBy(-4000f).setDuration(250000);
    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        int cont = 0;
        while (mLine != null) {
            sb.append(mLine+"\n"); // process line
            mLine = reader.readLine();
            cont++;
        }
        System.out.println("Leidos "+Integer.toString(cont));
        reader.close();
        return sb.toString();
    }
}
