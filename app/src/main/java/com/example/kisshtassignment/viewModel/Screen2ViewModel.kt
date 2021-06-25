package com.example.kisshtassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.kisshtassignment.domain.Repository
import com.example.kisshtassignment.model.Photos
import com.example.kisshtassignment.PhotoCredentials
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class Screen2ViewModel constructor(private val repository: Repository) : BaseViewModel() {

    private val mPhotosLiveData = MutableLiveData<PagedList<Photos>>()
    val photosLiveData: LiveData<PagedList<Photos>> get() = mPhotosLiveData

    override fun getTag(): String {
        return Screen2ViewModel::class.java.simpleName
    }


    fun loadData() {
        repository.loadPhotos(PhotoCredentials.getPageSize())
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                mLoadingLiveData.postValue(true)
            }
            .observeOn(Schedulers.io())
            .subscribe(object : BaseObserver<PagedList<Photos>>() {
                override fun onSuccess(data: PagedList<Photos>?) {
                    mPhotosLiveData.postValue(data)
                }
            })
    }


}
