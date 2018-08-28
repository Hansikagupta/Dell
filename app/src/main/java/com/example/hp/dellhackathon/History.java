package com.example.hp.dellhackathon;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
  //place_order.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        HashMap<String,String> m=new HashMap<String,String>();
//        m.put("Name","Laptop");
//        m.put("Price","Rs 50,000");
//        Date now=new Date();
//        Long time=now.getTime();
//        String t=Long.toString(time);
//        m.put("time",t);
//        databaseReference.child("users").child(muser).setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        if (task.isSuccessful()) {
//        Toast.makeText(ItemDes.this, "order placed", Toast.LENGTH_SHORT).show();
//        }
//        }
//        });
//        }
//        });