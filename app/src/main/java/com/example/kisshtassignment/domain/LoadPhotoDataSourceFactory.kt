package com.example.kisshtassignment.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.kisshtassignment.NetworkEndpoints
import com.example.kisshtassignment.model.Photos

class LoadPhotoDataSourceFactory constructor(private val networkEndpoints: NetworkEndpoints) :
    DataSource.Factory<Int, Photos>() {

    val sourceLiveData = MutableLiveData<LoadPhotoDataSource>()

    override fun create(): DataSource<Int, Photos> {
        val source = LoadPhotoDataSource(networkEndpoints)
        sourceLiveData.postValue(source)
        return source
    }
}
