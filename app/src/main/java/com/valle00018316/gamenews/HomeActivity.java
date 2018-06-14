package com.valle00018316.gamenews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.ListG;
import com.valle00018316.gamenews.ViewM.CatVM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private String access;
    private CatVM catviewm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("logbait",MODE_PRIVATE);
        access=sharedPreferences.getString("token","");

        GameNDatabase.getDatabase(getApplicationContext());
        catviewm= ViewModelProviders.of(this).get(CatVM.class);
        catviewm.getCategories().observe(this, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(@Nullable List<Categoria> categorias) {

            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;
//
//                        switch (menuItem.getItemId()) {
//                            case R.id.item1:
//                                fragment = new Fragment1();
//                                fragmentTransaction = true;
//                                break;
//                            case R.id.item2:
//                                fragment = new Fragment2();
//                                fragmentTransaction = true;
//                                break;
//                            case R.id.item3:
//                                fragment = new Fragment3();
//                                fragmentTransaction = true;
//                                break;
//                            case R.id.item4:
//                                fragment = new Fragment4();
//                                fragmentTransaction = true;
//                                break;
//                            case R.id.item5:
//                                fragment = new Fragment5();
//                                fragmentTransaction = true;
//                                break;
//                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        Toolbar appbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }

        return super.onOptionsItemSelected(item);
    }

}
