package desktop.hambug.presentation.login

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.MainActivity
import desktop.hambug.R
import desktop.hambug.presentation.component.HambugLoadingIndicator
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Apple
import desktop.hambug.presentation.ui.icon.appicons.Kakao
import desktop.hambug.presentation.ui.theme.Gray1000
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.ui.theme.KakaoYellow
import desktop.hambug.notification.NotificationPermissionHelper
import desktop.hambug.presentation.component.snackbar.CustomSnackbar
import desktop.hambug.util.TestEnvironment
import timber.log.Timber

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    testEnvironment: TestEnvironment = hiltViewModel<LoginViewModel>().testEnvironment
) {
    val context = LocalContext.current
    val activity = context as? MainActivity
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    val isProcessing = (uiState as? LoginUiState.Default)?.isProcessing ?: false

    // 알림 권한 요청 런처
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Timber.d("알림 권한 허용됨")
        } else {
            Timber.d("알림 권한 거부됨")
        }
    }

    // 권한 요청
    LaunchedEffect(Unit) {
        // 테스트 환경에서는 권한 요청 생략
        if (!testEnvironment.isFakeTest() &&
            !NotificationPermissionHelper.hasNotificationPermission(context)) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // apple 로그인 콜백 처리
    LaunchedEffect(Unit) {
        activity?.appleIdentityToken?.collect { token ->
            if (token != null) {
                Timber.d("LoginScreen에서 apple identity token 수신")
                loginViewModel.handleAppleCallback(token)
            }
        }
    }

    LaunchedEffect(Unit) {
        loginViewModel.loginEvent.collect { event ->
            when (event) {
                LoginEvent.NavigateToHome -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
    }

    // 에러 메시지 스낵바
    LaunchedEffect(Unit) {
        loginViewModel.errorMessage.collect { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = HambugTheme.colors.bgWhite,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 40.dp)
            ) { data ->
                CustomSnackbar(data)
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp),
            ) {
                Spacer(modifier = Modifier.height(148.dp))
                LoginHeaderSection()
                Spacer(modifier = Modifier.height(100.dp))
                LoginButtonSection(
                    onClickKakao = {
                        // 중복 클릭 방지
                        if (!isProcessing) loginViewModel.onKakaoLogin(context)
                    },
                    onClickApple = {
                        if (!isProcessing) loginViewModel.onAppleLogin(context)
                    }
                )
            }

            if (isProcessing) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(color = Color.Black.copy(alpha = 0.8f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    HambugLoadingIndicator()
                }
            }
        }
    }
}

@Composable
private fun LoginHeaderSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTitleText()
        Spacer(modifier = Modifier.height(34.dp))
        LoginLogoImage()
    }
}

@Composable
private fun LoginTitleText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = HambugTheme.typography.heading01.toSpanStyle().copy(
                    color = HambugTheme.colors.textHeadline
                )) {
                    append("안녕하세요.")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = HambugTheme.typography.heading01.toSpanStyle().copy(
                    HambugTheme.colors.primRed
                )) {
                    append("햄버그")
                }
                withStyle(style = HambugTheme.typography.heading01.toSpanStyle().copy(
                    color = HambugTheme.colors.textHeadline
                )) {
                    append("입니다:)")
                }
            }
        )
    }
}

@Composable
private fun LoginLogoImage() {
    Image(
        painter = painterResource(id = R.drawable.logo_login),
        contentDescription = null
    )
}

@Composable
private fun LoginButtonSection(
    onClickKakao: () -> Unit,
    onClickApple: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SNS 계정으로 간편 가입하기",
            style = HambugTheme.typography.body03,
            color = HambugTheme.colors.borderDefault
        )
        Spacer(modifier = Modifier.height(14.dp))
        KakaoButton(
            onClick = { onClickKakao() }
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppleButton(
            onClick = { onClickApple() }
        )
    }
}

@Composable
private fun KakaoButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = KakaoYellow, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.Kakao,
                contentDescription = null,
                tint = Gray1000
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "카카오 로그인",
                style = HambugTheme.typography.title02,
                color = HambugTheme.colors.textHeadline
            )
        }
    }
}

@Composable
private fun AppleButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = Gray1000, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.Apple,
                contentDescription = null,
                tint = HambugTheme.colors.bgWhite
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Apple로 로그인",
                style = HambugTheme.typography.title02,
                color = HambugTheme.colors.bgWhite
            )
        }
    }
}
