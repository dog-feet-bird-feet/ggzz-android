package com.analysis.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val GGZZ_DATA_STORE_NAME = "ggzz_data_store"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = GGZZ_DATA_STORE_NAME)

class GgzzDataStore
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        val userAccessToken: Flow<String?> = context.dataStore.data
            .map { preferences -> preferences[USER_ACCESS_TOKEN] }

        suspend fun setAccessToken(accessToken: String):Boolean {
            return try {
                context.dataStore.edit { prefs ->
                    prefs[USER_ACCESS_TOKEN] = accessToken
                }
                true
            } catch (e: Exception) {
                false
            }
        }

        companion object {
            private val USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
        }
    }
