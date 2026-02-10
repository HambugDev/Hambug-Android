package desktop.hambug.presentation.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import desktop.hambug.MainActivity
import desktop.hambug.data.di.RepositoryModule
import desktop.hambug.data.repository.fake.FakeAuthRepository
import desktop.hambug.domain.repository.AuthRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
class KakaoLoginFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        hiltRule.inject()
        (authRepository as FakeAuthRepository).shouldSuccess = true
    }

    @Test
    fun `카카오로그인 후 홈 진입`() {
        waitForNode("카카오 로그인")

        composeTestRule
            .onNodeWithText("카카오 로그인")
            .assertIsDisplayed()
            .performClick()

        waitForNode("햄버그")

        composeTestRule
            .onNodeWithText("햄버그")
            .assertIsDisplayed()
    }

    // 5초 동안 반복해서 UI 확인
    private fun waitForNode(text: String, timeoutMillis: Long = 5_000) {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                composeTestRule
                    .onAllNodesWithText(text = text, useUnmergedTree = true)  // 모든 노드 트리 검색
                    .fetchSemanticsNodes(atLeastOneRootRequired = false)      // compose가 초기화되지 않아도 검색 시도
                    .isNotEmpty()                                             // 해당 텍스트를 가진 노드가 발견되면 대기 종료
            } catch (e: IllegalStateException) {
                false  // 발생하는 예외를 무시하고 계속 대기
            }
        }
    }
}
