package com.example.projectx_foodmania;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemDetails extends AppCompatActivity {

    ImageView productImage;
    TextView productPrice,productName,productDescription;
    FoodDataModel modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);




        productDescription = findViewById(R.id.IdProductDescription);
        productName = findViewById(R.id.IdProductName);
        productPrice = findViewById(R.id.IdProductDetailsPrice);
        productImage = findViewById(R.id.IdProductImage);


        modelClass = (FoodDataModel) getIntent().getSerializableExtra("productModel");

        productDescription.setText(modelClass.getDescription());
        productName.setText(modelClass.getName());
        productPrice.setText(String.valueOf(modelClass.getPrice()));
        Glide.with(productImage.getContext()).load(modelClass.getLink()).into(productImage);


    }

    public void AddToCart(View view) {

//        Intent intent = new Intent(view.getContext(), CartAdd.class);
//        Log.d("AddToCart: ","working111111");
//
//
//        intent.putExtra("image",modelClass.getPicUrl());
//        intent.putExtra("name",modelClass.getProductName());
//        intent.putExtra("price",modelClass.getPrice());
//
//
//        Log.d("AddToCart: ","working001");
//        startActivity(intent);
//
//        Log.d("AddToCart: ","working");


    }
}