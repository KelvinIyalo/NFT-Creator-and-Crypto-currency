package com.example.nativeandroiddesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeandroiddesign.dataModule.UserClassItem
import com.example.nativeandroiddesign.databinding.GiftRowBinding

class RecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GiftRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = differ.currentList[position]
        holder.binding.apply {

            userName.text = "@${users.username}"
            fullName.text = users.name
            userRowLayout.setOnClickListener {
                onItemCliclListener?.let { it(users) }
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<UserClassItem>() {
        override fun areItemsTheSame(oldItem: UserClassItem, newItem: UserClassItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserClassItem, newItem: UserClassItem): Boolean {
            return oldItem.id == newItem.id
        }

    }


    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemCliclListener: ((UserClassItem) -> Unit)? = null
    fun setOnItemClickListener(Listener: (UserClassItem) -> Unit) {
        onItemCliclListener = Listener
    }

}

class ViewHolder(val binding: GiftRowBinding) : RecyclerView.ViewHolder(binding.root)