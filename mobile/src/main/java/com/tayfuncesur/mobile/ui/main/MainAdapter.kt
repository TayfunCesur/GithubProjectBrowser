package com.tayfuncesur.mobile.ui.main

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.model.Project
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.single_row.view.*

class MainAdapter(private var list: List<Project>, private val onClick: (Project) -> Unit) :
    RecyclerView.Adapter<MainAdapter.SingleRow>() {

    fun updateData(_list: List<Project>) {
        this@MainAdapter.list = _list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SingleRow {
        return SingleRow(LayoutInflater.from(p0.context).inflate(R.layout.single_row, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: SingleRow, p1: Int) {
        p0.bind(p1)
    }


    inner class SingleRow(private val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(pos: Int) {
            view.repoName.text = list[pos].fullName
            view.starCount.text = "${list[pos].starCount} Star"
            view.favImage.visibility = if (list[pos].isBookmarked) View.VISIBLE else View.GONE
            PushDownAnim.setPushDownAnimTo(view.singleRowItem).setScale(PushDownAnim.MODE_STATIC_DP, 5F)
                .setOnClickListener {
                    onClick.invoke(list[pos])
                }
        }
    }
}