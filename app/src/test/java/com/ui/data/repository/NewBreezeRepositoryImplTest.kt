package com.ui.data.repository

import com.ui.data.data.remote.JsonPlaceholderApi
import com.ui.remote.inValidResponse
import com.ui.remote.malFormed
import com.ui.remote.validResponse
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewBreezeRepositoryImplTest {

    private lateinit var repository: NewBreezeRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: JsonPlaceholderApi


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(JsonPlaceholderApi::class.java)

        repository = NewBreezeRepositoryImpl(
            apiRequest = api
        )

    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `Get the List of Articles and return result `() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validResponse)
        )

        val result = runBlocking { repository.getBreakingNews("us", "us") }
        assertThat(  "Pass",result.isSuccess)

    }


    @Test
    fun `Get the List of Articles in case of invalid response `() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(inValidResponse)
        )

        val result = runBlocking { repository.getBreakingNews("us", "us") }
        assertThat(  "Error",result.isFailure)

    }

    @Test
    fun `Get the List of Articles in case of malfunction  response `() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malFormed)
        )
        val result = runBlocking { repository.getBreakingNews("us", "us") }
        assertThat(  "Malfunction",result.isFailure)

    }


}