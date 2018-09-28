package cse3310.beerpong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class WonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final Button accept_button = (Button) findViewById(R.id.accept_btn);
        final int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("CREDITS", (creditAmount+20));
        editor.apply();

        if(!settings.getBoolean("LVLTWO", false) || !(settings.getBoolean("LVLTHREE", false)) || !(settings.getBoolean("LVLFOUR", false))) { //LVL UNLOCKED
            showLevelImage();
        } else{
            //nothing
        }

        if(!settings.getBoolean("LVLTWO", false)) {
            editor.putBoolean("LVLTWO", true);
            editor.apply();
        } else if(!settings.getBoolean("LVLTHREE", false)) {
            editor.putBoolean("LVLTHREE", true);
            editor.apply();
        } else if(!settings.getBoolean("LVLFOUR", false)) {
            editor.putBoolean("LVLFOUR", true);
            editor.apply();
        }




        accept_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent menu = new Intent(WonActivity.this, MenuActivity.class);
                startActivity(menu);

            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Exit to main menu?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(WonActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();
    }

    public void showLevelImage() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.new_level);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }
}
