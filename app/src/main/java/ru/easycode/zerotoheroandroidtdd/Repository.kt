package ru.easycode.zerotoheroandroidtdd

import ru.easycode.zerotoheroandroidtdd.data.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.SimpleService

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String
    ) : Repository {

        override suspend fun load(): SimpleResponse {
            return service.fetch(url)
        }
    }
}