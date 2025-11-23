package desktop.hambug.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// datastore 키 정의
private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")

@Singleton
class HambugTokenManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    // access token 저장
    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
        }
    }

    // access token 조회
    suspend fun getAccessToken(): String? {
        return dataStore.data
            .map { preferences ->
                preferences[ACCESS_TOKEN_KEY]
            }.firstOrNull()
    }

    suspend fun getRefreshToken(): String? {
        return dataStore.data
            .map { preferences ->
                preferences[REFRESH_TOKEN_KEY]
            }.firstOrNull()
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}
