package desktop.hambug.presentation.login

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import desktop.hambug.R
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Apple
import desktop.hambug.presentation.ui.icon.appicons.Kakao
import desktop.hambug.presentation.ui.theme.Gray1000
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.ui.theme.KakaoYellow

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
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
                    loginViewModel.startKakaoLogin(context)
                }
            )
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
    onClickKakao: () -> Unit
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
        AppleButton(onClick = {})
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
