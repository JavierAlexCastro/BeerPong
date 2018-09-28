package cse3310.beerpong;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Balls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balls);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);

        final TextView amount = (TextView) findViewById(R.id.amount);
        amount.setText(String.valueOf(creditAmount));

        final Button uta_btn = (Button) findViewById(R.id.button4);
        final Button texas_btn = (Button) findViewById(R.id.button5);
        final Button khalili_btn = (Button) findViewById(R.id.button3);

        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(Balls.this, Shop.class);
                startActivity(about);

            }
        });

        uta_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                if(settings.getBoolean("UTABALL", false)) { //already purchased
                    showPurchasedImage();
                } else{
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if(creditAmount<50) {
                        showNotEnoughImage();
                    } else {
                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                        creditAmount -= 50;
                        editor.putBoolean("UTABALL", true);
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
                if(settings.getBoolean("TEXASBALL", false)) { //purchased before
                    showPurchasedImage();
                } else{
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if (creditAmount < 100) {
                        showNotEnoughImage();
                    } else {
                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount -= 100;
                        editor.putBoolean("TEXASBALL", true);
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
                if(settings.getBoolean("KHALILIBALL", false)) { //purchased before
                    showPurchasedImage();
                } else {
                    int creditAmount = settings.getInt("CREDITS", Context.MODE_PRIVATE);
                    if (creditAmount < 250) {
                        showNotEnoughImage();
                    } else {

                        SharedPreferences.Editor editor = settings.edit();
                        creditAmount -= 250;
                        editor.putBoolean("KHALILIBALL", true);
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
