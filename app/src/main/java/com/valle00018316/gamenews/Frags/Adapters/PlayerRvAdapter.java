package com.valle00018316.gamenews.Frags.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Entidad.Player;
import com.valle00018316.gamenews.R;

import java.util.List;

public class PlayerRvAdapter extends RecyclerView.Adapter<PlayerRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    public List<Player> mListaPlayers;

    Dialog mDialog;

    public PlayerRvAdapter(Context context) {
        mcontext = context;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_player, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    public void setN(List<Player> h){
        mListaPlayers = h;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Picasso.with(mcontext)
                .load(mListaPlayers.get(position).getAvatar())
                .error(R.drawable.picture)
                .into(holder.coverImage);

//
//  avatar, coverImage,favorito;

        holder.title.setText(mListaPlayers.get(position).getName());
        holder.date.setText(mListaPlayers.get(position).getGame());
        holder.description.setText(mListaPlayers.get(position).getBio());




//




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
        if(mListaPlayers==null){
            return 0;
        }
        return mListaPlayers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView coverImage;
        TextView title,date,description;

        CardView noticia;



        public ViewHolder(View itemView){
            super(itemView);


            coverImage = itemView.findViewById(R.id.media_imagep);

            title = itemView.findViewById(R.id.playername);
            date = itemView.findViewById(R.id.playergame);
            description=itemView.findViewById(R.id.bio);

        }
    }
}

