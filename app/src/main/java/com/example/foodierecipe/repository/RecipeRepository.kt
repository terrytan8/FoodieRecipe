package com.example.foodierecipe.repository

import androidx.annotation.WorkerThread
import com.example.foodierecipe.model.database.RecipeDao
import com.example.foodierecipe.model.entities.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepository @Inject constructor (private val recipeDao: RecipeDao) {

    //@WorkerThread
    suspend fun insertFavDishData(favDish: Recipe){
        recipeDao.insertFavDishDetails(favDish)
    }

    val allDishesList: Flow<List<Recipe>> = recipeDao.getAllDishesList()

   //@WorkerThread
    suspend fun updateFavDishData(favDish: Recipe){
        recipeDao.updateFaveDishDetails(favDish)
    }

   // @WorkerThread
    suspend fun deleteFavDishData(favDish: Recipe){
        recipeDao.deleteFavDishDetails(favDish)
    }

    fun filteredDistList(value:String): Flow<List<Recipe>> =recipeDao.getFilteredDishesList(value)



    val favouriteDishList: Flow<List<Recipe>> = recipeDao.getFavouriteDishesList()
}