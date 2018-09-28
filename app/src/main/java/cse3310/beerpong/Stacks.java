package cse3310.beerpong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Stacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stacks);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        final Button ten_cups = (Button) findViewById(R.id.cups10);
        final Button six_cups = (Button) findViewById(R.id.cups6);
        final Button three_cups = (Button) findViewById(R.id.cups3);
        final Button one_cup = (Button) findViewById(R.id.cups1);
        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(Stacks.this, MenuActivity.class);
                startActivity(about);

            }
        });


        ten_cups.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                editor.putInt("SELECTEDSTACK", 10);
                editor.commit();
                Intent lvl = new Intent(Stacks.this, LevelAndDifficulty.class);
                startActivity(lvl);
            }
        });

        six_cups.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                editor.putInt("SELECTEDSTACK", 6);
                editor.commit();
                Intent lvl = new Intent(Stacks.this, LevelAndDifficulty.class);
                startActivity(lvl);

            }
        });

        three_cups.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                editor.putInt("SELECTEDSTACK", 3);
                editor.commit();
                Intent lvl = new Intent(Stacks.this, LevelAndDifficulty.class);
                startActivity(lvl);

            }
        });

        one_cup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                editor.putInt("SELECTEDSTACK", 1);
                editor.commit();
                Intent lvl = new Intent(Stacks.this, LevelAndDifficulty.class);
                startActivity(lvl);

            }
        });


    }

}
