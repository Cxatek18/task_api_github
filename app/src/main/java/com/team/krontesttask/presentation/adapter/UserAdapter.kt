package com.team.krontesttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.team.krontesttask.databinding.UserItemBinding
import com.team.krontesttask.domain.models.UserToList

class UserAdapter : ListAdapter<UserToList, UserViewHolder>(UserDiffCallback()) {

    var onClickUser: ((String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding) {
            with(user) {
                userLogin.text = user.login
                userSubtitle.text = user.id.toString()
            }
        }
        Glide.with(holder.itemView)
            .load(user.avatar)
            .into(holder.binding.userImage)
        holder.itemView.setOnClickListener {
            onClickUser?.invoke(user.login)
            true
        }
    }
}