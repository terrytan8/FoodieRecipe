package com.example.foodierecipe.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.foodierecipe.model.entities.Recipe
import com.example.foodierecipe.repository.RecipeRepository
import kotlinx.coroutines.launch

/**
 * The ViewModel's role is to provide data to the UI and survive configuration changes.
 * A ViewModel acts as a communication center between the Repository and the UI.
 * You can also use a ViewModel to share data between fragments.
 * The ViewModel is part of the lifecycle library.
 *
 * @param repository - The repository class is
 */
class RecipeViewModel @ViewModelInject constructor(private val repository: RecipeRepository) : ViewModel() {


    fun insert(dish: Recipe) = viewModelScope.launch {
        // Call the repository function and pass the details.
        repository.insertFavDishData(dish)
    }

    val allDishList: LiveData<List<Recipe>> = repository.allDishesList.asLiveData()

    fun update(dish: Recipe) = viewModelScope.launch {
        repository.updateFavDishData(dish)
    }

    val favouriteRecipe: LiveData<List<Recipe>> = repository.favouriteDishList.asLiveData()

    fun delete(dish: Recipe) = viewModelScope.launch {
        repository.deleteFavDishData(dish)
    }

    fun getFilteredList(value: String): LiveData<List<Recipe>> =
        repository.filteredDistList(value).asLiveData()

}

