package com.ui.di

import android.content.Context
import androidx.room.Room
import com.ui.data.data.local.ArticleDao
import com.ui.data.data.local.NewsBreezeDatabase
import com.ui.data.repository.NewsBreezeRoomRepositoryImpl
import com.ui.domain.repository.NewsBreezeRoomRepository
import com.ui.domain.useCases.ArticleCacheUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsBreezeDatabase(@ApplicationContext appContext: Context): NewsBreezeDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsBreezeDatabase::class.java,
            "news_breeze_database"
        ).build()

    }


    @Provides
    fun provideArticleDao(database: NewsBreezeDatabase): ArticleDao {
        return database.newsBreezeDao()
    }

    @Provides
    @Singleton
    fun provideNewsBreezeRoomRepository(articleDao: ArticleDao): NewsBreezeRoomRepository {
        return NewsBreezeRoomRepositoryImpl(articleDao)
    }

    @Singleton
    @ViewModelScoped
    fun provideNewsBreezeCacheUseCase(repo: NewsBreezeRoomRepository): ArticleCacheUseCase {
        return ArticleCacheUseCase(repo)
    }

}