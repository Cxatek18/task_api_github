package com.team.krontesttask.di

import com.team.krontesttask.data.network.UserApiService
import com.team.krontesttask.data.repository.UserDetailRepositoryImpl
import com.team.krontesttask.data.repository.UserToListRepositoryImpl
import com.team.krontesttask.domain.repository.UserDetailRepository
import com.team.krontesttask.domain.repository.UserToListRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideUserApiService(client: OkHttpClient): UserApiService {
        val BASE_URL = "https://api.github.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpLoggingInterceptor = httpLoggingInterceptor
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideUserDetailRepository(userApiService: UserApiService): UserDetailRepository {
        return UserDetailRepositoryImpl(userApiService)
    }

    @Provides
    fun provideUserToListRepository(userApiService: UserApiService): UserToListRepository {
        return UserToListRepositoryImpl(userApiService)
    }
}