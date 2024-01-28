package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
) : ViewModel() {

    init {
        listLiveDataWrapper.update(emptyList())
    }

    fun liveData() = listLiveDataWrapper.liveData()

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        val restoredList = bundle.restore()
        listLiveDataWrapper.update(restoredList)
    }
}
