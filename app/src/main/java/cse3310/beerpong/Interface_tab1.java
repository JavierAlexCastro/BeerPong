package cse3310.beerpong;

/**
 * Created by Javier on 4/17/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Interface_tab1 extends Fragment {

    int selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.interface_tab1, container, false);
        final SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final ImageView current_item = (ImageView) rootView.findViewById(R.id.b_curr_item);
        selected = settings.getInt("SELECTEDBALL", Context.MODE_PRIVATE);
        switch (selected) {
            case -1:
                current_item.setImageResource(R.drawable.ping_pong_ball);
                break;
            case 0:
                current_item.setImageResource(R.drawable.uta_ball2);
                break;
            case 1:
                current_item.setImageResource(R.drawable.texas_ball1);
                break;
            case 2:
                current_item.setImageResource(R.drawable.khalili_ball2);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }

        final Button b_item1_btn = (Button) rootView.findViewById(R.id.b_item1);
        final Button b_item2_btn = (Button) rootView.findViewById(R.id.b_item2);
        final Button b_item3_btn = (Button) rootView.findViewById(R.id.b_item3);
        final ImageView uta_b = (ImageView) rootView.findViewById(R.id.b_image1);
        final ImageView texas_b = (ImageView) rootView.findViewById(R.id.b_image2);
        final ImageView khalili_b = (ImageView) rootView.findViewById(R.id.b_image3);
        final Button back_btn = (Button) rootView.findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(getActivity(), MenuActivity.class);
                startActivity(about);

            }
        });

        final Button b_accept_btn = (Button) rootView.findViewById(R.id.b_accept_button);
        b_accept_btn.setEnabled(false);


        b_accept_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if(selected>=0) { //when an item different than current is selected
                    Toast.makeText(getActivity().getApplicationContext(), "Item is now set as current", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();

                    switch (selected) {
                        case 0:
                            current_item.setImageResource(R.drawable.uta_ball2);
                            editor.putInt("SELECTEDBALL", 0);
                            break;
                        case 1:
                            current_item.setImageResource(R.drawable.texas_ball1);
                            editor.putInt("SELECTEDBALL", 1);
                            break;
                        case 2:
                            current_item.setImageResource(R.drawable.khalili_ball2);
                            editor.putInt("SELECTEDBALL", 2);
                            break;
                        default:
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    editor.apply();
                    b_accept_btn.setEnabled(false);
                    selected = -1;
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(settings.getBoolean("UTABALL", false)) { //already unlocked
            uta_b.setVisibility(rootView.VISIBLE);
        } else{
            uta_b.setVisibility(rootView.INVISIBLE);
            b_item1_btn.setEnabled(false);
        }

        if(settings.getBoolean("TEXASBALL", false)) { //already unlocked
            texas_b.setVisibility(rootView.VISIBLE);
        } else{
            texas_b.setVisibility(rootView.INVISIBLE);
            b_item2_btn.setEnabled(false);
        }

        if(settings.getBoolean("KHALILIBALL", false)) { //already unlocked
            khalili_b.setVisibility(rootView.VISIBLE);
        } else{
            khalili_b.setVisibility(rootView.INVISIBLE);
            b_item3_btn.setEnabled(false);
        }

        b_item1_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                b_item1_btn.setBackgroundResource(R.drawable.selected_box);
                b_item2_btn.setBackgroundResource(R.drawable.black_box);
                b_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 0;
                b_accept_btn.setEnabled(true);

            }
        });

        b_item2_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                b_item1_btn.setBackgroundResource(R.drawable.black_box);
                b_item2_btn.setBackgroundResource(R.drawable.selected_box);
                b_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 1;
                b_accept_btn.setEnabled(true);
            }
        });

        b_item3_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                b_item1_btn.setBackgroundResource(R.drawable.black_box);
                b_item2_btn.setBackgroundResource(R.drawable.black_box);
                b_item3_btn.setBackgroundResource(R.drawable.selected_box);
                selected = 2;
                b_accept_btn.setEnabled(true);
            }
        });

        return rootView;
    }
}
