package cse3310.beerpong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        final Button erase_btn = (Button) findViewById(R.id.erase);

        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(AboutActivity.this, MenuActivity.class);
                startActivity(about);

            }
        });

        erase_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("REGISTERED", true);
                editor.putString("NAME", "");
                editor.putInt("AGE", 0);
                editor.putInt("CREDITS", 50);
                editor.putBoolean("UTABALL", false);
                editor.putBoolean("UTABACKGROUND", false);
                editor.putBoolean("UTATABLE", false);
                editor.putBoolean("TEXASBALL", false);
                editor.putBoolean("TEXASBACKGROUND", false);
                editor.putBoolean("TEXASTABLE", false);
                editor.putBoolean("KHALILIBALL", false);
                editor.putBoolean("KHALILITABLE", false);
                editor.putBoolean("KHALILIBACKGROUND", false);
                editor.putBoolean("LVLTWO", false);
                editor.putBoolean("LVLTHREE", false);
                editor.putBoolean("LVLFOUR", false);
                editor.putInt("SELECTEDBALL", -1); //-1 default, 0 uta, 1 texas, 2 khalili
                editor.putInt("SELECTEDTABLE", -1);
                editor.putInt("SELECTEDBG", -1);
                editor.putInt("SELECTEDSTACK", 0);
                editor.putInt("DIFFICULTY", 0);
                editor.apply();
                editor.clear();
                editor.commit();


                Toast.makeText(getBaseContext(), "Data was erased. Restarting app", Toast.LENGTH_LONG).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }, 5000);
            }
        });

    }
}
