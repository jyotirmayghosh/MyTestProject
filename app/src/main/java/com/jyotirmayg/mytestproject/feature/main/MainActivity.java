package com.jyotirmayg.mytestproject.feature.main;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.jyotirmayg.mytestproject.R;
import com.jyotirmayg.mytestproject.data.db.entities.Item;
import com.jyotirmayg.mytestproject.databinding.ActivityMainBinding;
import com.jyotirmayg.mytestproject.util.ContextUtil;
import com.jyotirmayg.mytestproject.feature.addItem.AddItemActivity;

import java.util.List;
import java.util.Locale;

/**
 * @author jyoti
 * @created on 17-05-2022
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel viewModel;

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale localeToSwitchTo = new Locale("hi");
        ContextWrapper localeUpdatedContext = ContextUtil.updateLocale(newBase, localeToSwitchTo);
        super.attachBaseContext(localeUpdatedContext);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModelFactor factor = new MainViewModelFactor(this);
        viewModel = new ViewModelProvider(this, factor).get(MainViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.addFab.setOnClickListener(view -> {
            startActivity(new Intent(this, AddItemActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getItemData();
    }

    /**
     * This method will be called to get the updated data from the database.
     */
    private void getItemData() {
        LiveData<List<Item>> itemLiveData = viewModel.getItemList();
        itemLiveData.observe(this, items -> mBinding.recycleView.setAdapter(new ItemAdapter(items)));
    }
}
