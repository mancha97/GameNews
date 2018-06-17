package com.valle00018316.gamenews.Frags.Adapters;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Models.NoticiaM;

import com.valle00018316.gamenews.R;

import java.util.List;

public class NotiRvAdapter extends RecyclerView.Adapter<NotiRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    public List<Noticia> mListaNoticias;

    Dialog mDialog;

    public NotiRvAdapter(Context context) {
        mcontext = context;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_allnoti, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mDialog = new Dialog(mcontext);
        mDialog.setContentView(R.layout.fullnew);
        return viewHolder;
    }

    public void setN(List<Noticia> h){
        mListaNoticias = h;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Picasso.with(mcontext)
                .load(mListaNoticias.get(position).getCoverImage())
                .error(R.drawable.picture)
                .into(holder.coverImage);

//
//  avatar, coverImage,favorito;

        holder.title.setText(mListaNoticias.get(position).getTitle());
        holder.date.setText(mListaNoticias.get(position).getGame());
        holder.description.setText(mListaNoticias.get(position).getDescription());

        int daux =mListaNoticias.get(position).getIsFav();
        String aux=mListaNoticias.get(position).getGame().toUpperCase();
        boolean h=false;
        if(daux==1){
            h=true;
        }
        if(aux.equals("OVERWATCH")){
            holder.avatar.setImageResource(R.drawable.overwatch);
        }
        else if(aux.equals("CSGO")){

            holder.avatar.setImageResource(R.drawable.csgo0);
        }
        else if(aux.equals("LOL")){

            holder.avatar.setImageResource(R.drawable.lol);
        }
        else{
            holder.avatar.setImageResource(R.drawable.newspaper);
        }




//

        holder.coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView title = mDialog.findViewById(R.id.newfulltitle);
                final TextView body = mDialog.findViewById(R.id.body);
                final ImageView coverimage = mDialog.findViewById(R.id.newfullimage);
                title.setText(mListaNoticias.get(position).getTitle());
                body.setText(mListaNoticias.get(position).getBody());
                Picasso.with(mcontext)
                        .load(mListaNoticias.get(position).getCoverImage())
                        .error(R.drawable.picture)
                        .into(coverimage);

                mDialog.show();


            }

        });


//
//        boton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (!favo) {
//                    if (!mListContacts.get(position).isFav()) {
//                        mListContacts.get(position).setFav(true);
//                        holder.boton.setImageResource(R.drawable.starfull);
//                        holder.fav = true;
//
//
//                    } else {
//                        mListContacts.get(position).setFav(false);
//                        holder.boton.setImageResource(R.drawable.star);
//                        holder.fav = false;
//
//
//                    }
//
//                } else {
//                    if (!holder.fav) {
//                        mListContacts.get(position).setFav(true);
//                        holder.boton.setImageResource(R.drawable.star);
//                        holder.fav = true;
//
//
//                    } else {
//                        mListContacts.get(position).setFav(false);
//                        holder.boton.setImageResource(R.drawable.starfull);
//                        holder.fav = false;
//
//
//                    }
//
//                }
////
//                viewPager.setCurrentItem(0);
//                viewPager.setCurrentItem(1);
//            }
//        });
//
//
    }

    @Override
    public int getItemCount() {
        if(mListaNoticias==null){
            return 0;
        }
        return mListaNoticias.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView avatar, coverImage,favorito;
        TextView title,date,description;

        CardView noticia;



        public ViewHolder(View itemView){
            super(itemView);

            noticia=itemView.findViewById(R.id.CardViewNoticia);
            avatar = itemView.findViewById(R.id.avatar_image);
            coverImage = itemView.findViewById(R.id.media_image);
            favorito = itemView.findViewById(R.id.action_button_1);
            title = itemView.findViewById(R.id.title_text);
            date = itemView.findViewById(R.id.subtitle_text);
            description=itemView.findViewById(R.id.supporting_text);

        }
    }
}
