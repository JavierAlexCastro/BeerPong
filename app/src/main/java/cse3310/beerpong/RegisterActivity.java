package cse3310.beerpong;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;


/**
 * Created by Javier on 3/28/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        final SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        if(settings.getBoolean("REGISTERED", false)) { //already registered
            Intent menu = new Intent(RegisterActivity.this, MenuActivity.class);
            startActivity(menu);
            finish();
        } else {
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
        }


        final EditText name_text = (EditText) findViewById(R.id.name_edit);
        final EditText age_text = (EditText) findViewById(R.id.age_edit);
        final Button reg_button = (Button) findViewById(R.id.cont_button);

        name_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        age_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        reg_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                try {
                    String name_str = name_text.getText().toString();
                    Integer age_str = Integer.parseInt(age_text.getText().toString());

                    if(!name_str.isEmpty() && !(age_text.getText().toString().isEmpty())) {
                        if (!name_str.equals("") && (age_str > 17 && age_str < 145)) {
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("NAME", name_str);
                            editor.putInt("AGE", age_str);
                            editor.commit();
                            Intent menu = new Intent(RegisterActivity.this, MenuActivity.class);
                            startActivity(menu);
                        } else {
                            Toast.makeText(getBaseContext(), "Invalid data", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show();
                    }

                }catch(NumberFormatException e) {
                    Toast.makeText(getBaseContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
