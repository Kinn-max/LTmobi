package com.example.nodemanager.di

import com.example.nodemanager.repository.Api
import com.example.nodemanager.repository.ApiImpl
import com.example.nodemanager.repository.MainLog
import com.example.nodemanager.repository.MainLogImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindMainLog(
        log:MainLogImpl
    ):MainLog

    @Binds
    @Singleton
    abstract fun bindApi(
        api:ApiImpl
    ):Api
}