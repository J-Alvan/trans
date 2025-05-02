package com.example.swifttrans.network

import com.example.swifttrans.models.MpesaRequest
import com.example.swifttrans.models.MpesaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MpesaApi{
    @POST("/stkpush/")
    suspend fun initiateSTKPush(@Body paymentData: MpesaRequest): Response<MpesaResponse>
}