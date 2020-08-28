package com.app.keepcount.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keepcount.R
import com.app.keepcount.adapters.SettingsAdapter
import com.app.keepcount.listeners.OnListItemClickListener
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(),OnListItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        val list = ArrayList<String>()
        list.add(getString(R.string.txt_rate))
        list.add(getString(R.string.txt_feedback))
        list.add(getString(R.string.txt_share))
        list.add(getString(R.string.txt_enable_lock))
        list.add(getString(R.string.txt_theme))
        list.add(getString(R.string.txt_logout))

        val manager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        settingsRecyclerView.layoutManager = manager
        settingsRecyclerView.adapter = SettingsAdapter(list,this)
       // settingsRecyclerView.addItemDecoration( DividerItemDecoration(settingsRecyclerView.context, DividerItemDecoration.VERTICAL))
    }

    override fun onListItemClick(item: String) {
        when(item)
        {
            getString(R.string.txt_logout)->
            {
                logoutClicked()
            }
        }
    }

    private fun logoutClicked() {

    }
}