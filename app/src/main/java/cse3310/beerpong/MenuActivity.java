package cse3310.beerpong;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        /*mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.menu_background_music);
        mediaPlayer.start();*/


        final Button about_btn = (Button) findViewById(R.id.about_button);
        final Button play_btn = (Button) findViewById(R.id.play_button);
        final Button interface_btn = (Button) findViewById(R.id.interface_button);
        final Button shop_btn = (Button) findViewById(R.id.shop_button);
        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                new AlertDialog.Builder(MenuActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                        .setMessage("Are you sure you want to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                System.exit(0);
                            }
                        }).setNegativeButton("No", null).show();
            }
        });


        about_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(about);

            }
        });

        play_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //Intent play = new Intent(MenuActivity.this, Stacks.class);
                Intent stack = new Intent(MenuActivity.this, Stacks.class);
                //startActivity(play);
                startActivity(stack);

            }
        });

        interface_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent interf = new Intent(MenuActivity.this, InterfaceActivity.class);
                startActivity(interf);

            }
        });

        shop_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent shopmenu = new Intent(MenuActivity.this, Shop.class);
                startActivity(shopmenu);

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        mediaPlayer.release();

    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }*/

}
