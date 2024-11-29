package dev.abhishekagrahari.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel :ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val  response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loding = false,
                    error = null
                )
            }catch(e: Exception){
                _categorieState.value = _categorieState.value.copy(
                    loding = false  ,
                    error= "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loding:Boolean= true,
        val list: List<Category> = emptyList(),
        val error: String?= null,
    )
}