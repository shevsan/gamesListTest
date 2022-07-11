package ua.oshevchuk.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.oshevchuk.api.R
import ua.oshevchuk.api.databinding.ItemBinding
import ua.oshevchuk.api.model.ResutModelItem

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val gameCallback = object : DiffUtil.ItemCallback<ResutModelItem>() {
        override fun areItemsTheSame(oldItem: ResutModelItem, newItem: ResutModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResutModelItem, newItem: ResutModelItem): Boolean {
            return oldItem == newItem
        }


    }
    private val differ = AsyncListDiffer(this, gameCallback)
    var games: List<ResutModelItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val game = games[position]
        holder.binding.apply {
            titleItem.text = game.title

        }
        Glide.with(holder.itemView).load(game.thumbnail).into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return games.size
    }
}