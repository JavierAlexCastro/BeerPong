package cse3310.beerpong;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Starting extends Activity {


    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }
    }

    Thread startThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        StartAnimation();
    }

    private void StartAnimation() {
        Animation start_anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        start_anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(start_anim);

        start_anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        start_anim.reset();
        ImageView i_view = (ImageView) findViewById(R.id.splash);
        i_view.clearAnimation();
        i_view.startAnimation(start_anim);

        startThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }

                    Intent intent = new Intent(Starting.this, RegisterActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Starting.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    Starting.this.finish();
                }
            }
        };
        startThread.start();
    }
}

