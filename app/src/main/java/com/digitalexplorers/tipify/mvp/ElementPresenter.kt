package com.digitalexplorers.tipify.mvp

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList as ArrayList1


class ElementPresenter( var view: ListContract.View? ): ElementsProvider{


    override fun loadElements(): Single<List<Element>> {
        this@ElementPresenter.showLoading()
       var elementList = listOf(Element("hello"))//MutableList ArrayList<Element>
        val testSingle = Single.just(listOf<Element>())//Single.just(elementList)

        val disposable: Disposable = testSingle
                //.delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith<DisposableSingleObserver<List<Element>>>(
                        object : DisposableSingleObserver<List<Element>> (){
                            override fun onSuccess(t: List<Element>) {
                                this@ElementPresenter.hideLoading()
                                if(elementList.isEmpty()){
                                    this@ElementPresenter.showError("EMPTY LIST")

                                }else this@ElementPresenter.setElements(elementList)
                            }
                            override fun onError(e: Throwable) {
                                this@ElementPresenter.hideLoading()
                                this@ElementPresenter.showError(e.toString())
                            }
                        })
        disposable.dispose()
        return testSingle
    }

    fun onDestroy() {
        view = null
    }

    fun setElements(t: List<Element>){
        view?.apply {

            setElements(t)
        }
    }
    fun showError(str: String) {
        view?.apply {

            showError(str)
        }
    }
    fun showLoading() {
        view?.apply {
            showLoading()
        }
    }
    fun hideLoading() {
        view?.apply {
            hideLoading()
        }
    }

}