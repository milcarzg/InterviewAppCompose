package dk.trackman.androidnative.feature.friends.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FriendApiModule {

    @Provides
    @Singleton
    fun provideFriendApi(retrofit: Retrofit): FriendApi = retrofit.create(FriendApi::class.java)
}