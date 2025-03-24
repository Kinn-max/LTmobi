package com.example.furniturestore.di

import com.example.furniturestore.repositories.MainLog
import com.example.furniturestore.repositories.TaskRepository
import com.example.furniturestore.repositories.impl.MainLogImpl
import com.example.furniturestore.repositories.impl.TaskRepositoryImpl
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
    abstract fun bindMainLog(log: MainLogImpl): MainLog

    @Binds
    @Singleton
    abstract fun bindTaskRepository(taskRepository: TaskRepositoryImpl): TaskRepository
}