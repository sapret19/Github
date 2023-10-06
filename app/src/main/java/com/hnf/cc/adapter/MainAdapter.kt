package com.hnf.cc.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnf.cc.activity.DetailActivity
import com.hnf.cc.api.response.DetailResponse
import com.hnf.cc.api.response.ItemsItem
import com.hnf.cc.databinding.ItemUserBinding

class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var dataUser: List<ItemsItem> = ArrayList()

    constructor(dataUser: List<ItemsItem>) : this() {
        this.dataUser = dataUser
    }


    private var onClickItemCallback: OnClickItemCallback? = null
    fun setOnClickItemCallback(onClickItemCallback: OnClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.root.setOnClickListener{
                onClickItemCallback?.onClickItem(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(imgUser)
                textUser.text =user.login


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataUser.size

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataUser[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USERNAME, dataUser[position].login)
                intent.putExtra(DetailActivity.AVATAR, dataUser[position].avatarUrl)
            holder.itemView.context.startActivity(intent)
        }
    }
    interface OnClickItemCallback {
        fun onClickItem(user: ItemsItem)
    }
}