/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"


//We need to create a Moshi object with the KotlinJsonAdapaterFactory
//Moshi makes it easy to parse JSON into Java objects
private val moshi = Moshi.Builder()
    //add kotlinJsonAdapter Factory
    .add(KotlinJsonAdapterFactory())
    //build moshi object
    .build()

//Use Retrofit Builder with ScalarsConverterFactory and BASE_URL
//This line of code makes it where retrofit knows how to turn the Json Response to a string
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)) //Todo findout why you need to use a Moshi ConverterFactory
    .baseUrl(BASE_URL)
    .build()

// Implements the MarsApiService interface with @GET get Properties returning a list of MarsProperties
interface MarsApiService {
    @GET("realestate")
    fun getProperties():
            List<MarsProperty>
}

//Create the MarsApi object using Retrofit to implement the MarsApiService
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}