package desktop.hambug.data.repository.fake

import android.content.Context
import desktop.hambug.data.dto.auth.LoginResponse
import desktop.hambug.data.dto.auth.LoginToken
import desktop.hambug.data.dto.auth.LoginUser
import desktop.hambug.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    // 테스트 시나리오 제어용 플래그
    var shouldSuccess = true
    var shouldThrowSpecificError: Exception? = null

    override suspend fun loginWithKakao(context: Context): LoginResponse {
        // 특정 에러를 던지도록 설정된 경우
        shouldThrowSpecificError?.let { throw it }

        // 실패
        if (!shouldSuccess) {
            throw Exception("mock kakao login failed")
        }

        // 성공
        return LoginResponse(
            token = LoginToken(
                accessToken = "fake_access_token",
                refreshToken = "fake_refresh_token"
            ),
            user = LoginUser(
                isRegister = true,
                kakao = true,
                loginType = "KAKAO",
                nickname = "HAMBUG_0123456789",
                profileImageUrl = null,
                role = "ROLE_USER",
                userId = 1
            )
        )
    }

    override suspend fun loginWithApple(identityToken: String): LoginResponse {
        shouldThrowSpecificError?.let { throw it }

        if (!shouldSuccess) {
            throw Exception("mock apple login failed")
        }

        return LoginResponse(
            token = LoginToken(
                accessToken = "fake_access_token",
                refreshToken = "fake_refresh_token"
            ),
            user = LoginUser(
                isRegister = true,
                kakao = true,
                loginType = "APPLE",
                nickname = "HAMBUG_0123456789",
                profileImageUrl = null,
                role = "ROLE_USER",
                userId = 1
            )
        )
    }

    override suspend fun logout() {
        if (!shouldSuccess) {
            throw Exception("mock logout failed")
        }
    }

    override suspend fun unlink(provider: String) {
        if (!shouldSuccess) {
            throw Exception("mock unlink failed")
        }
    }
}
