package com.example.kisshtassignment.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kisshtassignment.R
import com.example.kisshtassignment.adapter.PhotosAdapter
import com.example.kisshtassignment.databinding.ActivityScreen2Binding
import com.example.kisshtassignment.viewModel.Screen2ViewModel
import com.example.kisshtassignment.Injector


class Screen2Activity : AppCompatActivity() {
    var dataBinding: ActivityScreen2Binding? = null
    private lateinit var viewModel: Screen2ViewModel
    private lateinit var mAdapter: PhotosAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_screen2)

        mLayoutManager = GridLayoutManager(this,3)
        // recycler view adapter
        mAdapter = PhotosAdapter(this)
        // recycler view configuration
        dataBinding!!.unsplashPickerRecyclerView.setHasFixedSize(true)
        dataBinding!!.unsplashPickerRecyclerView.itemAnimator = null
        dataBinding!!.unsplashPickerRecyclerView.layoutManager = mLayoutManager
        dataBinding!!.unsplashPickerRecyclerView.adapter = mAdapter

        viewModel =
            ViewModelProviders.of(this, Injector.createPickerViewModelFactory())
                .get(Screen2ViewModel::class.java)
        observeViewModel()

        viewModel.loadData()

    }

    private fun observeViewModel() {
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        })
        viewModel.messageLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loadingLiveData.observe(this, Observer {
            dataBinding!!.unsplashPickerProgressBarLayout.visibility = if (it != null && it) View.VISIBLE else View.GONE
        })
        viewModel.photosLiveData.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }



}