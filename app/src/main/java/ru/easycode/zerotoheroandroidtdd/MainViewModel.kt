package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val repository: Repository
) : ViewModel(), LiveDataWrapper.Observe {

    init {
        liveDataWrapper.update(UiState.Init)
    }

    override fun liveData() = liveDataWrapper.liveData()

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.update(uiState)
    }

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val result = repository.load()
            result.show(liveDataWrapper)
        }
    }
}
