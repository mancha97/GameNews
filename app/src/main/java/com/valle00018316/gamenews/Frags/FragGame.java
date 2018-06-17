package com.valle00018316.gamenews.Frags;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valle00018316.gamenews.Frags.Adapters.ViewPagerAdapter;
import com.valle00018316.gamenews.R;



public class FragGame extends Fragment {


    private View v;
    public RecyclerView recyclerView;
    private  TabLayout tabLayout;
    public ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    String game;


    public Context mcontext;


    public static FragGame newInstance(String game){

        Bundle arguments = new Bundle();
        arguments.putString("game", game);

        FragGame gamefrag = new FragGame();
        gamefrag.setArguments(arguments);

        return gamefrag;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getArguments().getString("game");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.frag_game, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(FragmentNoti.newInstance(2, game.toLowerCase()), "News" );
        viewPagerAdapter.addFragment(FragmentNoti.newInstance(2, game.toLowerCase()), "Players" );
        //viewPagerAdapter.addFragment("Photos", GamePicturesFragment.newInstance(game));

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    return view;

    }



}