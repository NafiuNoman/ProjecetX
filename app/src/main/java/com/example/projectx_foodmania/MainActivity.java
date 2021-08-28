package com.example.projectx_foodmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    SliderAdapter sliderAdapter;
    ItemRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;

    private FirebaseAuth mAuth;

    Toolbar toolbar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

     Retrofit  retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.IdDrawer);
        toolbar = findViewById(R.id.IdToolBar);

        recyclerView = findViewById(R.id.IdRecycler);






            sliderView = findViewById(R.id.IdImageSlider);

            sliderAdapter = new SliderAdapter(SliderImages.getSliderImages());

            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
            sliderView.startAutoCycle();


        drawerWork();

//        https://run.mocky.io/v3/ebf65eda-8f5a-46c6-b714-4d0131f87e10

            retrofit = new Retrofit.Builder().baseUrl(" https://run.mocky.io/v3/").addConverterFactory(GsonConverterFactory.create()).build();

            MyAPi myAPi = retrofit.create(MyAPi.class);

             Call<List<FoodDataModel>> call = myAPi.getData();

             call.enqueue(new Callback<List<FoodDataModel>>() {

                 @Override
                 public void onResponse(Call<List<FoodDataModel>> call, Response<List<FoodDataModel>> response) {

                     Log.d( "onResponse: ","Code...:"+response.code() );


                     if (response.isSuccessful())
                     {
                         List<FoodDataModel> list =  response.body();

                         for(FoodDataModel foodDataModel : list)
                         {
                             Log.d("onResponse: ",""+foodDataModel.getName()+"..."+foodDataModel.getPrice()+"..."+foodDataModel.getLink()+"\n");
                         }


                         recyclerAdapter = new ItemRecyclerAdapter(list);

                         recyclerView.setAdapter(recyclerAdapter);


                     }



                 }

                 @Override
                 public void onFailure(Call<List<FoodDataModel>> call, Throwable t) {

                 }
             });



    }

    private void drawerWork() {



        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.IdNavigation);

        View headerVIew = navigationView.getHeaderView(0);

        TextView navName = headerVIew.findViewById(R.id.IdNavName);
        TextView navEmail = headerVIew.findViewById(R.id.IdNavEmailId);

        String name = getIntent().getExtras().getString("username");
//        Toast.makeText(this, "from main::"+name, Toast.LENGTH_SHORT).show();
        String email = getIntent().getExtras().getString("useremail");

        //        navEmail.setText(email);
        navName.setText(name);
        navEmail.setText(email);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.History:
                        Toast.makeText(MainActivity.this, "History will be here", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Favourite:
                        Toast.makeText(MainActivity.this, "Favourite will be here", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Location:
                        Toast.makeText(MainActivity.this, "Map will be here", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Offer:
                        Toast.makeText(MainActivity.this, "All offer will be here", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout:

                        mAuth.signOut();
                        finish();

                        Intent intent1 = new Intent(MainActivity.this, LogIn.class);
                        startActivity(intent1);

                        Toast.makeText(MainActivity.this, "logOut", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        Toast.makeText(MainActivity.this, "okokokokok", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });





    }


}