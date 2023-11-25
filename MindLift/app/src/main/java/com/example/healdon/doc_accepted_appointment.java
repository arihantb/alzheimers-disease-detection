package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class doc_accepted_appointment extends AppCompatActivity {

    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String> dates = new ArrayList<String>();
    public ArrayList<String> times = new ArrayList<String>();

    Save_del_var save_obj=new Save_del_var();
    String d_id=save_obj.d_id;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_accepted_appointment);

        listView=(ListView)findViewById(R.id.listview);

        fetch_appointments();
    }

    private void fetch_appointments() {
        DatabaseReference myRef = database.getReference().child("doctors").child(d_id).child("accepted_appoinment");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int i=0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Log.d("Log","Entered snapshot for loop");
                    final String id=ds.getValue(String.class);


                    //fetching details of patient
                    DatabaseReference myRef1 = database.getReference().child("users").child(id);
                    myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String fetched_name= (String) dataSnapshot.child("name").getValue(String.class);
                            String fetched_date=(String) dataSnapshot.child("appointment").child("app_date").getValue(String.class);
                            String fetched_time=(String) dataSnapshot.child("appointment").child("app_time").getValue(String.class);
                            //fetched_u_id.add(i,id);
                            //fetched_key.add(i,key);
                            names.add(i,fetched_name);
                            dates.add(i,fetched_date);
                            times.add(i,fetched_time);



                            Log.d("Log","ids: "+names);


                            customAdapter= new CustomAdapter();
                            listView.setAdapter(customAdapter);
                            i++;
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
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
            view = getLayoutInflater().inflate(R.layout.doc_custom_list_view_accetp,null);

            TextView name=(TextView)view.findViewById(R.id.name_txt);
            TextView date=(TextView)view.findViewById(R.id.date_txt);
            TextView time=(TextView)view.findViewById(R.id.time_txt);

            //Log.d("Log","ids: "+names);
            //Log.d("Log","names: "+fetched_u_id);
            name.setText(names.get(i));
            date.setText("Date:"+dates.get(i));
            time.setText("Time:"+times.get(i));

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Log","clicked d_id="+d_id_array.get(i));

                    //Save_del_var save_obj=new Save_del_var();
                    //save_obj.apply_appointmet_d_id=fetched_u_id.get(i);

                    ///Intent intent = new Intent(doc_recieved_appointment.this, doc_patient_details.class);
                    //intent.putExtra("key", fetched_key.get(i));
                    //startActivity(intent);
                }
            });
            //to sort in reverse order
//            name.setText(names.get(names.size()-1-i));
//            amount.setText(amounts.get(names.size()-1-i));

            return view;
        }
    }
}