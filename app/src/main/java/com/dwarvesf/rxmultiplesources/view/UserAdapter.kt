package com.dwarvesf.rxmultiplesources.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dwarvesf.rxmultiplesources.R
import com.dwarvesf.rxmultiplesources.database.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var mUsers: List<User> = users

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUsers[position]
        holder.setUser(user)
    }

    override fun getItemCount(): Int = mUsers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setUser(user: User) {
            Glide.with(itemView.context).load(user.picture).into(itemView.userImage)
            itemView.tvName.text = user.name
            itemView.tvEmail.text = user.email
            itemView.tvType.text = user.type
        }
    }
}