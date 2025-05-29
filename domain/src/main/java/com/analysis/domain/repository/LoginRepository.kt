package com.analysis.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository{
    fun hasAccessToken():Flow<Boolean>

    fun login(
        email:String,
        password:String,
    ): Flow<Boolean>
}
