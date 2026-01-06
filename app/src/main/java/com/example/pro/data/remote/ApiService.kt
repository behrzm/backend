package com.example.app.data.remote

import com.example.pro.data.model.AttemptDto
import com.example.pro.data.model.ChallengeDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("api/challenges")
    suspend fun getChallenges(): List<ChallengeDto>

    @POST("api/attempts")
    suspend fun submitAttempt(
        @Query("userId") userId: String,
        @Query("challengeId") challengeId: String,
        @Query("submittedCode") code: String,
        @Query("score") score: Int,
        @Query("passed") passed: Boolean
    ): AttemptDto
}
