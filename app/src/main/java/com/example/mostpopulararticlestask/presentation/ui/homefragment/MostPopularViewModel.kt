package com.example.mostpopulararticlestask.presentation.ui.homefragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.mostpopulararticlestask.R
import com.example.mostpopulararticlestask.domain.models.Article
import com.example.mostpopulararticlestask.domain.models.MostPobularData
import com.example.mostpopulararticlestask.domain.usecases.GetShareDetailsUseCase
import com.example.mostpopulararticlestask.presentation.core.ErrorHandling
import com.example.mostpopulararticlestask.presentation.core.SingleLiveEvent
import com.example.mostpopulararticlestask.presentation.rx.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MostPopularViewModel @Inject constructor(
    val shareUseCase: GetShareDetailsUseCase,
    val schedulers: SchedulersProvider,
    val errorHandling: ErrorHandling,
    val context: Context
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _dataLoading = MutableLiveData<MostPobularData>()
    val loading = SingleLiveEvent<Boolean>()
    val openArticleEvent = SingleLiveEvent<Article>()



    fun getMostPopular(period:String){
        shareUseCase.getMostPopular(period)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe({loading.value = true})
            .doFinally({loading.value = false})
            .subscribe({
                it?.let {
                    _dataLoading.value = it
                }
            },{
                Log.d("error",it.message+"")
                errorHandling.accept(it,context.getString(R.string.error))
            }).let {
                compositeDisposable.add(it)
            }
    }

    private val _items: LiveData<List<Article>> = _dataLoading.switchMap {
        getArticleList(it)
    }
    val items: LiveData<List<Article>> =_items

    private fun getArticleList(mostPobularData: MostPobularData): LiveData<List<Article>> {
        val result = MutableLiveData<List<Article>>()
        result.value = mostPobularData.articles
        return result
    }

    fun openArticle(article: Article) {
        openArticleEvent.value = article
    }



    override fun onCleared() {
        if (compositeDisposable != null) {
            compositeDisposable.clear()
            compositeDisposable.dispose()
        }
    }

}