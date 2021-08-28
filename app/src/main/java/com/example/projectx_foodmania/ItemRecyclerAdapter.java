package com.example.projectx_foodmania;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.MyViewModel> {

    List<FoodDataModel> list;

    public ItemRecyclerAdapter(List<FoodDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.item_row,parent,false);

        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {

        holder.itemName.setText(list.get(position).getName());
        holder.itemPrice.setText(String.valueOf(list.get(position).getPrice()));
        Glide.with(holder.itemImage.getContext()).load(list.get(position).getLink()).into(holder.itemImage);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               int i  = holder.getAdapterPosition();


                Intent intent = new Intent(holder.row.getContext(), ItemDetails.class);

                intent.putExtra("productModel",(Serializable) list.get(i) );
                holder.row.getContext().startActivity(intent);



            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder  {

        TextView itemPrice,itemName;
        CircleImageView itemImage;
        ConstraintLayout row;
        public MyViewModel(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.IdRowItemImage);
            itemPrice = itemView.findViewById(R.id.IdRowitemPrice);
            itemName = itemView.findViewById(R.id.IdRowItemName);
            row = itemView.findViewById(R.id.itemRow);




        }
    }
}
