package com.example.healdon;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.unity3d.player.UnityPlayerActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    TextView signout_btn,nearbydoc_btn,appointment_status_btn,u_id_txt,detection_test_txt,water_reminder,med_reminder;
    String u_id;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_more, container, false);


        signout_btn=view.findViewById(R.id.signout_txt);
        nearbydoc_btn=view.findViewById(R.id.nearbydoc_txt);
        appointment_status_btn=view.findViewById(R.id.appointment_status);
        u_id_txt=view.findViewById(R.id.u_id_txt);
        detection_test_txt=view.findViewById(R.id.detection_test_txt);
        water_reminder=view.findViewById(R.id.water_reminder_text);
        med_reminder=view.findViewById(R.id.med_reminder_text);

        water_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WaterDrinkingReminder.class);
                startActivity(i);
            }
        });

        med_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),MedicationReminder.class);
                startActivity(i);
            }
        });

        nearbydoc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Nearby_doctors.class);
                startActivity(i);
            }
        });

        Save_del_var obj=new Save_del_var();
        u_id=obj.u_id;
        u_id_txt.setText("U_id: "+obj.u_id);

        signout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_out_fun();
            }
        });

        nearbydoc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_to_nearby_doctors();
            }
        });

        appointment_status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_to_appointment_status();
            }
        });

        detection_test_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Loading...",Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("game", "finished");
                editor.commit(); //done

                start_unity();
            }
        });



        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            String[] PERMISSIONS_STORAGE = {
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };
            int REQUEST_EXTERNAL_STORAGE = 1;
            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }






        return view;
    }

    public void sign_out_fun(){
        FirebaseAuth.getInstance().signOut();

        SharedPreferences settings = this.getActivity().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        settings.edit().clear().commit();

        Intent i=new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }

    public void go_to_appointment_status(){
        Intent i=new Intent(getActivity(), appoinment_status.class);
        startActivity(i);
    }


    public void start_unity(){
        Intent i=new Intent(getActivity(), UnityPlayerActivity.class);
        i.putExtra("u_id",u_id);
        startActivity(i);
    }

    public void go_to_nearby_doctors(){
        Intent i=new Intent(getActivity(),Nearby_doctors.class);
        startActivity(i);
    }
}
