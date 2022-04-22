package com.example.foodierecipe.model.database

import androidx.room.*
import com.example.foodierecipe.model.entities.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

    @Insert
    suspend fun insertFavDishDetails(recipe: Recipe)


    @Query("SELECT * FROM RECIPES_TABLE ORDER BY ID")
    fun getAllDishesList(): Flow<List<Recipe>>


    @Update
    suspend fun updateFaveDishDetails(favDish: Recipe)

    @Query("SELECT * FROM RECIPES_TABLE WHERE favourite_recipe = 1")
    fun getFavouriteDishesList(): Flow<List<Recipe>>

    @Delete
    suspend fun deleteFavDishDetails(favDish: Recipe)

    @Query("SELECT* FROM RECIPES_TABLE WHERE type = :filterType")
    fun getFilteredDishesList(filterType:String): Flow<List<Recipe>>
}