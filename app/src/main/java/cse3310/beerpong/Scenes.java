package cse3310.beerpong;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.transition.Scene;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Scenes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);

        final TextView amount = (TextView) findViewById(R.id.amount);
        amount.setText(String.valueOf(creditAmount));

        final Button uta_btn = (Button) findViewById(R.id.button11);
        final Button texas_btn = (Button) findViewById(R.id.button12);
        final Button khalili_btn = (Button) findViewById(R.id.button10);
        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(Scenes.this, Shop.class);
                startActivity(about);

            }
        });

        uta_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                if(settings.getBoolean("UTABACKGROUND", false)) { //already purchased
                    showPurchasedImage();
                } else{
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if(creditAmount<50) {
                        showNotEnoughImage();
                    } else {
                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                        creditAmount -= 50;
                        editor.putBoolean("UTABACKGROUND", true);
                        editor.putInt("CREDITS", creditAmount);
                        editor.apply();
                        amount.setText(String.valueOf(creditAmount));
                        showSuccessImage();
                    }
                }

            }
        });

        texas_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                if(settings.getBoolean("TEXASBACKGROUND", false)) { //purchased before
                    showPurchasedImage();
                } else{
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if (creditAmount < 100) {
                        showNotEnoughImage();
                    } else {
                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount -= 100;
                        editor.putBoolean("TEXASBACKGROUND", true);
                        editor.putInt("CREDITS", creditAmount);
                        editor.apply();
                        amount.setText(String.valueOf(creditAmount));
                        showSuccessImage();
                    }
                }
            }
        });

        khalili_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                if(settings.getBoolean("KHALILIBACKGROUND", false)) { //purchased before
                    showPurchasedImage();
                } else {
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if (creditAmount < 250) {
                        showNotEnoughImage();
                    } else {

                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount -= 250;
                        editor.putBoolean("KHALILIBACKGROUND", true);
                        editor.putInt("CREDITS", creditAmount);
                        editor.apply();
                        amount.setText(String.valueOf(creditAmount));
                        showSuccessImage();
                    }
                }
            }
        });


    }

    public void showNotEnoughImage() {
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
        imageView.setBackgroundResource(R.drawable.not_enough_money);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public void showSuccessImage() {
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
        imageView.setBackgroundResource(R.drawable.purchase_success);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public void showPurchasedImage() {
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
        imageView.setBackgroundResource(R.drawable.already_purchased);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }
}
