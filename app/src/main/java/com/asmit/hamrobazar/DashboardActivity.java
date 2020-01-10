package com.asmit.hamrobazar;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asmit.hamrobazar.api.UsersAPI;
import com.asmit.hamrobazar.model.User;
import com.asmit.hamrobazar.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    ViewFlipper vflipper;
    private RecyclerView recyclerView,recyclerViewSecond;
    Button btnlogin;
    CircleImageView imgProfileImg;
    ImageView icon;
    Dialog myDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
  menuInflater.inflate(R.menu.menu,menu);
         return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        icon=findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ShowPopup(v);
                openDialog();
            }
        });
        myDialog= new Dialog(this);


       final Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgProfileImg=findViewById(R.id.imgProfileImg);

        loadCurrentUser();

        recyclerView=findViewById(R.id.recyclerView);


        recyclerViewSecond=findViewById(R.id.recyclerViewSecond);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        Call<List<ListedAds>> listsCall=usersAPI.getListedAds();



        //asynchronous
        listsCall.enqueue(new Callback<List<ListedAds>>() {
            @Override
            public void onResponse(Call<List<ListedAds>> call, Response<List<ListedAds>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("msg","onFailure"+ response.code());

                    return;
                }


                List<ListedAds> listedAdsList = response.body();

                Listed_ads_adapter listedAdsAdapter = new Listed_ads_adapter(DashboardActivity.this,listedAdsList);
                recyclerView.setAdapter(listedAdsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this,LinearLayoutManager.HORIZONTAL,false));



            }


            @Override
            public void onFailure(Call<List<ListedAds>> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        recyclerViewSecond=findViewById(R.id.recyclerViewSecond);

        //instance for interface
        Call<List<ListedAds>> listCall=usersAPI.getListedAds();



        //asynchronous
        listCall.enqueue(new Callback<List<ListedAds>>() {
            @Override
            public void onResponse(Call<List<ListedAds>> call, Response<List<ListedAds>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("msg","onFailure"+ response.code());

                    return;
                }


                List<ListedAds> listedAdsList = response.body();

                Listed_ads_adapter listedAdsAdapter = new Listed_ads_adapter(DashboardActivity.this,listedAdsList);
                recyclerViewSecond.setAdapter(listedAdsAdapter);
                recyclerViewSecond.setLayoutManager(new LinearLayoutManager(DashboardActivity.this,LinearLayoutManager.HORIZONTAL,false));



            }


            @Override
            public void onFailure(Call<List<ListedAds>> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        int images[]={R.drawable.bike,R.drawable.furniture,R.drawable.bike,R.drawable.house,R.drawable.furniture};

        vflipper=findViewById(R.id.vflipper);



        for (int image:images)
        {
            flipperimages(image);
        }
    }

    private void loadCurrentUser() {
        UsersAPI usersAPI=Url.getInstance().create(UsersAPI.class);
        Call<User> userCall=usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath + response.body().getImage();
                Picasso.get().load(imgPath).into(imgProfileImg);
            }




            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void openDialog() {
        LoginDialog loginDialog=new LoginDialog();
        loginDialog.show(getSupportFragmentManager(),"Login");

    }


    public void flipperimages (int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        vflipper.addView(imageView);
        vflipper.setFlipInterval(4000);
        vflipper.setAutoStart(true);

        //animation
        vflipper.setInAnimation(this,android.R.anim.slide_in_left);
        vflipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
}
