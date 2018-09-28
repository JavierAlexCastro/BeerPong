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

public class Interface_tab2 extends Fragment {

    int selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.interface_tab2, container, false);
        final SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        final ImageView current_item = (ImageView) rootView.findViewById(R.id.t_curr_item);
        selected = settings.getInt("SELECTEDTABLE", Context.MODE_PRIVATE);
        switch (selected) {
            case -1:
                current_item.setImageResource(R.drawable.default_table);
                break;
            case 0:
                current_item.setImageResource(R.drawable.uta_table1);
                break;
            case 1:
                current_item.setImageResource(R.drawable.texas_table1);
                break;
            case 2:
                current_item.setImageResource(R.drawable.khalili_table1);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                break;
        }

        final Button t_item1_btn = (Button) rootView.findViewById(R.id.t_item1);
        final Button t_item2_btn = (Button) rootView.findViewById(R.id.t_item2);
        final Button t_item3_btn = (Button) rootView.findViewById(R.id.t_item3);
        final ImageView uta_t = (ImageView) rootView.findViewById(R.id.t_image1);
        final ImageView texas_t = (ImageView) rootView.findViewById(R.id.t_image2);
        final ImageView khalili_t = (ImageView) rootView.findViewById(R.id.t_image3);


        final Button t_accept_btn = (Button) rootView.findViewById(R.id.t_accept_button);
        t_accept_btn.setEnabled(false);

        final Button back_btn = (Button) rootView.findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(getActivity(), MenuActivity.class);
                startActivity(about);

            }
        });


        t_accept_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if(selected>=0) { //when an item different than current is selected
                    Toast.makeText(getActivity().getApplicationContext(), "Item is now set as current", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();

                    switch (selected) {
                        case 0:
                            current_item.setImageResource(R.drawable.uta_table1);
                            editor.putInt("SELECTEDTABLE", 0);
                            break;
                        case 1:
                            current_item.setImageResource(R.drawable.texas_table1);
                            editor.putInt("SELECTEDTABLE", 1);
                            break;
                        case 2:
                            current_item.setImageResource(R.drawable.khalili_table1);
                            editor.putInt("SELECTEDTABLE", 2);
                            break;
                        default:
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    editor.apply();
                    t_accept_btn.setEnabled(false);
                    selected = -1;
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                }

            }
        });


        if(settings.getBoolean("UTATABLE", false)) { //already unlocked
            uta_t.setVisibility(rootView.VISIBLE);
        } else{
            uta_t.setVisibility(rootView.INVISIBLE);
            t_item1_btn.setEnabled(false);
        }

        if(settings.getBoolean("TEXASTABLE", false)) { //already unlocked
            texas_t.setVisibility(rootView.VISIBLE);
        } else{
            texas_t.setVisibility(rootView.INVISIBLE);
            t_item2_btn.setEnabled(false);
        }

        if(settings.getBoolean("KHALILITABLE", false)) { //already unlocked
            khalili_t.setVisibility(rootView.VISIBLE);
        } else{
            khalili_t.setVisibility(rootView.INVISIBLE);
            t_item3_btn.setEnabled(false);
        }

        t_item1_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                t_item1_btn.setBackgroundResource(R.drawable.selected_box);
                t_item2_btn.setBackgroundResource(R.drawable.black_box);
                t_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 0;
                t_accept_btn.setEnabled(true);
            }
        });

        t_item2_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                t_item1_btn.setBackgroundResource(R.drawable.black_box);
                t_item2_btn.setBackgroundResource(R.drawable.selected_box);
                t_item3_btn.setBackgroundResource(R.drawable.black_box);
                selected = 1;
                t_accept_btn.setEnabled(true);
            }
        });

        t_item3_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                t_item1_btn.setBackgroundResource(R.drawable.black_box);
                t_item2_btn.setBackgroundResource(R.drawable.black_box);
                t_item3_btn.setBackgroundResource(R.drawable.selected_box);
                selected = 2;
                t_accept_btn.setEnabled(true);
            }
        });

        return rootView;
    }
}
