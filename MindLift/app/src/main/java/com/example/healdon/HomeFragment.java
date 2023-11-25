package com.example.healdon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.player.UnityPlayerActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private DatabaseReference mDatabase,mDatabase1;

    String u_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        String game=sharedPreferences1.getString("game", "");
        if(game.equals("finished")){

            SharedPreferences settings = this.getActivity().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
            settings.edit().remove("game").commit();

//            Intent i=new Intent(getActivity(), Voice_recorder.class);
//            startActivity(i);
        }


        Save_del_var obj = new Save_del_var();
        u_id = obj.u_id;
        if(u_id.equals(null)){
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
            u_id=sharedPreferences.getString("id", "");
        }



        Log.d("Lag","u_id:"+u_id);
        mDatabase = FirebaseDatabase.getInstance().getReference(); //db connectivity

        fetching_date_update();
        set_progressbar(view);

        ImageView gamesimage = (ImageView) view.findViewById(R.id.imageView6);
        ImageView musicimage = (ImageView) view.findViewById(R.id.imageView7);
        ImageView yogaimage = (ImageView) view.findViewById(R.id.imageView8);
        ImageView photoimage = (ImageView) view.findViewById(R.id.imageView9);
        ImageView dietimage = (ImageView) view.findViewById(R.id.imageView10);
        gamesimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").child(u_id).child("activity").child("game").setValue("1");
                FragmentTransaction music_fr = getFragmentManager().beginTransaction();
                music_fr.replace(R.id.main_frame, new GamesFragment());
                music_fr.commit();
            }
        });
        musicimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").child(u_id).child("activity").child("music").setValue("1");
                FragmentTransaction music_fr = getFragmentManager().beginTransaction();
                music_fr.replace(R.id.main_frame, new MusicFragment());
                music_fr.commit();
            }
        });
        yogaimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").child(u_id).child("activity").child("exercise").setValue("1");
                FragmentTransaction music_fr = getFragmentManager().beginTransaction();
                music_fr.replace(R.id.main_frame, new ExerciseFragment());
                music_fr.commit();
            }
        });
        photoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").child(u_id).child("activity").child("photo").setValue("1");
                FragmentTransaction music_fr = getFragmentManager().beginTransaction();
                music_fr.replace(R.id.main_frame, new GalleryFragment());
                music_fr.commit();
            }
        });
        dietimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").child(u_id).child("activity").child("tips").setValue("1");
                FragmentTransaction music_fr = getFragmentManager().beginTransaction();
                music_fr.replace(R.id.main_frame, new DietFragment());
                music_fr.commit();
            }
        });
        return view;
    }

    private void set_progressbar(View view) {
        final ImageView image= (ImageView) view.findViewById(R.id.imageViewheadlon);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(u_id).child("activity");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String game = (String) dataSnapshot.child("game").getValue();
                String music = (String) dataSnapshot.child("music").getValue();
                String exercise = (String) dataSnapshot.child("exercise").getValue();
                String photo = (String) dataSnapshot.child("photo").getValue();
                String tips = (String) dataSnapshot.child("tips").getValue();
                
                int total_sum= Integer.parseInt(game)+Integer.parseInt(music)+Integer.parseInt(exercise)+Integer.parseInt(photo)+Integer.parseInt(tips);

                if(total_sum==0){
                    image.setImageResource(R.drawable.prog_bar_0);
                }else if(total_sum==1){
                    image.setImageResource(R.drawable.prog_bar_1);
                }else if(total_sum==2){
                    image.setImageResource(R.drawable.prog_bar_2);
                }else if(total_sum==3){
                    image.setImageResource(R.drawable.prog_bar_3);
                }else if(total_sum==4){
                    image.setImageResource(R.drawable.prog_bar_4);
                }else if(total_sum==5){
                    image.setImageResource(R.drawable.prog_bar_5);
                }else {
                    image.setImageResource(R.drawable.prog_bar_0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

    String fetched_date;

    private void fetching_date_update() {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(u_id).child("activity").child("date");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               fetched_date= (String) dataSnapshot.getValue();


                if(fetched_date.equals(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()))){
                    //do nothing
                }else {
                    //initialize everthing to 0 & update date
                    Initialize_activity init_act_obj = new Initialize_activity();
                    mDatabase1 = FirebaseDatabase.getInstance().getReference();
                    mDatabase1.child("users").child(u_id).child("activity").setValue(init_act_obj);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
}
