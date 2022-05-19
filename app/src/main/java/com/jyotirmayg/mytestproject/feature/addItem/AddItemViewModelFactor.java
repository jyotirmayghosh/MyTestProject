package com.jyotirmayg.mytestproject.feature.addItem;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jyoti
 * @created on 18-05-2022
 */
public class AddItemViewModelFactor implements ViewModelProvider.Factory {

    private final Context context;

    public AddItemViewModelFactor(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddItemViewModel(context);

    }
}
