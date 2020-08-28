package com.app.keepcount.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.keepcount.R
import com.app.keepcount.listeners.OnListItemClickListener
import kotlinx.android.synthetic.main.single_settings_item_layout.view.*

class SettingsAdapter(var list:ArrayList<String>,var listener:OnListItemClickListener): RecyclerView.Adapter<SettingsAdapter.CustomAdapter>() {

    class CustomAdapter(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter {
        return CustomAdapter(LayoutInflater.from(parent.context).inflate(R.layout.single_settings_item_layout,parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomAdapter, position: Int) {
        val item=list[position]
        holder.itemView.txtSettingItem.text=item

        holder.itemView.setOnClickListener {
            listener.onListItemClick(item)
        }
    }
}