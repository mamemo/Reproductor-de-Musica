package tc2.mamendez.reproductordemusica;

import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> music_show;
    List<Integer> music;
    boolean is_Paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_music);

        music_show = new ArrayList<String>();
        music = new ArrayList<>();

        agregar_Canciones();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                music_show );

        lv.setAdapter(arrayAdapter);
    }

    public void agregar_Canciones(){
        music.add(R.raw.cafe_sura__quiero_ver);
        music_show.add("Café Surá - Quiero Ver");
    }

    public void pausar(View view){
        ImageButton pause = findViewById(R.id.btn_pausar);
        if(!is_Paused) {
            pause.setImageResource(android.R.drawable.ic_media_play);
            is_Paused = true;
        }else{
            pause.setImageResource(android.R.drawable.ic_media_pause);
            is_Paused = false;
        }
    }
}
