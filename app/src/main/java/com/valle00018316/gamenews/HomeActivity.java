package com.valle00018316.gamenews;

import android.accessibilityservice.AccessibilityService;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.Frags.FragmentNoti;
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
    Toolbar toolbar;
    NavigationView navigationView;
    private CatVM catviewm;
    GameNDatabase gameNDatabase;
    private MenuItem mPreviousMenuItem;
    ScrollView scrollView;
    Toolbar appbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        navigationView= findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new FragmentNoti());
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.appsbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(navigationView.getMenu().findItem(R.id.item0).getTitle());
        navigationView.getMenu().findItem(R.id.item0).setCheckable(true);
        navigationView.getMenu().findItem(R.id.item0).setChecked(true);
        mPreviousMenuItem=navigationView.getMenu().findItem(R.id.item0);


        gameNDatabase=GameNDatabase.getDatabase(getApplicationContext());
        catviewm= ViewModelProviders.of(this).get(CatVM.class);
        catviewm.getCategories().observe(this, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(@Nullable List<Categoria> categorias) {

                navigationView.getMenu().findItem(R.id.item2).getSubMenu().clear();

                for (Categoria game : categorias){
                    navigationView.getMenu().findItem(R.id.item2).getSubMenu().add(game.getName().toUpperCase());
                }
                for(int i=0;i<navigationView.getMenu().findItem(R.id.item2).getSubMenu().size();i++){

                    String aux=navigationView.getMenu().findItem(R.id.item2).getSubMenu().getItem(i).getTitle().toString();

                    if(aux.equals("OVERWATCH")){
                        navigationView.getMenu().findItem(R.id.item2).getSubMenu().getItem(i).setIcon(R.drawable.overwatch);
                    }
                    else if(aux.equals("CSGO")){
                        navigationView.getMenu().findItem(R.id.item2).getSubMenu().getItem(i).setIcon(R.drawable.csgo0);
                    }
                    else if(aux.equals("LOL")){
                        navigationView.getMenu().findItem(R.id.item2).getSubMenu().getItem(i).setIcon(R.drawable.lol);
                    }
                    else{
                        navigationView.getMenu().findItem(R.id.item2).getSubMenu().getItem(i).setIcon(R.drawable.joystick);
                    }
                }
            }
        });








        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;
                        menuItem.setCheckable(true);
                        menuItem.setChecked(true);
                        if (mPreviousMenuItem != null && mPreviousMenuItem != menuItem) {
                            mPreviousMenuItem.setChecked(false);

                        }
                        if(mPreviousMenuItem==menuItem){
                            drawerLayout.closeDrawers();
                            return true;
                        }
                        mPreviousMenuItem = menuItem;



                        switch (menuItem.getItemId()) {
                            case R.id.item0:

                                fragment = new FragmentNoti();
                                fragmentTransaction = true;
                                break;
                            case R.id.item1:
                                fragment = new FragmentNoti();
                                fragmentTransaction = true;
                                break;
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
                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();



                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        Toolbar appbar = (Toolbar) findViewById(R.id.appsbar);
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

    public void setGameMenu(List<Categoria> listag){
    }

}
