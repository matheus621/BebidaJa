package com.example.bebidaja.di

import com.example.bebidaja.data.FakeProductRepository
import com.example.bebidaja.data.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RespositoryModule {
    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = FakeProductRepository()
}