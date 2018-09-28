package cse3310.beerpong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayAIActivity_one extends AppCompatActivity implements View.OnClickListener {


    private static final int SWIPE_MIN_DISTANCE = 10; //120
    private static final int SWIPE_MAX_OFF_PATH = 1000;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    int selected_bg;
    int selected_b;
    int selected_t;
    int r;
    int user_cups = 1;
    int ai_cups = 1;
    Boolean wait = true;

    List<Integer> cups_list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Random random_number = new Random();
    int percent;
    MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiplay_one);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        RelativeLayout lay = (RelativeLayout) findViewById(R.id.activity_play);
        music = MediaPlayer.create(this, R.raw.menu_background_music);
        music.setLooping(true);
        music.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        //music.prepareAsync();
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        selected_bg = settings.getInt("SELECTEDBG", Context.MODE_PRIVATE);
        selected_t = settings.getInt("SELECTEDTABLE", Context.MODE_PRIVATE);
        selected_b = settings.getInt("SELECTEDBALL", Context.MODE_PRIVATE);
        String name = settings.getString("NAME", null);
        int difficulty = settings.getInt("DIFFICULTY", Context.MODE_PRIVATE);

        if(difficulty==0)
            percent=3;
        else if(difficulty==1)
            percent=4;
        else if(difficulty==2)
            percent=6;
        else if(difficulty==3)
            percent=8;

        final TextView user_text = (TextView) findViewById(R.id.player_text);
        final TextView ai_text = (TextView) findViewById(R.id.bob_text);
        Log.d("Name", "value: " + name);



        user_text.setText(" " + name + ": "+ String.valueOf(user_cups));
        ai_text.setText(" Bob: " + String.valueOf(ai_cups));

        switch (selected_bg) {
            case -1:
                lay.setBackgroundResource(R.drawable.default_bg1_vert);
                break;
            case 0:
                lay.setBackgroundResource(R.drawable.uta_bg1_vert);
                break;
            case 1:
                lay.setBackgroundResource(R.drawable.texas_bg1_vert);
                break;
            case 2:
                lay.setBackgroundResource(R.drawable.khalili_bg1_vert);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }

        ImageView table = (ImageView) findViewById(R.id.table);
        switch (selected_t) {
            case -1:
                table.setImageResource(R.drawable.default_table_vert);
                break;
            case 0:
                table.setImageResource(R.drawable.uta_table1_vert);
                break;
            case 1:
                table.setImageResource(R.drawable.texas_table1_vert);
                break;
            case 2:
                table.setImageResource(R.drawable.khalili_table1_vert);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }
        table.setAlpha(0.7f);

        ImageView ball = (ImageView) findViewById(R.id.ball);
        ImageView ball_fake = (ImageView) findViewById(R.id.ball_fake);
        ball_fake.setVisibility(View.INVISIBLE);
        switch (selected_b) {
            case -1:
                ball.setImageResource(R.drawable.ping_pong_ball);
                ball_fake.setImageResource(R.drawable.ping_pong_ball);
                break;
            case 0:
                ball.setImageResource(R.drawable.uta_ball2);
                ball_fake.setImageResource(R.drawable.uta_ball2);
                break;
            case 1:
                ball.setImageResource(R.drawable.texas_ball1);
                ball_fake.setImageResource(R.drawable.texas_ball1);
                break;
            case 2:
                ball.setImageResource(R.drawable.khalili_ball2);
                ball_fake.setImageResource(R.drawable.khalili_ball2);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        table.setOnClickListener(PlayAIActivity_one.this);
        table.setOnTouchListener(gestureListener);




    }

    public void onClick(View v) {
        //nothing
    }



    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                //Toast.makeText(PlayAIActivity.this, "on Fling", Toast.LENGTH_SHORT).show();

                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                    return false;
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    ImageView ball = (ImageView) findViewById(R.id.ball);
                    ImageView ball_fake = (ImageView) findViewById(R.id.ball_fake);

                    float delta_x = e2.getX()- e1.getX();
                    float delta_y = e2.getY()- e1.getY();
                    float time_up = Math.abs((e2.getY() / velocityY));

                    TranslateAnimation animation = new TranslateAnimation(0, delta_x, 0, (Math.abs(velocityY * time_up) - ball.getY()));
                    //TranslateAnimation animation = new TranslateAnimation(0, delta_x, 0, (delta_y)*(3/2)); //previous
                    //TranslateAnimation animation = new TranslateAnimation(0, delta_x, 0, Math.abs(f_dist));

                    //final ImageView cup2 = (ImageView) findViewById(R.id.cup2);

                    animation.setAnimationListener(new MyAnimationListener());
                    animation.setDuration(800);
                    animation.setFillAfter(true);

                    switch (selected_b) {
                        case -1:
                            ball.setImageResource(R.drawable.ping_pong_ball);
                            break;
                        case 0:
                            ball.setImageResource(R.drawable.uta_ball2);
                            break;
                        case 1:
                            ball.setImageResource(R.drawable.texas_ball1);
                            break;
                        case 2:
                            ball.setImageResource(R.drawable.khalili_ball2);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    ball.startAnimation(animation);


                    ball_fake.setVisibility(View.INVISIBLE);

                    final ImageView cup1 = (ImageView) findViewById(R.id.cup1);

                    final SharedPreferences settings = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
                    final TextView user_text = (TextView) findViewById(R.id.player_text);

                    if((cup1.getVisibility() == View.VISIBLE) && (((Math.abs(cup1.getX()-(ball.getX() + delta_x))<30)) && (Math.abs((cup1.getY() - Math.round(Math.abs(velocityY*time_up))))<30))) {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                cup1.setVisibility(View.INVISIBLE);
                                user_cups--;
                                String name = settings.getString("NAME", "");
                                user_text.setText( name + ": "+ String.valueOf(user_cups));
                            }
                        }, 1000);
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            public void run() {
                                UserIn();
                            }
                        }, 900);
                    }
                    else{
                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                UserOut();
                            }
                        }, 900);
                    }

                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    //Toast.makeText(PlayAIActivity.this, "Down Swipe", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                // nothing
            }
            return false;


        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }



    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Exit to main menu?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        music.stop();
                        Intent intent = new Intent(PlayAIActivity_one.this, MenuActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();
    }



    public void UserIn() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(user_cups!=0) {
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            if(AiTurn()) {
                                ai_cups--;
                                TextView ai_text = (TextView) findViewById(R.id.bob_text);
                                ai_text.setText(" Bob: " + String.valueOf(ai_cups));
                                AiIn();
                            } else {
                                AiOut();
                            }
                        }
                    }, 200);
                } else {
                    music.stop();
                    Intent won = new Intent(PlayAIActivity_one.this, WonActivity.class);
                    startActivity(won);
                }

            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.user_in);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public void UserOut() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(user_cups!=0) {
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            if (AiTurn()) {
                                ai_cups--;
                                TextView ai_text = (TextView) findViewById(R.id.bob_text);
                                ai_text.setText(" Bob: " + String.valueOf(ai_cups));
                                AiIn();
                            } else {
                                AiOut();
                            }
                        }
                    }, 200);
                } else {
                    music.stop();
                    Intent won = new Intent(PlayAIActivity_one.this, WonActivity.class);
                    startActivity(won);
                }
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.user_out);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public Boolean AiTurn() {
        r = random_number.nextInt(10);
        if(r < percent)
        {
            cups_list.remove(cups_list.get(cups_list.size() - 1));
            return true;
        }
        return false;
    }

    public void AiIn() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ImageView ball = (ImageView) findViewById(R.id.ball);
                ImageView ball_fake = (ImageView) findViewById(R.id.ball_fake);
                ball.setImageResource(R.drawable.transparent);
                ball_fake.setVisibility(View.VISIBLE);
                if(ai_cups!=0) {
                    //nothing
                } else {
                    music.stop();
                    Intent lost = new Intent(PlayAIActivity_one.this, LostActivity.class);
                    startActivity(lost);
                }
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.ai_in);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    public void AiOut() {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ImageView ball = (ImageView) findViewById(R.id.ball);
                ImageView ball_fake = (ImageView) findViewById(R.id.ball_fake);
                ball.setImageResource(R.drawable.transparent);
                ball_fake.setVisibility(View.VISIBLE);
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.ai_out);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(music.isPlaying()) {
            music.pause();
            //music.release();
        }
    }

    @Override
    protected void onResume() {
        if(music != null && !music.isPlaying())
            music.start();
        super.onResume();
    }

}
