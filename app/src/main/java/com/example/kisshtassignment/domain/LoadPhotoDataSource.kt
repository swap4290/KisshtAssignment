package com.example.kisshtassignment.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.kisshtassignment.NetworkEndpoints
import com.example.kisshtassignment.model.Photos
import com.example.kisshtassignment.PhotoCredentials
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response


class LoadPhotoDataSource(private val networkEndpoints: NetworkEndpoints) : PageKeyedDataSource<Int, Photos>() {

    val networkState = MutableLiveData<NetworkState>()

    private var lastPage: Int? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photos>) {
        networkState.postValue(NetworkState.LOADING)
        networkEndpoints.loadPhotos(PhotoCredentials.getAccessKey(), 1, params.requestedLoadSize)
            .subscribe(object : Observer<Response<List<Photos>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(response: Response<List<Photos>>) {

                    if (response.isSuccessful) {
                        lastPage = response.headers().get("x-total")?.toInt()?.div(params.requestedLoadSize)
                        callback.onResult(response.body()!!, null, 2)
                        networkState.postValue(NetworkState.SUCCESS)
                    }
                    else {
                        networkState.postValue(NetworkState.error(response?.message()))
                    }
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.error(e.message))
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photos>) {
        networkState.postValue(NetworkState.LOADING)
        networkEndpoints.loadPhotos(PhotoCredentials.getAccessKey(), params.key, params.requestedLoadSize)
            .subscribe(object : Observer<Response<List<Photos>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(response: Response<List<Photos>>) {
                    if (response.isSuccessful) {
                        val nextPage = if (params.key == lastPage) null else params.key + 1
                        callback.onResult(response.body()!!, nextPage)
                        networkState.postValue(NetworkState.SUCCESS)
                    }
                    else {
                        networkState.postValue(NetworkState.error(response.message()))
                    }
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.error(e.message))
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photos>) {
    }
}
