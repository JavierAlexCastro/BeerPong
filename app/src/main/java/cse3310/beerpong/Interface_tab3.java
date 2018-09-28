package cse3310.beerpong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Javier on 4/17/2017.
 */

public class Interface_tab3 extends Fragment {

    int selected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.interface_tab3, container, false);
        final SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final ImageView current_item = (ImageView) rootView.findViewById(R.id.bg_curr_item);
        selected = settings.getInt("SELECTEDBG", Context.MODE_PRIVATE);
        switch (selected) {
            case -1:
                current_item.setImageResource(R.drawable.default_bg1);
                break;
            case 0:
                current_item.setImageResource(R.drawable.uta_bg1);
                break;
            case 1:
                current_item.setImageResource(R.drawable.texas_bg1);
                break;
            case 2:
                current_item.setImageResource(R.drawable.khalili_bg1);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }

        final Button bg_item1_btn = (Button) rootView.findViewById(R.id.bg_item1);
        final Button bg_item2_btn = (Button) rootView.findViewById(R.id.bg_item2);
        final Button bg_item3_btn = (Button) rootView.findViewById(R.id.bg_item3);
        final ImageView uta_bg = (ImageView) rootView.findViewById(R.id.bg_image1);
        final ImageView texas_bg = (ImageView) rootView.findViewById(R.id.bg_image2);
        final ImageView khalili_bg = (ImageView) rootView.findViewById(R.id.bg_image3);

        final Button bg_accept_btn = (Button) rootView.findViewById(R.id.bg_accept_button);
        bg_accept_btn.setEnabled(false);

        final Button back_btn = (Button) rootView.findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(getActivity(), MenuActivity.class);
                startActivity(about);

            }
        });


        bg_accept_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if(selected>=0) { //when an item different than current is selected
                    Toast.makeText(getActivity().getApplicationContext(), "Item is now set as current", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();

                    switch (selected) {
                        case 0:
                            current_item.setImageResource(R.drawable.uta_bg1);
                            editor.putInt("SELECTEDBG", 0);
                            break;
                        case 1:
                            current_item.setImageResource(R.drawable.texas_bg1);
                            editor.putInt("SELECTEDBG", 1);
                            break;
                        case 2:
                            current_item.setImageResource(R.drawable.khalili_bg1);
                            editor.putInt("SELECTEDBG", 2);
                            break;
                        default:
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    editor.apply();
                    bg_accept_btn.setEnabled(false);
                    selected = -1;
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(settings.getBoolean("UTABACKGROUND", false)) { //already unlocked
            uta_bg.setVisibility(rootView.VISIBLE);
        } else{
            uta_bg.setVisibility(rootView.INVISIBLE);
            bg_item1_btn.setEnabled(false);
        }

        if(settings.getBoolean("TEXASBACKGROUND", false)) { //already unlocked
            texas_bg.setVisibility(rootView.VISIBLE);
        } else{
            texas_bg.setVisibility(rootView.INVISIBLE);
            bg_item2_btn.setEnabled(false);
        }

        if(settings.getBoolean("KHALILIBACKGROUND", false)) { //already unlocked
            khalili_bg.setVisibility(rootView.VISIBLE);
        } else{
            khalili_bg.setVisibility(rootView.INVISIBLE);
            bg_item3_btn.setEnabled(false);
        }


        bg_item1_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                bg_item1_btn.setBackgroundResource(R.drawable.selected_box);
                bg_item2_btn.setBackgroundResource(R.drawable.black_box);
                bg_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 0;
                bg_accept_btn.setEnabled(true);
            }
        });

        bg_item2_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                bg_item1_btn.setBackgroundResource(R.drawable.black_box);
                bg_item2_btn.setBackgroundResource(R.drawable.selected_box);
                bg_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 1;
                bg_accept_btn.setEnabled(true);
            }
        });

        bg_item3_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                bg_item1_btn.setBackgroundResource(R.drawable.black_box);
                bg_item2_btn.setBackgroundResource(R.drawable.black_box);
                bg_item3_btn.setBackgroundResource(R.drawable.selected_box);
                selected = 2;
                bg_accept_btn.setEnabled(true);
            }
        });

        return rootView;
    }
}
