package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MOCA_9 extends AppCompatActivity  implements LocationListener {

    private TextView textView;
    private LocationManager locationManager;
    private double longtitude;
    private double latitude;
    String corr_city, corr_country, corr_date, corr_day, corr_month, corr_year;
    TextView date, month, year, day, country, city;
    private DatabaseReference mDatabase;
    String u_id;
    String jump="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_c_a_9);
        date = findViewById(R.id.ans1);
        month = findViewById(R.id.ans2);
        year = findViewById(R.id.ans3);
        day = findViewById(R.id.ans4);
        country = findViewById(R.id.ans5);
        city = findViewById(R.id.ans6);

        Save_del_var obj = new Save_del_var();
        u_id = obj.u_id;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MOCA_9.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)){
//                ActivityCompat.requestPermissions(MOCA_9.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            }else{
//                ActivityCompat.requestPermissions(MOCA_9.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MOCA_9.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(MOCA_9.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(MOCA_9.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }

        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        if(location!=null) {
            Log.d("Log", "location=" + location);
            onLocationChanged(location);
            loc_func(location);
        }

        corr_date=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        corr_month=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        corr_year=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        corr_day= new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date());
        Log.d("Log","date="+corr_date+" "+corr_month+" "+corr_year+" day="+corr_day);
        return;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MOCA_9.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location){
        longtitude  = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public  void onStatusChanged(String s, int i, Bundle bundle){

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void loc_func(Location location){
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses =null ;
            addresses=geocoder.getFromLocation(latitude,longtitude,1);

            //   String actual_country = addresses.get(0).getCountryName();
            corr_city = addresses.get(0).getLocality();
            corr_country= addresses.get(0).getCountryName();



            Log.d("tag","city="+corr_city+" country="+corr_country);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();
        }
    }

    MOCA_Score mark_obj=new MOCA_Score();
    public void Finish_exam(View view) {
        int total_sum=0;

        try{
            if(corr_date.equals(date.getText().toString())){
                total_sum++;
            }
            if(corr_month.equals(month.getText().toString())){
                total_sum++;
            }
            if(corr_year.equals(year.getText().toString())){
                total_sum++;
            }
            if(corr_day.equals(day.getText().toString())){
                total_sum++;
            }
            if(corr_city.equals(city.getText().toString())){
                total_sum++;
            }
            if(corr_country.equals(country.getText().toString())){
                total_sum++;
            }

        }catch (Exception e){

        }
        Log.d("Log","total_sum="+total_sum);
        mark_obj.que8=total_sum;

        calculate_total_MOCA();
        go_to_final_score();
    }



    private void calculate_total_MOCA() {
        //TODO: if user is <= 12yr Education >> +1 to total_marks
        mark_obj.total_marks=mark_obj.que1+mark_obj.que2+mark_obj.que3+mark_obj.que4+mark_obj.que5+mark_obj.que6+mark_obj.que7+mark_obj.que8;
        if(mark_obj.total_marks>=18){
            mark_obj.result="pass";
        }else{
            mark_obj.result="fail";
        }

        push_data_to_db();
    }

    private void push_data_to_db() {

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        u_id=sharedPreferences.getString("id", "");


        MOCA_Score2 score_obj=new MOCA_Score2(mark_obj.que1,mark_obj.que2,mark_obj.que3,mark_obj.que4,mark_obj.que5,mark_obj.que6,mark_obj.que7,mark_obj.que8,mark_obj.total_marks,mark_obj.result);





        //push to db
        mDatabase = FirebaseDatabase.getInstance().getReference(); //db connectivity

        mDatabase.child("users").child(u_id).child("test").child("moca_score").setValue(score_obj);


        //TODO: after pushing data calculate final prediction using voting
        voating();
    }

    private void voating() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(u_id).child("test");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String game = (String) dataSnapshot.child("game_score").child("Model_Prediction").getValue();
                String moca = mark_obj.result;
                String voice = (String) dataSnapshot.child("voice").child("model_prediction").getValue();

                int count = 0;
                if (game.equals("1")) {
                    count++;
                }
                if (moca.equals("fail")) {
                    count++;
                }
                if (voice.equals("1")) {
                    count++;
                }

                Save_del_var save_obj=new Save_del_var();
                save_obj.moca_score=moca;
                save_obj.voice_pred=voice;
                save_obj.game_pred=game;

                Log.d("Log","moca="+save_obj.moca_score);
                Log.d("Log","voice="+save_obj.voice_pred);
                Log.d("Log","game_pred="+save_obj.game_pred);


                if(count>=2){
                    save_obj.final_pred="Positive";
                }else{
                    save_obj.final_pred="Negative";
                }

                Log.d("Log","final_pred="+save_obj.final_pred);
                jump="1";

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    private void go_to_final_score() {
        if(jump.equals("1")) {
            Intent i = new Intent(MOCA_9.this, Final_Score.class);
            startActivity(i);
        }
    }
}
