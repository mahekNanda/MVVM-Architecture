package com.example.itemlist3.di

import com.example.itemlist3.dao.ProductDao
import com.example.itemlist3.data.ProductRepository
import com.example.itemlist3.network.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        api: ProductApiService,
        dao: ProductDao
    ): ProductRepository {

        return ProductRepository(api, dao)
    }
}