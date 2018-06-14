package com.valle00018316.gamenews.ViewM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Repo.CategoRepo;
import com.valle00018316.gamenews.Dba.Repo.NotiRepo;

import java.util.List;

public class CatVM extends AndroidViewModel{

    private CategoRepo mRepository;



    public CatVM(Application app) {
        super(app);
        mRepository = new CategoRepo(app);

    }

    public LiveData<List<Categoria>> getCategories() { return mRepository.getCategories();}

    public void insert(Categoria categoria) { mRepository.insert(categoria); }
}
