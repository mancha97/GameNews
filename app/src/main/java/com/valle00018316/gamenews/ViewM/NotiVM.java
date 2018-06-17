package com.valle00018316.gamenews.ViewM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Repo.NotiRepo;

import java.util.List;

public class NotiVM extends AndroidViewModel {

    private NotiRepo mRepository;

    private LiveData<List<Noticia>> mAllNoti;

    public NotiVM (Application application) {
        super(application);
        mRepository = new NotiRepo(application);
    }

    public LiveData<List<Noticia>> getAllNoti() { return mRepository.getAllNoti(); }
    public LiveData<List<Noticia>>  getNBGame(String game){return mRepository.getNBGame(game);}

    public void insert(Noticia noticia) { mRepository.insert(noticia); }
}