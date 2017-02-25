package com.in.trashcashserver;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private EditText til_uid1;
    private TextView tv_trash, tv_wrappers, tv_bottle, tv_1, tv_2;
    private ImageButton ib1, ib2;
    String value;
    int count;
    int int_credits;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("TrashCash");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAppHead));

        til_uid1 = (EditText) findViewById(R.id.til_uid);
        tv_trash = (TextView) findViewById(R.id.tVtrash);
        tv_wrappers = (TextView) findViewById(R.id.tVwrappers);
        tv_bottle = (TextView) findViewById(R.id.tVbottle);
        tv_1 = (TextView) findViewById(R.id.tVno1);
        tv_2 = (TextView) findViewById(R.id.tVno2);



        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        til_uid1.setTypeface(custom_font);
        tv_trash.setTypeface(custom_font);
        tv_wrappers.setTypeface(custom_font);
        tv_bottle.setTypeface(custom_font);
        tv_1.setTypeface(custom_font);
        tv_2.setTypeface(custom_font);

        ib1 = (ImageButton) findViewById(R.id.iBno1);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = tv_1.getText().toString();
                count = Integer.parseInt(string) + 2;
                String string2 = Integer.toString(count);
                tv_1.setText(string2);
                updateCredit();

            }
        });


    }

    private void updateCredit(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        String credits = tv_1.getText().toString().trim();
        String username = til_uid1.getText().toString().toLowerCase().trim();



        DatabaseReference saveReference = reference.child("client").child("users").child(username);
        saveReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.child("credits").getValue(String.class);
                Log.d("home", "Value is: "+ Integer.parseInt(value));
                Log.d("home", "Count is: "+ count);
                int_credits = Integer.parseInt(value) + count;
                Log.d("home", "final credit is: "+ Integer.toString(int_credits));
                invokeCredit(int_credits);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void invokeCredit(int integer){
        String username = til_uid1.getText().toString().toLowerCase().trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference1 = database.getReference();
        DatabaseReference saveReference1 = reference1.child("client").child("users").child(username);

        Map<String, Object> creditMap = new HashMap<>();
        String str_integer = Integer.toString(integer);
        creditMap.put("credits", str_integer);

        Log.d("home", "invokeCredit :" + str_integer);

    }
}
