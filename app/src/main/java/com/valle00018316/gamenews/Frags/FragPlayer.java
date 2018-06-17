package com.valle00018316.gamenews.Frags;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Entidad.Player;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.Frags.Adapters.NotiRvAdapter;
import com.valle00018316.gamenews.Frags.Adapters.PlayerRvAdapter;
import com.valle00018316.gamenews.Models.NoticiaM;
import com.valle00018316.gamenews.R;
import com.valle00018316.gamenews.ViewM.NotiVM;
import com.valle00018316.gamenews.ViewM.PlayerVM;

import java.util.ArrayList;
import java.util.List;

public class FragPlayer extends Fragment {

    private PlayerVM playerVM;
    private GameNDatabase gameNDatabase;
    private View v;
    private RecyclerView recyclerView;

    public static List<Noticia> list = new ArrayList<>();

    PlayerRvAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private String game;
    private int type;
    private Context context;


    public static FragPlayer newInstance(int type, String game) {
        Bundle arguments = new Bundle();
        arguments.putInt("type", type);
        arguments.putString("game", game);

        FragPlayer newsFragment = new FragPlayer();
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

        v = inflater.inflate(R.layout.frag_players, container, false);
        recyclerView = v.findViewById(R.id.player_rv);

        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        adapter = new PlayerRvAdapter(getContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        gameNDatabase= GameNDatabase.getDatabase(getContext());



            playerVM = ViewModelProviders.of(this).get(PlayerVM.class);
            playerVM.getNBGame(game.toLowerCase()).observe(this, new Observer<List<Player>>() {
                @Override
                public void onChanged(@Nullable List<Player> p) {

                    adapter.setN(p);


                }
            });


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