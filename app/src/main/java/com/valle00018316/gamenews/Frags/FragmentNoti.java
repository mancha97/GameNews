package com.valle00018316.gamenews.Frags;


import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.Frags.Adapters.NotiRvAdapter;
import com.valle00018316.gamenews.Models.NoticiaM;
import com.valle00018316.gamenews.R;

import com.valle00018316.gamenews.ViewM.NotiVM;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoti extends Fragment {

    private NotiVM notiviewm;
    private  GameNDatabase gameNDatabase;
    private View v;
    private RecyclerView recyclerView;

    public static List<Noticia> list = new ArrayList<>();
    public static List<NoticiaM> filteredContactList = new ArrayList<>();
    public static List<NoticiaM> aux = new ArrayList<>();
    NotiRvAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private String game;
    private int type;
    private Context context;



//    public static

    public static FragmentNoti newInstance(int type, String game) {
        Bundle arguments = new Bundle();
        arguments.putInt("type", type);
        arguments.putString("game", game);

        FragmentNoti newsFragment = new FragmentNoti();
        newsFragment.setArguments(arguments);
        return newsFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        type = getArguments().getInt("type");
        game = getArguments().getString("game");
        context = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_allnoti, container, false);
        recyclerView = v.findViewById(R.id.noticias_rv);

        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        adapter = new NotiRvAdapter(getContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        gameNDatabase= GameNDatabase.getDatabase(getContext());

        if(game!=""){
            Log.d("no firl", "FILTRADO");
            Log.d("no firl", game);
            notiviewm = ViewModelProviders.of(this).get(NotiVM.class);
            notiviewm.getNBGame(game).observe(this, new Observer<List<Noticia>>() {
                @Override
                public void onChanged(@Nullable List<Noticia> noticias) {
                    adapter.setN(noticias);
                    for (Noticia g : noticias) {
                        Log.d("VACIL", g.getTitle());
                    }

                }
            });
        }else {
            notiviewm = ViewModelProviders.of(this).get(NotiVM.class);
            notiviewm.getAllNoti().observe(this, new Observer<List<Noticia>>() {
                @Override
                public void onChanged(@Nullable List<Noticia> noticias) {
                    adapter.setN(noticias);
                    for (Noticia g : noticias) {

                    }

                }
            });
        }

        return v;


    }

//    private List<NoticiaM> getNoti() {
//        if (list.size()==0 && c==1){
//            list = new ArrayList<>();
//
//
//            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
////            requestPermission();
//            } else {
//
//                Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
//
//                while (cursor.moveToNext()) {
//
//                    list.add(new ModelContact(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
//                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),false));
//
//                }
//
//                cursor.close();
//            }
//        }else{}
//
//
//        return list;
//    }

//    public List<ModelContact> filter( String query){
//        query = query.toLowerCase();
//
//        if (c==1){
//            aux=list;
//        }
//        c=0;
//        filteredContactList= new ArrayList<>();
//        for ( ModelContact model:aux){
//            final String text = model.getName().toLowerCase();
//            if (text.startsWith(query)){
//                filteredContactList.add(model);
//                Log.d(" ViewPagerAdapter", "Agregando un modelo a la lista: " + text);
//            }
//        }
//        return filteredContactList;
//    }

//    public static void updatelist(){
//
//        list = filteredContactList;
//
//    }
//
//    public static void allist(){
//        list=aux;
//        c=1;
//    }

//    public void requestPermission() {
//        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, request);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        if (requestCode == request && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            ContactRvAdapter adapter = new ContactRvAdapter(getContext(), getContacts());
//            recyclerView.setAdapter(adapter);
//        } else {
//            requestPermission();
//        }
//
//    }
}