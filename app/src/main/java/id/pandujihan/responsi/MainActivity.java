package id.pandujihan.responsi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import id.pandujihan.responsi.adapter.CoronaDataAdapter;
import id.pandujihan.responsi.databinding.ActivityMainBinding;
import id.pandujihan.responsi.model.Corona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView mRecyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        Preferences.clearLoggedInUser(getBaseContext());
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initializationViews();
        getAllCorona();

        swipeRefresh.setOnRefreshListener(this::getAllCorona);
    }


    private void initializationViews() {
        swipeRefresh = findViewById(R.id.swipeRefresh);
        mRecyclerView = findViewById(R.id.viewCorona);
    }

    private void getAllCorona() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllCorona().observe(this, new Observer<List<Corona>>() {
            @Override
            public void onChanged(@Nullable List<Corona> coronaList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(coronaList);
            }
        });
    }

    private void prepareRecyclerView(List<Corona> coronaList) {
        CoronaDataAdapter coronaDataAdapter = new CoronaDataAdapter(coronaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(coronaDataAdapter);
        coronaDataAdapter.setCoronaList((ArrayList<Corona>) coronaList);
    }
}
