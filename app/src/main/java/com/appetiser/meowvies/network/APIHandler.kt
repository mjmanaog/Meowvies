package com.appetiser.meowvies.network

import androidx.lifecycle.ViewModel
import com.appetiser.meowvies.helper.FailCallbackThrowable
import com.appetiser.meowvies.helper.SuccessCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * We created a separate APIHandler to handle any public api call
 */
class APIHandler: ViewModel(){
    companion object{
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()

        /**
         * @param observable to subscribe/listen to a certain state
         * @param successCallback returns any
         * @param failCallback returns any throwable method
         */
        fun sendAPI(
            observable: Observable<*>,
            successCallback: SuccessCallback,
            failCallback: FailCallbackThrowable
        ) {
            compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        successCallback.invoke()
                    }, {
                        failCallback.invoke(it)
                    })
            )
        }
    }
}