package cse3310.beerpong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LevelAndDifficulty extends AppCompatActivity {

    public Button btn;
    public Button btnLevel1;
    public Button btnLevel2;
    public Button btnLevel3;
    public Button btnLevel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_and_difficulty);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        btn = (Button) findViewById(R.id.btnLevel1);
        btnLevel2 = (Button) findViewById(R.id.btnLevel2);
        btnLevel3 = (Button) findViewById(R.id.btnLevel3);
        btnLevel4 = (Button) findViewById(R.id.btnLevel4);
        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(LevelAndDifficulty.this, Stacks.class);
                startActivity(about);

            }
        });

        if(settings.getBoolean("LVLTWO", false)) { //already unlocked
            //showPurchasedImage();
            btnLevel2.setBackgroundResource(R.drawable.level2_button_effect);
        } else{
            btnLevel2.setBackgroundResource(R.drawable.level2_not_clickable);
            btnLevel2.setEnabled(false);
        }

        if(settings.getBoolean("LVLTHREE", false)) { //already unlocked
            //showPurchasedImage();
            btnLevel3.setBackgroundResource(R.drawable.level3_button_effect);
        } else{
            btnLevel3.setBackgroundResource(R.drawable.level3_not_clickable);
            btnLevel3.setEnabled(false);
        }
        if(settings.getBoolean("LVLFOUR", false)) { //already unlocked
            //showPurchasedImage();
            btnLevel4.setBackgroundResource(R.drawable.level4_button_effect);
        } else{
            btnLevel4.setBackgroundResource(R.drawable.level4_not_clickable);
            btnLevel4.setEnabled(false);
        }

        final int stack = settings.getInt("SELECTEDSTACK", Context.MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is Beer Pong App!");
                editor.putInt("DIFFICULTY", 0);
                editor.apply();
                //Toast.makeText(getApplicationContext(), "Level 1 Selected.", Toast.LENGTH_SHORT).show();
                switch(stack) {
                    case 10:
                        Intent cups_10 = new Intent(LevelAndDifficulty.this, PlayAIActivity.class);
                        startActivity(cups_10);
                        break;
                    case 6:
                        Intent cups_6 = new Intent(LevelAndDifficulty.this, PlayAIActivity_six.class);
                        startActivity(cups_6);
                        break;
                    case 3:
                        Intent cups_3 = new Intent(LevelAndDifficulty.this, PlayAIActivity_three.class);
                        startActivity(cups_3);
                        break;
                    case 1:
                        Intent cups_1 = new Intent(LevelAndDifficulty.this, PlayAIActivity_one.class);
                        startActivity(cups_1);
                        break;

                }

            }
        });


        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is Beer Pong App!");
                editor.putInt("DIFFICULTY", 1);
                editor.apply();
                //Toast.makeText(getApplicationContext(), "Level 1 Selected.", Toast.LENGTH_SHORT).show();
                switch(stack) {
                    case 10:
                        Intent cups_10 = new Intent(LevelAndDifficulty.this, PlayAIActivity.class);
                        startActivity(cups_10);
                        break;
                    case 6:
                        Intent cups_6 = new Intent(LevelAndDifficulty.this, PlayAIActivity_six.class);
                        startActivity(cups_6);
                        break;
                    case 3:
                        Intent cups_3 = new Intent(LevelAndDifficulty.this, PlayAIActivity_three.class);
                        startActivity(cups_3);
                        break;
                    case 1:
                        Intent cups_1 = new Intent(LevelAndDifficulty.this, PlayAIActivity_one.class);
                        startActivity(cups_1);
                        break;

                }
            }
        });


        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is Beer Pong App!");
                editor.putInt("DIFFICULTY", 2);
                editor.apply();
                //Toast.makeText(getApplicationContext(), "Level 1 Selected.", Toast.LENGTH_SHORT).show();
                switch(stack) {
                    case 10:
                        Intent cups_10 = new Intent(LevelAndDifficulty.this, PlayAIActivity.class);
                        startActivity(cups_10);
                        break;
                    case 6:
                        Intent cups_6 = new Intent(LevelAndDifficulty.this, PlayAIActivity_six.class);
                        startActivity(cups_6);
                        break;
                    case 3:
                        Intent cups_3 = new Intent(LevelAndDifficulty.this, PlayAIActivity_three.class);
                        startActivity(cups_3);
                        break;
                    case 1:
                        Intent cups_1 = new Intent(LevelAndDifficulty.this, PlayAIActivity_one.class);
                        startActivity(cups_1);
                        break;

                }
            }
        });


        btnLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is Beer Pong App!");
                editor.putInt("DIFFICULTY", 3);
                editor.apply();
                //Toast.makeText(getApplicationContext(), "Level 1 Selected.", Toast.LENGTH_SHORT).show();
                switch(stack) {
                    case 10:
                        Intent cups_10 = new Intent(LevelAndDifficulty.this, PlayAIActivity.class);
                        startActivity(cups_10);
                        break;
                    case 6:
                        Intent cups_6 = new Intent(LevelAndDifficulty.this, PlayAIActivity_six.class);
                        startActivity(cups_6);
                        break;
                    case 3:
                        Intent cups_3 = new Intent(LevelAndDifficulty.this, PlayAIActivity_three.class);
                        startActivity(cups_3);
                        break;
                    case 1:
                        Intent cups_1 = new Intent(LevelAndDifficulty.this, PlayAIActivity_one.class);
                        startActivity(cups_1);
                        break;

                }
            }
        });

    }
}
