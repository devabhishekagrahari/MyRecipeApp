package dev.abhishekagrahari.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val recipeService = retrofit.create(ApiService::class.java)

interface ApiService{

    //url we have is https://www.themealdb.com/api/json/v1/1/categories.php

    @GET("categories.php")
    suspend fun getCategories():CategoriesResonse

    //suspend keyword use to run in background the program if the fun is taking more time
    //than usual it doesn't freeze the api
}