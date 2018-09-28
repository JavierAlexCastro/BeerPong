package cse3310.beerpong;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Shop extends AppCompatActivity {
    Button btnballs;
    Button btntables;
    Button btnscene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        final Button play_btn = (Button) findViewById(R.id.button5);
        final Button interface_btn = (Button) findViewById(R.id.button6);
        final Button back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(Shop.this, MenuActivity.class);
                startActivity(about);

            }
        });

        play_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //Intent play = new Intent(MenuActivity.this, Stacks.class);
                Intent stack = new Intent(Shop.this, Stacks.class);
                //startActivity(play);
                startActivity(stack);

            }
        });

        interface_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent interf = new Intent(Shop.this, InterfaceActivity.class);
                startActivity(interf);

            }
        });

        btnballs = (Button)findViewById(R.id.shop_balls);
        btnballs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent balls = new Intent(Shop.this, Balls.class);
                startActivity(balls);
            }
        });

        btntables = (Button)findViewById(R.id.shop_tables);
        btntables.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent tables = new Intent(Shop.this, Tables.class);
                startActivity(tables);
            }
        });

        btnscene = (Button)findViewById(R.id.shop_bg);
        btnscene.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                Intent bgs = new Intent(Shop.this, Scenes.class);
                startActivity(bgs);
            }
        });
    }

}
