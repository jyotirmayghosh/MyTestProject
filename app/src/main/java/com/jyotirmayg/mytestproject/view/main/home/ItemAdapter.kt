package com.jyotirmayg.mytestproject.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jyotirmayg.mytestproject.BR
import com.jyotirmayg.mytestproject.data.db.entities.Item
import com.jyotirmayg.mytestproject.databinding.LayoutItemBinding
import com.jyotirmayg.mytestproject.databinding.LayoutTableHeaderBinding

/**
 * @author jyoti
 * @created on 09-04-2022
 */
class ItemAdapter(
    private val list: List<Item>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ItemViewHolder(private val layoutItemBinding: LayoutItemBinding) :
        RecyclerView.ViewHolder(layoutItemBinding.root) {

        fun bind(item: Item) {
            with(layoutItemBinding) {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }
}