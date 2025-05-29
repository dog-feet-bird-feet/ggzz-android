package com.analysis.data.remote.interceptor

import com.analysis.data.BuildConfig
import com.analysis.data.local.GgzzDataStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GgzzInterceptor
    @Inject
    constructor(
        private val ggzzDataStore: GgzzDataStore,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val req = chain.request()

            if (req.url.encodedPath == "/api/v1/login") {
                return chain.proceed(req)
            }

            val accessToken = BuildConfig.TEST_TOKEN // or ggzzDataStore.userAccessToken
            val newReq = req.newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "$TOKEN_PREFIX $accessToken")
                .build()
            return chain.proceed(newReq)
        }

        companion object {
            private const val AUTHORIZATION_HEADER = "Authorization"
            private const val TOKEN_PREFIX = "Bearer"
        }
    }
