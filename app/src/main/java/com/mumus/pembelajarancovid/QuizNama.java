package com.mumus.pembelajarancovid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizNama extends AppCompatActivity {


    public static ArrayList<Modelclass> list;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiznama);

        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Question");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Modelclass modelclass = dataSnapshot.getValue(Modelclass.class);
                    list.add(modelclass);
                }

                Intent intent = new Intent(QuizNama.this, com.mumus.pembelajarancovid.QuizPertanyaan.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button startbutton=(Button)findViewById(R.id.button);
        Button aboutbutton=(Button)findViewById(R.id.button2);
        final EditText nametext=(EditText)findViewById(R.id.editName);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nametext.getText().toString();
                Intent intent=new Intent(getApplicationContext(),QuizPertanyaan.class);
                intent.putExtra("myname",name);

                startActivity(intent);
            }

        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        }, 1500);

    }

}
