package ru.easycode.zerotoheroandroidtdd

import ru.easycode.zerotoheroandroidtdd.data.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.SimpleService
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(data = service.fetch(url))
            } catch (e: Exception) {
                LoadResult.Error(e is UnknownHostException)
            }
        }
    }
}

interface LoadResult {

    fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(private val data: SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(text = data.text))
        }
    }

    data class Error(private val noConnection: Boolean) : LoadResult {

        private val text = if (noConnection) "No internet connection" else "Something went wrong"
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(text = text))
        }
    }
}