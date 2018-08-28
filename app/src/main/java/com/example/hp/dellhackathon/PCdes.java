package com.example.hp.dellhackathon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by hp on 28-Aug-18.
 */

public class PCdes extends AppCompatActivity {

    ImageView image;
    TextView name,price,description;
    EditText city;
    Button eta,place_order;
    RadioButton btn;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    String tp;
    RelativeLayout layout;
    int ti;
    String muser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_des);
        image=(ImageView)findViewById(R.id.des_image);
        name=(TextView)findViewById(R.id.des_name);
        layout=(RelativeLayout)findViewById(R.id.item_des);
        price=(TextView)findViewById(R.id.des_price);
        description=(TextView)findViewById(R.id.des_des);
        city=(EditText)findViewById(R.id.city_name);
        eta=(Button)findViewById(R.id.des_btn);
        btn=(RadioButton)findViewById(R.id.del_type);
        place_order=(Button)findViewById(R.id.place_order);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        mauth=FirebaseAuth.getInstance();
        muser= mauth.getCurrentUser().getUid();
        name.setText("PC");
        price.setText("Rs 40,000");
        description.setText("2.64 GHz Intel Pentium J3710 processor Intel HD Shared Graphics 19.5-inch HD+ (1600 x 900) Anti-Glare LED-Backlit " +
                "Display McAfee Multi Device 15 month subscription");
        image.setImageResource(R.drawable.pc);
        eta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ccity=city.getText().toString();

                databaseReference.child("City").child(ccity).child("time").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(btn.isChecked())
                        {
                            tp=dataSnapshot.child("Fast").getValue().toString();
                        }
                        else
                            tp=String.valueOf(dataSnapshot.child("Normal").getValue());
                        String[]p=tp.split("\\s");
                        ti=(Integer.valueOf(p[0])+7);
                        int time=(int)Math.ceil((double)ti/24);
                        Log.i("TAGi",""+tp+ti+" "+time+" "+p[0]);
                        Snackbar snackBar = Snackbar.make(layout
                                , "Your item will be delivered in next "+time+" working days", Snackbar.LENGTH_SHORT);
                        //Toast.makeText(ItemDes.this,"Your item will be delivered in next "+time+" working days",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> m=new HashMap<String,String>();
                m.put("Name","PC");
                m.put("Price","Rs 40,000");
                Date now=new Date();
                Long time=now.getTime();
                String t=Long.toString(time);
                m.put("time",t);
                Log.i("TAGi",""+muser);
                databaseReference.child("users").child(muser).setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PCdes.this, "order placed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
