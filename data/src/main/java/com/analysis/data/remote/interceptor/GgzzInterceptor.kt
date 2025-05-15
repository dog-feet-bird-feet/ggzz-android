package com.analysis.data.remote.interceptor

import com.analysis.data.BuildConfig
import com.analysis.data.local.GgzzDataStore
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class GgzzInterceptor @Inject constructor(
    private val ggzzDataStore: GgzzDataStore,
) : Authenticator {
    override fun authenticate(
        route: Route?,
        response: Response,
    ): Request? {
        if (response.request.header(AUTHORIZATION_HEADER) != null) {
            return null
        }

//        val accessToken = ggzzDataStore.userAccessToken
        val accessToken = BuildConfig.TEST_TOKEN

        return response.request.newBuilder()
            .header(AUTHORIZATION_HEADER, "$TOKEN_PREFIX$accessToken")
            .build()
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }
}
