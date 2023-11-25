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

import java.util.ArrayList;

public class doc_recieved_appointment extends AppCompatActivity {

    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String> fetched_u_id = new ArrayList<String>();
    public ArrayList<String> fetched_date = new ArrayList<String>();
    public ArrayList<String> fetched_key = new ArrayList<String>();

    Save_del_var save_obj=new Save_del_var();
    String d_id=save_obj.d_id;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_recieved_appointment);

        listView=(ListView)findViewById(R.id.listview);

        fetch_requests();
    }

    private void fetch_requests() {
        DatabaseReference myRef = database.getReference().child("doctors").child(d_id).child("pending_appointment");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            int i=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Log.d("Log","Entered snapshot for loop");
                    final String id=ds.getValue(String.class);
                    final String key=ds.getKey().toString();



                    //fetching details of patient
                    DatabaseReference myRef1 = database.getReference().child("users").child(id);
                    myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String fetched_name= (String) dataSnapshot.child("name").getValue(String.class);
                            String fetched_date_db=(String) dataSnapshot.child("appointment").child("date").getValue(String.class);
                            fetched_u_id.add(i,id);
                            fetched_key.add(i,key);
                            names.add(i,fetched_name);
                            fetched_date.add(i,fetched_date_db);


                            Log.d("Log","ids: "+names);
                            Log.d("Log","names: "+fetched_u_id);

                            customAdapter=new CustomAdapter();
                            listView.setAdapter(customAdapter);
                            i++;
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }



                //Log.d("Log","array contents: d_ids="+d_id_array);



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
            view = getLayoutInflater().inflate(R.layout.doc_custom_list_view,null);

            TextView name=(TextView)view.findViewById(R.id.name);

            //Log.d("Log","ids: "+names);
            //Log.d("Log","names: "+fetched_u_id);
            name.setText(names.get(i));

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Log","clicked d_id="+d_id_array.get(i));

                    Save_del_var save_obj=new Save_del_var();
                    save_obj.apply_appointmet_d_id=fetched_u_id.get(i);

                    Intent intent = new Intent(doc_recieved_appointment.this, doc_patient_details.class);
                    intent.putExtra("key", fetched_key.get(i));
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