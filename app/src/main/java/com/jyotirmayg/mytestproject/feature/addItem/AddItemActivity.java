package com.jyotirmayg.mytestproject.feature.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.jyotirmayg.mytestproject.R;
import com.jyotirmayg.mytestproject.data.db.entities.Item;
import com.jyotirmayg.mytestproject.databinding.ActivityAddItemBinding;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddItemBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_item);

        AddItemViewModelFactor factor = new AddItemViewModelFactor(this);
        AddItemViewModel viewModel = new ViewModelProvider(this, factor).get(AddItemViewModel.class);

        mBinding.btnAddItem.setOnClickListener(view -> {
            String itemName = mBinding.etItemName.getText().toString().trim();
            String qty = mBinding.etQty.getText().toString().trim();
            String rate = mBinding.etRate.getText().toString().trim();
            String gst = mBinding.etGST.getText().toString();

            Item item = new Item(itemName, qty, Double.parseDouble(rate), Double.parseDouble(gst));

            viewModel.addItem(item);
        });

        viewModel.addItemFlow.observe(this, aLong -> {
            if (aLong > 0) {
                Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}