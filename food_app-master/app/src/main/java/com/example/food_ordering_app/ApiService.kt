package com.example.food_ordering_app

import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import java.net.CacheRequest

interface ApiService {
   @POST(Constants.Register_URL)
     suspend fun Register(@Body request: RegisterUser)

    @POST(Constants.Register_URL)
    suspend fun register(@Body user: User): Response<RegisterUser>

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest):Response<LoginResponse>
   @GET("/eaterapp/dishes")
   suspend fun GetDishes(@Header("Authorization") token: String):Response<FooditemsList>

}