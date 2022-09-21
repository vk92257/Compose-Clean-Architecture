package com.ui.di

import com.ui.data.data.JsonPlaceholderApi
import com.ui.data.repository.JsonPlaceholderRepositoryImpl
import com.ui.data.repository.NewBreezeRepositoryImpl
import com.ui.domain.repository.JsonPlaceholderRepository
import com.ui.domain.repository.NewBreezeRepository
import com.ui.domain.useCases.GetBreakingNewsUseCase
import com.ui.domain.useCases.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * @Author: Vivek
 * @Date: 26/02/22
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                interceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderApi(client: OkHttpClient): JsonPlaceholderApi {
        return Retrofit.Builder()
            .baseUrl(JsonPlaceholderApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesJsonPlaceHolderRepository(
        api: JsonPlaceholderApi
    ): JsonPlaceholderRepository {
        return JsonPlaceholderRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun providesNewsBreezeRepository(
        api: JsonPlaceholderApi
    ): NewBreezeRepository {
        return NewBreezeRepositoryImpl(api)
    }


    @Singleton
    @ViewModelScoped
    fun provideGetBreakingNewsUseCase(repository: NewBreezeRepository): GetBreakingNewsUseCase {
        return GetBreakingNewsUseCase(repository)

    }


    @Singleton
    @ViewModelScoped
    fun provideHomeUseCases(repository: JsonPlaceholderRepository): GetPostsUseCase {
        return GetPostsUseCase(repository)
    }
}