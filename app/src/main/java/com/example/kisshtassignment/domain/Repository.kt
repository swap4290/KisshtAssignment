package com.example.kisshtassignment.domain

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.kisshtassignment.NetworkEndpoints
import com.example.kisshtassignment.model.Photos
import io.reactivex.Observable

class Repository constructor(private val networkEndpoints: NetworkEndpoints) {

    fun loadPhotos(pageSize: Int): Observable<PagedList<Photos>> {
        return RxPagedListBuilder(
            LoadPhotoDataSourceFactory(networkEndpoints),
            PagedList.Config.Builder()
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(pageSize)
                .build()
        ).buildObservable()
    }

}
