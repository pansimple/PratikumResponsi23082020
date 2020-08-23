package id.pandujihan.responsi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.pandujihan.responsi.model.Corona;
import id.pandujihan.responsi.model.CoronaRepository;

import java.util.List;

public class MainViewModel  extends AndroidViewModel {

    private CoronaRepository coronaRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        coronaRepository = new CoronaRepository();
    }
    public LiveData<List<Corona>> getAllCorona() {
        return coronaRepository.getMutableLiveData();
    }
}
