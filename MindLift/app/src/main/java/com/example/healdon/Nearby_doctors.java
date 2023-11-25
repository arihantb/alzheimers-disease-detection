package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Nearby_doctors extends AppCompatActivity {

    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String> d_id_array = new ArrayList<String>();

    Save_del_var save_obj=new Save_del_var();
    String u_id=save_obj.u_id;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_doctors);

        Log.d("Log","next line fetch doc");
        fetch_doctors();
        Log.d("Log","returned from fetch doc");

        listView=(ListView)findViewById(R.id.listView);
    }

    private void fetch_doctors() {
        DatabaseReference myRef = database.getReference().child("doctors");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int i=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Log.d("Log","Entered snapshot for loop");
                    String fetched_names=ds.child("name").getValue(String.class);
                    String fetched_key=ds.getKey();


                    names.add(i,fetched_names);
                    d_id_array.add(i,fetched_key);
                    i++;
                }
                Log.d("Log","array contents: names="+names);
                Log.d("Log","array contents: d_ids="+d_id_array);


                customAdapter=new CustomAdapter();
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.size();  //no of entries
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_list_view,null);

            TextView name=(TextView)view.findViewById(R.id.name);
            ImageView banner=view.findViewById(R.id.banner);

//            for(String a:names){         //can be used to verify contents of array list
//                Log.w("aaa","name_new:"+a);
//            }
//
//            for(String a:amounts){
//                Log.w("aaa","amount:"+a);
//            }
//
//            for(String a:descriptions){
//                Log.w("aaa","description:"+a);
//            }
            name.setText(names.get(i));

            banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Log","clicked d_id="+d_id_array.get(i));

                    Save_del_var save_obj=new Save_del_var();
                    save_obj.apply_appointmet_d_id=d_id_array.get(i);

                    Intent intent = new Intent(Nearby_doctors.this, User_request_appoinment.class);
                    startActivity(intent);
                }
            });
            //to sort in reverse order
//            name.setText(names.get(names.size()-1-i));
//            amount.setText(amounts.get(names.size()-1-i));

            return view;
        }
    }
}