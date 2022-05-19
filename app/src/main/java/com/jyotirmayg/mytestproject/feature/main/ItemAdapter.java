package com.jyotirmayg.mytestproject.feature.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jyotirmayg.mytestproject.data.db.entities.Item;
import com.jyotirmayg.mytestproject.databinding.LayoutItemBinding;

import java.util.List;

/**
 * @author jyoti
 * @created on 17-05-2022
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Item> list;

    ItemAdapter(List<Item> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemBinding view = LayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public LayoutItemBinding layoutItemBinding;

        public ItemViewHolder(LayoutItemBinding view) {
            super(view.getRoot());
            this.layoutItemBinding = view;
        }

        public void bind(Item item) {
            layoutItemBinding.setItem(item);
            layoutItemBinding.executePendingBindings();
        }
    }
}
