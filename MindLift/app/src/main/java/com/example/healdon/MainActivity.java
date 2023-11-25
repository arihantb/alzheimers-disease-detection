package com.example.healdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView email_text, pass_text;
    String email, password;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        email_text = findViewById(R.id.email_text);
        pass_text = findViewById(R.id.pass_text);

    }

    public void sign_in_auth(View view){

        email=email_text.getText().toString();
        password=pass_text.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this, "Fill all details!",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Log", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            fetching_uid_db_using_mail(email);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Log", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
        }
    }

    String fetched_uid;
    private void fetching_uid_db_using_mail(final String email) {
        Log.d("Log","fetching uid called");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {

                    try {
                        String fetched_email = item_snapshot.child("email").getValue().toString();
                        Log.d("Log", "fetching email=" + fetched_email);
                        if (email.equals(fetched_email)) {
                            fetched_uid = item_snapshot.child("u_id").getValue().toString();
                            Log.d("Log", "u_id from sign in page=" + fetched_uid);

                            //setting shared preff
                            SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", fetched_uid);
                            editor.commit(); //done

                            //saving u_id in static class
                            Save_del_var obj = new Save_del_var();
                            obj.u_id = fetched_uid;
                            Log.d("Log", "msg from Login u_id fetched=" + fetched_uid + " obj=" + obj.u_id);

                            updateUI();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Unable to set U_id",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void updateUI() {

        Log.d("Log","fetching uid returned");
        Intent i=new Intent(MainActivity.this, Homepage.class);
        startActivity(i);
    }

    public void signup_start(View view) {
        Intent i=new Intent(MainActivity.this, Signup.class);
        startActivity(i);
    }


    public void go_to_doc_signin(View view) {
        Intent i=new Intent(MainActivity.this, doc_sign_in.class);
        startActivity(i);
    }

    public void go_to_fam_signin(View view) {
        Intent i=new Intent(MainActivity.this, fam_sign_in.class);
        startActivity(i);
    }
}
