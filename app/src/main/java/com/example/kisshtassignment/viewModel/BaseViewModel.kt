package com.example.kisshtassignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisshtassignment.PhotoCredentials
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {

    // the error live data triggered in case of failure
    protected val mErrorLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean> get() = mErrorLiveData

    // the message live data triggered if something has to be shown on the screen
    private val mMessageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = mMessageLiveData

    // the loading live data triggered every time the loading state changes
    protected val mLoadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = mLoadingLiveData

    protected val mCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }

    protected abstract fun getTag(): String

    protected abstract inner class BaseObserver<Data> : Observer<Data> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
            mCompositeDisposable.add(d)
        }

        override fun onNext(value: Data) {
            if (PhotoCredentials.isLoggingEnabled()) {
                Log.i(getTag(), value.toString())
            }
            mLoadingLiveData.postValue(false)
            onSuccess(value)
        }

        override fun onError(e: Throwable) {
            Log.e(getTag(), e.message, e)
            mLoadingLiveData.postValue(false)
            mErrorLiveData.postValue(false)
        }

        abstract fun onSuccess(data: Data?)
    }
}
