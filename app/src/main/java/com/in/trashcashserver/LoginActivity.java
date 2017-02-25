package com.in.trashcashserver;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button btngetloc, btnsignin;
    private TextView tvlocation;
    private EditText et_tollid, et_uid;
    private String str_loc, str_uid, str_tid;
    String  value1, value2;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        btngetloc = (Button) findViewById(R.id.button_loc);
        btnsignin = (Button) findViewById(R.id.button_signin);
        tvlocation = (TextView) findViewById(R.id.tV_loc);
        et_tollid = (EditText) findViewById(R.id.eT_Toll);
        et_uid = (EditText) findViewById(R.id.eT_uid);




        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        et_tollid.setTypeface(custom_font);
        et_uid.setTypeface(custom_font);
        btnsignin.setTypeface(custom_font);
        btngetloc.setTypeface(custom_font);
        tvlocation.setTypeface(custom_font);

        tvlocation.setText("12abc");
        str_loc = tvlocation.getText().toString();
        str_uid = et_uid.getText().toString();
        str_tid = et_tollid.getText().toString();

        database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference().child("server").child("1234");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value1 = dataSnapshot.child("location").getValue(String.class);
                Log.d("FIREBASE", "Value is: " + value1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value2 = dataSnapshot.child("uid").getValue(String.class);
                Log.d("Firebase2","Value2 is:"+value2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt();
            }
        });














    }

    public void loginAttempt()
    {
        if(true)
        {
            Log.d("IF STATEMENT", "Testing IF condition");
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } else
        {
            Log.d("ELSE", "Testing else condition");
            Toast.makeText(LoginActivity.this,"Login Failed!", Toast.LENGTH_SHORT).show();
        }

    }
}
