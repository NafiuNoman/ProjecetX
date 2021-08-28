package com.example.projectx_foodmania;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MySliderViewHolder> {

    int [] images;

    public SliderAdapter(int[] images) {
        this.images = images;
    }


    @Override
    public MySliderViewHolder onCreateViewHolder(ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sliderlayout,parent,false);

        MySliderViewHolder myViewHolder = new MySliderViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MySliderViewHolder viewHolder, int position) {

        Glide.with(viewHolder.itemView).load(images[position]).into(viewHolder.imageView);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class MySliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public MySliderViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IdItemImageView);
        }
    }
}
