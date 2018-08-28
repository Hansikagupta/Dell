package com.example.hp.dellhackathon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    List<Item> list;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    RecyclerView mrecycle;
    ImageView logout,order;

    int backpress=0;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
         //drawerLayout=(DrawerLayout)findViewById(R.id.func);
         mrecycle=(RecyclerView)findViewById(R.id.item);
        //actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
       // drawerLayout.addDrawerListener(actionBarDrawerToggle);
       // actionBarDrawerToggle.syncState();
        databaseReference= FirebaseDatabase.getInstance().getReference("Product");
        firebaseAuth=FirebaseAuth.getInstance();
        name=(TextView)findViewById(R.id.username);
        order=(ImageView)findViewById(R.id.order);
        logout=(ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
       // setNavigationViewListener();
       // name.setText(firebaseAuth.getCurrentUser().getUid());

    }
//
//    private void setNavigationViewListener() {
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.logout: {
                logout();
                //do somthing
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        list=new ArrayList<>();
        list.add(new Item("Laptop","Rs 50,000",R.drawable.download,1,"rgr"));
        list.add(new Item("PC","Rs 40,000",R.drawable.pc,2,"f"));
        list.add(new Item("Jukebox","Rs 14,000",R.drawable.jukebox,1,"vf"));
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.item);
        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recyclerViewAdapter);


    }
//@Override
//public void onStart() {
//    super.onStart();
//    Log.i("TAGi","onstart "+databaseReference);
//
//    Query query = FirebaseDatabase.getInstance()
//            .getReference()
//            .child("Product");
//    FirebaseRecyclerOptions<Item> options =
//            new FirebaseRecyclerOptions.Builder<Item>()
//                    .setQuery(query, Item.class)
//                    .build();
//    firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {
//        @Override
//        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Log.i("TAGi","onCreateViewHolder"+" ");
//            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false));
//
//        }
//
//        @Override
//        protected void onBindViewHolder(@NonNull final ItemViewHolder holder, int position, @NonNull Item model) {
//            //holder.setDescription(model.getDescription());
//            Log.i("TAGi","onBindViewHolder"+" ");
//            final String uid = getRef(position).getKey();
//            final String[] name = new String[1];
//            final String[] image = new String[1];
//            final String[] price=new String[1];
//            Log.i("TAGi",uid);
//
//            databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot documentSnapshot) {
//
//                    name[0] = uid;
//                    image[0] = documentSnapshot.child("Image").getValue().toString();
//                    price[0]=documentSnapshot.child("Price").getValue().toString();
//
////                        if (documentSnapshot.hasChild("online")) {
////                            String userOnline =  documentSnapshot.child("online").getValue().toString();
////                            holder.setUserOnline(userOnline);
////                        }
//                    holder.bind(name[0], image[0],price[0]);
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    Intent intent = new Intent(HomePage.this, ItemDes.class);
//                    intent.putExtra("name", uid);
//                    intent.putExtra("image", image[0]);
//                    startActivity(intent);
//
//
//                }
//            });
//        }
//    };
//    mrecycle.setAdapter(firebaseRecyclerAdapter);
//    firebaseRecyclerAdapter.startListening();
//
//
//}
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        View mView;
//
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//
//            mView = itemView;
//        }
////
////        public void setDate(String date) {
////
////            TextView userstatusTextView = mView.findViewById(R.id.user_single_status);
////            userstatusTextView.setText(date);
////
////        }
//
//        public void bind(String name, final String image,String price1) {
//            TextView itemNameTextView = mView.findViewById(R.id.name);
//            itemNameTextView.setText(name);
//            TextView price=mView.findViewById(R.id.price);
//            price.setText(price1);
//            final ImageView imageView=mView.findViewById(R.id.image);
////            if (!image.equals("default"))
////                Picasso.get()
////                        .load(image)
////                        .into(imageView, new Callback() {
////                            @Override
////                            public void onSuccess() {
////
////                            }
////
////                            @Override
////                            public void onError(Exception e) {
////                                if (!image.equals("default"))
////                                    Picasso.get()
////                                            .load(image)
////                                            .into(imageView);
////                            }
////                        });
//
//
//
//        }
//
//
//   }

    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            super.onBackPressed();
        }
    }

    public void logout()
    {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HomePage.this,Login.class));
    }


}
