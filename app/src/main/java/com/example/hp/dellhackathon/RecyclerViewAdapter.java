package com.example.hp.dellhackathon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Item> mdata;

    public RecyclerViewAdapter(Context mcontext, List<Item> mdata) {
        this.mcontext = mcontext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view=layoutInflater.inflate(R.layout.card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.name.setText(mdata.get(position).getName());
        holder.img_thumbnail.setImageResource(mdata.get(position).getThumbnail());
        holder.price.setText(mdata.get(position).getPrice());

        //Set listener for cards here
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:   Intent intent=new Intent(mcontext,ItemDes.class);
                        mcontext.startActivity(intent);
                        break;
                    case 1:Intent intent1=new Intent(mcontext,PCdes.class);
                        mcontext.startActivity(intent1);
                        break;
                    case 2:Intent intent2=new Intent(mcontext,Jukeboxdes.class);
                        mcontext.startActivity(intent2);
                        break;


                }
                //passing data to activity
                // Intent intent= new Intent(mcontext,Segregation.class);
              /*  intent.putExtra("Waste",mdata.get(position).getName());
                intent.putExtra("Type",mdata.get(position).getType());  */
                //start activity
                // mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView img_thumbnail;
        CardView cardView;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            img_thumbnail=(ImageView) itemView.findViewById(R.id.image);
            cardView=(CardView)itemView.findViewById(R.id.card);
            price=(TextView)itemView.findViewById(R.id.price);


        }
    }
}
