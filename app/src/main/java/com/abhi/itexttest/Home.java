package com.abhi.itexttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Toolbar toolbar;
    EditText p1et,p2et,p3et,p4et;
    int p1q,p2q,p3q,p4q;
    Button create_bill;
    Button updaterate;
    EditText customername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        p1et = findViewById(R.id.p1_et);
        p2et = findViewById(R.id.p2_et);
        p3et = findViewById(R.id.p3_et);
        p4et = findViewById(R.id.p4_et);
        customername = findViewById(R.id.customername_et);


        create_bill = findViewById(R.id.create_bill);
        updaterate = findViewById(R.id.updaterate_btn);

        updaterate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,RateUpdate.class);
                startActivity(intent);

            }
        });



        create_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(p1et.getText().toString().isEmpty())
                    p1q =  0;
                else
                    p1q = Integer.valueOf(p1et.getText().toString());

                if(p2et.getText().toString().isEmpty())
                    p2q =  0;
                else
                    p2q = Integer.valueOf(p2et.getText().toString());

                if(p3et.getText().toString().isEmpty())
                    p3q =  0;
                else
                    p3q = Integer.valueOf(p3et.getText().toString());

                if(p4et.getText().toString().isEmpty())
                    p4q =  0;
                else
                    p4q = Integer.valueOf(p4et.getText().toString());



                if((p1q==0 && p2q==0) && (p3q ==0 && p4q ==0))
                {
                    Toast.makeText(getApplicationContext(), "Fill Any of the field", Toast.LENGTH_SHORT).show();
                }
              else
              {
                  Intent intent = new Intent(Home.this,MainActivity.class);
                  intent.putExtra("p1q" , p1q);
                  intent.putExtra("p2q" , p2q);
                  intent.putExtra("p3q" , p3q);
                  intent.putExtra("p4q" , p4q);
                  intent.putExtra("c_name",customername.getText().toString());
                  startActivity(intent);

              }
            }
        });


    }
}