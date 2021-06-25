package com.example.kisshtassignment.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.kisshtassignment.R
import com.example.kisshtassignment.databinding.RowPhotosBinding
import com.example.kisshtassignment.model.Photos
import com.squareup.picasso.Picasso


class PhotosAdapter(context: Activity
)
    : PagedListAdapter<Photos, PhotosAdapter.PhotoViewHolder>(COMPARATOR)  {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { photo ->
            holder.dataBinding.cvView.setCardBackgroundColor(Color.parseColor(photo.color))
            Picasso.get().load(photo.urls.small)
                .into(holder.dataBinding.ivImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        val dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_photos,
            parent,
            false
        ) as RowPhotosBinding
        return  PhotoViewHolder(dataBinding)
    }

    inner class PhotoViewHolder(var dataBinding: RowPhotosBinding) :
        RecyclerView.ViewHolder(dataBinding.root)

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Photos>() {
            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem == newItem
        }
    }


}