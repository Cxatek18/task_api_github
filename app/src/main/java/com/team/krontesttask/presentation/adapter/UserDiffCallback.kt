package com.team.krontesttask.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.team.krontesttask.domain.models.UserToList

class UserDiffCallback : DiffUtil.ItemCallback<UserToList>() {

    override fun areItemsTheSame(oldItem: UserToList, newItem: UserToList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserToList, newItem: UserToList): Boolean {
        return oldItem == newItem
    }
}