package com.example.foodierecipe.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.foodierecipe.model.database.RecipeRoomDatabase
import com.example.foodierecipe.other.Constants.DATABASE_NAME
import com.example.foodierecipe.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.foodierecipe.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RecipeRoomDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipeRoomDatabase) = db.getRecipeDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}